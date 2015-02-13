package mt.locks;

public class ReadWriteDemo {
	public static void main(String[] args) {
		new SynchronizedDataSource(100, 5).runTest();
		new LockingDataSource(100, 5).runTest();
	}
}
