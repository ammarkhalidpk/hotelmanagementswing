package hotel.model.user;

import hotel.hibernate.HibernateProxyUtil;
import hotel.model.BaseModel;
import hotel.service.CommandService;
import hotel.service.user.UserServiceCommand;
import hotel.util.MessageUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class User extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("User.id"));
		fieldMapLabel.put("name", MessageUtil.getMessage("User.name"));
		fieldMapLabel
				.put("loginName", MessageUtil.getMessage("User.loginName"));
		fieldMapLabel.put("password", MessageUtil.getMessage("User.password"));
		fieldMapLabel.put("description", MessageUtil
				.getMessage("User.description"));
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

	public static void upgrade(User user) {
		user = (User) HibernateProxyUtil.getImplementation(user);
		if (user.getPoint() >= 20) {
			upgrade(user, 20);
		} else if (user.getPoint() >= 15) {
			upgrade(user, 15);
		} else if (user.getPoint() >= 10) {
			upgrade(user, 10);
		} else if (user.getPoint() >= 5) {
			upgrade(user, 5);
		}
	}
	
	private static Map levelMapDescription = new HashMap();
	static {
		levelMapDescription.put("20", "4级VIP");
		levelMapDescription.put("15", "3级VIP");
		levelMapDescription.put("10", "2级VIP");
		levelMapDescription.put("5", "1级VIP");
	}
	private static void upgrade(User user, int i) {
		if (user instanceof Guest) {
			Map condition = new HashMap();
			VIPUser vip = new VIPUser();
			vip.setDescription((String) levelMapDescription.get(i + ""));
			vip.setIdentifier(((Guest)user).getIdentifier());
			vip.setName(user.getName());
			vip.setPoint(((Guest)user).getPoint());
			condition.put("entry", vip);
			CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getSaveOrUpdateEntryCommand(), condition));
		} else if (user instanceof VIPUser) {
			((VIPUser)user).setDescription((String) levelMapDescription.get(i + ""));
			Map condition = new HashMap();
			condition.put("entry", user);
			CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getSaveOrUpdateEntryCommand(), condition));
		}
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

	public int getPoint() {
		return 0;
	}

	public void setPoint(int point) {
	}
}
