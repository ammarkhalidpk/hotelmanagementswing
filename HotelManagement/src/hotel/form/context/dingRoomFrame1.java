package hotel.form.context;

import hotel.dbtools.DBTools;
import hotel.form.main.MainFrame;
import hotel.model.room.Room;
import hotel.model.user.Guest;
import hotel.model.user.User;
import hotel.model.user.VIPUser;
import hotel.service.CommandService;
import hotel.service.dingroom.DingRoomServiceCommand;
import hotel.service.room.RoomServiceCommand;
import hotel.service.user.UserServiceCommand;
import hotel.util.DateUtil;
import hotel.util.ui.mydatechooser.MyDateChooser;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class dingRoomFrame1 extends JDialog implements ActionListener, ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] str = { "1", "0" };
	JLabel jLabel1 = new JLabel();
	JComboBox jComboBox1 = new JComboBox();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JLabel jLabel7 = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel jLabel10 = new JLabel();
	JLabel jLabel12 = new JLabel();
	JLabel jLabel14 = new JLabel();
	JLabel jLabel15 = new JLabel();
	JComboBox jComboBox2 = new JComboBox(str);
	JLabel jLabel16 = new JLabel();
	JTextArea jTextArea1 = new JTextArea();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JLabel jLabel17 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	JTextField dingRoomDate = new JTextField();
	JLabel jLabel9 = new JLabel();
	JTextField jTextField4 = new JTextField();
	private Room selectedRoom;
	private DingRoom parent;

	public dingRoomFrame1(DingRoom parent) {
		this.parent = parent;
		this.setLayout(null);
		this.setModal(true);
		setSize(new Dimension(515, 252));
		this.setTitle("订房管理");
		jLabel1.setText("客房编号");
		jLabel1.setBounds(new Rectangle(17, 14, 53, 25));
		jComboBox1.setBounds(new Rectangle(74, 20, 70, 22));
		List rooms = (List) CommandService.getInstance().execute(new RoomServiceCommand(RoomServiceCommand.getNotInUseRoomCommand()));
		this.addRooms(jComboBox1, rooms);
		jComboBox1.addItem("选择房间");
		jComboBox1.setSelectedItem("选择房间");
		jLabel2.setText("客房种类");
		jLabel2.setBounds(new Rectangle(166, 14, 53, 25));
		jLabel3.setBorder(BorderFactory.createLoweredBevelBorder());
		jLabel3.setBounds(new Rectangle(225, 20, 70, 22));
		jLabel4.setText("客房位置");
		jLabel4.setBounds(new Rectangle(329, 14, 53, 25));
		jLabel5.setBorder(BorderFactory.createLoweredBevelBorder());
		jLabel5.setBounds(new Rectangle(393, 20, 82, 22));
		jLabel6.setText("客房单价");
		jLabel6.setBounds(new Rectangle(16, 69, 53, 25));
		jLabel7.setBorder(BorderFactory.createLoweredBevelBorder());
		jLabel7.setBounds(new Rectangle(75, 69, 70, 22));
		jLabel8.setText("顾客姓名");
		jLabel8.setBounds(new Rectangle(165, 69, 53, 25));
		jLabel10.setText("顾客身份证号码");
		jLabel10.setBounds(new Rectangle(307, 69, 94, 25));
		jLabel12.setDisplayedMnemonic('0');
		jLabel12.setHorizontalAlignment(SwingConstants.LEFT);
		jLabel12.setText("入住日期");
		jLabel12.setBounds(new Rectangle(15, 122, 53, 25));
		jLabel14.setBorder(null);
		jLabel14.setText("折扣");
		jLabel14.setBounds(new Rectangle(151, 122, 34, 22));
		jLabel15.setBorder(BorderFactory.createLoweredBevelBorder());
		jLabel15.setBounds(new Rectangle(191, 122, 51, 21));
		jComboBox2.setBounds(new Rectangle(447, 122, 53, 22));
		jLabel16.setText("备注信息");
		jLabel16.setBounds(new Rectangle(251, 122, 55, 25));
		jTextArea1.setBounds(new Rectangle(303, 123, 65, 59));
		jButton1.setBounds(new Rectangle(15, 181, 60, 23));
		jButton1.setText("登记");
		jButton2.setBounds(new Rectangle(91, 181, 60, 23));
		jButton2.setText("取消");
		jLabel17.setText("是否入住");
		jLabel17.setBounds(new Rectangle(387, 122, 53, 25));
		jTextField1.setBounds(new Rectangle(221, 69, 70, 22));
		jTextField2.setBounds(new Rectangle(404, 69, 94, 22));
		dingRoomDate.setBounds(new Rectangle(75, 124, 70, 22));
		dingRoomDate.setText(DateUtil.getNowAsString());
		dingRoomDate.setToolTipText("双击选择时间");
		dingRoomDate.setEnabled(false);
		dingRoomDate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					MyDateChooser dateChooser = new MyDateChooser(null,
							dingRoomDate, "yyyymmddhhttss",
							"yyyy-MM-dd hh:mm:ss");
					dateChooser.setVisible(true);
				}
			}
		});
		jLabel9.setText("账单号");
		jLabel9.setBounds(new Rectangle(380, 156, 41, 25));
		jTextField4.setBounds(new Rectangle(429, 158, 70, 22));
		{
			//生成流水号
			String[][] result = null;
			try {
				//result = DBTools.executeQueryWithTableHead("select * from dingfangxinxi");
				//jTextField4.setText(result.length + "");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		this.add(jLabel7);
		this.add(jLabel3);
		this.add(jLabel8);
		this.add(jLabel10);
		this.add(jLabel14);
		this.add(jLabel15);
		this.add(jLabel16);
		this.add(jLabel5);
		this.add(jLabel4);
		this.add(jLabel1);
		this.add(jComboBox1);
		this.add(jLabel2);
		this.add(jLabel6);
		this.add(jLabel12);
		this.add(jTextField1);
		this.add(dingRoomDate);
		this.add(jTextField2);
		this.add(jComboBox2);
		this.add(jLabel17);
		this.add(jTextField4);
		this.add(jButton1);
		this.add(jButton2);
		this.add(jTextArea1);
		this.add(jLabel9);

		jButton1.addActionListener(this);
		jButton2.addActionListener(this);

		jComboBox1.addItemListener(this);

		jButton1.setActionCommand("true");
		jButton2.setActionCommand("false");
		
		this.setLocationRelativeTo(this);
		
		this.setVisible(true);
	}

	private void addRooms(JComboBox comboBox, List rooms) {
		for (Iterator iter = rooms.iterator(); iter.hasNext(); ) {
			Room room = (Room) iter.next();
			comboBox.addItem(room.getRoomNum());
		}
	}

	public void itemStateChanged(ItemEvent i) {
		int in = i.getStateChange();
		if (in == ItemEvent.SELECTED) {
			try {
				this.jComboBox1.removeItem("选择房间");
				int a = jComboBox1.getSelectedIndex() + 1; // 得到选择的行
				String strno = (String) jComboBox1.getSelectedItem(); // 得到下拉元素
				this.selectedRoom = this.getSelectedRoom(strno);

				jLabel3.setText(this.selectedRoom.getType());
				jLabel5.setText(this.selectedRoom.getFloor());
				jLabel7.setText(this.selectedRoom.getPrise() + "");
				jLabel15.setText("1");

			} catch (Exception sqle) {
				sqle.printStackTrace();
				JOptionPane.showMessageDialog(this, "写入数据失败！", "失败",
						JOptionPane.ERROR_MESSAGE);
				System.out.println("妈呀出错了！");
			}

		}
	}

	private Room getSelectedRoom(String strno) {
		Map condition = new HashMap();
		condition.put("roomNum", strno);
		return (Room) CommandService.getInstance().execute(new RoomServiceCommand(RoomServiceCommand.getByRoomNumberCommand(), condition));
	}

	public void actionPerformed(ActionEvent e) {

		String str = e.getActionCommand();
		if (str.equals("true")) {
			Object sele = jComboBox1.getSelectedItem();
			if (this.selectedRoom == null) {
				JOptionPane.showMessageDialog(this, "请选择房间！", "失败",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String txt1 = jTextField1.getText(); // 5
			System.out.println(txt1);
			String txt2 = jTextField2.getText(); // 6
			/*/
			System.out.println(str);
			String box2 = (String) jLabel3.getText(); // 2
			System.out.println(box2);
			String box3 = (String) jLabel5.getText(); // 3
			System.out.println(box3);
			String box4 = (String) jLabel7.getText();
			float flo = Float.parseFloat(box4); // 4
			System.out.println(flo);
			String txt1 = jTextField1.getText(); // 5
			System.out.println(txt1);
			String txt2 = jTextField2.getText(); // 6
			System.out.println(txt2);
			String txt3 = jTextField3.getText(); // 7
			System.out.println(txt3);
			String box5 = (String) jLabel15.getText();
			float fl = Float.parseFloat(box5); // 8
			System.out.println(fl);
			String tax1 = jTextArea1.getText(); // 9
			System.out.println(tax1);
			String box6 = (String) jComboBox2.getSelectedItem(); // 10
			int f = Integer.parseInt(box6);

			String txt4 = jTextField4.getText();
			int o = Integer.parseInt(txt4); // 流水号
			String h2omane = jTextField4.getText(); // 得到流水号String

			System.out.println("h2omane第一次号" + h2omane);
			//*/
			try {
				/*/
				String strSql = "insert into dingfangxinxi(id,客房编号,客房种类,客房位置,客房单价,顾客姓名,顾客身份证号码,入住日期,折扣,备注信息,账单流水号) values (?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(strSql);

				ps.setInt(1, Types.NULL);
				ps.setInt(2, in);
				ps.setString(3, box2);
				ps.setString(4, box3);
				ps.setFloat(5, flo);
				ps.setString(6, txt1);
				ps.setString(7, txt2);
				ps.setString(8, txt3);
				ps.setFloat(9, fl);
				ps.setString(10, tax1);
				// ps.setFloat(10,f); //是否入住
				ps.setInt(11, o); // 插入流水号
				ps.executeUpdate();

				String strno = (String) ((Vector) jComboBox1.getSelectedItem()).get(0); // 得到客编
				String strSql1 = "update footinfo set 是否入住=?";
				String strno1 = " where 客房编号=";

				System.out.println("是否入住" + f);
				System.out.println(strSql1 + strno1 + strno);

				PreparedStatement pss = con.prepareStatement(strSql1 + strno1
						+ strno);
				pss.setInt(1, f);
				pss.executeUpdate();

				System.out.println("1或0插入成");
				//*/
				hotel.model.dingroom.DingRoom dingRoom = new hotel.model.dingroom.DingRoom();
				dingRoom.setUser(this.getUser(txt1, txt2));
				dingRoom.setRoom(this.selectedRoom);
				dingRoom.setDiscount(1);
				dingRoom.setStart(DateUtil.getDate(dingRoomDate.getText().trim()));
				Map condition = new HashMap();
				condition.put("entity", dingRoom);
				CommandService.getInstance().execute(new DingRoomServiceCommand(DingRoomServiceCommand.getSaveOrUpdateCommand(), condition));
				JOptionPane.showMessageDialog(this, "登记成功！", "成功",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception sqle) {
				sqle.printStackTrace();
				JOptionPane.showMessageDialog(this, "写入数据失败！", "失败",
						JOptionPane.ERROR_MESSAGE);
				System.out.println("出错aaa");
			}

		}
		if (str.equals("false")) {
			this.dispose();
			this.parent.refresh();
		}
	}

	private User getUser(String name, String idCard) {
		Map condition = new HashMap();
		condition.put("idCard", idCard);
		Object user = CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getUserByIdCard(), condition));
		if (user != null) {
			if (user instanceof Guest) {
				((Guest)user).setPoint(((Guest)user).getPoint() + 1);
			} else if (user instanceof VIPUser) {
				((VIPUser)user).setPoint(((VIPUser)user).getPoint() + 1);
			}
			return (User) user;
		} else {
			Guest guest = new Guest();
			guest.setName(name);
			guest.setIdentifier(idCard);
			CommandService.getInstance().assignId(guest);
			return guest;
		}
	}

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

}