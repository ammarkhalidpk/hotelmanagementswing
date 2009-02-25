package cg;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dbtools.ConnectionPool;
import dbtools.DBTools;

class jiesuan extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel jLabel3 = new JLabel();
	public JTextField jTextField1 = new JTextField();
	public JLabel jLabel6 = new JLabel();
	public JLabel jLabel5 = new JLabel();
	public JButton jButton1 = new JButton();
	public JButton jButton2 = new JButton();
	public JButton jButton3 = new JButton();
	public float rel;

	public jiesuan() {

		String stradd = String.valueOf(footInfo.addname);
		System.out.println("第一次是" + footInfo.addname);
		jLabel3.setText(stradd);

		JPanel contentPane;
		JLabel jLabel1 = new JLabel();
		JLabel jLabel2 = new JLabel();
		JLabel jLabel4 = new JLabel();
		JLabel jLabel5 = new JLabel();

		jButton1.addActionListener(this);
		jButton2.addActionListener(this);
		jButton3.addActionListener(this);

		jButton1.setActionCommand("1");
		jButton2.setActionCommand("2");
		jButton3.setActionCommand("3");

		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(348, 280));
		setTitle("结算窗口");
		jLabel1.setFont(new java.awt.Font("宋体", Font.BOLD, 18));
		jLabel1.setDisplayedMnemonic('0');
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel1.setText("欢迎您再次光临本酒店");
		jLabel1.setBounds(new Rectangle(38, 21, 272, 38));
		jLabel2.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel2.setText("您应该支付：");
		jLabel2.setBounds(new Rectangle(34, 65, 104, 33));
		jLabel3.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
		jLabel3.setBorder(BorderFactory.createLoweredBevelBorder());
		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel3.setBounds(new Rectangle(171, 65, 140, 33));
		jLabel4.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
		jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel4.setText("您实付：");
		jLabel4.setBounds(new Rectangle(34, 114, 104, 33));
		jTextField1.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		jTextField1.setBounds(new Rectangle(171, 112, 140, 33));
		jLabel5.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
		jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel5.setText("应找您：");
		jLabel5.setBounds(new Rectangle(34, 163, 104, 33));
		jLabel6.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
		jLabel6.setBorder(BorderFactory.createLoweredBevelBorder());
		jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel6.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel6.setBounds(new Rectangle(171, 159, 140, 33));
		jButton3.setText("结算");
		jButton3.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
		jButton3.setBounds(new Rectangle(48, 216, 80, 24));
		jButton1.setBounds(new Rectangle(140, 216, 80, 24));
		jButton1.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
		jButton1.setText("结帐");
		jButton2.setBounds(new Rectangle(232, 216, 80, 24));
		jButton2.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
		jButton2.setText("取消");
		contentPane.add(jLabel1);
		contentPane.add(jButton2);
		contentPane.add(jLabel2);
		contentPane.add(jLabel3);
		contentPane.add(jTextField1);
		contentPane.add(jLabel4);
		contentPane.add(jLabel5);
		contentPane.add(jLabel6);
		contentPane.add(jButton1);
		contentPane.add(jButton3);

		this.setSize(380, 300);
		this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("2")) {
			this.setVisible(false);
		}
		if (str.equals("1")) {
			Connection con = null;
			try {
				con = ConnectionPool.getDBConnection();
				String strSql = "insert into xiaofei(账单流水号,退房日期,结算金额) values (?,?,?)";

				PreparedStatement ps = con.prepareStatement(strSql);
				ps.setString(1, footInfo.str);
				ps.setString(2, footInfo.strtxt1);
				ps.setString(3, jLabel3.getText());

				int count = ps.executeUpdate();
				
				if (count > 0) {
					String sql = "update dingfangxinxi set 使用状态='结束' where 账单流水号=?";
					ps = con.prepareStatement(sql);
					ps.setString(1, footInfo.str);
					count = ps.executeUpdate();
					
					String sqlDingFang = "select 客房编号 from dingfangxinxi where 账单流水号=" + footInfo.str;
					String[][] roomnum = DBTools.executeQueryWithTableHead(sqlDingFang); 
					
					String sqlFoo = "update footinfo set 是否入住=0 where 客房编号=?";
					ps = con.prepareStatement(sqlFoo);
					ps.setInt(1, Integer.parseInt(roomnum[1][0]));
					count = ps.executeUpdate();
					
					if (count > 0) {
						JOptionPane.showMessageDialog(this, "结帐完毕", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						jButton1.enable(false);
						this.dispose();
					}
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("结账时出错了！");
			} finally {
				try {
					ConnectionPool.putDBConnection(con);
				} catch (SQLException e1) {
					throw new RuntimeException(e1);
				}
			}
		}
		if (str.equals("3")) {
			rel = Float.parseFloat(jTextField1.getText()) - (footInfo.addname);
			System.out.println("addname是" + footInfo.addname);
			String str1 = String.valueOf(rel);
			jLabel6.setText(str1);
			jButton3.enable(false);
		}
	}
}