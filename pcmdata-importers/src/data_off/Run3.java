package data_off;

import java.util.Set;

public class Run3 {

	public static void main(String[] args) {
		OFFactsCSVCreator creator = new OFFactsCSVCreator();
		
		Set<String> cat = creator.getCategoriesWithBetween(10000, 100000);
		System.out.println(cat.toString());
		
		creator.close();
	}

}
