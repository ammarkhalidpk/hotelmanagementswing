package hotel.form.context;

import hotel.model.user.User;
import hotel.service.CommandService;
import hotel.service.user.UserServiceCommand;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class UserEditFrame extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private JPasswordField oldPassword, newPassword, confirmNewPassword;
	
	private UserFrame parent;

	public UserEditFrame(UserFrame userFrame, Map condition) {
		super();
		parent = userFrame;
		user = (User) CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getByIdCommand(), condition));
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 2));
		center.add(new JLabel("旧密码"));
		oldPassword = new JPasswordField();
		oldPassword.setColumns(20);
		center.add(oldPassword);
		center.add(new JLabel("新密码"));
		newPassword = new JPasswordField();
		newPassword.setColumns(20);
		center.add(newPassword);
		center.add(new JLabel("确认新密码"));
		confirmNewPassword = new JPasswordField();
		confirmNewPassword.setColumns(20);
		center.add(confirmNewPassword);
		this.setLayout(new BorderLayout());
		this.add(center, BorderLayout.CENTER);
		
		JPanel south = new JPanel();
		JButton ok = new JButton("确定");
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		south.add(ok);
		JButton cancel = new JButton("取消");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		south.add(cancel);
		this.add(south, BorderLayout.SOUTH);
		
		this.setSize(300, 200);
		this.setLocationRelativeTo(this);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("ok")) {
			if (this.oldPassword.getText().trim().equals(this.user.getPassword())) {
				if (this.newPassword.getText().trim().equals(this.confirmNewPassword.getText().trim())) {
					this.user.setPassword(this.newPassword.getText().trim());
					Map condition = new HashMap();
					condition.put("entry", user);
					CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getSaveOrUpdateEntryCommand(), condition));
					JOptionPane.showMessageDialog(UserEditFrame.this, "修改成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					parent.refresh();
				} else {
					JOptionPane.showMessageDialog(UserEditFrame.this, "两次输入的密码不一致", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(UserEditFrame.this, "旧密码错误", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (actionCommand.equals("cancel")) {
			dispose();
			parent.refresh();
		}
		
	}

}
