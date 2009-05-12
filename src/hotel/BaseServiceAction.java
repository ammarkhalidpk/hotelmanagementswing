package hotel;

import hotel.model.dingroom.DingRoom;
import hotel.model.room.Room;
import hotel.model.user.Guest;
import hotel.model.user.User;
import hotel.model.user.VIPUser;
import hotel.service.dingroom.DingRoomServiceCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class BaseServiceAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List freeRoom;
	
	private int roomNum;
	
	@SuppressWarnings("unchecked")
	public String queryRoomAction() {
		List footedDingRoom = (List) this.commandService.execute(new DingRoomServiceCommand(DingRoomServiceCommand.getFootedDingRoomCommand()));
		freeRoom = new ArrayList();
		Map map = new HashMap();
		int count = 0;
		for (Iterator iter = footedDingRoom.iterator(); iter.hasNext() && count < roomNum; ) {
			Room room = ((DingRoom)iter.next()).getRoom();
			if (!map.containsKey(room.getRoomNum())) {
				map.put(room.getRoomNum(), room);
				count++;
			}
		}
		freeRoom.addAll(map.values());
		return SUCCESS;
	}
	
	private List myHistory = new ArrayList();
	
	private List myInUse = new ArrayList();
	
	private List mySub = new ArrayList();
	
	public String myConsume() {
		User loginUser = this.getLoginUser();
		Map condition = new HashMap();
		if (loginUser instanceof Guest) {
			condition.put("idCard", ((Guest)loginUser).getIdentifier());	
		} else if (loginUser instanceof VIPUser) {
			condition.put("idCard", ((VIPUser)loginUser).getIdentifier());
		}
		
		List dataList = (List) commandService.getCurrentExecutionContext().getSession().createQuery("from hotel.model.dingroom.DingRoom dingRoom where dingRoom.user.identifier = :idCard").setParameter("idCard", condition.get("idCard")).list();
		List temp = new ArrayList(dataList);
		for (Iterator iter = temp.iterator(); iter.hasNext(); ) {
			hotel.model.dingroom.DingRoom dingRoom = (DingRoom) iter.next();
			//所有未结算的
			if (dingRoom.getEnd() == null || (dingRoom.getEnd() != null && dingRoom.getFootState().equals("未结算"))) {
				
			} else {
				iter.remove();
			}
		}
		myInUse = temp;
		temp = new ArrayList(dataList);
		for (Iterator iter = temp.iterator(); iter.hasNext(); ) {
			hotel.model.dingroom.DingRoom dingRoom = (DingRoom) iter.next();
			//所有已结算的
			if (dingRoom.getEnd() == null || (dingRoom.getEnd() != null && dingRoom.getFootState().equals("未结算"))) {
				iter.remove();
			}
		}
		myHistory = temp;
		
		temp = new ArrayList(dataList);
		for (Iterator iter = temp.iterator(); iter.hasNext(); ) {
			hotel.model.dingroom.DingRoom dingRoom = (DingRoom) iter.next();
			if (dingRoom.getStart() != null) {
				iter.remove();
			}
		}
		mySub = temp;
		
		return SUCCESS;
	}

	public List getFreeRoom() {
		return freeRoom;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public List getMyHistory() {
		return myHistory;
	}

	public List getMyInUse() {
		return myInUse;
	}

	public List getMySub() {
		return mySub;
	}

}
