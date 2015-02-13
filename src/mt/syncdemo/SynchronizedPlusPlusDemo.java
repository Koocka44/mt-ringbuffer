package mt.syncdemo;

import java.util.concurrent.Semaphore;

public class SynchronizedPlusPlusDemo {
	private static int ourCounter = 0;
	private static final Object ourGuard = new Object();

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
		System.out.println("Done, counter: " + ourCounter + ", test took " + (System.currentTimeMillis() - start)
		        + " ms");
	}

	private static void startCounterThread(final Semaphore doneSema) {
		(new Thread() {
			public void run() {
				for (int round = 0; round < 50000000; round++) {
					synchronized (ourGuard) {
						SynchronizedPlusPlusDemo.ourCounter++;
					}
				}
				doneSema.release();
				System.out.println("Thread exiting");
			}
		}).start();
	}

}
