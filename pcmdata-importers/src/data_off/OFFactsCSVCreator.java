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
import java.util.Map.Entry;

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
		MongoCursor<Document> cursorForHeader = getMongoCursorForCategory(category);
		if(category.contains(":")){
			category = category.replace(':', '_');
		}
		OFFToProduct.setGetImageUrl(getImageUrl);
		int count = createCSVFromMongoCursor(category, cursor, cursorForHeader);
		
		System.out.println(count + " products in the category " + category);
	}

	public static int createCSVFromMongoCursor(String fileName, MongoCursor<Document> cursor, MongoCursor<Document> cursorForHeader) throws JSONException{
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
			
			CSVWriter csvwriter = new CSVWriter(writer, ';', '\"');
			List<String> nutriments = getNutrimentsList(cursorForHeader);
			String[] header  = makeHeader(nutriments);
//			System.out.println(header.length);
			csvwriter.writeNext(header);//writing the header
			Document product;
			int count = 0;
			while(cursor.hasNext()){
				product = cursor.next();
				csvwriter.writeNext(OFFToProduct.mkOFFProductStrings(OFFToProduct.mkOFFProductFromBSON(product), nutriments));
				count++;
			}
			csvwriter.close();
			return count;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}


	private static String[] makeHeader(List<String> nutriments) {
		List<String> header = new ArrayList<String>();
		header.add("id");
		header.add("product_name");
		header.add("countries");
		header.add("ingredients");
		header.add("brands");
		header.add("stores");
		header.addAll(nutriments);
		header.add("image_url");
		String[] a = new String[1];
		return header.toArray(a);
	}

	private static List<String> getNutrimentsList(MongoCursor<Document> cursorForHeader) {
		Map<String, Integer> nutriments = new HashMap<String, Integer>();
		Document product;
		Set<Entry<String, Object>> nutrimentsSet;
		String key;
		while(cursorForHeader.hasNext()){
			product = cursorForHeader.next();
			nutrimentsSet = ((Document) product.get("nutriments")).entrySet();
			for(Entry<String, Object> entry : nutrimentsSet){
				key = entry.getKey();
				if(key.endsWith("_value")){
					key = key.substring(0, key.length()-6);
					if(!nutriments.containsKey(key)){
						nutriments.put(key, 1); //adding the nutriment to the map if it is not already in
					}else{
						nutriments.put(key, nutriments.get(key) + 1); //incrementing
					}
				}   
			}
		}

		return filterNMostUsedNutriments(nutriments, 10);
	}

	private static List<String> filterNMostUsedNutriments(Map<String, Integer> nutriments, Integer i) {
		List<String> list = new ArrayList<String>();
		while(list.size() < i){
			int max = 0;
			String key = null;
			for(Entry<String, Integer> entry : nutriments.entrySet()){
				if(entry.getValue() > max){
					key = entry.getKey();
					max = entry.getValue();
				}
			}
			list.add(key);
			nutriments.remove(key);
		}
		
		return list;
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
