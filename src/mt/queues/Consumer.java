package mt.queues;


public class Consumer extends Thread {

	private final ProducerConsumerDemo mySource;

	public Consumer(String name, ProducerConsumerDemo source) {
		super(name);
		mySource = source;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				Service.service(mySource.getRequest());
			}
		} catch (InterruptedException e) {
			System.out.println(getName() + " exiting");
		}
	}
}
