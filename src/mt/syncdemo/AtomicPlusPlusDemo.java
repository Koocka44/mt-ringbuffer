package mt.syncdemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPlusPlusDemo {
	private static final AtomicInteger ourCounter = new AtomicInteger();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Semaphore done = new Semaphore(0);
		startCounterThread(done);
		startCounterThread(done);
		System.out.println("Threads running ++ op, waiting for them to finish");
		done.acquireUninterruptibly(2);
		System.out.println("Done, counter: " + ourCounter.get() + ", test took " + (System.currentTimeMillis() - start)
		        + " ms");
	}

	private static void startCounterThread(final Semaphore doneSema) {
		(new Thread() {
			public void run() {
				for (int round = 0; round < 50000000; round++) {
					AtomicPlusPlusDemo.ourCounter.incrementAndGet();
				}
				doneSema.release();
				System.out.println("Thread exiting");
			}
		}).start();
	}

}
