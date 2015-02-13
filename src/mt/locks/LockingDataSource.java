package mt.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockingDataSource extends AbstractDataSource {

	public LockingDataSource(int readers, int writers) {
		super(readers, writers);
	}

	private final ReadWriteLock myLock = new ReentrantReadWriteLock();

	public void read() throws CustomException, InterruptedException {
		myLock.readLock().lockInterruptibly();
		try {
			doRead();
		} finally {
			myLock.readLock().unlock();
		}
	}

	public void write() throws InterruptedException, CustomException {
		myLock.writeLock().lockInterruptibly();
		try {
			doWrite();
		} finally {
			myLock.writeLock().unlock();
		}
	}
}
