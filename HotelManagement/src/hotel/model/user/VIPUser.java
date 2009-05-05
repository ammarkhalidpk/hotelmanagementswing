package hotel.model.user;

import hotel.util.MessageUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class VIPUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("Guest.id"));
		fieldMapLabel.put("name", MessageUtil.getMessage("Guest.name"));
		fieldMapLabel.put("identifier", MessageUtil.getMessage("Guest.identifier"));
		fieldMapLabel.put("point", MessageUtil.getMessage("Guest.point"));
		fieldMapLabel.put("description", MessageUtil.getMessage("Guest.description"));
	}

	protected long id;

	protected String name;

	/**
	 * 身份证号码
	 */
	protected String identifier;
	
	/**
	 * 积分
	 */
	protected int point;

	protected String description;

	public static Map getFieldMapLabel() {
		return fieldMapLabel;
	}
	
	@Override
	public String getAttLabel(String attName) {
		return (String) fieldMapLabel.get(attName);
	}

	public VIPUser() {
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return this.getIdentifier() + "(" + this.getName() + ")";
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}
	
	public Role getRole() {
		return role;
	}
}
