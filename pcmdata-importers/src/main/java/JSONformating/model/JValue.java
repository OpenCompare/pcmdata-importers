package JSONformating.model;

import java.util.Collection;

public abstract class JValue {
	
	private Object value;
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public String toString(){
		return "";
	}

	public Object export() {
		return "";
	}

	public boolean sameValue(JValue value) {
		return this.equals(value);
	}
}
