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
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mongodb.*;
import com.mongodb.client.*;

import kotlin.MapIterator;

public class OFFactsCSVCreator {

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
		Document product;
		String header  = "id;product_name;countries;ingredients;brands;stores;image_url;\n";
		String res = "";
		int count = 0;
		
		if(category.contains(":")){
			category = category.replace(':', '_');
		}
		createCSVFromString(category, header);
		while(cursor.hasNext()){
			count++;
			product = cursor.next();
			res += getProductFromDocument(product, getImageUrl);
			if(count%1000 == 0){
				System.out.println(count + " products done");
				appendStringToCSV(category, res);
				res = "";
			}
		}
		if(!res.isEmpty()){
			System.out.println(count + " products in category " + category);
			appendStringToCSV(category, res);
		}
	}

	@SuppressWarnings("unchecked")
	public String getProductFromDocument(Document product, boolean getImageUrl) throws IOException, JSONException{
		List<Document> ingredientsList;
		String res  = "";

		String id = product.getString("id");
		res += id + separator;
		res += quotes + product.get("product_name") + quotes + separator;
		res += quotes + product.get("countries") + quotes + separator;
		ingredientsList = (ArrayList<Document>) product.get("ingredients");
		res += quotes;
		try{
			for(Document o : ingredientsList){
				res += quotes + o.getString("id") + "/" + o.getString("text") + quotes + ",";
			}
		}catch(NullPointerException e){
			System.err.println("Product " + id + " null pointer exception on ingredientsList");
		}
		res += quotes + separator;
		res += quotes + product.get("brands") + quotes + separator;
		res += quotes + product.get("stores") + quotes + separator;
		res += quotes + (getImageUrl?getImageUrl(id):null) + quotes + separator;
		res += "\n";
		return res;
	}

	private String getImageUrl(String id) throws IOException, JSONException {
		URL url = new URL("http://world.openfoodfacts.org/api/v0/product/"+ id +".json");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
		String input = in.readLine();
		in.close();
		JSONObject json = new JSONObject(input);
		totalProducts++;
		if(json.getString("status_verbose").equals("product not found")){
			System.out.println("Product " + id + " not found");
			
			notFoundThroughAPI++;
			
			return null;
		}else{
			try {
				return json.getJSONObject("product").getString("image_url");
			} catch (JSONException e) {
				System.out.println("No image found for product " + id);
				noImageFound++;
			}
		}
		return null;
	}

	public String getAllProductsWithIngredient(String ingredient){
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("ingredients_tags", ingredient);
		FindIterable<Document> test = collection.find(whereQuery);
		MongoCursor<Document> cursor = test.iterator();
		Document product;
		String res = "id;product_name;";
		int count = 0;
		while(cursor.hasNext()){
			product = cursor.next();
			res += "\n";
			res += product.getString("id") + separator;
			res += product.getString("product_name") + separator ;

			count++;
		}

		System.out.println(count + " products containing " + ingredient);
		return res;
	}

	public static void createCSVFromString(String fileName, String content){
		System.out.println("Writing to file " + fileName);
		//		DateFormat dateFormat = new SimpleDateFormat("-ddMMyyyy-HHmmss");
		//		Date date = new Date();
		String newFileName = "off_output/" + fileName /* + dateFormat.format(date)*/ + ".csv";
		File file = new File(newFileName);

		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		String print = "\n#######\n\nTotal = " + totalProducts;
		print += "\nNot found Products through API = " + notFoundThroughAPI;
		print += "\nNo image found = " + (noImageFound+notFoundThroughAPI);
		print += "\nNot Found Products/Total Product = " + notFoundThroughAPI*100/totalProducts + "%";
		print += "\nNot Found Image/Total Product = " + (noImageFound+notFoundThroughAPI)*100/totalProducts + "%";
		System.out.println(print);		
	}



}
