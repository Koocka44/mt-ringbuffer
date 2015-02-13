package mt;

public class Utils {

	public static void sleep(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	public static void busyWait(long duration) {
		long end = System.currentTimeMillis() + duration;
		while (System.currentTimeMillis() < end) {
			// busy waiting
		}
	}
}
