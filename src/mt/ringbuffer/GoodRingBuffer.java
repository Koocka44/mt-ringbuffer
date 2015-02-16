package mt.ringbuffer;

import java.lang.ProcessBuilder.Redirect;

public class GoodRingBuffer implements RingBuffer {

	private final int[] buffer;
	private long readCount;
	private long writeCount;

	private final Object readerGuard = new Object();
	private final Object writerGuard = new Object();

	public GoodRingBuffer(int size) {
		buffer = new int[size];
	}

	@Override
	public int get() throws InterruptedException {
		int result;
		synchronized (readerGuard) {

			while (readCount >= writeCount) {
				readerGuard.wait();
			}
			int readIndex = (int) (readCount % buffer.length);
			result = buffer[readIndex];
			readCount++;
		}
		synchronized (writerGuard) {
			if (writeCount < readCount + buffer.length) {
				writerGuard.notifyAll();
			}
		}
		return result;

	}

	@Override
	public void put(int value) throws InterruptedException {
		synchronized (writerGuard) {
			while (writeCount >= readCount + buffer.length) {
				writerGuard.wait();
			}

			int writeIndex = (int) (writeCount % buffer.length);
			buffer[writeIndex] = value;
			writeCount++;
		}
		synchronized (readerGuard) {
			if (readCount < writeCount) {
				readerGuard.notifyAll();
			}
		}
	}

}
