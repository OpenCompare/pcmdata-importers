package misc;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


import JSONformating.model.JCell;
import JSONformating.model.JMultipleValue;
import JSONformating.model.JStringValue;
import JSONformating.model.JValue;
import JSONformating.model.newJSONFormat;
import JSONformating.model.newJSONFormatType;

public class Dummy {

	public static void main(String[] args) {
		JCell cell = new JCell();
		cell.setFeatureID("F0");
		cell.setId("C0");
		cell.setProductID("P0");
		cell.setType(newJSONFormatType.STRING);
		JStringValue val = new JStringValue();
		val.setValue("test");
		JMultipleValue mulval = new JMultipleValue();
		List<JValue> list = new ArrayList<>();
		list.add(val);
		mulval.setValue(list);;
		cell.setValue(mulval);
		newJSONFormat nf = new newJSONFormat();
		System.out.println(nf.exportCellJSON(cell));
		
	}

}
