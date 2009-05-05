package hotel.model.resource;

import java.util.LinkedHashMap;
import java.util.Map;

import hotel.model.BaseModel;
import hotel.util.MessageUtil;

public class RoleFunction extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("RoleFunction.id"));
		fieldMapLabel.put("function", MessageUtil.getMessage("RoleFunction.function"));
	}
	
	public static Map getFieldMapLabel() {
		return fieldMapLabel;
	}
	
	private long id;
	
	private Function function;
	
	public RoleFunction() {
	}
	@Override
	public String getAttLabel(String attName) {
		return (String) fieldMapLabel.get(attName);
	}
	
	@Override
	public String toString() {
		return function.toString();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Function getFunction() {
		return function;
	}
	
	public void setFunction(Function function) {
		this.function = function;
	}

}
