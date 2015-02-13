package mt.queues;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class ProducerConsumerDemo {
	private final BlockingQueue<String> myQueue;
	private final Set<Consumer> myConsumers = new HashSet<Consumer>();
	private final Set<Producer> myProducers = new HashSet<Producer>();
	private final CountDownLatch myLatch;

	private ProducerConsumerDemo(int nProducers, int nConsumers, int queueSize) {
		myQueue = new ArrayBlockingQueue<String>(queueSize);
		for (int i = 0; i < nProducers; i++) {
			myProducers.add(new Producer("Producer_" + i, this));
		}
		for (int i = 0; i < nConsumers; i++) {
			myConsumers.add(new Consumer("Consumer_" + i, this));
		}
		myLatch = new CountDownLatch(nProducers);
	}

	private void runTest() {
		for (Thread t : myProducers) {
			t.start();
		}
		for (Thread t : myConsumers) {
			t.start();
		}
		// wait for producers to stop
		try {
			myLatch.await();
		} catch (InterruptedException e) {
			// main thread is never interrupted
			e.printStackTrace();
		}
		for (Consumer c : myConsumers) {
			c.interrupt();
		}
		Set<String> remainingRequests = new HashSet<String>();
		myQueue.drainTo(remainingRequests);
		for (String request : remainingRequests) {
			Service.service(request);
		}
	}

	public void addRequest(String request) throws InterruptedException {
		myQueue.put(request);
	}

	public String getRequest() throws InterruptedException {
		return myQueue.take();
	}

	public void producerShutdown() {
		myLatch.countDown();
	}

	public static void main(String[] args) {
		new ProducerConsumerDemo(10, 3, 5).runTest();
	}

}
