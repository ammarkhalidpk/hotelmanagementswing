package hotel.service.user;

import hotel.hibernate.HibernateProxyUtil;
import hotel.model.user.Guest;
import hotel.model.user.User;
import hotel.model.user.VIPUser;
import hotel.service.AbstractServiceCommand;
import hotel.service.ExecutionContext;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class UserServiceCommand extends AbstractServiceCommand {

	public UserServiceCommand(String command) {
		super(command);
	}
	
	public UserServiceCommand(String command, Map condition) {
		super(command, condition);
	}
	
	public Object execute(ExecutionContext executionContext) {
		if (this.command.equals(getSaveOrUpdateEntryCommand())) {
			executionContext.getSession().saveOrUpdate(this.condition.get("entry"));
		} else if (this.command.equalsIgnoreCase("getUser")) {
			Query query = executionContext.getSession().createQuery("from hotel.model.user.User user where user.loginName = :logName and user.password = :password");
			query.setParameter("logName", this.condition.get("logName"));
			query.setParameter("password", this.condition.get("password"));
			return getHightestLevelUser(query.list());
		} else if (this.command.equalsIgnoreCase("getAllAsArray")) {
			List all =  executionContext.getSession().createQuery("from hotel.model.user.User user").list();
			return this.toArray(all);
		} else if (this.command.equals(getDeleteByIdCommand())) {
			long id = Long.parseLong((String) this.condition.get("id"));
			User user = (User) executionContext.getSession().createQuery("from hotel.model.user.User user where user.id = :id").setParameter("id", id).uniqueResult();
			executionContext.getSession().delete(user);
		} else if (this.command.equals(getUserByIdCard())) {
			String idCard = (String) this.condition.get("idCard");
			return executionContext.getSession().createQuery("from hotel.model.user.User user where user.identifier = :idCard").setParameter("idCard", idCard).list();
		} else if (this.command.equals(getByIdCommand())) {
			long id = Long.parseLong((String) this.condition.get("id"));
			return executionContext.getSession().createQuery("from hotel.model.user.User user where user.id = :id").setParameter("id", id).uniqueResult();
		} else if (this.command.equals(getAllGuestAsArray())) {
			List result = executionContext.getSession().createQuery("from hotel.model.user.Guest guest order by guest.point desc").list();
			return this.toArray(result);
		} else if (this.command.equals(getAllVipAsArray())) {
			List result = executionContext.getSession().createQuery("from hotel.model.user.VIPUser vip order by vip.point desc").list();
			return this.toArray(result);
		} else if (this.command.equals(getVipByIdCardCommand())) {
			String idCard = (String) this.condition.get("idCard");
			return executionContext.getSession().createQuery("from hotel.model.user.VIPUser vip where vip.identifier = :idCard").setParameter("idCard", idCard).uniqueResult();
		}
		return null;
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
	
	public static String getUserByIdCard() {
		return "getUserByIdCard";
	}
	
	public static String getAllGuestAsArray() {
		return "getAllGuestAsArray";
	}
	
	public static String getAllVipAsArray() {
		return "getAllVipAsArray";
	}

	public static String getVipByIdCardCommand() {
		return "getVipByIdCardCommand";
	}
}
