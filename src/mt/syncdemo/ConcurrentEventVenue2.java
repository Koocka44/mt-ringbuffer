package mt.syncdemo;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import mt.Utils;

public class ConcurrentEventVenue2 extends EventVenue {
	void runTest() {
		super.startTest();
		while (true) {
			Utils.sleep(10);
			Event event = getRandomEvent();
			System.out.println("Attendees of " + event.getName()
					+ " (in alphabetical order):");
			Set<Person> attendees = event.getAttendees();
			SortedSet<Person> sortedPeople;
			sortedPeople = new TreeSet<Person>(attendees);
			System.out.println(sortedPeople);
		}
	}

	public static void main(String[] args) {
		new ConcurrentEventVenue2().runTest();
	}
}
