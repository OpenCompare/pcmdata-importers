import static org.junit.Assert.*;

import org.junit.Test;

public class WikidataImageRetrievalTest {
	
	
	@Test
	public void testPython() {
		String urlPython = new WikidataImageRetrieval().retrieve("Q28865", "P154");
		assertEquals("https://upload.wikimedia.org/wikipedia/commons/f/f8/Python_logo_and_wordmark.svg", urlPython);
	}
	
	@Test
	public void testJava() {
		String urlJava = new WikidataImageRetrieval().retrieve("Q251", "P154");
		assertEquals("https://upload.wikimedia.org/wikipedia/commons/4/40/Wave.svg", urlJava);
	}
}
