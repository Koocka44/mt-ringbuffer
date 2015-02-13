package mt.queues;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Alarm implements Delayed {
	private final long myAlarmTime;
	private final String myMessage;

	public Alarm(String message, int delaySec) {
		myMessage = message;
		myAlarmTime = System.currentTimeMillis() + 1000 * delaySec;
	}

	public long getDelay(TimeUnit unit) {
		return unit.convert((myAlarmTime - System.currentTimeMillis()), TimeUnit.MILLISECONDS);
	}

	public int compareTo(Delayed o) {
		long thisMillis = getDelay(TimeUnit.MILLISECONDS);
		long otherMillis = o.getDelay(TimeUnit.MILLISECONDS);
		int result;
		if (thisMillis > otherMillis) {
			result = 1;
		} else if (thisMillis < otherMillis) {
			result = -1;
		} else {
			result = 0;
		}
		return result;
	}

	public String getMessage() {
		return "Woke up at " + new Date() + ": " + myMessage;
	}

}
