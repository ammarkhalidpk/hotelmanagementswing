package hotel;

import hotel.model.user.User;

import org.apache.struts2.ServletActionContext;

public class LogoutAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if (user != null) {
			ServletActionContext.getRequest().getSession().removeAttribute("loginUser");	
		}
		return SUCCESS;
	}
	

}
