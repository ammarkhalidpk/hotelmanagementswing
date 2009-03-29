package hotel.model.room;

import hotel.model.BaseModel;

import java.util.LinkedHashMap;
import java.util.Map;

import util.MessageUtil;

public class Room extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("Room.id"));
		fieldMapLabel.put("roomNum", MessageUtil.getMessage("Room.roomNum"));
		fieldMapLabel.put("type", MessageUtil.getMessage("Room.type"));
		fieldMapLabel.put("floor", MessageUtil.getMessage("Room.floor"));
		fieldMapLabel.put("prise", MessageUtil.getMessage("Room.prise"));
		fieldMapLabel.put("description", MessageUtil.getMessage("Room.description"));
	}
	
	protected long id;
	
	private String roomNum;
	
	private String type;
	
	private String floor;
	
	private float prise;
	
	protected String description;
	
	public Room() {
	}
	
	@Override
	public String getAttLabel(String attName) {
		return (String) fieldMapLabel.get(attName);
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public float getPrise() {
		return prise;
	}

	public void setPrise(float prise) {
		this.prise = prise;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static Map getFieldMapLabel() {
		return fieldMapLabel;
	}

}
