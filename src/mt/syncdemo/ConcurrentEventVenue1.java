package mt.syncdemo;

import mt.Utils;

public class ConcurrentEventVenue1 extends EventVenue {
	void runTest() {
		super.startTest();
		while (true) {
			Utils.sleep(10);
			System.out.println(getRandomEvent());
		}
	}

	public static void main(String[] args) {
		new ConcurrentEventVenue1().runTest();
	}
}
