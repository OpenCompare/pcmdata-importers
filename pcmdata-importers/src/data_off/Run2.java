package data_off;

import java.io.IOException;
import java.util.Set;

import org.json.JSONException;

public class Run2 {

public static void main(String[] arg0) throws IOException, JSONException{
		
		OFFactsCSVCreator creator = new OFFactsCSVCreator();
		//String canned = creator.getAllProductsInCategory("en:canned-meals");
		//System.out.println(canned);
		//String pureeTomate = creator.getAllProductsWithIngredient("puree-de-tomate-mi-reduite");
		
		creator.createCSVFromCategory("en:cheeses", false);
		
		creator.close();
		//OFFactsCSVCreator.createCSVFromString("canned-meals", canned);
		//OFFactsCSVCreator.createCSVFromString("puree-de-tomate-mi-reduite", pureeTomate);
		//OFFactsCSVCreator.createCSVFromString("off_categories1000", categories);
	}

}
