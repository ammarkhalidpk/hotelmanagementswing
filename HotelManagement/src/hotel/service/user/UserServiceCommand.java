package hotel.service.user;

import hotel.model.user.User;
import hotel.service.AbstractServiceCommand;
import hotel.service.ExecutionContext;

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
		if (this.command.equalsIgnoreCase("saveOrUpdateUser")) {
			executionContext.getSession().save(this.condition.get("user"));
		} else if (this.command.equalsIgnoreCase("getUser")) {
			Query query = executionContext.getSession().createQuery("from hotel.model.user.User user where user.loginName = :logName and user.password = :password");
			query.setParameter("logName", this.condition.get("logName"));
			query.setParameter("password", this.condition.get("password"));
			return query.uniqueResult();
		} else if (this.command.equalsIgnoreCase("getAllAsArray")) {
			List all =  executionContext.getSession().createQuery("from hotel.model.user.User user").list();
			return this.toArray(all);
		} else if (this.command.equalsIgnoreCase("delete")) {
			long id = Long.parseLong((String) this.condition.get("id"));
			User user = (User) executionContext.getSession().createQuery("from hotel.model.user.User user where user.id = :id").setParameter("id", id).uniqueResult();
			executionContext.getSession().delete(user);
		} else if (this.command.equals(getUserByIdCard())) {
			String idCard = (String) this.condition.get("idCard");
			return executionContext.getSession().createQuery("from hotel.model.user.User user where user.identifier = :idCard").setParameter("idCard", idCard).uniqueResult();
		}
		return null;
	}

	public static String getUserByIdCard() {
		return "getUserByIdCard";
	}

}
