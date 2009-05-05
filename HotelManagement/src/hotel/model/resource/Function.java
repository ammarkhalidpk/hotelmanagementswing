package hotel.model.resource;

import java.util.LinkedHashMap;
import java.util.Map;

import hotel.model.BaseModel;
import hotel.util.MessageUtil;

public class Function extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("Function.id"));
		fieldMapLabel.put("commandName", MessageUtil.getMessage("Function.commandName"));
		fieldMapLabel.put("label", MessageUtil.getMessage("Function.label"));
		fieldMapLabel.put("className", MessageUtil.getMessage("Function.className"));
		fieldMapLabel.put("description", MessageUtil.getMessage("Function.description"));
	}
	
	public static Map getFieldMapLabel() {
		return fieldMapLabel;
	}
	
	private long id;
	
	private String commandName;
	
	private String label;
	
	private String className;
	
	private String description;
	
	public Function() {
	}
	
	@Override
	public String getAttLabel(String attName) {
		return (String) fieldMapLabel.get(attName);
	}

	@Override
	public String toString() {
		return description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
