package cg;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dbtools.ConnectionPool;

public class UserdelFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lab2;
	JButton btnOk, btnCad;
	JComboBox jcb;
	public DatabaseUser dbo;

	public UserdelFrame() {

		Container me = this.getContentPane();
		me.setLayout(null);

		dbo = new DatabaseUser();

		btnOk = new JButton("确定");
		btnOk.setBounds(new Rectangle(101, 217, 65, 28));

		btnCad = new JButton("退出");
		btnCad.setBounds(new Rectangle(187, 217, 65, 28));

		lab2 = new JLabel("用户名:");
		lab2.setForeground(Color.blue);
		lab2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		lab2.setBounds(new Rectangle(50, 81, 78, 31));

		jcb = new JComboBox(dbo.getAllTabelName());
		jcb.setBounds(new Rectangle(163, 81, 114, 31));

		me.add(lab2);
		me.add(btnOk);
		me.add(btnCad);
		me.add(jcb);

		this.setTitle("");
		this.setSize(400, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(this);
		this.setVisible(true);

		btnOk.setActionCommand("ok");
		btnCad.setActionCommand("exit");

		btnOk.addActionListener(this);
		btnCad.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		String str = e.getActionCommand();
		String str1 = (String) ((Vector) jcb.getSelectedItem()).get(0);
		Connection con = null;
		if (str.equals("ok")) {
			try {
				con = ConnectionPool.getDBConnection();
				String strSql = "DELETE FROM user WHERE 用户名=?";
				PreparedStatement ps = con.prepareStatement(strSql);
				ps.setString(1, str1);
				int count = ps.executeUpdate();
				if (count < 1) {
				} else {
					JOptionPane.showMessageDialog(null, "成功删除");
					this.setVisible(false);
				}
				ps.close();
				con.close();
			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			} finally {
				try {
					ConnectionPool.putDBConnection(con);
				} catch (SQLException e1) {
					throw new RuntimeException(e1);
				}
			}
		}
		if (str.equals("exit")) {
			this.setVisible(false);
		}

	}
}