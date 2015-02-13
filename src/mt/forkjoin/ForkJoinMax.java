package mt.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinMax extends RecursiveTask<Integer> {

	private static final int N_CORES = Runtime.getRuntime().availableProcessors();
	
	private static final int N_THREADS = N_CORES;

	private static final int N_ITEMS = 100 * 1000 * 1000;

	private static final int SEQUENTIAL_THRESHOLD = N_ITEMS / N_CORES;

	private final int[] data;
	private final int start;
	private final int end;

	public ForkJoinMax(int[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	public ForkJoinMax(int[] data) {
		this(data, 0, data.length);
	}

	@Override
	protected Integer compute() {
		final int length = end - start;
		if (length <= SEQUENTIAL_THRESHOLD) {
			return computeDirectly();
		}
		final int split = length / 2;
		final ForkJoinMax left = new ForkJoinMax(data, start, start + split);
		left.fork();
		final ForkJoinMax right = new ForkJoinMax(data, start + split, end);
		return Math.max(right.compute(), left.join());
	}

	private Integer computeDirectly() {
		int max = Integer.MIN_VALUE;
		for (int i = start; i < end; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println("Running " + N_THREADS + " threads on " + N_CORES + " cores");
		final int[] data = createRandomData();
		testForkJoin(data);
		testDirect(data);
	}

	private static void testDirect(int[] data) {
		int max = new ForkJoinMax(data).computeDirectly();
		long start = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			int result = new ForkJoinMax(data).computeDirectly();
			if (result != max) {
				throw new Error("Got " + result + ", expected " + max);
			}
		}
		System.out.println("Direct took " + (System.nanoTime() - start)
				/ 1000000 / 100 + "ms, max: " + max);
	}

	private static void testForkJoin(final int[] data) {
		int max = new ForkJoinMax(data).computeDirectly();
		long start = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			int result = findMaxForkJoin(data);
			if (result != max) {
				throw new Error("Got " + result + ", expected " + max);
			}
		}
		System.out.println("Fork-Join took " + (System.nanoTime() - start)
				/ 1000000 / 100 + "ms, max: " + max);
	}

	private static Integer findMaxForkJoin(final int[] data) {
		// for demo only; you could use ForkJoinPool.commonPool()
		ForkJoinPool pool = new ForkJoinPool(N_THREADS);
		ForkJoinMax finder = new ForkJoinMax(data);
		return pool.invoke(finder);
	}

	private static int[] createRandomData() {
		final int[] data = new int[N_ITEMS];
		final Random random = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt();
		}
		return data;
	}
}