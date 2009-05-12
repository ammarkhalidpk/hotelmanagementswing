package hotel;

import hotel.hibernate.HibernateProxyUtil;
import hotel.model.user.Guest;
import hotel.model.user.User;
import hotel.model.user.VIPUser;

import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loginName;
	
	private String password;

	@Override
	public String execute() throws Exception {
		Query query = this.commandService.getCurrentExecutionContext().getSession().createQuery("from hotel.model.user.User user where user.loginName = :logName and user.password = :password");
		query.setParameter("logName", loginName);
		query.setParameter("password", password);
		User user = getHightestLevelUser(query.list());
		if (user == null) {
			this.addActionError("登录失败，用户名或密码有误");
			return "input";
		}
		ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
		return SUCCESS;
	}
	
	private User getHightestLevelUser(List users) {
		VIPUser vip = null;
		Guest guest = null;
		User u = null;
		for (Iterator iter = users.iterator(); iter.hasNext(); ) {
			User user = (User) iter.next();
			user = (User) HibernateProxyUtil.getImplementation(user);
			if (user instanceof Guest) {
				guest = (Guest) user;
			} else if (user instanceof VIPUser) {
				vip = (VIPUser) user;
				return vip;
			} else {
				u = user;
			}
			
		}
		if (guest != null)
		return guest;
		else 
			return u;
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

}
