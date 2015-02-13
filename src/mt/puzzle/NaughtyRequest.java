package mt.puzzle;

public class NaughtyRequest extends Request {

	public void performRequest(final Service service) {
		System.out.println("Request has started on " + service);
		new Thread(){
			public void run() {
				service.service(new VeryNaughtyRequest());
			};
		}.start();
		try {
			service.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Request is done on " + service);
	}

	public class VeryNaughtyRequest extends Request {

		@Override
		public void performRequest(Service service) {
			service.notifyAll();
			try {
				service.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
