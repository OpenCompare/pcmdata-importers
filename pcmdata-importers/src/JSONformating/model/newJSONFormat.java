package JSONformating.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class newJSONFormat {

	private String name;
	private String license;
	private String source;
	private String primaryFeatureID;
	private List<JFeature> features = new ArrayList<>();
	private List<JProduct> products = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPrimaryFeatureID() {
		return primaryFeatureID;
	}

	public void setPrimaryFeatureID(String primaryFeatureID) {
		this.primaryFeatureID = primaryFeatureID;
	}

	public void addFeature(JFeature f){
		features.add(f);
	}
	
	public void addProduct(JProduct p){
		products.add(p);
	}

	public void export(){
		//TODO to text
	}
	
	public String toString(){
		String res = "Name:" + name + " primaryFeatureID: " + primaryFeatureID + "\n\nFeatures:\n";
		for(JFeature f : features){
			res += 	f.getId() + " " +
					f.getName() + " " + 
					f.getType().toString() + "\n";
		}
		res += "\nProducts:\n";
		for(JProduct p : products){
			for(JCell c : p.getCells()){
				res += 	c.getId() + " " + 
						c.getProductID() + " " +
						c.getFeatureID() + " " +
						(c.getType()==null? 
								"" : 
								c.getType().toString()) + " " +
						(c.getValue()==null? 
								"" : 
								(c.getValue().getClass().equals(JMultipleValue.class)?
									((JMultipleValue)c.getValue()).toString():
									c.getValue().getValue().toString())) + "\n";
			}
		}
		
		return res;
	}

	public List<JFeature> getFeatures() {
		return features;
	}
	
	public newJSONFormatType getTypeForFeature(String featureID){
		Set<newJSONFormatType> types = new HashSet<>();
		for(JProduct p : products){
			for(JCell c : p.getCells()){
				if(c.getFeatureID().equals(featureID)){
					if(featureID.equals("F0") && c.getType().equals(newJSONFormatType.MULTIPLE)){ //DEBUG
						System.err.println(((JMultipleValue)c.getValue()).toString());
					}
					types.add(c.getType());
				}
			}
		}
		if(types.size() == 1){
			newJSONFormatType[] a = new newJSONFormatType[0];;
			return types.toArray(a)[0];
		}
		List<newJSONFormatType> input = new ArrayList<>();
		input.add(newJSONFormatType.BOOLEAN);
		input.add(newJSONFormatType.INTEGER);
		input.add(newJSONFormatType.REAL);
		input.add(newJSONFormatType.UNDEFINED);
		if(containsOnly(types, input)){
			return newJSONFormatType.REAL; //bool, int, real and undef are grouped as reals
		}
		input.add(newJSONFormatType.STRING);
		input.add(newJSONFormatType.DATE);
		input.add(newJSONFormatType.IMAGE);
		input.add(newJSONFormatType.URL);
		input.add(newJSONFormatType.VERSION);
		if(containsOnly(types, input)){
			return newJSONFormatType.STRING; //all of the above plus string, date, image, url and version are grouped as strings
		}
		System.out.println("feature " + featureID + " " + types.toString());
		return newJSONFormatType.UNDEFINED; //undefined if multiples are mixed up
	}
	
	private boolean containsOnly(Set<newJSONFormatType> set, List<newJSONFormatType> input){
		for(newJSONFormatType t : set){
			if(!input.contains(t)){ //si t n'est pas dans input, set ne contient pas uniquement des elements de input
				
				return false;
			}
		}
		return true;
	}

}
