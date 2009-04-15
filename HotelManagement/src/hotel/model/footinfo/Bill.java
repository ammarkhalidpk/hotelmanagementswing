package hotel.model.footinfo;

import java.util.LinkedHashMap;
import java.util.Map;

import hotel.model.BaseModel;
import hotel.util.MessageUtil;

public class Bill extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("Bill.id"));
		fieldMapLabel.put("footInfo", MessageUtil.getMessage("Bill.footInfo"));
		fieldMapLabel.put("description", MessageUtil.getMessage("Bill.description"));
	}
	
	public static Map getFieldMapLabel() {
		return fieldMapLabel;
	}
	
	@Override
	public String getAttLabel(String attName) {
		return (String) fieldMapLabel.get(attName);
	}
	
	private long id;
	
	private FootInfo footInfo;

	public Bill() {
	}
	
	Bill(FootInfo footInfo) {
		this.footInfo = footInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public FootInfo getFootInfo() {
		return footInfo;
	}

	public void setFootInfo(FootInfo footInfo) {
		this.footInfo = footInfo;
	}
}
