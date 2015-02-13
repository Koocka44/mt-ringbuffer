package mt.syncfacilitiesdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import mt.Utils;

public class ServiceStation {
	private final Semaphore mySlots;
	private final CountDownLatch myLatch;
	private final int myNCars;
	private final List<Thread> myCarThreads = new ArrayList<Thread>();

	public ServiceStation(int nSlots, int nCars) {
		myNCars = nCars;
		mySlots = new Semaphore(nSlots);
		myLatch = new CountDownLatch(nCars);
	}

	public void enter(Car c) throws InterruptedException {
		System.out.println(c + " wants to enter");
		mySlots.acquire();
		System.out.println(c + " has just entered");
	}

	public void leave(Car c) {
		System.out.println(c + " is leaving");
		mySlots.release();
	}

	public void exit(Car c) {
		myLatch.countDown();
		System.out.println(c + " has exited");
	}

	private void runTest() {
		for (int i = 0; i < myNCars; i++) {
			Thread t = new Thread(new Car("Car_" + i, this));
			t.start();
			myCarThreads.add(t);
		}
		// let the cars run for a bit
		Utils.sleep(10000);
		System.out.println("Telling cars to exit");
		// now tell them to stop
		for (Thread t : myCarThreads) {
			t.interrupt();
		}
		try {
			myLatch.await();
		} catch (InterruptedException e) {
			// the main thread is never interrupted
		}
		System.out.println("All done.");
	}

	public static void main(String[] args) {
		new ServiceStation(5, 8).runTest();
    }

	public int fixCar(Car c) {
		System.out.println("Fixing car " + c);
		long endTime = System.currentTimeMillis() + (long) (Math.random() * 10000);
		boolean interrupted = false;
		while (System.currentTimeMillis() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// we were interrupted while fixing the car is in progress, this
				// cannot be aborted now
				interrupted = true;
			}
		}
		if (interrupted) {
			// let the car know
			Thread.currentThread().interrupt();
		}
		int price = (int) (System.nanoTime() % 1000);
		return price;
    }
}
