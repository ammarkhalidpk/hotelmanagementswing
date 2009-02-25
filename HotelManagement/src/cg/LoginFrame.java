package cg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dbtools.DBTools;

public class LoginFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnOk, btnCad;
	private javax.swing.JLabel lab1, lab2, lab3, lab4;
	private javax.swing.JTextField txtName;
	private javax.swing.JPasswordField txtPaw;

	public LoginFrame() {
		java.awt.Container me = this.getContentPane();
		me.setLayout(null);

		btnOk = new JButton("登陆");
		btnOk.setBounds(new Rectangle(101, 217, 65, 28));

		btnCad = new JButton("退出");
		btnCad.setBounds(new Rectangle(187, 217, 65, 28));

		lab1 = new JLabel("水星酒店欢迎您");
		lab1.setForeground(Color.red);
		lab1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		lab1.setHorizontalAlignment(SwingConstants.CENTER);
		lab1.setBounds(new Rectangle(100, 10, 185, 29));
		// lab1.setEnabled(false);

		lab2 = new JLabel("用户帐号:");
		lab2.setForeground(Color.blue);
		lab2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		lab2.setBounds(new Rectangle(50, 81, 78, 31));

		lab3 = new JLabel("用户密码:");
		lab3.setForeground(Color.blue);
		lab3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		lab3.setBounds(new Rectangle(50, 138, 78, 31));

		lab4 = new JLabel("输入时,不必区分大小写！");
		lab4.setHorizontalAlignment(SwingConstants.CENTER);
		lab4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		lab4.setBounds(new Rectangle(68, 40, 250, 35));

		txtName = new JTextField();
		txtName.setBounds(new Rectangle(163, 81, 114, 31));

		txtPaw = new JPasswordField();
		txtPaw.setBounds(new Rectangle(163, 138, 114, 31));

		me.add(btnOk);
		me.add(btnCad);
		me.add(lab1);
		me.add(lab2);
		me.add(lab3);
		me.add(lab4);
		me.add(txtName);
		me.add(txtPaw);
		btnOk.addActionListener(this);
		btnCad.addActionListener(this);
		btnOk.setActionCommand("ok");
		btnCad.setActionCommand("cander");

		this.setTitle("水星酒店");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);// 默认关闭程序类似"System.exit(0);"语句
		this.setResizable(false);
		this.setLocationRelativeTo(this);// 居中显示
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String strcmd = e.getActionCommand();
		if (strcmd.equals("ok")) {
			login();
		}
		if (strcmd.equals("cander")) {
			System.exit(0);
		}

	}

	public void login() {
		String name = txtName.getText().trim();
		String paw = txtPaw.getText();
		try {

			// String strSql = "select count(*) as nubler FROM [user] WHERE 用户名
			// = '"+ name +"' and 密码 = '"+paw+"'";

			String strSql = "select * from user where 用户名 = '" + name
					+ "' and 密码 = '" + paw + "'";

			String[][] result = DBTools.executeQueryWithTableHead(strSql);

			if (result.length == 1) {
				javax.swing.JOptionPane.showMessageDialog(this, "您的帐号或密码错误!",
						"提示", JOptionPane.ERROR_MESSAGE);
				txtName.requestFocus(true);
			} else {
				JOptionPane.showMessageDialog(null, "登陆成功");
				new MainFrame();
				this.dispose();// 弹出主窗体后登陆窗体自动关闭
			}
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}