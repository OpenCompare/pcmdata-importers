package data_off;

import java.io.IOException;
import java.util.Set;

import org.json.JSONException;

public class Run {

	public static void main(String[] arg0) throws IOException, JSONException{
		
		OFFactsCSVCreator creator = new OFFactsCSVCreator();
		//String canned = creator.getAllProductsInCategory("en:canned-meals");
		//System.out.println(canned);
		//String pureeTomate = creator.getAllProductsWithIngredient("puree-de-tomate-mi-reduite");
		
		Set<String> cat = creator.getCategoriesWithBetween(1000, 5000);
		int max = cat.size();
		int count = 0;
		String temp;
		for(String s : cat){
			count++;
			System.out.println(s + " " + count + " / " + max);
			temp = creator.getAllProductsInCategory(s, true);
			if(s.contains(":")){
				s = s.substring(s.indexOf(":")+1);
			}
			OFFactsCSVCreator.createCSVFromString(s, temp);
		}
		
		//OFFactsCSVCreator.createCSVFromString("beers", creator.getAllProductsInCategory("en:beers", true));
		
		
		creator.close();
		//OFFactsCSVCreator.createCSVFromString("canned-meals", canned);
		//OFFactsCSVCreator.createCSVFromString("puree-de-tomate-mi-reduite", pureeTomate);
		//OFFactsCSVCreator.createCSVFromString("off_categories1000", categories);
	}
}
