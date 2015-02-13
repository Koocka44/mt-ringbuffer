package mt.locks;


public class SynchronizedDataSource extends AbstractDataSource {

	public SynchronizedDataSource(int readers, int writers) {
		super(readers, writers);
	}

	public synchronized void read() throws CustomException {
		doRead();
	}

	public synchronized void write() throws CustomException {
		doWrite();
	}
}
