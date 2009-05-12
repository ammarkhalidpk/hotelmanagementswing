package hotel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hotel.hibernate.HibernateProxyUtil;
import hotel.model.user.Guest;
import hotel.model.user.Role;
import hotel.model.user.User;
import hotel.model.user.VIPUser;
import hotel.service.CommandService;
import hotel.service.dingroom.DingRoomServiceCommand;
import hotel.service.user.RoleServiceCommand;
import hotel.service.user.UserServiceCommand;

public class Register extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loginName;
	private String userName;
	private String password;
	private String identifier;
	private String description;
	private List allUser;

	@Override
	public String execute() throws Exception {
		allUser = (List) this.commandService.getCurrentExecutionContext().getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom").list();
		System.out.println(allUser.size());
		
		
		Map condition = new HashMap();
		condition.put("idCard", identifier);
		List users = (List) CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getUserByIdCard(), condition));
		User user = getHightestLevelUser(users);
		if (user != null) {
			this.addActionError("用户已存在");
			return "input";
		}
		Role defGuestRole = (Role) CommandService.getInstance().execute(new RoleServiceCommand(RoleServiceCommand.getDefGuestRoleCommand()));
		Guest guest = new Guest();
		guest.setDescription(description);
		guest.setIdentifier(identifier);
		guest.setLoginName(loginName);
		guest.setName(userName);
		guest.setRole(defGuestRole);
		commandService.assignId(guest);
		this.addActionMessage("注册成功");
		return SUCCESS;
	}
	
	private User getHightestLevelUser(List users) {
		VIPUser vip = null;
		Guest guest = null;
		for (Iterator iter = users.iterator(); iter.hasNext(); ) {
			User user = (User) iter.next();
			user = (User) HibernateProxyUtil.getImplementation(user);
			if (user instanceof Guest) {
				guest = (Guest) user;
			} else if (user instanceof VIPUser) {
				vip = (VIPUser) user;
				return vip;
			}
			
		}
		return guest;
	}
	
	public String input() throws Exception {
		return "input";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List getAllUser() {
		return allUser;
	}

	public void setAllUser(List allUser) {
		this.allUser = allUser;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
