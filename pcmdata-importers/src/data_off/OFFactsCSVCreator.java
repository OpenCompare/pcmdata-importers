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

	public OFFactsCSVCreator(){

		mongo = new MongoClient();
		collection = mongo.getDatabase("off").getCollection("products");
		System.out.println(collection.count() + " products in database");

	}

	@SuppressWarnings("unchecked")
	public String getAllProductsInCategory(String category, boolean getImageUrl) throws IOException, JSONException{
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("categories_tags", category);
		FindIterable<Document> test = collection.find(whereQuery);
		MongoCursor<Document> cursor = test.iterator();
		Document product;
		List<Document> ingredientsList;
//		Map<Integer, String> map = new HashMap<Integer, String>();
		int count = 0;
		String res  = "id;product_name;countries;ingredients;brands;stores;image_url;";
		while(cursor.hasNext()){
			product = cursor.next();
			res += "\n";
			String id = product.getString("id");
			res += id + separator;
//			if(id != null){
//				if(map.containsKey(id.length())){
//					
//				}else{
//					map.put(id.length(), id);
//				}
//			}
			res += quotes + product.get("product_name") + quotes + separator;
			res += quotes + product.get("countries") + quotes + separator;
			ingredientsList = (ArrayList<Document>) product.get("ingredients");
			res += quotes;
			for(Document o : ingredientsList){//check for null, catch exception
				res += quotes + o.getString("id") + "/" + o.getString("text") + quotes + ",";
			}
			res += quotes + separator;
			res += quotes + product.get("brands") + quotes + separator;
			res += quotes + product.get("stores") + quotes + separator;
			res += quotes + (getImageUrl?getImageUrl(id):null) + quotes + separator;
			count++;
		}
//		System.out.println(map.toString());
		System.out.println(count + " products in the category " + category);
		return res;
	}

	private String getImageUrl(String id) throws IOException, JSONException {
		URL url = new URL("http://world.openfoodfacts.org/api/v0/product/"+ id +".json");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
		String input = in.readLine();
		in.close();
		JSONObject json = new JSONObject(input);
		if(json.getString("status_verbose").equals("product not found")){
			System.out.println("Product " + id + " not found");
			return null;
		}else{
			try {
				return json.getJSONObject("product").getString("image_url");
			} catch (JSONException e) {
				System.out.println("No image found for product " + id);
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

		System.out.println(file.exists()?"File exists":"File doesnt exist");
		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(content);
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



}
