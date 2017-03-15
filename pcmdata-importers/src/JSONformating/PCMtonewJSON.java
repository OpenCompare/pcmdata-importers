package JSONformating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.opencompare.api.java.*;
import org.opencompare.api.java.impl.value.*;
import org.opencompare.api.java.value.*;

import JSONformating.model.*;
import data_off.PCMInterpreter;
import data_off.PCMUtil;

public class PCMtonewJSON {

	public static newJSONFormat mkNewJSONFormatFromPCM(PCMContainer pcmC){

		PCM pcm = pcmC.getPcm();
		newJSONFormat nJSONf = new newJSONFormat();

		nJSONf.setName(pcm.getName());
		nJSONf.setCreator(pcmC.getMetadata().getCreator());
		nJSONf.setLicense(pcmC.getMetadata().getLicense());
		nJSONf.setSource(pcmC.getMetadata().getSource());

		Map<Feature,String> features = new HashMap<>();

		int fCount = 0;
		JFeature jf;
		for(Feature f : pcm.getConcreteFeatures()){
			jf = new JFeature();
			jf.setId("F" + fCount);
			jf.setName(f.getName());
			jf.setType(newJSONFormatType.UNDEFINED);//TODO
			nJSONf.addFeature(jf);
			features.put(f, jf.getId());
			fCount++;
		}

		int pCount = 0;
		int cCount = 0;
		JProduct jp;
		JCell jc;
		for(Product p : pcm.getProducts()){
			jp = new JProduct();
			nJSONf.addProduct(jp);
			jp.setId("P" + pCount);
			for(Cell c : p.getCells()){
				jc = new JCell();

				jc.setId("C" + cCount);
				jc.setFeatureID(features.get(c.getFeature()));
				jc.setProductID(jp.getId());

				JValue value = createJValueFromCellForJCell(c, jc);

				jc.setValue(value);

				jp.getCells().add(jc);
				cCount++;
			}
			pCount++;
		}

		String primaryFeatureID = features.get(pcm.getProductsKey());
		nJSONf.setPrimaryFeatureID(primaryFeatureID);

		addTypesToFeatures(nJSONf);

		return nJSONf;

	}

	private static void addTypesToFeatures(newJSONFormat nJSONf) {
		for(JFeature jf : nJSONf.getFeatures()){
			jf.setType(nJSONf.getTypeForFeature(jf.getId()));
		}

	}

	public static JValue createJValueFromCellForJCell(Cell c, JCell jc){
		Class<? extends Value> valClass = c.getInterpretation().getClass();

		if(valClass.equals(BooleanValueImpl.class)){

			jc.setType(newJSONFormatType.BOOLEAN);
			JBooleanValue bool = new JBooleanValue();
			bool.setValue(((BooleanValue) c.getInterpretation()).getValue());
			return bool;

		}else if(valClass.equals(DateValueImpl.class)){

			jc.setType(newJSONFormatType.DATE);
			JStringValue value = new JStringValue();
			value.setValue(((DateValue) c.getInterpretation()).getValue());
			return value;

		}else if(valClass.equals(IntegerValueImpl.class)){

			jc.setType(newJSONFormatType.INTEGER);
			JNumberValue numValue = new JNumberValue();
			numValue.setValue(((IntegerValue) c.getInterpretation()).getValue());
			return numValue;

		}else if(valClass.equals(RealValueImpl.class)){

			jc.setType(newJSONFormatType.REAL);
			JNumberValue value = new JNumberValue();
			value.setValue(((RealValue) c.getInterpretation()).getValue());
			return value;

		}else if(valClass.equals(StringValueImpl.class)){

			StringValue sv = (StringValue) c.getInterpretation();
			if(Pattern.matches("^\\s*http:\\/\\/.*"
					+ "|^\\s*https:\\/\\/.*", sv.getValue())
			&& Pattern.matches(".*\\.jpg\\s*$"
					+ "|.*\\.svg\\s*$"
					+ "|.*\\.jpeg\\s*$"
					+ "|.*\\.bmp\\s*$"
					+ "|.*\\.png\\s*$"
					+ "|.*\\.gif\\s*$", sv.getValue())){
				jc.setType(newJSONFormatType.IMAGE);
			}else if(Pattern.matches("^\\s*http:\\/\\/.*"
					+ "|^\\s*https:\\/\\/.*", sv.getValue())){
				jc.setType(newJSONFormatType.URL);
			}else{
				jc.setType(newJSONFormatType.STRING);
			}
			JStringValue value = new JStringValue();
			value.setValue(((StringValue) c.getInterpretation()).getValue());
			return value;
		}else if(valClass.equals(MultipleImpl.class)){
			jc.setType(newJSONFormatType.MULTIPLE);
			JMultipleValue mulvalue = new JMultipleValue();
			mulvalue.setValue(createJValuesForMultiple(((Multiple) c.getInterpretation()).getSubValues()));
			return mulvalue;
		}else if(valClass.equals(VersionImpl.class)){
			jc.setType(newJSONFormatType.VERSION);
		}else if(valClass.equals(NotApplicableImpl.class)){
			jc.setType(newJSONFormatType.UNDEFINED);
		}else{
			jc.setType(newJSONFormatType.UNDEFINED);
		}
		return null;
	}

	public static List<JValue> createJValuesForMultiple(List<Value> values){
		List<JValue> jvalues = new ArrayList<>();
		Class<? extends Value> valClass;
		
		for(Value val : values){
			valClass = val.getClass();
			if(valClass.equals(BooleanValueImpl.class)){
				JBooleanValue value = new JBooleanValue();
				value.setValue(((BooleanValue) val).getValue());
				jvalues.add(value);
			}else if(valClass.equals(DateValueImpl.class)){
				JStringValue value = new JStringValue();
				value.setValue(((DateValue) val).getValue());
				jvalues.add(value);
			}else if(valClass.equals(IntegerValueImpl.class)){
				JNumberValue value = new JNumberValue();
				value.setValue(((IntegerValue) val).getValue());
				jvalues.add(value);
			}else if(valClass.equals(RealValueImpl.class)){
				JNumberValue value = new JNumberValue();
				value.setValue(((RealValue) val).getValue());
				jvalues.add(value);
			}else if(valClass.equals(StringValueImpl.class)){
				JStringValue value = new JStringValue();
				value.setValue(((StringValue) val).getValue());
				jvalues.add(value);
			}else if(valClass.equals(MultipleImpl.class)){
				
			}else if(valClass.equals(VersionImpl.class)){
				
			}else if(valClass.equals(NotApplicableImpl.class)){
				
			}else{
				
			}
			

		}
		return jvalues;
	}

	public static void main(String[] args) throws IOException {

//		String filename = "off_output/pcms/en_candies_m.pcm";
		String filename = "output/movies.json";
		PCMContainer pcmC = PCMUtil.loadPCMContainer(filename);
		newJSONFormat nf = mkNewJSONFormatFromPCM(pcmC);
		System.out.println(nf.toString());
//		PCMInterpreter.writeToFile("off_output/pcms/new_en_candies.pcm", nf.export());
		PCMInterpreter.writeToFile("output/new_movies.pcm", nf.export());
	}

}
