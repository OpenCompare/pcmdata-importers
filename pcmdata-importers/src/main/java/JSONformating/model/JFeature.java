package JSONformating.model;

public class JFeature {
	private String id;
	private String name;
	private JSONFormatType type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public JSONFormatType getType() {
		return type;
	}
	public void setType(JSONFormatType type) {
		this.type = type;
	}
}
