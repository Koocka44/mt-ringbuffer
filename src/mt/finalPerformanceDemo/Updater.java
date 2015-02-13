package mt.finalPerformanceDemo;

public class Updater extends Thread {
	private final Invariant myInvariant;
	private int myCounter = 0;

	Updater(Invariant invariant) {
		myInvariant = invariant;

	}

	@Override
	public void run() {
		int sum = 0;
		for (int i = 0; i < 5E5; i++) {
			myCounter++;
			myInvariant.setA(myCounter % 100);
			myCounter++;
			myInvariant.setB(myCounter % 100);
			sum += myInvariant.sum();
		}
		System.out.println(sum);
	}
}
