package data_off;

import java.io.IOException;
import java.util.*;

import org.json.JSONException;

public class Run2 {

public static void main(String[] arg0) throws IOException, JSONException{
		
//		OFFactsCSVCreator creator = new OFFactsCSVCreator();
//		
//		creator.createCSVFromCategory("en:candies", true);
		OFFPCMCreator.mkPCMFromCategory("en:candies");
//		
//		OFFStats.printStats();
//		
//		creator.close();
	}

}
