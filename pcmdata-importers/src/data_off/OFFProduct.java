package data_off;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private String id;
	private String product_name;
	private String[] countries;
	private Map<String, String> ingredients;
	private String[] brands;
	private String[] stores;
	private String image_url;

	public OFFProduct(){
		ingredients = new HashMap<>();
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

	public String[] getCountries() {
		return countries;
	}

	public String getCountriesString() throws IOException {
		return OFFactsCSVCreator.OBJECT_MAPPER.writeValueAsString(countries);
	}

	public void setCountries(String[] countries) {
		this.countries = countries;
	}

	public void setCountriesFromString(String countries) {
		try{
			if(countries.isEmpty()){
				this.countries = null;
			}else{
				this.countries = countries.split(",");
			}
		}
		catch(NullPointerException e){
			this.stores = null;
		}
	}

	public Map<String, String> getIngredients() {
		return ingredients;
	}

	public String getIngredientsString() throws IOException{
		return OFFactsCSVCreator.OBJECT_MAPPER.writeValueAsString(ingredients);
	}

	public void setIngredients(Map<String, String> ingredients) {
		this.ingredients = ingredients;
	}

	public String[] getBrands() {
		return brands;
	}

	public String getBrandsString() throws IOException {
		return OFFactsCSVCreator.OBJECT_MAPPER.writeValueAsString(brands);
	}

	public void setBrands(String[] brands) {
		this.brands = brands;
	}

	public void setBrandsFromString(String brands) {
		try{
			if(brands.isEmpty()){
				this.brands = null;
			}else{
				this.brands = brands.split(",");
			}
		}
		catch(NullPointerException e){
			this.brands = null;
		}
	}

	public String[] getStores() {
		return stores;
	}

	public String getStoresString() throws IOException {
		return OFFactsCSVCreator.OBJECT_MAPPER.writeValueAsString(stores);
	}

	public void setStores(String[] stores) {
		this.stores = stores;
	}

	public void setStoresFromString(String stores) {
		try{
			if(stores.isEmpty()){
				this.stores = null;
			}else{
				this.stores = stores.split(",");
			}
		}
		catch(NullPointerException e){
			this.stores = null;
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
			if(ingredientsList.size() > 0){
			for(Document o : ingredientsList){
				this.ingredients.put(o.getString("id"), o.getString("text"));
			}
			}else{
				this.ingredients = null;
			}

		}catch(NullPointerException e){
			System.err.println("Product " + id + " null pointer exception on ingredientsList");
		}
	}

}
