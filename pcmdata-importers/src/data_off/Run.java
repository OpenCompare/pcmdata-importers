package data_off;

import java.io.IOException;
import java.util.Set;

import org.json.JSONException;

public class Run {

	public static void main(String[] arg0) throws IOException, JSONException{

		OFFactsCSVCreator creator = new OFFactsCSVCreator();

		Set<String> cat = creator.getCategoriesWithBetween(100, 400);
		
//		int max = cat.size();

//		int count = 0;
//		for(String s : cat){
//			count++;
//			System.out.println(s + " " + count + " / " + max);
//			creator.createCSVFromCategory(s, true);
//			try{
//				OFFPCMCreator.mkPCMFromCategory(s);
//			}catch (java.lang.OutOfMemoryError e) {
//				e.printStackTrace();
//			}
//		}
		OFFStats.printStats();

		creator.close();

	}
}
