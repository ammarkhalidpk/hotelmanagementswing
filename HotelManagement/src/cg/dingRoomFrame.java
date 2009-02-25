package cg;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class dingRoomFrame extends JFrame implements ActionListener {
	// public dingRoom maf;
	public JButton btn1, btn2;
	// public JComboBox
	// jComboBox1,jComboBox2,jComboBox3,jComboBox4,jComboBox5,jComboBox6;
	// public JTextField jTextField1,jTextField2,jTextField3;
	// public JTextArea jTextArea1;
	public odbc dbo;
	public Connection con;
	// public dingRoom din;

	public String[] strzk = { "95", "90", "85", "80", "75", "70", "65", "60" };
	public String[] strrz = { "1", "0" };
	public String[] strph = { "101", "102", "103", "201", "202", "203", "301",
			"302" };
	public String[] strzl = { "大众房", "标准间", "总统套房", "王八" };
	public String[] strwz = { "一层", "二层", "三层" };
	public String[] strjg = { "200", "800", "1800" };

	public JComboBox jComboBox1 = new JComboBox(strph);
	public JComboBox jComboBox2 = new JComboBox(strzl);
	public JComboBox jComboBox3 = new JComboBox(strwz);
	public JComboBox jComboBox4 = new JComboBox(strjg);
	public JTextField jTextField1 = new JTextField();
	public JTextField jTextField2 = new JTextField();
	public JTextField jTextField3 = new JTextField();
	public JComboBox jComboBox5 = new JComboBox(strzk);
	public JScrollPane jScrollPane1 = new JScrollPane();
	public JTextArea jTextArea1 = new JTextArea();
	public JComboBox jComboBox6 = new JComboBox(strrz);
	public JTable tab = new JTable();

	public dingRoomFrame() {
		// super(ding,true);
		// din=ding;

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

		odbc dbo = new odbc();

		btn1 = new JButton("确定");
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

		this.setTitle("订房窗口");
		this.setSize(495, 245);
		this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		// Connection con=dbo.getConnection();

		String str = e.getActionCommand();
		if (str.equals("true")) {
			String box1 = (String) jComboBox1.getSelectedItem();
			int in = java.lang.Integer.parseInt(box1); // 1
			System.out.println(box1);
			String box2 = (String) jComboBox2.getSelectedItem(); // 2
			System.out.println(box2);
			String box3 = (String) jComboBox3.getSelectedItem(); // 3
			System.out.println(box3);
			String box4 = (String) jComboBox4.getSelectedItem();
			float flo = java.lang.Float.parseFloat(box4); // 4
			System.out.println(flo);
			String txt1 = jTextField1.getText(); // 5
			System.out.println(txt1);
			String txt2 = jTextField2.getText(); // 6
			System.out.println(txt2);
			String txt3 = jTextField3.getText(); // 7
			System.out.println(txt3);
			String box5 = (String) jComboBox5.getSelectedItem();
			float fl = java.lang.Float.parseFloat(box5); // 8
			System.out.println(fl);
			String tax1 = jTextArea1.getText(); // 9
			System.out.println(tax1);
			String box6 = (String) jComboBox6.getSelectedItem();
			int f = java.lang.Integer.parseInt(box6); // 10
			System.out.println(f);

			// Connection con = null;
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("jdbc:odbc:myOdbc", "sa", "");

				Statement sta = con.createStatement();
				String strSql = "insert into dingfangxinxi(客房编号,客房种类,客房位置,客房单价,顾客姓名,顾客身份证号码,入住日期,折扣,备注信息,是否入住) values (?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(strSql);

				ps.setInt(1, in);
				ps.setString(2, box2);
				ps.setString(3, box3);
				ps.setFloat(4, flo);
				ps.setString(5, txt1);
				ps.setString(6, txt2);
				ps.setString(7, txt3);
				ps.setFloat(8, fl);
				ps.setString(9, tax1);
				ps.setFloat(10, f);
				int count = ps.executeUpdate();
				JOptionPane.showMessageDialog(this, "登记成功！", "成功",
						JOptionPane.INFORMATION_MESSAGE);
				sta.close();
			} catch (Exception sqle) {
				sqle.printStackTrace();
				JOptionPane.showMessageDialog(this, "写入数据失败！", "失败",
						JOptionPane.ERROR_MESSAGE);
				System.out.println("出错aaa");
			}

		}
		if (str.equals("false")) {
			this.setVisible(false);
			;
		}
	}
}
