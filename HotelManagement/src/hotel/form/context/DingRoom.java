package hotel.form.context;

import hotel.form.main.MainFrame;
import hotel.service.CommandService;
import hotel.service.dingroom.DingRoomServiceCommand;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DingRoom extends BasePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btnInsert, btnDelete, btnUpdate, btnSelect, btnSelect1,
			btnSelect2;
	public JComboBox jComboBox1, jComboBox2, jComboBox3, jComboBox4,
			jComboBox5, jComboBox6;
	public JTextField jTextField1, jTextField2, jTextField3;
	public JTextArea jTextArea1;
	public JTable tabShow;
	public Connection con;

	public DingRoom(MainFrame parent) {
		super(parent);

		tabShow = new JTable();

		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tabShow), BorderLayout.CENTER);
		this.add(new SouthPanel(), BorderLayout.SOUTH);

		this.parent.setTitle("订房信息主窗口");
		this.setVisible(true);

	}

	private class SouthPanel extends JPanel implements ActionListener {
		public SouthPanel() {
			super(new FlowLayout());
			btnInsert = new JButton("开房");
			btnDelete = new JButton("退房");
			btnSelect = new JButton("查询所有房");
			btnSelect1 = new JButton("正使用房");
			btnSelect2 = new JButton("空闲房");

			this.add(btnInsert);
			this.add(btnDelete);
			this.add(btnSelect);
			this.add(btnSelect1);
			this.add(btnSelect2);

			btnSelect.addActionListener(this);
			btnInsert.addActionListener(this);
			btnSelect1.addActionListener(this);
			btnSelect2.addActionListener(this);
			btnDelete.addActionListener(this);

			btnSelect.setActionCommand("select");
			btnInsert.setActionCommand("insert");
			btnSelect1.setActionCommand("select1");
			btnSelect2.setActionCommand("select2");
			btnDelete.setActionCommand("delete");
		}

		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			if (str.equals("select")) {
				refresh();
			}
			if (str.equals("insert")) {
				new dingRoomFrame1(DingRoom.this);
			}
			/*if (str.equals("select1")) {
				DefaultTableModel dtm = new DefaultTableModel(dbo
						.getDataByTabname1(), dbo.getColumnNamesByTabname1());
				tabShow.setModel(dtm);
			}
			if (str.equals("select2")) {
				DefaultTableModel dtm = new DefaultTableModel(dbo
						.getDataByTabname2(), dbo.getColumnNamesByTabname2());
				tabShow.setModel(dtm);
			}
			
			 * if(str.equals("update")){ new dingRoomFrameUpdate(); }
			 
			if (str.equals("delete")) {
				new dingRoomFrameDelete();
			}
			if (str.equals("exit")) {
				this.setVisible(false);
			}*/
		}
	}

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

	@Override
	public void refresh() {
		DefaultTableModel dingRoom = new DefaultTableModel((Object[][]) CommandService.getInstance().execute(new DingRoomServiceCommand(DingRoomServiceCommand.getAllAsArrayCommand())), hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
		tabShow.setModel(dingRoom);
	}

}