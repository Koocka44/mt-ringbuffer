package mt.syncdemo;

import mt.Utils;

class Subscriptions extends Thread {
	private final EventVenue myVenue; 
	Subscriptions(EventVenue venue) {
		myVenue = venue;
	}
	public void run() {
		while (true) {
			Event e = myVenue.getRandomEvent();
			Person p = myVenue.getRandomPerson();
			e.addAttendee(p);
			Utils.sleep(1);
		}
	}
}