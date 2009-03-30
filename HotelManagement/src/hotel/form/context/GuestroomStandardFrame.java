package hotel.form.context;

import hotel.form.main.MainFrame;
import hotel.model.room.Room;
import hotel.service.CommandService;
import hotel.service.room.RoomServiceCommand;
import hotel.util.MessageUtil;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GuestroomStandardFrame extends BasePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnInsert, btnDelete, btnSelect;

	private JTable tabShow;
	
	private GuestroomStandardFrame instance;
	
	public GuestroomStandardFrame(MainFrame parent) {
		super(parent);
		instance = this;
		tabShow = new JTable();

		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tabShow), BorderLayout.CENTER);
		this.add(new SouthPanel(), BorderLayout.SOUTH);

		this.parent.setTitle(MessageUtil.getMessage("MainFrame.GuestroomStandardFrame.title"));
		this.setVisible(true);
		refresh();
	}

	private class SouthPanel extends JPanel implements ActionListener {
		public SouthPanel() {
			super(new FlowLayout());
			btnInsert = new JButton(MessageUtil.getMessage("MainFrame.GuestroomStandardFrame.add"));
			btnDelete = new JButton(MessageUtil.getMessage("MainFrame.GuestroomStandardFrame.delete"));
			btnSelect = new JButton(MessageUtil.getMessage("MainFrame.GuestroomStandardFrame.query"));

			add(btnInsert);
			add(btnDelete);
			add(btnSelect);

			btnInsert.setActionCommand("ins");
			btnDelete.setActionCommand("del");
			btnSelect.setActionCommand("sel");

			btnInsert.addActionListener(this);
			btnDelete.addActionListener(this);
			btnSelect.addActionListener(this);

		}

		public void actionPerformed(ActionEvent e) {
			String strcmd = e.getActionCommand();
			if (strcmd.equals("ins")) {
				new GuestroomStandardFrame1(instance);
			}
			if (strcmd.equals("del")) {
				int selectedRowIndex = tabShow.getSelectedRow();
				if (selectedRowIndex != -1) {
					String id = (String) tabShow.getModel().getValueAt(selectedRowIndex, 0);
					if (JOptionPane.showConfirmDialog(instance, "确定删除序号为：" + id + "的房间吗？") == JOptionPane.OK_OPTION) {
						Map condition = new HashMap();
						condition.put("id", id);
						CommandService.getInstance().execute(new RoomServiceCommand("delete", condition));
						refresh();
					}
				}
			}
			if (strcmd.equals("sel")) {
				refresh();
			}
		}
	}

	public void refresh() {
		DefaultTableModel dtm = new DefaultTableModel((Object[][]) CommandService.getInstance().execute(new RoomServiceCommand("getAllAsArray")), Room.getFieldMapLabel().values().toArray());
		tabShow.setModel(dtm);
	}
	
	public void access(MainFrame vistor) {
		vistor.visit(this);
	}
}