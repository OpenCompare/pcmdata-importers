package JSONformating.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import JSONformating.model.JSONFormat;

public class JSONReader {

	public static JSONFormat importJSON(String filename){
		try {
			Scanner scanner = new Scanner(new File(filename));
			String json = scanner.useDelimiter("\\Z").next();
			scanner.close();
			System.out.println(json);
			JsonElement jelement = new JsonParser().parse(json);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	
		return null;
	}

	public static void main(String[] args) {
		String filename = "off_output/pcms/fr_biscottes-pauvres-en-sel.new.pcm";
		JSONFormat jf = importJSON(filename);
		
	}

}
