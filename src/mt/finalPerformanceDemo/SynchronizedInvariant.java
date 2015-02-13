package mt.finalPerformanceDemo;

public class SynchronizedInvariant implements Invariant {
	private int myA = 42;
	private int myB = 0;

	public synchronized void setA(int newValue) {
		myA = newValue;
		myB = 42 - myA;
	}
	public synchronized void setB(int newValue) {
		myB = newValue;
		myA = 42 - myB;
	}
	public synchronized int sum() {
		return myA + myB;
	}
}
