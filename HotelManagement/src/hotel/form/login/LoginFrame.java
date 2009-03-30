package hotel.form.login;

import hotel.form.main.MainFrame;
import hotel.model.user.User;
import hotel.service.CommandService;
import hotel.service.user.UserServiceCommand;
import hotel.util.MessageUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


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

		btnOk = new JButton(MessageUtil.getMessage("LoginFrame.login"));
		btnOk.setBounds(new Rectangle(101, 217, 65, 28));

		btnCad = new JButton(MessageUtil.getMessage("LoginFrame.exit"));
		btnCad.setBounds(new Rectangle(187, 217, 65, 28));

		lab1 = new JLabel(MessageUtil.getMessage("LoginFrame.welcome"));
		lab1.setForeground(Color.red);
		lab1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		lab1.setHorizontalAlignment(SwingConstants.CENTER);
		lab1.setBounds(new Rectangle(100, 10, 185, 29));
		// lab1.setEnabled(false);

		lab2 = new JLabel(MessageUtil.getMessage("LoginFrame.userAcc"));
		lab2.setForeground(Color.blue);
		lab2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		lab2.setBounds(new Rectangle(50, 81, 78, 31));

		lab3 = new JLabel(MessageUtil.getMessage("LoginFrame.userPassword"));
		lab3.setForeground(Color.blue);
		lab3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		lab3.setBounds(new Rectangle(50, 138, 78, 31));

		lab4 = new JLabel(MessageUtil.getMessage("LoginFrame.inputHint"));
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

		txtPaw.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		
		this.setTitle(MessageUtil.getMessage("LoginFrame.title"));
		this.setSize(400, 300);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);// Ĭ�Ϲرճ�������"System.exit(0);"���
		this.setResizable(false);
		this.setLocationRelativeTo(this);// ������ʾ
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
			Map con = new HashMap();
			con.put("logName", name);
			con.put("password", paw);
			User user = (User) CommandService.getInstance().execute(new UserServiceCommand("getUser", con));
			if (user == null) {
				javax.swing.JOptionPane.showMessageDialog(this, MessageUtil.getMessage("LoginFrame.loginFailHint"),
						MessageUtil.getMessage("LoginFrame.loginFailHint.title"), JOptionPane.ERROR_MESSAGE);
				txtName.requestFocus(true);
			} else {
				JOptionPane.showMessageDialog(null, MessageUtil.getMessage("LoginFrame.loginSuccessHint"));
				new MainFrame();
				this.dispose();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}