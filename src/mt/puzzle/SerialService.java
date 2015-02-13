package mt.puzzle;
public class SerialService implements Service {
	private Counters counters = new Counters();
	private final Object guard = new Object();

	public Counters service(Request request) {
		synchronized (guard) {
			counters = counters.incrementRequested();
			try {
				request.performRequest(this);
			} catch (Throwable t) {
				System.out.println("Nice try :-)");
			}
			counters = counters.incrementServed();
		}
		return counters;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
