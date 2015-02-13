package mt.queues;

import mt.Utils;

public class Service {
	public static void service(String request) {
		System.out.println(Thread.currentThread().getName() + " is servicing " + request);
		Utils.sleep((long) (Math.random() * 1000));
	}
}
