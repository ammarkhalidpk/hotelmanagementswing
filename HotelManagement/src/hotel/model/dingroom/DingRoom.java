package hotel.model.dingroom;

import hotel.model.BaseModel;
import hotel.model.footinfo.FootInfo;
import hotel.model.room.Room;
import hotel.model.user.Guest;
import hotel.model.user.User;
import hotel.service.CommandService;
import hotel.util.MessageUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
		fieldMapLabel.put("create", MessageUtil.getMessage("DingRoom.create"));
		fieldMapLabel.put("start", MessageUtil.getMessage("DingRoom.start"));
		fieldMapLabel.put("end", MessageUtil.getMessage("DingRoom.end"));
		fieldMapLabel.put("discount", MessageUtil.getMessage("DingRoom.discount"));
		fieldMapLabel.put("footState", MessageUtil.getMessage("DingRoom.footState"));
		fieldMapLabel.put("pledge", MessageUtil.getMessage("DingRoom.pledge"));
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
	 * 订房时间
	 */
	private Date create;
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
	 * 是否结算
	 */
	private String footState = "";
	/**
	 * 押金
	 */
	private float pledge;
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
	
	public FootInfo createFootInfo() {
		FootInfo footInfo = new FootInfo(this);
		int days = this.getDays();
		float paied = this.room.getPrise() * days * this.discount;
		paied = paied - this.getPledge();
		footInfo.setPaied(paied);
		return footInfo;
	}
	
	public int getDays() {
		long t = this.end.getTime() - this.start.getTime();
		int days = (int) (t/60/60/1000/24);
		return days;
	}
	
	private static Map descriptionMapDiscount = new HashMap();
	static {
		descriptionMapDiscount.put("4级VIP", 0.8 + "");
		descriptionMapDiscount.put("3级VIP", 0.85 + "");
		descriptionMapDiscount.put("2级VIP", 0.9 + "");
		descriptionMapDiscount.put("1级VIP", 0.95 + "");
	}
	private float getDiscountByUser(User user) {
		if (user instanceof Guest) {
			return 1;
		}
		return Float.parseFloat((String) descriptionMapDiscount.get(user.getDescription()));
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
		setDiscount(getDiscountByUser(user));
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

	public String getFootState() {
		return footState;
	}

	public void setFootState(String footState) {
		this.footState = footState;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public float getPledge() {
		return pledge;
	}

	public void setPledge(float pledge) {
		this.pledge = pledge;
	}

}
