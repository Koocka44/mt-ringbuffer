/**
 *
 */
package mt.volatiledemo;

public class LongDemo {
	private static long ourValue = 1L;

	public static void main(String[] args) {
		(new Thread() {
			public void run() {
				while (true) {
					LongDemo.ourValue = -LongDemo.ourValue;
				}
			}
		}).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long counter = 0;
		while (true) {
			counter++;
			long value = ourValue;
			if ((value != 1) && (value != -1)) {
				System.out.println("value: " + value);
				break;
			}
		}
		System.out.println("Exiting after " + counter + " test loops");
		System.exit(0);
	}

}
