package hotel.service.dingroom;

import java.util.List;
import java.util.Map;

import hotel.model.dingroom.DingRoom;
import hotel.service.AbstractServiceCommand;
import hotel.service.ExecutionContext;

public class DingRoomServiceCommand extends AbstractServiceCommand {


	public DingRoomServiceCommand(String command) {
		super(command);
	}
	
	public DingRoomServiceCommand(String command, Map condition) {
		super(command, condition);
	}

	@Override
	public Object execute(ExecutionContext executionContext) {
		if (this.command.equals(this.getAllAsArrayCommand())) {
			List all =  executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom").list();
			return this.toArray(all);
		} else if (this.command.equals(this.getSaveOrUpdateCommand())) {
			Object entity = this.condition.get("entity");
			executionContext.getSession().save(entity);
		} else if (this.command.equals(this.getDeleteCommand())) {
			long id = Long.parseLong((String) this.condition.get("id"));
			DingRoom dingRoom = (DingRoom) executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.id = :id").setParameter("id", id).uniqueResult();
			executionContext.getSession().delete(dingRoom);
		}
		return null;
	}

}
