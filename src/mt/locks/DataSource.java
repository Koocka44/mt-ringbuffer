package mt.locks;

public interface DataSource {

	void read() throws CustomException, InterruptedException;

	void write() throws InterruptedException, CustomException;

	void exit();

}