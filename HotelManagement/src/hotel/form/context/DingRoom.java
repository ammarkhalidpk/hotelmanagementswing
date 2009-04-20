package hotel.form.context;

import hotel.form.main.MainFrame;
import hotel.service.CommandService;
import hotel.service.dingroom.DingRoomServiceCommand;
import hotel.util.DateUtil;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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
	public JButton btnInsert, btnDelete, btnUpdate, allDingRoomInfo, inUse, btnSelect2;
	public JComboBox jComboBox1, jComboBox2, jComboBox3, jComboBox4,
			jComboBox5, jComboBox6;
	public JTextField jTextField1, jTextField2, jTextField3;
	public JTextArea jTextArea1;
	public JTable tabShow;

	public DingRoom(MainFrame parent) {
		super(parent);

		tabShow = new JTable();
		refresh();

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
			allDingRoomInfo = new JButton("查询所有订房信息");
			inUse = new JButton("使用房间");
			btnSelect2 = new JButton("已退房间");

			this.add(btnInsert);
			this.add(btnDelete);
			this.add(allDingRoomInfo);
			this.add(inUse);
			this.add(btnSelect2);

			allDingRoomInfo.addActionListener(this);
			btnInsert.addActionListener(this);
			inUse.addActionListener(this);
			btnSelect2.addActionListener(this);
			btnDelete.addActionListener(this);

			allDingRoomInfo.setActionCommand("select");
			btnInsert.setActionCommand("insert");
			inUse.setActionCommand("select1");
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
			if (str.equals("select1")) {
				//inUse,未退房间
				DefaultTableModel dingRoom = new DefaultTableModel((Object[][]) CommandService.getInstance().execute(new DingRoomServiceCommand(DingRoomServiceCommand.getUnCheckOutDingRoomAsArrayCommand())), hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
				tabShow.setModel(dingRoom);
			}
			if (str.equals("select2")) {
				//已退房间
				DefaultTableModel dingRoom = new DefaultTableModel((Object[][]) CommandService.getInstance().execute(new DingRoomServiceCommand(DingRoomServiceCommand.getCheckOutDingRoomAsArrayCommand())), hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
				tabShow.setModel(dingRoom);
			}
			if (str.equals("delete")) {
				int selectedRowIndex = tabShow.getSelectedRow();
				if (selectedRowIndex != -1) {
					//首先根据id得到DingRoom实体
					String id = (String) tabShow.getModel().getValueAt(selectedRowIndex, 0);
					Map condition = new HashMap();
					condition.put("id", id);
					hotel.model.dingroom.DingRoom dingRoom = (hotel.model.dingroom.DingRoom) CommandService.getInstance().execute(new DingRoomServiceCommand(DingRoomServiceCommand.getByIdCommand(), condition));
					if (dingRoom.getEnd() != null) {
						JOptionPane.showMessageDialog(DingRoom.this, "请选择房间正在使用的房间", "错误提示", JOptionPane.ERROR_MESSAGE);
					} else {
						String roomNum = (String) tabShow.getModel().getValueAt(selectedRowIndex, 2);
						if (JOptionPane.showConfirmDialog(DingRoom.this, "确定要退房间号为：" + roomNum + "的房间吗？") == JOptionPane.OK_OPTION) {
							dingRoom.setEnd(DateUtil.getNow());
							dingRoom.setFootState("未结算");
							condition.put("entity", dingRoom);
							CommandService.getInstance().execute(new DingRoomServiceCommand(DingRoomServiceCommand.getSaveOrUpdateEntryCommand(), condition));
							refresh();
						}
					}
				} else {
					JOptionPane.showMessageDialog(DingRoom.this, "请选择房间", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
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