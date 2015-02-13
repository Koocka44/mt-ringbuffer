package mt.locks;

import mt.Utils;

public class Writer extends Thread {
	private final DataSource myDataSource;

	public Writer(DataSource d, String name) {
		super(name);
		myDataSource = d;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				try {
					myDataSource.write();
				} catch (CustomException e) {
					// handle exception here then retry
				}
				Utils.sleep((long) (Math.random() * 100));
			}
		} catch (InterruptedException e) {
			// OK, exiting
		}
		myDataSource.exit();
	}
}
