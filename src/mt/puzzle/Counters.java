package mt.puzzle;
public class Counters {
	private final int requested;
	private final int served;
	Counters() {
		requested = 0;
		served = 0;
	}
	private Counters(int requested, int served) {
		this.requested = requested;
		this.served = served;
	}
	public Counters incrementRequested() {
		return new Counters(requested + 1, served);
	}
	public Counters incrementServed() {
		return new Counters(requested, served + 1);
	}
	public int getRequested() {
		return requested;
	}
	public int getServed() {
		return served;
	}
}
