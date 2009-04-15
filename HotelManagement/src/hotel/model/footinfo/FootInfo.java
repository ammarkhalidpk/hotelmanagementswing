package hotel.model.footinfo;

import java.util.LinkedHashMap;
import java.util.Map;

import hotel.model.BaseModel;
import hotel.model.dingroom.DingRoom;
import hotel.service.CommandService;
import hotel.util.MessageUtil;

public class FootInfo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map fieldMapLabel = new LinkedHashMap();
	static {
		fieldMapLabel.put("id", MessageUtil.getMessage("FootInfo.id"));
		fieldMapLabel.put("dingRoom", MessageUtil.getMessage("FootInfo.dingRoom"));
		fieldMapLabel.put("paied", MessageUtil.getMessage("FootInfo.paied"));
		fieldMapLabel.put("description", MessageUtil.getMessage("FootInfo.description"));
	}

	public static Map getFieldMapLabel() {
		return fieldMapLabel;
	}
	
	private long id;
	
	/**
	 * 订房信息
	 */
	private DingRoom dingRoom;
	
	/**
	 * 应付款
	 */
	private float paied;
	
	/**
	 * 备注
	 */
	private String description;
	
	public FootInfo() {
	}
	
	public FootInfo(DingRoom dingRoom) {
		this.dingRoom = dingRoom;
	}
	
	public Bill createBill() {
		Bill bill = new Bill(this);
		CommandService.getInstance().assignId(bill);
		return bill;
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

	public DingRoom getDingRoom() {
		return dingRoom;
	}

	public void setDingRoom(DingRoom dingRoom) {
		this.dingRoom = dingRoom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPaied() {
		return paied;
	}

	public void setPaied(float paied) {
		this.paied = paied;
	}

}
