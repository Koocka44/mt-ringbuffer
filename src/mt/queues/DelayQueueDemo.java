package mt.queues;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class DelayQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Scheduling alarms at " + new Date());
		DelayQueue<Alarm> alarms = new DelayQueue<Alarm>();
		alarms.add(new Alarm("after 5 seconds", 5));
		alarms.add(new Alarm("after 1 second", 1));
		alarms.add(new Alarm("after 2 seconds", 2));
		System.out.println(alarms.take().getMessage());
		System.out.println(alarms.take().getMessage());
		System.out.println(alarms.take().getMessage());
	}
}
