package data_off;

import java.io.IOException;
import java.util.*;

import org.json.JSONException;

public class Run2 {

public static void main(String[] arg0) throws IOException, JSONException{
		
		OFFactsCSVCreator creator = new OFFactsCSVCreator();
		
//		creator.createCSVFromCategory("en:beers", false);
//		OFFPCMCreator.mkPCMFromCategory("en:beers");
//		creator.createCSVFromCategory("en:candies", false);
//		OFFPCMCreator.mkPCMFromCategory("en:candies");
		creator.createCSVFromCategory("en:seeds", false);
		OFFPCMCreator.mkPCMFromCategory("en:seeds");
		
		OFFStats.printStats();
		
		creator.close();
	}

}
