package data_off;

import java.io.IOException;

import org.opencompare.api.java.AbstractFeature;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMFactory;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.impl.PCMFactoryImpl;
import org.opencompare.api.java.impl.value.*;
import org.opencompare.api.java.value.Multiple;


public class OFFPCMModifier {
	
	

	public static void main(String[] args) throws IOException {
		
		String filename = "off_output/pcms/en_seeds.pcm";
		PCM pcm = PCMUtil.loadPCM(filename);
		
		for(Feature f : pcm.getConcreteFeatures()){
			if(f.getName().equals("ingredients")){
				for(Cell c : f.getCells()){
					c.setInterpretation(toMultipleValue(c.getContent()));
				}
			}
		}
	}

	private static Value toMultipleValue(String content) {
		//TODO Spliter le content et remplir la multiple value
		PCMFactory f = new PCMFactoryImpl();
		Multiple multiple = f.createMultiple();
		multiple.addSubValue(/*TODO*/);
		return multiple;
	}

}
