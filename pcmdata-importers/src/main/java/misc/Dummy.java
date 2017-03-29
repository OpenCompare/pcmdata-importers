package misc;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import JSONformating.model.JBooleanValue;
import JSONformating.model.JCell;
import JSONformating.model.JMultipleValue;
import JSONformating.model.JProduct;
import JSONformating.model.JStringValue;
import JSONformating.model.JValue;
import JSONformating.model.JSONFormat;
import JSONformating.model.JSONFormatType;

public class Dummy {

	public static void main(String[] args) {
		JCell cell = new JCell();
		cell.setFeatureID("F0");
		cell.setId("C0");
		cell.setProductID("P0");
		cell.setType(JSONFormatType.MULTIPLE);
		JStringValue val = new JStringValue();
		val.setValue("test");
		JBooleanValue bool = new JBooleanValue();
		bool.setValue(true);
		JMultipleValue mulval = new JMultipleValue();
		List<JValue> list = new ArrayList<>();
		list.add(val);
		list.add(bool);
		mulval.setValue(list);;
		cell.setValue(mulval);
		JProduct prod = new JProduct();
		prod.setId("P0");
		List<JCell> cells = new ArrayList<>();
		cells.add(cell);
		prod.setCells(cells);
		JSONFormat nf = new JSONFormat();
		System.out.println(nf.exportCell(cell));
		System.out.println(nf.exportProduct(prod));
		
	}

}
