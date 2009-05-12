package hotel;

import org.apache.struts2.ServletActionContext;

import hotel.model.user.User;
import hotel.service.CommandService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected CommandService commandService;

	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}
	
	protected User getLoginUser() {
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}

}
