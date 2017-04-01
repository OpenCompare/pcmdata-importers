package JSONformating.reader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMMetadata;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.impl.FeatureImpl;
import org.opencompare.api.java.impl.PCMFactoryImpl;
import org.opencompare.api.java.value.BooleanValue;
import org.opencompare.api.java.value.DateValue;
import org.opencompare.api.java.value.IntegerValue;
import org.opencompare.api.java.value.Multiple;
import org.opencompare.api.java.value.RealValue;
import org.opencompare.api.java.value.StringValue;
import org.opencompare.api.java.value.Version;

import JSONformating.model.JBooleanValue;
import JSONformating.model.JCell;
import JSONformating.model.JFeature;
import JSONformating.model.JMultipleValue;
import JSONformating.model.JNumberValue;
import JSONformating.model.JProduct;
import JSONformating.model.JSONFormat;
import JSONformating.model.JStringValue;
import JSONformating.model.JValue;

public class JSONtoPCM {
	
	public static Map<String, Feature> featuresMap = new HashMap<>();
	
	public static PCM JSONFormatToPCM(JSONFormat jf){
		PCMFactoryImpl factory = new PCMFactoryImpl();
		PCM pcm = factory.createPCM();
		
		PCMMetadata meta = new PCMMetadata(pcm);
		meta.setCreator(jf.getCreator());
		meta.setLicense(jf.getLicense());
		meta.setSource(jf.getSource());
		
		pcm.setName(jf.getName());
		
		importFeatures(jf, pcm, factory);
		
		importProducts(jf, pcm, factory);
		
		return pcm;
	}

	private static void importFeatures(JSONFormat jf, PCM pcm, PCMFactoryImpl factory) {
		Feature feature;
		for(JFeature f : jf.getFeatures()){
			feature = factory.createFeature();
			feature.setName(f.getName());
			pcm.addFeature(feature);
			if(jf.isPrimaryFeature(f)){
				pcm.setProductsKey(feature);
			}
			featuresMap.put(f.getId(), feature);
		}
		
	}
	
	private static void importProducts(JSONFormat jf, PCM pcm, PCMFactoryImpl factory) {
		Product product;
		Cell cell;
		for(JProduct p : jf.getProducts()){
			product = factory.createProduct();
			for(JCell c : p.getCells()){
				cell = factory.createCell();
				cell.setFeature(featuresMap.get(c.getFeatureID()));
				cell.setInterpretation(getInterpretation(c, factory));
			}
			
		}
		
	}

	private static Value getInterpretation(JCell cell, PCMFactoryImpl factory) {
		Value value = null;
		switch(cell.getType()){
		case BOOLEAN:
			value = factory.createBooleanValue();
			((BooleanValue) value).setValue(((JBooleanValue) cell.getValue()).getValue());
			break;
		case DATE:
			value = factory.createDateValue();
			((DateValue) value).setValue(((JStringValue) cell.getValue()).getValue());
			break;
		case IMAGE:case STRING:case URL:
			value = factory.createStringValue();
			((StringValue) value).setValue(((JStringValue) cell.getValue()).getValue());
			break;
		case VERSION:
			value = factory.createVersion();
			//((Version) value).setValue(?????) //TODO
			break;
		case INTEGER:
			value = factory.createIntegerValue();
			((IntegerValue) value).setValue(((JNumberValue) cell.getValue()).getAsInteger());
			break;
		case REAL:
			value = factory.createRealValue();
			((RealValue) value).setValue(((JNumberValue) cell.getValue()).getValue());
			break;
		case MULTIPLE:
			value = factory.createMultiple();
			setSubvaluesForMultiple((Multiple) value, (JMultipleValue) cell.getValue());
			break;
		case UNDEFINED:
			break;
		default:
			break;
		
		}
		return value;
	}

	private static void setSubvaluesForMultiple(Multiple value, JMultipleValue mulValue) {
		for(JValue v : mulValue.getValue()){
			
		}
	}

	public static void main(String[] args) throws IOException {
		String filename = "off_output/pcms/en_french-blue-veined-cheeses.new.pcm";
		
		JSONFormat jf = JSONReader.importJSON(filename);
		
		PCM pcm = JSONFormatToPCM(jf);
		System.out.println(pcm.isValid() + "\n");
		System.out.println(pcm.getProductsKey().getName());
	}

}
