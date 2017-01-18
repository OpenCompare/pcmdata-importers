package data_off;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public class Run3 {

	public static void main(String[] args) {

		OFFactsCSVCreator creator = new OFFactsCSVCreator();

		MongoCursor<Document> cursor = creator.getMongoCursorForCategory("en:desserts");
		while(cursor.hasNext()){
			Document doc = cursor.next();
			File file = new File("data/test.json");

			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try (Writer writer = new BufferedWriter(new FileWriter(file))) {
				writer.append(doc.get("nutriments").toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		creator.close();

	}

}
