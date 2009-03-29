package hotel.service.user;

import java.util.Map;

import org.hibernate.Query;

import hotel.service.AbstractServiceCommand;
import hotel.service.ExecutionContext;

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
		}
		return null;
	}

}
