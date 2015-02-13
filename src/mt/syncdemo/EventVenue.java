package mt.syncdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class EventVenue {
	int N_PEOPLE = 1000;
	int N_EVENTS = 10;

	private final List<Person> myPeople = new ArrayList<Person>(N_PEOPLE);
	private final List<Event> myEvents = new ArrayList<Event>(N_EVENTS);

    void startTest() {
		for (int i = 0; i < N_PEOPLE; i++) {
			myPeople.add(new Person("Person_" + i));
		}
		for (int i = 0; i < N_EVENTS; i++) {
			myEvents.add(new Event("Event_" + i));
		}
		new Subscriptions(this).start();
		new Cancellations(this).start();
    }

	Event getRandomEvent() {
		return myEvents.get(new Random().nextInt(N_EVENTS));
	}

	Person getRandomPerson() {
		return myPeople.get(new Random().nextInt(N_PEOPLE));
	}
}
