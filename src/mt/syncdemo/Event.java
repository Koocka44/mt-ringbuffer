package mt.syncdemo;

import java.util.Set;
import java.util.HashSet;

class Event {
	private final String myName;
	private final Set<Person> myAttendees = new HashSet<Person>();

	public Event(String name) {
		myName = name;
	}

	void addAttendee(Person p) {
		myAttendees.add(p);
	}

	void removeAttendee(Person p) {
		myAttendees.remove(p);
	}

	Set<Person> getAttendees() {
		return myAttendees;
	}

	public String getName() {
		return myName;
	}

	public String toString() {
		return getName() + ", participants: " + getAttendees();
	}
}
