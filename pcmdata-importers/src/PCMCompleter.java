import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.opencompare.api.java.PCM;

public class PCMCompleter {
	
	

	@Test 
	public void completePCM() throws Exception {
		PCM pcm = PCMUtil.loadPCM("output/mergedPL.pcm");
		Map<String, String> product2Images = new PCMComplementerImageOfProduct().complete(pcm);
		System.err.println("product2Images found: " + product2Images);
	}

}
