package mt.deadlock;

import mt.Utils;

public class Philosopher extends Thread {
	private String myLeftFork;
	private String myRightFork;

	public Philosopher(String name, String left, String right) {
		super(name);
		myLeftFork = left;
		myRightFork = right;
	}

	private void takeSomeTime() {
		Utils.sleep((long) (Math.random() * 10));
	}

	public void eat() {
		System.out.println(getName() + " is hungry");
		synchronized (myLeftFork) {
			System.out.println(getName() + " has just grabbed left-hand fork");
			synchronized (myRightFork) {
				System.out.println(getName()
				        + " has just grabbed the right-hand fork, too, and is about to have lunch.");
				takeSomeTime();
			}
		}
		System.out.println(getName() + " has finished eating and has released both forks.");
	}

	public void think() {
		System.out.println(getName() + " is about to start thinking.");
		takeSomeTime();
	}

	public void run() {
		while (true) {
			think();
			eat();
		}
	}

	public static void main(String[] args) {
		String fork_1_2 = "Fork 1-2";
		String fork_2_3 = "Fork 2-3";
		String fork_3_1 = "Fork 3-1";
		Philosopher p1 = new Philosopher("Philosopher #1", fork_1_2, fork_3_1);
		Philosopher p2 = new Philosopher("Philosopher #2", fork_2_3, fork_1_2);
		Philosopher p3 = new Philosopher("Philosopher #3", fork_3_1, fork_2_3);
		p1.start();
		p2.start();
		p3.start();
	}

}
