package hotel.service.user;

import hotel.service.AbstractServiceCommand;
import hotel.service.ExecutionContext;

import java.util.List;
import java.util.Map;

public class RoleServiceCommand extends AbstractServiceCommand {

	public RoleServiceCommand(String command) {
		super(command);
	}
	
	public RoleServiceCommand(String command, Map condition) {
		super(command, condition);
	}

	@Override
	public Object execute(ExecutionContext executionContext) {
		if (this.command.equals(getAllCommand())) {
			List result = executionContext.getSession().createQuery("from hotel.model.user.Role role").list();
			return result;
		}
		else if (this.command.equals(getDefGuestRoleCommand())) {
			List result = executionContext.getSession().createQuery("from hotel.model.user.Role role where role.code = :code").setParameter("code", "guest").list();
			return result.get(0);
		}
		return null;
	}
	
	public static String getDefGuestRoleCommand() {
		return "getDefGuestRoleCommand";
	}

}
