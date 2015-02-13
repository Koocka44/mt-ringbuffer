package mt.puzzle;

public class Request {
	public void performRequest(Service service) {
		System.out.println("Request has started on " + service);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println("Request is done on " + service);
	}
}
