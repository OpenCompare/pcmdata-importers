import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PCMComplementerImageOfProduct {

	
	
	public Map<String, String> complete(PCM newPcm) throws Exception {
		
		Map<String, String> pdtNames2Images = new HashMap<>();
		
		List<Product> pdts = newPcm.getProducts();
		for (Product product : pdts) {
			
			String productName = product.getKeyCell().getContent();
			
			// TODO compute entityID
			try {
				String entityID = computeEntityWikidataID(productName);
							
				String propertyID = "P18"; //"P154" ; //  
				String wikidataImage = "";
				try {
					wikidataImage = new WikidataImageRetrieval().retrieve(entityID, propertyID);
				}
				catch (Exception e) {
					try {
					wikidataImage = new WikidataImageRetrieval().retrieve(entityID, "P154");
					}
					catch (Exception e1) {					
					}				
				}
				
				if (!wikidataImage.isEmpty()) {
					pdtNames2Images.put(productName, wikidataImage);
				}
				else {
					System.err.println("Image unfound for " + productName);
				}
			}
			catch (Exception e) {
				System.err.println("Image unfound for " + productName + " because unable to found entity");
			}
			
		}
		return pdtNames2Images;
	}

	private String computeEntityWikidataID(String productName) throws Exception {
		
		String wikiDataReq = "https://www.wikidata.org/w/api.php?action=wbsearchentities&search=" + productName + "&format=json&language=en&type=item&continue=0";
		
		/* 
		 * disambiguation 
		 */
		
		 URL url = new URL(wikiDataReq);
	     HttpURLConnection request = (HttpURLConnection) url.openConnection();
	     request.connect();

	     // Convert to a JSON object to print data
	     JsonParser jp = new JsonParser(); //from gson
	     JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
	     JsonObject rootObj = root.getAsJsonObject(); //May be an array, may be an object. 
		
	     
	     JsonArray searches = rootObj.get("search").getAsJsonArray();
	     
	     // end of disambiguation
	     // HERE we take the first one ;-) // FIXME
	     
	     JsonElement selectedEntity = searches.get(0);
	     
	   // extraction of WikidataID
	     String wikiDataID = selectedEntity.getAsJsonObject().get("id").getAsString();
	    
		 return wikiDataID;
	}

}
