package JSONformating;

import java.util.List;

public class newJSONFormat {

	private class Feature{
		public String id;
		public String name;
		public newJSONFormatType type;
	}

	private class Product{
		public String id;
		public List<Cell> cells;
	}

	private abstract class Value{
		
		Object value;

	}

	private class BooleanValue extends Value{

		boolean value;
		
	}

	private class NumberValue extends Value{
		
		float value;
	}

	private class StringValue extends Value{

		String value;
	}

	private class MultipleValue extends Value{

		List<String> value;
	}

	private class Cell{
		public String id;
		public String productID;
		public String featureID;
		public newJSONFormatType type;
		public boolean isPartial;
		public String unit;
		public Value value;
	}

	private String name;
	private String license;
	private String source;
	private String primaryFeatureID;
	private List<Feature> features;
	private List<Product> products;

}
