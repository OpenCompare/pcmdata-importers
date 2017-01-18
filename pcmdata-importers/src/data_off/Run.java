package data_off;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;

public class Run {

	public static void main(String[] arg0) throws IOException, JSONException{

		OFFactsCSVCreator creator = new OFFactsCSVCreator();

		//		Set<String> cat10001500 = new HashSet<String>();
		//		cat10001500.add("en:carbonated-drinks");
		//		cat10001500.add("en:pasteurized-cheeses");
		//		cat10001500.add("en:fresh-vegetables");
		//		cat10001500.add("en:hot-beverages");
		//		cat10001500.add("en:breads");
		//		cat10001500.add("en:plant-based-spreads");
		//		cat10001500.add("en:fresh-plant-based-foods");
		//		cat10001500.add("en:seeds");
		//		cat10001500.add("en:beers");
		//		cat10001500.add("en:fats");
		//		cat10001500.add("en:canned-vegetables");
		//		cat10001500.add("en:chips-and-fries");
		//		cat10001500.add("en:fruit-juices");
		//		cat10001500.add("en:breakfast-cereals");
		//		cat10001500.add("en:candies");
		//		cat10001500.add("en:microwave-meals");
		//		cat10001500.add("en:meals-with-meat");
		//		cat10001500.add("en:cow-cheeses");
		//		int max = cat10001500.size();

		Set<String> cat = creator.getCategoriesWithBetween(1000, 1500);
		int max = cat.size();

		int count = 0;
		for(String s : cat){
			count++;
			System.out.println(s + " " + count + " / " + max);
			creator.createCSVFromCategory(s, true);
//			try{
//				OFFPCMCreator.mkPCMFromCategory(s);
//			}catch (java.lang.OutOfMemoryError e) {
//				e.printStackTrace();
//			}
		}
		OFFStats.printStats();

		creator.close();

	}
}
