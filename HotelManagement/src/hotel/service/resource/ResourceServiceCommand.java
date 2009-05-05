package hotel.service.resource;

import hotel.model.resource.RoleFunction;
import hotel.model.user.Role;
import hotel.model.user.User;
import hotel.service.AbstractServiceCommand;
import hotel.service.ExecutionContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ResourceServiceCommand extends AbstractServiceCommand {
	
	public ResourceServiceCommand(String command) {
		super(command);
	}

	public ResourceServiceCommand(String command, Map condition) {
		super(command, condition);
	}

	@Override
	public Object execute(ExecutionContext executionContext) {
		if (this.command.equals(getFunctionByLoginUserCommand())) {
			User user = (User) this.condition.get("loginUser");
			user = (User) executionContext.getSession().createQuery("from hotel.model.user.User user where user.id = :id").setParameter("id", user.getId()).uniqueResult();
			Role role = user.getRole();
			List result = role.getFunctions();
			List result1 = new ArrayList();
			for (Iterator iter = result.iterator(); iter.hasNext(); ) {
				RoleFunction rf = (RoleFunction) iter.next();
				result1.add(rf.getFunction());
			}
			return result1;
			//return executionContext.getSession().createQuery("from hotel.model.resource.Function function").list();
		}
		else if (this.command.equals(getAllCommand())) {
			return executionContext.getSession().createQuery("from hotel.model.resource.Function function").list();
		}
		return null;
	}
	
	public static String getFunctionByLoginUserCommand() {
		return "getFunctionByLoginUserCommand";
	}

}
