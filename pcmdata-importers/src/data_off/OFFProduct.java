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
	private String energy_100g;
	private String sugars;
	private String fat;
	private String saturated_fat;
	private String fiber;
	private String sodium;
	private String proteins;
	private String carbohydrates;
	private String salt;
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

	public String getEnergy_100g() {
		return energy_100g;
	}

	public void setEnergy_100g(String energy_100g) {
		this.energy_100g = energy_100g;
	}

	public void setEnergy_100g(Object value, String unit) {
		if(value != null && !(value.toString().isEmpty())){
			if(unit.toLowerCase().equals("kj")){
				Float val;
				if(value.getClass() == String.class){
					val = Float.parseFloat((String) value);
					val = val*43/180;
				}else if(value.getClass() == Long.class){
					val = (float) ((Long) value).intValue();
					val = val*43/180;
				}else if(value.getClass() == Double.class){
					val = (float) ((Double) value).intValue();
					val = val*43/180;
				}else{
					val = (float) (((Integer) value)*43/180);
				}
				energy_100g = val.toString();
			}else{
				energy_100g = value.toString();
			}
		}
	}

	public String getSugars() {
		return sugars;
	}

	public void setSugars(String sugars) {
		this.sugars = sugars;
	}
	
	public void setSugars(Object sugars) {
		if(sugars != null){
			this.sugars = sugars.toString();
		}
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}
	
	public void setFat(Object fat) {
		if(fat != null){
			this.fat = fat.toString();
		}
	}


	public String getSaturated_fat() {
		return saturated_fat;
	}

	public void setSaturated_fat(String saturated_fat) {
		this.saturated_fat = saturated_fat;
	}

	public void setSaturated_fat(Object saturated_fat) {
		if(saturated_fat != null){
			this.saturated_fat = saturated_fat.toString();
		}
	}

	public String getFiber() {
		return fiber;
	}

	public void setFiber(String fiber) {
		this.fiber = fiber;
	}
	
	public void setFiber(Object fiber) {
		if(fiber != null){
			this.fiber = fiber.toString();
		}
	}

	

	public String getSodium() {
		return sodium;
	}

	public void setSodium(String sodium) {
		this.sodium = sodium;
	}
	
	public void setSodium(Object sodium) {
		if(sodium != null){
			this.sodium = sodium.toString();
		}
	}

	public String getProteins() {
		return proteins;
	}

	public void setProteins(String proteins) {
		this.proteins = proteins;
	}
	

	public void setProteins(Object proteins) {
		if(proteins != null){
			this.proteins = proteins.toString();
		}
	}

	

	public String getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(String carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	
	public void setCarbohydrates(Object carbohydrates) {
		if(carbohydrates != null){
			this.carbohydrates = carbohydrates.toString();
		}
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public void setSalt(Object salt) {
		if(salt != null){
			this.salt = salt.toString();
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
				if(key.contains("unit"))
					System.out.println(key+ " " + value);
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
