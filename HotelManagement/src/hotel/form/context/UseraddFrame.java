package hotel.form.context;

import hotel.model.user.User;
import hotel.service.CommandService;
import hotel.service.user.UserServiceCommand;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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

		lab3 = new JLabel("类  型");
		lab3.setForeground(Color.BLUE);
		lab3.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		lab3.setHorizontalAlignment(SwingConstants.CENTER);
		lab3.setBounds(new Rectangle(21, 115, 80, 29));

		// cbo = new JComboBox();
		cbo.addItem("普通用户");
		cbo.addItem("超级用户");
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

	public void actionPerformed(ActionEvent e) {
		String strcmd = e.getActionCommand();
		if (strcmd.equals("ok")) {

			String txtName1 = txtName.getText();
			String pwd1 = pwd.getText();
			String cbo1 = (String) cbo.getSelectedItem();
			try {
				
				User user = new User();
				user.setName(cbo1);
				user.setLoginName(txtName1);
				user.setPassword(pwd1);
				user.setDescription("");
				Map condition = new HashMap();
				condition.put("user", user);
				CommandService.getInstance().execute(new UserServiceCommand("saveOrUpdateUser", condition));
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