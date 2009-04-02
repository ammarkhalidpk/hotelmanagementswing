package hotel.model.dingroom;

import hotel.model.BaseModel;
import hotel.model.room.Room;
import hotel.model.user.User;
import hotel.util.MessageUtil;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class DingRoom extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("DingRoom.id"));
		fieldMapLabel.put("user", MessageUtil.getMessage("DingRoom.user"));
		fieldMapLabel.put("room", MessageUtil.getMessage("DingRoom.room"));
		fieldMapLabel.put("start", MessageUtil.getMessage("DingRoom.start"));
		fieldMapLabel.put("end", MessageUtil.getMessage("DingRoom.end"));
		fieldMapLabel.put("discount", MessageUtil.getMessage("DingRoom.discount"));
		fieldMapLabel.put("description", MessageUtil.getMessage("DingRoom.description"));
	}

	public static Map getFieldMapLabel() {
		return fieldMapLabel;
	}
	
	private long id;
	/**
	 * 谁
	 */
	private User user;
	/**
	 * 房间
	 */
	private Room room;
	/**
	 * 入住时间
	 */
	private Date start;
	/**
	 * 结算时间
	 */
	private Date end;
	/**
	 * 折扣
	 */
	private float discount;
	/**
	 * 备注
	 */
	private String description;
	
	public DingRoom() {
	}
	
	@Override
	public String getAttLabel(String attName) {
		return (String) fieldMapLabel.get(attName);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
