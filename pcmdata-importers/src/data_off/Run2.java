package data_off;

import java.io.IOException;
import java.util.*;

import org.json.JSONException;

public class Run2 {

public static void main(String[] arg0) throws IOException, JSONException{
		
		OFFactsCSVCreator creator = new OFFactsCSVCreator();
		
		creator.createCSVFromCategory("en:beverages", false);
		
//		creator.printStats();
		
//		List<String> list = creator.createListFromCategory("en:dairies", false);
//		System.out.println(list.size());
		
		creator.close();
	}

}
