package data_off;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.Set;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVWriter;

import data_off.OFFCSVProductFactory.A;
import kotlin.MapIterator;

/**
 * OFFProduct class
 * 
 * @author mael
 *
 */
public class OFFProduct {

	private final Logger _log = Logger.getLogger(OFFProduct.class.getName());

	private String id;
	private String product_name;
	private List<String> countries;
	private List<String> ingredients;
	private List<String> brands;
	private List<String> stores;
	private List<String> nutriments;
	private String image_url;

	public OFFProduct(){
		this.countries = new ArrayList<>();
		this.ingredients = new ArrayList<>();
		this.brands = new ArrayList<>();
		this.stores = new ArrayList<>();
		this.nutriments = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public List<String> getCountries() {
		return countries;
	}

	public String getCountriesString() throws IOException {
		return listToString(countries);
//		return OFFactsCSVCreator.OBJECT_MAPPER.writeValueAsString(countries);
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public void setCountriesFromString(String countries) {
		try{
			if(!countries.isEmpty()){
				this.countries = Arrays.asList(countries.split(","));
			}
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public String getIngredientsString() throws IOException{
		return listToString(ingredients);
//		return OFFactsCSVCreator.OBJECT_MAPPER.writeValueAsString(str);
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getBrands() {
		return brands;
	}

	public String getBrandsString() throws IOException {
		return listToString(brands);
//		return OFFactsCSVCreator.OBJECT_MAPPER.writeValueAsString(str);
	}

	public void setBrands(List<String> brands) {
		this.brands = brands;
	}

	public void setBrandsFromString(String brands) {
		try{
			if(!brands.isEmpty()){
				this.brands = Arrays.asList(brands.split(","));
			}
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}

	public List<String> getStores() {
		return stores;
	}

	public String getStoresString() throws IOException {
		return listToString(stores);
//		return OFFactsCSVCreator.OBJECT_MAPPER.writeValueAsString(str);
	}

	public void setStores(List<String> stores) {
		this.stores = stores;
	}

	public void setStoresFromString(String stores) {
		try{
			if(!stores.isEmpty()){
				this.stores = Arrays.asList(stores.split(","));
			}
		}
		catch(NullPointerException e){
			e.printStackTrace();;
		}

	}

	public List<String> getNutriments() {
		return nutriments;
	}

	public String getNutrimentsString() throws IOException{
		return listToString(nutriments);
//		return OFFactsCSVCreator.OBJECT_MAPPER.writeValueAsString(nutriments);
	}

	public void setNutriments(List<String> nutriments) {
		this.nutriments = nutriments;
	}

	public void setNutrimentsFromObject(Object nutriments){
		try{
			Set<Entry<String, Object>> nutrimentsSet = ((Document) nutriments).entrySet();
			String key, value;
			for(Entry<String, Object> e : nutrimentsSet){
				key = e.getKey();
				value = e.getValue().toString();
				if(key.endsWith("_value") || key.equals("energy_100g")){
					this.nutriments.add(key + " " + value);
				}
			}
		}catch(NullPointerException e){
			_log.warning("Product " + id + " null pointer exception on nutriments");
		}

	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


	@SuppressWarnings("unchecked")
	public void setIngredientsFromObject(Object ingredients) {
		try{
			List<Document> ingredientsList = (List<Document>) ingredients;
			for(Document o : ingredientsList){
				this.ingredients.add(o.getString("text"));
			}
		}catch(NullPointerException e){
			_log.warning("Product " + id + " null pointer exception on ingredientsList");
		}
	}

	private String listToString(List<String> list){
		String str = "";
		for (String e : list) {
			str += e + ", ";
		}
		return (str.isEmpty())?str:str.substring(0, str.length()-2);
	}
}
