package data_off;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.*;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;

import data_off.OFFCSVProductFactory.A;

public class OFFactsCSVCreator {
	
	/*
	 *  ps -ef | grep mongo
	 */
	public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private MongoClient mongo;
	private MongoCollection<Document> collection;
	private String separator = ";";
	private String quotes = "\"";
	
	/*
	 * Temporary variable for stats
	 */
	
	int totalProducts = 0;
	int notFoundThroughAPI = 0;
	int noImageFound = 0;
	
	public OFFactsCSVCreator(){

		mongo = new MongoClient();
		collection = mongo.getDatabase("off").getCollection("products");
		System.out.println(collection.count() + " products in database");

	}

	public MongoCursor<Document> getMongoCursorForCategory(String category){
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("categories_tags", category);
		FindIterable<Document> test = collection.find(whereQuery);
		MongoCursor<Document> cursor = test.iterator();
		return cursor;
	}

	public void createCSVFromCategory(String category, boolean getImageUrl) throws IOException, JSONException{
		MongoCursor<Document> cursor = getMongoCursorForCategory(category);
		
		if(category.contains(":")){
			category = category.replace(':', '_');
		}
		OFFToProduct.setGetImageUrl(getImageUrl);
		createCSVFromMongoCursor(category, cursor);
	}

//	@SuppressWarnings("unchecked")
//	public String getProductFromDocument(Document product, boolean getImageUrl) throws IOException, JSONException{
//		List<Document> ingredientsList;
//		String res  = "";
//
//		String id = product.getString("id");
//		res += id + separator;
//		res += quotes + product.get("product_name") + quotes + separator;
//		res += quotes + product.get("countries") + quotes + separator;
//		ingredientsList = (ArrayList<Document>) product.get("ingredients");
//		res += quotes;
//		boolean first = true;
//		try{
//			for(Document o : ingredientsList){
//				String text = o.getString("text");
//				String idIngr = o.getString("id");
//				text = text.replaceAll("[\n\r\t]", " ");
//				idIngr = idIngr.replaceAll("[\n\r\t]", " ");
//				if(text.contains(",") || idIngr.contains(",")){
//					System.out.println("##### Contains ',' #####\n"+text+"\n"+idIngr+"\n#####");
//				}
//				res += (first?"":",") + idIngr + "~>" + text;
//				first = false;
//			}
//			
//		}catch(NullPointerException e){
//			System.err.println("Product " + id + " null pointer exception on ingredientsList");
//		}
//		res += quotes;
//		res += separator;
//		res += quotes + product.get("brands") + quotes + separator;
//		res += quotes + product.get("stores") + quotes + separator;
//		res += quotes + (getImageUrl?getImageUrl(id):null) + quotes + separator;
//		res += "\n";
//		return res;
//	}


	public static int createCSVFromMongoCursor(String fileName, MongoCursor<Document> cursor) throws JSONException{
		System.out.println("Writing to file " + fileName);
		//		DateFormat dateFormat = new SimpleDateFormat("-ddMMyyyy-HHmmss");
		//		Date date = new Date();
		String newFileName = "off_output/" + fileName /* + dateFormat.format(date)*/ + ".csv";
		File file = new File(newFileName);

		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
			
			CSVWriter csvwriter = new CSVWriter(writer, ';', '"');
			String[] header  = {"id","product_name","countries","ingredients","brands","stores","image_url"};
			csvwriter.writeNext(header);//writing the header
			List<OFFProduct> productList = OFFToProduct.mkOFFProductsFromMongoCursor(cursor);
			List<String[]> strArrList = OFFToProduct.mkOFFProductsStrings(productList);
			for(String[] srtArr : strArrList){
				csvwriter.writeNext(srtArr); //TODO write with CSVWriter at each iteration of MongoCursor to free the memory
			}
			csvwriter.close();
			return productList.size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void appendStringToCSV(String fileName, String content){
		String newFileName = "off_output/" + fileName /* + dateFormat.format(date)*/ + ".csv";
		File file = new File(newFileName);

		try (Writer writer = new BufferedWriter(new FileWriter(file, true))) {
			writer.append(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public Set<String> getCategoriesWithBetween(int min, int max){
		FindIterable<Document> test = collection.find();
		MongoCursor<Document> cursor = test.iterator();
		Document product;
		List<String> catList;
		Map<String, Integer> catMap = new HashMap<String, Integer>();
		int count = 0;
		while(cursor.hasNext()){
			product = cursor.next();
			catList = (ArrayList<String>) product.get("categories_tags");
			if(catList != null){
				for(String cat : catList){
					if(catMap.containsKey(cat)){
						catMap.put(cat, catMap.get(cat)+1);
					}else{
						catMap.put(cat, 1);
					}
				}
			}
			count++;
			if(count%1000 == 0){
				System.out.println(count + " products checked");
			}
		}
		catMap.remove("");
		System.out.println(catMap.size() + " categories in total");
		String key;
		Map<String, Integer> temp = new HashMap<String, Integer>(catMap);
		for(Map.Entry<String, Integer> entry : temp.entrySet()){
			if(entry.getValue() < min || entry.getValue() > max){
				key = entry.getKey();
				catMap.remove(key);
			}
		}

		System.out.println(catMap.size() + " categories with between " + min + " and " + max + " products");
		return catMap.keySet();
	}


	public void close(){
		mongo.close();
	}

	public void printStats() {
		if(totalProducts == 0) 
			return;
		String print = "\n#######\n\nTotal = " + totalProducts;
		print += "\nNot found Products through API = " + notFoundThroughAPI;
		print += "\nNo image found = " + (noImageFound+notFoundThroughAPI);
		print += "\nNot Found Products/Total Product = " + notFoundThroughAPI*100/totalProducts + "%";
		print += "\nNot Found Image/Total Product = " + (noImageFound+notFoundThroughAPI)*100/totalProducts + "%";
		System.out.println(print);		
	}
	
	public void resetStats(){
		totalProducts = 0;
		notFoundThroughAPI = 0;
		noImageFound = 0;
	}



}
