package data_off;

public class Run {

	public static void main(String[] arg0){
		
		OFFactsCSVCreator creator = new OFFactsCSVCreator();
		String canned = creator.getAllProductsInCategory("en:canned-meals");
		System.out.println(canned);
		//String sucre = creator.getAllProductsWithIngredient("sucre");
		creator.close();
		OFFactsCSVCreator.createCSVFromString("canned-meals", canned);
		
	}
}
