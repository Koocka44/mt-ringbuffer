package mt.queues;


public class Producer extends Thread {
	private final ProducerConsumerDemo mySink;

	public Producer(String name, ProducerConsumerDemo sink) {
		super(name);
		mySink = sink;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			String request = "request #" + i + " by " + getName();
			System.out.println("About to add " + request);
			try {
				mySink.addRequest(request);
			} catch (InterruptedException e) {
				break;
			}
		}
		mySink.producerShutdown();
		System.out.println(getName() + " exiting");
	}
}
