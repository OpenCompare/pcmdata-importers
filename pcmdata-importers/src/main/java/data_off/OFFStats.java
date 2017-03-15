package data_off;

public class OFFStats {
	

	public static int TOTAL_PRODUCTS;
	public static int PRODUCTS_NOT_FOUND_THROUGH_API;
	public static int IMAGES_NOT_FOUND;
	
	public static void resetStats(){
		TOTAL_PRODUCTS = 0;
		PRODUCTS_NOT_FOUND_THROUGH_API = 0;
		IMAGES_NOT_FOUND = 0;
	}
	
	public static void printStats(){
		String print;
		if(OFFToProduct.getImageUrl()){
		print = "\n#######\n\nTotal = " + TOTAL_PRODUCTS;
		print += "\nNot found Products through API = " + PRODUCTS_NOT_FOUND_THROUGH_API;
		print += "\nImage not found = " + IMAGES_NOT_FOUND;
		print += "\nMissing images = " + (IMAGES_NOT_FOUND+PRODUCTS_NOT_FOUND_THROUGH_API);
		print += "\nNot Found Products/Total Products = " + PRODUCTS_NOT_FOUND_THROUGH_API*100/TOTAL_PRODUCTS + "%";
		print += "\nMissing Images/Total Products = " + (IMAGES_NOT_FOUND+PRODUCTS_NOT_FOUND_THROUGH_API)*100/TOTAL_PRODUCTS + "%\n";
		}else{
			print = "\n#######\n\nTotal Products = " + TOTAL_PRODUCTS + "\n";
		}
		System.out.println(print);
	}
}
