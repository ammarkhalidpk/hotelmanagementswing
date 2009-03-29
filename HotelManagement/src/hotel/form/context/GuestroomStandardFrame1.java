package hotel.form.context;

import hotel.model.room.Room;
import hotel.service.CommandService;
import hotel.service.room.RoomServiceCommand;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class GuestroomStandardFrame1 extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1;
	private JComboBox jTextField2;
	private JComboBox jTextField3;
	private JTextField jTextField4;
	private JTextArea jTextArea1;
	private JButton jButton1;
	private JButton jButton2;
	private GuestroomStandardFrame parent;

	public GuestroomStandardFrame1(GuestroomStandardFrame parent) {
		this.parent = parent;
		JPanel contentPane;
		String[] str11 = { "一层", "二层", "三层" };
		String[] str22 = { "大众房", "标准间", "总统套房" };
		JLabel jLabel1 = new JLabel();
		JLabel jLabel2 = new JLabel();
		JLabel jLabel3 = new JLabel();
		JLabel jLabel4 = new JLabel();
		JLabel jLabel9 = new JLabel();
		jTextField1 = new JTextField();
		jTextField2 = new JComboBox(str22);
		jTextField3 = new JComboBox(str11);
		jTextField4 = new JTextField();
		JScrollPane jScrollPane1 = new JScrollPane();
		jTextArea1 = new JTextArea();
		jButton1 = new JButton();
		jButton2 = new JButton();

		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(327, 245));
		setTitle("aaaa");
		jLabel1.setText("客房编号");
		jLabel1.setBounds(new Rectangle(9, 17, 61, 17));
		jLabel2.setText("客房类型");
		jLabel2.setBounds(new Rectangle(165, 17, 61, 17));
		jLabel3.setText("客房位置");
		jLabel3.setBounds(new Rectangle(9, 137, 61, 17));
		jLabel4.setText("客房单价");
		jLabel4.setBounds(new Rectangle(9, 79, 61, 17));
		jLabel9.setText("备注信息");
		jLabel9.setBounds(new Rectangle(164, 79, 61, 17));
		jTextField1.setBounds(new Rectangle(73, 17, 76, 18));
		jTextField2.setBounds(new Rectangle(224, 17, 76, 18));
		jTextField3.setBounds(new Rectangle(76, 137, 76, 18));
		jTextField4.setBounds(new Rectangle(74, 79, 76, 18));
		jScrollPane1.setBounds(new Rectangle(223, 79, 72, 75));
		jButton1.setBounds(new Rectangle(74, 185, 63, 22));
		jButton1.setText("确定");
		jButton2.setBounds(new Rectangle(172, 185, 61, 21));
		jButton2.setText("取消");
		contentPane.add(jLabel1);
		contentPane.add(jTextField1);
		contentPane.add(jTextField2);
		contentPane.add(jLabel2);
		contentPane.add(jLabel9);
		contentPane.add(jScrollPane1);
		contentPane.add(jLabel3);
		contentPane.add(jTextField3);
		contentPane.add(jLabel4);
		contentPane.add(jTextField4);
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		jScrollPane1.getViewport().add(jTextArea1);

		this.setTitle("客房定制");
		this.setSize(315, 245);
		this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this);
		this.setVisible(true);

		jButton1.setActionCommand("ok");
		jButton2.setActionCommand("no");

		jButton1.addActionListener(this);
		jButton2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		if (str.equals("ok")) {
			String str1 = jTextField1.getText();
			if (str1.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入客房编号!");
				jTextField1.requestFocus(true);
				return;
			}
			int no = Integer.parseInt(str1);
			String str2 = (String) jTextField2.getSelectedItem();
			String str3 = (String) jTextField3.getSelectedItem();
			String str4 = jTextField4.getText();
			if (str4.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入客房单价!");
				jTextField4.requestFocus(true);
				return;
			}
			int price = Integer.parseInt(str4);
			String str5 = jTextArea1.getText();
			try {
				/*String strSql = "INSERT INTO guestroomBase VALUES(?,?,?,?,?,?)";
				Connection con = ConnectionPool.getDBConnection();
				PreparedStatement ps = con.prepareStatement(strSql);
				ps.setNull(1, Types.INTEGER);
				ps.setInt(2, no);
				ps.setString(3, str2);
				ps.setString(4, str3);
				ps.setInt(5, price);
				ps.setString(6, str5);
				int count = ps.executeUpdate();*/
				
				Room room = new Room();
				room.setDescription(str5);
				room.setFloor(str3);
				room.setPrise(price);
				room.setRoomNum(no + "");
				room.setType(str2);
				Map condition = new HashMap();
				condition.put("room", room);
				CommandService.getInstance().execute(new RoomServiceCommand("saveOrUpdate", condition));
				JOptionPane.showMessageDialog(null, "插入成功");
				
				/*if (count < 1) {
				} else {
					String sql = "insert into footinfo values(?,?,?,?,?,?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setNull(1, Types.INTEGER);
					ps.setInt(2, no);
					ps.setString(3, str2);
					ps.setString(4, str3);
					ps.setInt(5, price);
					ps.setFloat(6, 1);
					ps.setInt(7, 0);
					ps.setString(8, str5);
					
					count = ps.executeUpdate();
					if (count > 0) {
						JOptionPane.showMessageDialog(null, "插入成功");	
					}
				}
				ps.close();
				con.close();*/
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			}
		}
		if (str.equals("no")) {
			this.setVisible(false);
			parent.refresh();
		}

	}
}