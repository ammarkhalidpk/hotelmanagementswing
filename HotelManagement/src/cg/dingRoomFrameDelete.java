package cg;

import java.awt.Dimension;
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
import javax.swing.JPanel;

import dbtools.ConnectionPool;

class dingRoomFrameDelete extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public odbc dbo = new odbc();

	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	JComboBox jComboBox1 = new JComboBox(dbo.getAllTabelName2());
	// JComboBox jComboBox2 = new JComboBox();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();

	public dingRoomFrameDelete() {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(400, 300));
		setTitle("Frame Title");
		jLabel1.setText("请选择要退的房间号：");
		jLabel1.setBounds(new Rectangle(10, 19, 130, 32));
		jComboBox1.setBounds(new Rectangle(140, 28, 102, 22));
		jButton1.setBounds(new Rectangle(29, 75, 74, 23));
		jButton1.setText("确定");
		jButton2.setBounds(new Rectangle(146, 75, 74, 23));
		jButton2.setText("取消");
		contentPane.add(jButton2);
		contentPane.add(jLabel1);
		contentPane.add(jComboBox1);
		contentPane.add(jButton1);

		jButton1.addActionListener(this);
		jButton2.addActionListener(this);

		jButton1.setActionCommand("true");
		jButton2.setActionCommand("false");

		this.setTitle("退房窗口");
		this.setSize(255, 150);
		this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("true")) {
			String it = (String) ((Vector) jComboBox1.getSelectedItem()).get(0);
			Connection con = null;
			try {
				con = ConnectionPool.getDBConnection();
				String strSql = "update footinfo set 是否入住=? where 客房编号=";
				System.out.println(strSql + it);
				PreparedStatement ps = con.prepareStatement(strSql + it);
				ps.setInt(1, 0);
				int rs = ps.executeUpdate();
				if (rs > 0) {
					JOptionPane.showMessageDialog(this, "退房成攻！", "成攻",
							JOptionPane.INFORMATION_MESSAGE);
				}
				ps.close();
				ps.close();
				con.close();
				this.setVisible(false);

			} catch (Exception sqle) {
				sqle.printStackTrace();
				JOptionPane.showMessageDialog(this, "退房失败！", "失败",
						JOptionPane.ERROR_MESSAGE);
			} finally {
				try {
					ConnectionPool.putDBConnection(con);
				} catch (SQLException e1) {
					throw new RuntimeException(e1);
				}
			}

		}
		if (str.equals("false")) {
			this.setVisible(false);
		}
	}
}