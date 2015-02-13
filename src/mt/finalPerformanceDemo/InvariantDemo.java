package mt.finalPerformanceDemo;

public class InvariantDemo {

	private static final int N_UPDATERS = 100;

	private static long measure(Invariant invariant) throws InterruptedException {
		Updater[] updaters = new Updater[N_UPDATERS];
		for (int i = 0; i < N_UPDATERS; i++) {
			updaters[i] = new Updater(invariant);
		}
		long startTime = System.currentTimeMillis();
		for (Updater u : updaters) {
			u.start();
		}
		for (Updater u : updaters) {
			u.join();
		}
		return (System.currentTimeMillis() - startTime);
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Synchronized took " + measure(new SynchronizedInvariant()) + " ms");
		System.out.println("Immutable took " + measure(new ImmutableInvariant()) + " ms");
	}
}
