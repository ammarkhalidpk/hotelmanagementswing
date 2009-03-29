package hotel.service.room;

import hotel.service.AbstractServiceCommand;
import hotel.service.ExecutionContext;

import java.util.List;
import java.util.Map;

public class RoomServiceCommand extends AbstractServiceCommand {

	public RoomServiceCommand(String command) {
		super(command);
	}
	
	public RoomServiceCommand(String command, Map condition) {
		super(command, condition);
	}

	public Object execute(ExecutionContext executionContext) {
		if (this.command.equalsIgnoreCase("get")) {
			
		} else if (this.command.equalsIgnoreCase("delete")) {
			String id = (String) this.condition.get("id");
			Object obj = get(executionContext, Long.parseLong(id));
			executionContext.getSession().delete(obj);
			
		} else if (this.command.equalsIgnoreCase("saveOrUpdate")) {
			Object obj = this.condition.get("room");
			executionContext.getSession().saveOrUpdate(obj);
		} else if (this.command.equalsIgnoreCase("getAll")) {
			return executionContext.getSession().createQuery("from hotel.model.room.Room room").list();	
		} else if (this.command.equalsIgnoreCase("getAllAsArray")) {
			List all = executionContext.getSession().createQuery("from hotel.model.room.Room room").list();
			return this.toArray(all);
		}
		return null;
	}

	private Object get(ExecutionContext executionContext, long id) {
		return executionContext.getSession().createQuery("from hotel.model.room.Room room where room.id = :id").setParameter("id", id).uniqueResult();
	}

}
