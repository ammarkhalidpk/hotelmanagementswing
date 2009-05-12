package hotel.form.context;

import hotel.model.user.Guest;
import hotel.model.user.Role;
import hotel.model.user.User;
import hotel.service.CommandService;
import hotel.service.user.RoleServiceCommand;
import hotel.service.user.UserServiceCommand;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class UseraddFrame extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnOk, btnexit;
	private javax.swing.JLabel lab1, lab2, lab3;


	public JTextField txtName = new JTextField();
	public JPasswordField pwd = new JPasswordField();
	public JComboBox cbo = new JComboBox();
	
	private UserFrame parent;

	public UseraddFrame(UserFrame userFrame) {
		this.parent = userFrame;
		this.setModal(true);

		java.awt.Container me = this.getContentPane();

		me.setLayout(null);

		lab1 = new JLabel("新用户");
		lab1.setForeground(Color.BLUE);
		lab1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		lab1.setHorizontalAlignment(SwingConstants.CENTER);
		lab1.setBounds(new Rectangle(21, 15, 80, 29));

		// txtName = new JTextField();
		txtName.setForeground(Color.BLUE);
		txtName.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		txtName.setBounds(new Rectangle(100, 15, 100, 29));

		lab2 = new JLabel("新密码");
		lab2.setForeground(Color.BLUE);
		lab2.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		lab2.setHorizontalAlignment(SwingConstants.CENTER);
		lab2.setBounds(new Rectangle(21, 65, 80, 29));

		// pwd = new JPasswordField();
		pwd.setForeground(Color.BLUE);
		pwd.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		pwd.setBounds(new Rectangle(100, 65, 100, 29));

		lab3 = new JLabel("角    色");
		lab3.setForeground(Color.BLUE);
		lab3.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		lab3.setHorizontalAlignment(SwingConstants.CENTER);
		lab3.setBounds(new Rectangle(21, 115, 80, 29));

		// cbo = new JComboBox();
		initRoleSelector(cbo);
		cbo.setBounds(new Rectangle(100, 115, 100, 29));

		btnOk = new JButton("确定");
		btnOk.setForeground(Color.BLUE);
		btnOk.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		btnOk.setBounds(new Rectangle(30, 165, 70, 25));

		btnexit = new JButton("退出");
		btnexit.setForeground(Color.BLUE);
		btnexit.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		btnexit.setBounds(new Rectangle(120, 165, 70, 25));

		me.add(lab1);
		me.add(txtName);
		me.add(lab2);
		me.add(pwd);
		me.add(lab3);
		me.add(cbo);
		me.add(btnOk);
		me.add(btnexit);

		btnOk.addActionListener(this);
		btnOk.setActionCommand("ok");

		btnexit.addActionListener(this);
		btnexit.setActionCommand("exit");

		this.setTitle("添加用户");
		this.setSize(300, 260);
		this.setResizable(false);
		// this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this);// 居中显示
		this.setVisible(true);
	}

	private void initRoleSelector(JComboBox cbo) {
		List roles = (List) CommandService.getInstance().execute(new RoleServiceCommand(RoleServiceCommand.getAllCommand()));
		for (Iterator iter = roles.iterator(); iter.hasNext(); ) {
			Role role = (Role) iter.next();
			if (role.getCode().equals("admin")) {
				cbo.addItem(role);	
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		String strcmd = e.getActionCommand();
		if (strcmd.equals("ok")) {

			String txtName1 = txtName.getText();
			String pwd1 = pwd.getText();
			if (pwd1 == null || pwd1.equals("")) {
				JOptionPane.showMessageDialog(this, "密码不能为空！", "错误提示",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Role role = (Role) cbo.getSelectedItem();
			try {
				if (role.getCode().equals("admin")) {
					User user = new User();
					user.setName(txtName1);
					user.setLoginName(txtName1);
					user.setPassword(pwd1);
					user.setDescription("");
					user.setRole(role);
					Map condition = new HashMap();
					condition.put("entry", user);
					CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getSaveOrUpdateEntryCommand(), condition));
				}
				else if (role.getCode().equals("guest")) {
					Guest guest = new Guest();
					guest.setName(txtName1);
					guest.setLoginName(txtName1);
					guest.setPassword(pwd1);
					guest.setDescription("");
					guest.setRole(role);
					Map condition = new HashMap();
					condition.put("entry", guest);
					CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getSaveOrUpdateEntryCommand(), condition));
				}
				
				JOptionPane.showMessageDialog(this, "登记成功！", "成功",
							JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ea) {
				ea.printStackTrace();
				JOptionPane.showMessageDialog(this, "写入数据失败！", "失败",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (strcmd.equals("exit")) {
			this.setVisible(false);
			this.parent.refresh();
		}
	}

}