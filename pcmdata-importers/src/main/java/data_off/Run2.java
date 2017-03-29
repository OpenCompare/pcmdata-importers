package data_off;

import java.io.IOException;

import org.json.JSONException;

public class Run2 {

public static void main(String[] arg0) throws IOException, JSONException{
		
		OFFactsCSVCreator creator = new OFFactsCSVCreator();
		
//		creator.createCSVFromCategory("en:beers", false);
//		OFFPCMCreator.mkPCMFromCategory("en:beers");
		creator.createCSVFromCategory("fr:biscottes-pauvres-en-sel", true);
		OFFPCMCreator.mkPCMFromCategory("fr:biscottes-pauvres-en-sel");
		OFFPCMCreator.mkNewPCMFromCategory("fr:biscottes-pauvres-en-sel");
//		creator.createCSVFromCategory("en:seeds", true);
//		OFFPCMCreator.mkPCMFromCategory("en:seeds");
		
		OFFStats.printStats();
		
		creator.close();
	}

}
