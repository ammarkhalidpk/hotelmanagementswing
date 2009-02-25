package cg;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class dingRoomFrameUpdate extends JFrame implements ActionListener {
	public dingRoom maf;
	public JButton btn1, btn2;
	public JComboBox jComboBox1, jComboBox2, jComboBox3, jComboBox4,
			jComboBox5, jComboBox6;
	public JTextField jTextField1, jTextField2, jTextField3;
	public JTextArea jTextArea1;
	public odbc dbo;
	public Connection con;

	public dingRoomFrameUpdate() {
		String[] strzk = { "95", "90", "85", "80", "75", "70", "65", "60" };
		String[] strrz = { "是", "否" };
		String[] strph = { "A101", "A102", "A103", "B101", "B102", "B103",
				"FFF" };
		String[] strzl = { "大众房", "标准间", "总统套房" };
		String[] strwz = { "一层", "二层", "三层" };
		String[] strjg = { "200", "800", "1800" };
		JPanel contentPane;
		JLabel jLabel1 = new JLabel();
		JLabel jLabel2 = new JLabel();
		JLabel jLabel3 = new JLabel();
		JLabel jLabel4 = new JLabel();
		JLabel jLabel5 = new JLabel();
		JLabel jLabel6 = new JLabel();
		JLabel jLabel7 = new JLabel();
		JLabel jLabel8 = new JLabel();
		JLabel jLabel9 = new JLabel();
		JLabel jLabel10 = new JLabel();
		JComboBox jComboBox1 = new JComboBox(strph);
		JComboBox jComboBox2 = new JComboBox(strzl);
		JComboBox jComboBox3 = new JComboBox(strwz);
		JComboBox jComboBox4 = new JComboBox(strjg);
		JTextField jTextField1 = new JTextField();
		JTextField jTextField2 = new JTextField();
		JTextField jTextField3 = new JTextField();
		JComboBox jComboBox5 = new JComboBox(strzk);
		JScrollPane jScrollPane1 = new JScrollPane();
		JTextArea jTextArea1 = new JTextArea();
		JComboBox jComboBox6 = new JComboBox(strrz);
		JTable tab = new JTable();
		odbc dbo = new odbc();

		btn1 = new JButton("修改");
		btn2 = new JButton("取消");

		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(493, 245));
		setTitle("aaaa");
		jLabel1.setText("客房编号");
		jLabel1.setBounds(new Rectangle(9, 17, 61, 17));
		jLabel2.setText("客房种类");
		jLabel2.setBounds(new Rectangle(165, 17, 61, 17));
		jLabel3.setText("客房位置");
		jLabel3.setBounds(new Rectangle(320, 17, 61, 17));
		jLabel4.setText("客房单价");
		jLabel4.setBounds(new Rectangle(10, 79, 61, 17));
		jLabel5.setText("顾客姓名");
		jLabel5.setBounds(new Rectangle(166, 79, 61, 17));
		jLabel6.setText("顾客身份证号码");
		jLabel6.setBounds(new Rectangle(303, 79, 104, 17));
		jLabel7.setText("入住日期");
		jLabel7.setBounds(new Rectangle(10, 140, 61, 17));
		jLabel8.setText("折扣");
		jLabel8.setBounds(new Rectangle(159, 140, 61, 17));
		jLabel9.setText("备注信息");
		jLabel9.setBounds(new Rectangle(253, 140, 61, 17));
		jLabel10.setText("是否入住");
		jLabel10.setBounds(new Rectangle(381, 140, 61, 17));
		jComboBox1.setBounds(new Rectangle(73, 17, 76, 18));
		jComboBox2.setBounds(new Rectangle(224, 17, 76, 18));
		jComboBox3.setBounds(new Rectangle(385, 17, 76, 18));
		jComboBox4.setBounds(new Rectangle(74, 82, 76, 18));
		jTextField1.setBounds(new Rectangle(223, 82, 76, 18));
		jTextField2.setBounds(new Rectangle(409, 82, 76, 18));
		jTextField3.setBounds(new Rectangle(71, 138, 76, 18));
		jComboBox5.setBounds(new Rectangle(188, 138, 58, 18));
		jScrollPane1.setBounds(new Rectangle(304, 138, 72, 68));
		jComboBox6.setBounds(new Rectangle(434, 138, 48, 18));
		btn1.setBounds(new Rectangle(10, 180, 61, 22));
		btn2.setBounds(new Rectangle(85, 180, 61, 22));

		contentPane.add(jLabel9);
		contentPane.add(jLabel10);
		contentPane.add(jScrollPane1);
		jScrollPane1.getViewport().add(jTextArea1);
		contentPane.add(tab);
		contentPane.add(jComboBox6);
		contentPane.add(jComboBox5);
		contentPane.add(jLabel8);
		contentPane.add(jLabel1);
		contentPane.add(jLabel4);
		contentPane.add(jLabel7);
		contentPane.add(jComboBox1);
		contentPane.add(jComboBox2);
		contentPane.add(jComboBox3);
		contentPane.add(jLabel3);
		contentPane.add(jComboBox4);
		contentPane.add(jTextField3);
		contentPane.add(jLabel2);
		contentPane.add(jLabel5);
		contentPane.add(jTextField1);
		contentPane.add(jTextField2);
		contentPane.add(jLabel6);
		contentPane.add(btn1);
		contentPane.add(btn2);

		btn1.addActionListener(this);
		btn2.addActionListener(this);

		btn1.setActionCommand("true");
		btn2.setActionCommand("false");

		this.setTitle("修改窗口");
		this.setSize(495, 245);
		this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		String str = e.getActionCommand();
		if (str.equals("false")) {
			this.setVisible(false);
		}

		if (str.equals("true")) {
			try {
				Statement sta = con.createStatement();
				String strSql = "SELECT * FROM dingfangxinxi";
				ResultSet rs = sta.executeQuery(strSql);
				while (rs.next()) {
					String str1 = rs.getString(1);
				}

			} catch (Exception ce) {
				ce.printStackTrace();
			}
		}
		/*
		 * String strsql="update dingfangxinxi set 客房编号=? where 客房位置=?";
		 * PreparedStatement ps=con.prepareStatement(strsql);
		 * 
		 * 
		 * 
		 * String box1=(String)jComboBox1.setSelectedItem(); String
		 * box2=(String)jComboBox2.setSelectedItem(); String
		 * box3=(String)jComboBox3.setSelectedItem(); String
		 * box4=(String)jComboBox4.setSelectedItem(); String
		 * txt1=jTextField1.setText(); String txt2=jTextField2.setText(); String
		 * txt3=jTextField3.setText(); String
		 * box5=(String)jComboBox5.setSelectedItem(); String
		 * tax1=jTextArea1.setText(); String
		 * box6=(String)jComboBox6.setSelectedItem();
		 */
	}
}