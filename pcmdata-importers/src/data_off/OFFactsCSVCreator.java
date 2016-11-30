package data_off;

import org.bson.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.*;
import com.mongodb.client.*;

public class OFFactsCSVCreator {

	private MongoClient mongo;
	private MongoCollection<Document> collection;
	
	public OFFactsCSVCreator(){
		
		mongo = new MongoClient();
		collection = mongo.getDatabase("off").getCollection("products");
		System.out.println(collection.count() + " products in database");
		
	}

	public String getAllProductsInCategory(String category){
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("categories_tags", category);
		FindIterable<Document> test = collection.find(whereQuery);
		MongoCursor<Document> cursor = test.iterator();
		Document product;
		int count = 0;
		while(cursor.hasNext()){
			product = cursor.next();
			count++;
		}
		System.out.println(count + " products in the category " + category);
		return "TODO";
	}

	public String getAllProductsWithIngredient(String ingredient){
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("ingredients_tags", ingredient);
		FindIterable<Document> test = collection.find(whereQuery);
		MongoCursor<Document> cursor = test.iterator();
		Document product;
		String res;
		int count = 0;
		while(cursor.hasNext()){
			product = cursor.next();
			count++;
		}
		
		System.out.println(count + " products containing " + ingredient);
		return "TODO";
	}

	public static void createCSVFromString(String fileName, String content){
		DateFormat dateFormat = new SimpleDateFormat("-ddMMyyyy-HHmmss");
		Date date = new Date();
		String newFileName = "output/" + fileName + dateFormat.format(date) + ".csv";
		File file = new File(newFileName);

		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(file.exists()?"File exists":"File doesnt exist");
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close(){
		mongo.close();
	}



}
