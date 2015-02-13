package mt.syncdemo;

class Person implements Comparable<Person> {
	private final String myName;
	Person(String name) {
		myName = name;
	}
	public String toString() {
		return myName;
	}
	public int compareTo(Person o) {
		return this.myName.compareTo(o.myName);
	}
}
