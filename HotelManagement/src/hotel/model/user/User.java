package hotel.model.user;

import hotel.model.BaseModel;

import java.util.LinkedHashMap;
import java.util.Map;

import util.MessageUtil;

public class User extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("User.id"));
		fieldMapLabel.put("name", MessageUtil.getMessage("User.name"));
		fieldMapLabel.put("loginName", MessageUtil.getMessage("User.loginName"));
		fieldMapLabel.put("password", MessageUtil.getMessage("User.password"));
		fieldMapLabel.put("description", MessageUtil.getMessage("User.description"));
	}
	
	public static Map getFieldMapLabel() {
		return fieldMapLabel;
	}
	
	protected long id;
	
	protected String name;
	
	protected String loginName;
	
	protected String password;
	
	protected String description;
	
	
	public User() {
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getAttLabel(String attName) {
		return (String) fieldMapLabel.get(attName);
	}
	
	public static void main(String[] args) {
		User user = new User();
		String[] attNames = user.getAttributeNames();
		for (int i = 0; i < attNames.length; i++) {
			String attName = attNames[i];
			System.out.println(user.getAttLabel(attName));
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
