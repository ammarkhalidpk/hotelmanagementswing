package cg;

import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

//结算信息模块
public class CountInfo extends JFrame implements ActionListener {

	private JButton btnInput, btnOutput, btnExit, btnLogin, btnBack;

	// 构造方法
	public CountInfo() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		Container cc = this.getContentPane();
		cc.setLayout(null);

		btnInput = new JButton("结算信息输入");
		btnInput.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		btnInput.setHorizontalAlignment(SwingConstants.CENTER);
		btnInput.setBounds(new Rectangle(82, 60, 200, 29));

		btnOutput = new JButton("结算信息查询");
		btnOutput.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		btnOutput.setHorizontalAlignment(SwingConstants.CENTER);
		btnOutput.setBounds(new Rectangle(320, 60, 200, 29));

		btnExit = new JButton("退出");
		btnExit.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		btnExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnExit.setBounds(new Rectangle(100, 160, 200, 29));

		btnLogin = new JButton("重新登陆");
		btnLogin.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		btnLogin.setHorizontalAlignment(SwingConstants.CENTER);
		btnLogin.setBounds(new Rectangle(320, 160, 200, 29));

		btnBack = new JButton("返回");
		btnBack.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		btnBack.setHorizontalAlignment(SwingConstants.CENTER);
		btnBack.setBounds(new Rectangle(200, 110, 200, 29));

		cc.add(btnInput);
		cc.add(btnOutput);
		cc.add(btnExit);
		cc.add(btnLogin);
		cc.add(btnBack);

		btnInput.addActionListener(this);
		btnLogin.addActionListener(this);
		btnExit.addActionListener(this);
		btnOutput.addActionListener(this);
		btnBack.addActionListener(this);

		btnInput.setActionCommand("input");
		btnOutput.setActionCommand("output");
		btnExit.setActionCommand("exit");
		btnLogin.setActionCommand("login");
		btnBack.setActionCommand("back");

		this.setTitle("系统设置");
		this.setSize(600, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(this);// 居中显示
		this.setVisible(true);
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("input")) {

		}
		if (str.equals("output")) {

		}
		if (str.equals("login")) {
			new LoginFrame();
			this.dispose();
		}
		if (str.equals("exit")) {
			System.exit(0);
		}
		if (str.equals("back")) {
			new MainFrame();
			this.dispose();
		}
	}
}