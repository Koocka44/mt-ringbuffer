package mt.volatiledemo;

import mt.Utils;

public class BooleanDemo {
	public static boolean ourKeepRunning = true;

	public static void main(String[] args) {
		(new Thread() {
			public void run() {
				while (BooleanDemo.ourKeepRunning) {
					// try again
				}
				System.out.println("Background thread stopping at\n\t" + System.currentTimeMillis());
				System.exit(0);
			}
		}).start();
		System.out.println("Waiting a bit");
		Utils.sleep(5000);
		System.out.println("Requesting stop at\n\t" + System.currentTimeMillis());
		ourKeepRunning = false;
	}

}
