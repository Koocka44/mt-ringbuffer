package mt.puzzle;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ServiceTest {

	@Test
	public void testWellBehavedChild() {
		assertAfterServicingRequestRequestedEqualsServed(new Request());
	}

//	@Test
//	public void testNaughtyChild() {
//		assertAfterServicingRequestRequestedEqualsServed(new NaughtyRequest());
//	}

	private void assertAfterServicingRequestRequestedEqualsServed(Request request) {
		SerialService service = new SerialService();
		Counters counters = service.service(request);
		assertEquals(counters.getRequested(), counters.getServed());
	}
}
