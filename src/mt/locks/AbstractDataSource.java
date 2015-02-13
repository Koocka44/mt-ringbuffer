package mt.locks;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import mt.Utils;

public abstract class AbstractDataSource implements DataSource {

	private final AtomicInteger myReadCount = new AtomicInteger(0);
	private final AtomicInteger myWriteCount = new AtomicInteger(0);
	protected final CountDownLatch myLatch;
	protected final Set<Thread> myThreads;

	public AbstractDataSource(int nReaders, int nWriters) {
		int nThreads = nReaders + nWriters;
		myThreads = new HashSet<Thread>(nThreads);
		myLatch = new CountDownLatch(nThreads);

		for (int i = 0; i < nReaders; i++) {
			myThreads.add(new Reader(this, "Reader_" + i));
		}

		for (int i = 0; i < nWriters; i++) {
			myThreads.add(new Writer(this, "Writer_" + i));
		}
	}

	protected void runTest() {
    	for (Thread t : myThreads) {
    		t.start();
    	}
    	Utils.sleep(5000);
    	for (Thread t : myThreads) {
    		t.interrupt();
    	}
    	try {
    		myLatch.await();
    		System.out.println(this.getClass().getSimpleName() + " served " + myReadCount.get() + " reads and "
			        + myWriteCount.get() + " writes");
    	} catch (InterruptedException e) {
    		// main thread is never interrupted
    		e.printStackTrace();
    	}
    }

	protected void doRead() throws CustomException {
    	myReadCount.incrementAndGet();
    	Utils.sleep((long) (Math.random() * 10));
    	if (Math.random() < 0.01) {
    		throw new CustomException();
    	}
    }

	protected void doWrite() throws CustomException {
    	myWriteCount.incrementAndGet();
    	Utils.sleep((long) (Math.random() * 100));
    	if (Math.random() < 0.01) {
    		throw new CustomException();
    	}
    }

	public void exit() {
		myLatch.countDown();
	}

}