package hotel.service.room;

import hotel.model.dingroom.DingRoom;
import hotel.model.room.Room;
import hotel.service.AbstractServiceCommand;
import hotel.service.CommandService;
import hotel.service.ExecutionContext;
import hotel.service.dingroom.DingRoomServiceCommand;

import java.util.ArrayList;
import java.util.Iterator;
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
		if (this.command.equalsIgnoreCase("getByRoomNumber")) {
			String roomNum = (String) this.condition.get("roomNum");
			return executionContext.getSession().createQuery("from hotel.model.room.Room room where room.roomNum = :roomNum").setParameter("roomNum", roomNum).uniqueResult();
		} else if (this.command.equalsIgnoreCase("delete")) {
			String id = (String) this.condition.get("id");
			Object obj = get(executionContext, Long.parseLong(id));
			executionContext.getSession().delete(obj);
		} else if (this.command.equalsIgnoreCase("saveOrUpdate")) {
			Object obj = this.condition.get("room");
			executionContext.getSession().saveOrUpdate(obj);
		} else if (this.command.equalsIgnoreCase(RoomServiceCommand.getAllCommand())) {
			return executionContext.getSession().createQuery("from hotel.model.room.Room room").list();	
		} else if (this.command.equalsIgnoreCase("getAllAsArray")) {
			List all = executionContext.getSession().createQuery("from hotel.model.room.Room room").list();
			return this.toArray(all);
		} else if (this.command.equalsIgnoreCase(getInUseRoomCommand())) {
			//得到所有正在使用的房间
			List unFootDingRoom = (List) CommandService.getInstance().execute(new DingRoomServiceCommand(DingRoomServiceCommand.getUnfootDingRoomCommand()));
			List result = new ArrayList();
			for (Iterator iter = unFootDingRoom.iterator(); iter.hasNext(); ) {
				DingRoom dingRoom = (DingRoom) iter.next();
				result.add(dingRoom.getRoom());
			}
			return result;
		} else if (this.command.equalsIgnoreCase(getNotInUseRoomCommand())) {
			//得到所有未使用的房间
			List allRoom = (List) CommandService.getInstance().execute(new RoomServiceCommand(RoomServiceCommand.getAllCommand()));
			List allInUseRoom = (List) CommandService.getInstance().execute(new RoomServiceCommand(RoomServiceCommand.getInUseRoomCommand()));
			for (Iterator iter = allRoom.iterator(); iter.hasNext(); ) {
				Room room = (Room) iter.next();
				if (in(room, allInUseRoom)) {
					iter.remove();
				}
			}
			return allRoom;
		}
		return null;
	}

	private boolean in(Room room, List allInUseRoom) {
		for (Iterator iter = allInUseRoom.iterator(); iter.hasNext(); ) {
			Room item = (Room) iter.next();
			if(item.getId() == room.getId()) {
				return true;
			}
		}
		return false;
	}

	private Object get(ExecutionContext executionContext, long id) {
		return executionContext.getSession().createQuery("from hotel.model.room.Room room where room.id = :id").setParameter("id", id).uniqueResult();
	}
	
	public static String getByRoomNumberCommand() {
		return "getByRoomNumber";
	}
	
	public static String getInUseRoomCommand() {
		return "getInUseRoomCommand";
	}
	
	public static String getNotInUseRoomCommand() {
		return "getNotInUseRoomCommand";
	}

}
