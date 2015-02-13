package mt.syncfacilitiesdemo;


public class Car implements Runnable {
	private final ServiceStation myServiceStation;
	private final String myName;

	public Car(String name, ServiceStation station) {
		myName = name;
		myServiceStation = station;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				myServiceStation.enter(this);

				int price = myServiceStation.fixCar(this);
				System.out.println(this + " will pay " + price + " for repair");

				myServiceStation.leave(this);

				// if we were interrupted while the car was being fixed, this
				// will throw an InterruptedException
				Thread.sleep((long) (Math.random() * 10000));
			}
		} catch (InterruptedException e) {
			// OK, exiting
			e.printStackTrace(System.out);
		}
		myServiceStation.exit(this);
	}

	public String toString() {
		return myName;
	}
}
