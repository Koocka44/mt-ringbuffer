package mt.threadlocal;

import mt.Utils;

public class ThreadLocalDemo {
	private static final ThreadLocal<String> ourSafeText = new ThreadLocal<String>();
	private static String ourUnsafeText;

	private static void setText(String t) {
		ourSafeText.set(t);
		ourUnsafeText = t;
	}

	private static void printText() {
		System.out.println(Thread.currentThread().getName() + ": safe text: " + ourSafeText.get() + ", unsafe text: "
		        + ourUnsafeText);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread() {
				public void run() {
					setText("Hello from " + getName());
					Utils.sleep(100);
					printText();
				}
			};
			t.setName("Thread_" + i);
			t.start();
		}
	}

}
