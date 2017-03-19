package data_off;

import java.io.IOException;

import org.json.JSONException;

public class Run2 {

public static void main(String[] arg0) throws IOException, JSONException{
		
		OFFactsCSVCreator creator = new OFFactsCSVCreator();
		
//		creator.createCSVFromCategory("en:beers", false);
//		OFFPCMCreator.mkPCMFromCategory("en:beers");
//		creator.createCSVFromCategory("en:candies", true);
//		OFFPCMCreator.mkPCMFromCategory("en:candies");
		OFFPCMModifier.addMultiplesToFile("en:candies");
//		creator.createCSVFromCategory("en:seeds", true);
//		OFFPCMCreator.mkPCMFromCategory("en:seeds");
		
		OFFStats.printStats();
		
		creator.close();
	}

}
