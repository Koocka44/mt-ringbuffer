package mt.deadlock;

import mt.Utils;

public class SleepingWithLockHeld {
	private String myResult;

	synchronized String getResult() {
		while (null == myResult) {
			System.out.println("Main thread - result is not in, going to sleep");
			Utils.sleep(5000);
		}
		return myResult;
	}

	synchronized void setResult(String result) {
		this.myResult = result;
	}

	public static void main(String[] args) {
		final SleepingWithLockHeld badCode = new SleepingWithLockHeld();
		Thread bg = new Thread() {
			public void run() {
				System.out.println("Background thread starts working on result");
				Utils.sleep(7500);
				System.out.println("Result is ready");
				badCode.setResult("Done");
			}
		};
		bg.setName("Background thread");
		bg.start();
		System.out.println("The result is: " + badCode.getResult());
	}

}
