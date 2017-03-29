package JSONformating.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map.Entry;
import java.util.Scanner;

import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import JSONformating.model.JBooleanValue;
import JSONformating.model.JCell;
import JSONformating.model.JFeature;
import JSONformating.model.JMultipleValue;
import JSONformating.model.JNumberValue;
import JSONformating.model.JProduct;
import JSONformating.model.JSONFormat;
import JSONformating.model.JSONFormatType;
import JSONformating.model.JStringValue;
import JSONformating.model.JValue;

public class JSONReader {

	public static JSONFormat importJSON(String filename){
		try {
			Scanner scanner = new Scanner(new File(filename));
			String json = scanner.useDelimiter("\\Z").next();
			scanner.close();
			System.out.println(json);
			JsonElement jelement = new JsonParser().parse(json);
			return createJSONFormat(jelement);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	
		return null;
	}

	private static JSONFormat createJSONFormat(JsonElement jelement) {
		JFeature feature;
		JProduct product;
		JCell cell;
		JsonObject jo, jC, jV, features, obj, products, cells;
		JSONFormat jf = new JSONFormat();
		obj = jelement.getAsJsonObject();
		jf.setName(obj.get("name").getAsString());
		jf.setCreator(obj.get("creator").getAsString());
		jf.setLicense(obj.get("license").getAsString());
		jf.setSource(obj.get("source").getAsString());
		jf.setPrimaryFeatureID(obj.get("primaryFeatureID").getAsString());
		features = obj.get("features").getAsJsonObject();
		
		for(Entry<String, JsonElement> e : features.entrySet()){
			jo = e.getValue().getAsJsonObject();
			feature = new JFeature();
			feature.setId(jo.get("id").getAsString());
			feature.setName(jo.get("name").getAsString());
			feature.setType(JSONFormatType.getType(jo.get("type").getAsString()));
			jf.addFeature(feature);
		}
		products = obj.get("products").getAsJsonObject();
		for(Entry<String, JsonElement> eP : products.entrySet()){
			jo = eP.getValue().getAsJsonObject();
			product = new JProduct();
			product.setId(jo.get("id").getAsString());
			cells = jo.get("cells").getAsJsonObject();
			for(Entry<String, JsonElement> eC : cells.entrySet()){
				jC = eC.getValue().getAsJsonObject();
				cell = new JCell();
				cell.setId(jC.get("id").getAsString());
				cell.setType(JSONFormatType.getType(jC.get("type").getAsString()));
				cell.setFeatureID(jC.get("featureID").getAsString());
				cell.setProductID(jC.get("productID").getAsString());
				cell.setPartial(jC.get("isPartial").getAsBoolean());
				cell.setUnit(jC.get("unit").getAsString());
				switch(cell.getType()){
				case BOOLEAN:
					JBooleanValue boolValue = new JBooleanValue();
					boolValue.setValue(jC.get("value").getAsBoolean());
					cell.setValue(boolValue);
					product.addCell(cell);
					break;
				case DATE:case IMAGE:case STRING:case URL:case VERSION:
					JStringValue stringValue = new JStringValue();
					stringValue.setValue(jC.get("value").getAsBoolean());
					cell.setValue(stringValue);
					product.addCell(cell);
					break;
				case INTEGER:case REAL:
					JNumberValue numValue = new JNumberValue();
					numValue.setValue(jC.get("value").getAsFloat());
					cell.setValue(numValue);
					product.addCell(cell);
					break;
				case MULTIPLE:
					JMultipleValue mulValue = new JMultipleValue();
					cell.setValue(mulValue);
					JsonArray array = jC.get("value").getAsJsonArray();
					for(JsonElement je : array){
						//TODO make a recursive method to import JMultipleValue
					}
					break;
				case UNDEFINED:
				default:
					break;
				
				}
			}
		}
		return jf;
	}

	public static void main(String[] args) {
		String filename = "off_output/pcms/test2.pcm";
		JSONFormat jf = importJSON(filename);
		System.out.println(jf.getName());
	}

}
