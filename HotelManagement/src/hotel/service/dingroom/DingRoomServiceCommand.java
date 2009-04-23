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
		if (this.command.equals(getByIdCommand())) {
			long id = Long.parseLong((String) this.condition.get("id"));
			DingRoom dingRoom = (DingRoom) executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.id = :id").setParameter("id", id).uniqueResult();
			return dingRoom;
		} else if (this.command.equals(getAllAsArrayCommand())) {
			List all =  executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom").list();
			return this.toArray(all);
		} else if (this.command.equals(getUpdateCommand())) {
			Object entity = this.condition.get("entity");
			executionContext.getSession().update(entity);
		} else if (this.command.equals(getSaveOrUpdateEntryCommand())) {
			Object entity = this.condition.get("entity");
			executionContext.getSession().saveOrUpdate(entity);
		} else if (this.command.equals(getDeleteByIdCommand())) {
			long id = Long.parseLong((String) this.condition.get("id"));
			DingRoom dingRoom = (DingRoom) executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.id = :id").setParameter("id", id).uniqueResult();
			executionContext.getSession().delete(dingRoom);
		} else if (this.command.equals(getFootedDingRoomCommand())) {
			return executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.end is not null and dingRoom.footState = :footState").setParameter("footState", "已结算").list();
		} else if(this.command.equals(getEndedAndUnfootDingRoomCommand())) {
			return executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.end is not null and dingRoom.footState = :footState").setParameter("footState", "未结算").list();
		} else if (this.command.equals(getUnfootDingRoomCommand())) {
			return executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where (dingRoom.end is not null and dingRoom.footState = :footState) or dingRoom.end is null").setParameter("footState", "未结算").list();
		} else if (this.command.equals(getUnfootDingRoomAsArrayCommand())) {
			List result = executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where (dingRoom.end is not null and dingRoom.footState = :footState) or dingRoom.end is null").setParameter("footState", "未结算").list();
			return this.toArray(result);
		} else if (this.command.equals(getFootedDingRoomAsArrayCommand())) {
			List result = executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.end is not null and dingRoom.footState = :footState").setParameter("footState", "已结算").list();
			return this.toArray(result);
		} else if (this.command.equals(getUnCheckOutDingRoomAsArrayCommand())) {
			return this.toArray(executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.end is null").list());
		} else if (this.command.equals(getCheckOutDingRoomAsArrayCommand())) {
			return this.toArray(executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.end is not null and dingRoom.footState = :footState").setParameter("footState", "未结算").list());
		} else if (this.command.equals(getUnfootDingRoomByRoomNumCommand())) {
			String roomNum = (String) this.condition.get("roomNum");
			return executionContext.getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.end is not null and dingRoom.footState = :footState and dingRoom.room.roomNum = :roomNum").setParameter("roomNum", roomNum).setParameter("footState", "未结算").list();
		}
		return null;
	}
	
	
	public static String getFootedDingRoomCommand() {
		return "getFootedDingRoomCommand";
	}
	
	public static String getUnfootDingRoomCommand() {
		return "getUnfootDingRoomCommand";
	}

	public static String getUnfootDingRoomAsArrayCommand() {
		return "getUnfootDingRoomAsArrayCommand";
	}
	
	public static String getFootedDingRoomAsArrayCommand() {
		return "getFootedDingRoomAsArrayCommand";
	}

	public static String getUpdateCommand() {
		return "getUpdateCommand";
	}

	public static String getUnCheckOutDingRoomAsArrayCommand() {
		return "getUnCheckOutDingRoomAsArrayCommand";
	}

	public static String getCheckOutDingRoomAsArrayCommand() {
		return "getCheckOutDingRoomAsArrayCommand";
	}

	public static String getUnfootDingRoomByRoomNumCommand() {
		return "getUnfootDingRoomByRoomNumCommand";
	}
	
	public static String getEndedAndUnfootDingRoomCommand() {
		return "getEndedAndUnfootDingRoomCommand";
	}

}
