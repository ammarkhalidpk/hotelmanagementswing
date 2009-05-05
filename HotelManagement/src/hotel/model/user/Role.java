package hotel.model.user;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import hotel.model.BaseModel;
import hotel.model.resource.Function;
import hotel.model.resource.RoleFunction;
import hotel.service.CommandService;
import hotel.util.MessageUtil;

public class Role extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("Role.id"));
		fieldMapLabel.put("name", MessageUtil.getMessage("Role.name"));
		fieldMapLabel.put("code", MessageUtil.getMessage("Role.code"));
		fieldMapLabel.put("description", MessageUtil.getMessage("Role.description"));
	}

	public static Map getFieldMapLabel() {
		return fieldMapLabel;
	}
	
	/*/
	public static void main(String[] args) {
		Role admin = new Role();
		admin.setName("系统管理员");
		admin.setCode("admin");
		admin.setDescription("系统管理员角色");
		Role guest = new Role();
		guest.setName("宾客");
		guest.setCode("guest");
		guest.setDescription("宾客角色");
		CommandService.getInstance().assignId(admin);
		CommandService.getInstance().assignId(guest);
	}
	//*/

	private long id;
	
	private String name;
	
	private String code;
	
	private String description;
	
	private List functions;
	
	public Role() {
	}

	@Override
	public String getAttLabel(String attName) {
		return (String) fieldMapLabel.get(attName);
	}

	@Override
	public String toString() {
		return name;
	}
	
	public void addFunction(RoleFunction roleFunction) {
		this.functions.add(roleFunction);
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List getFunctions() {
		return functions;
	}

	public void setFunctions(List functions) {
		this.functions = functions;
	}

}
