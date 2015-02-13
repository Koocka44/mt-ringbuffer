/**
 * 
 */
package mt.syncdemo;

import mt.Utils;

class Cancellations extends Thread {
	private final EventVenue myVenue; 
	Cancellations(EventVenue venue) {
		myVenue = venue;
	}
	public void run() {
		while (true) {
			Event e = myVenue.getRandomEvent();
			Person p = myVenue.getRandomPerson();
			e.removeAttendee(p);
			Utils.sleep(1);
		}
	}
}