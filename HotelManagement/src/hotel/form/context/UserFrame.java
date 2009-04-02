/*用户管理模块：  	添加、修改和删除用户。*/

package hotel.form.context;

import hotel.form.main.MainFrame;
import hotel.model.BaseModel;
import hotel.model.room.Room;
import hotel.model.user.Guest;
import hotel.model.user.User;
import hotel.model.user.VIPUser;
import hotel.service.CommandService;
import hotel.service.room.RoomServiceCommand;
import hotel.service.user.UserServiceCommand;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserFrame extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1818419725557789409L;
	private javax.swing.JLabel lab, lab1, lab2;
	private javax.swing.JTextField txtName, txaType;
	private javax.swing.JPasswordField pwd;
	private JTable tabShow;
	private javax.swing.JButton btnInsert, btnDelete, btnSelect;
	private javax.swing.JComboBox cob;

	public UserFrame(MainFrame parent) {
		super(parent);
		tabShow = new JTable();
		this.refresh();

		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tabShow), BorderLayout.CENTER);
		this.add(new SouthPanel(), BorderLayout.SOUTH);

		this.parent.setTitle("用户管理");
		this.setVisible(true);
	}

	private class SouthPanel extends JPanel implements ActionListener {

		public SouthPanel() {
			super(new FlowLayout());
			btnInsert = new JButton("增加");
			btnDelete = new JButton("删除");
			btnSelect = new JButton("查询");

			add(btnInsert);
			add(btnDelete);
			add(btnSelect);

			btnInsert.addActionListener(this);
			btnInsert.setActionCommand("inser");

			btnDelete.addActionListener(this);
			btnDelete.setActionCommand("dele");

			btnSelect.addActionListener(this);
			btnSelect.setActionCommand("sele");

		}

		public void actionPerformed(ActionEvent e) {
			String strcmd = e.getActionCommand();
			if (strcmd.equals("sele")) {
				refresh();
			}
			if (strcmd.equals("inser")) {
				new UseraddFrame(UserFrame.this);
			}
			if (strcmd.equals("dele")) {
				int selectedRowIndex = tabShow.getSelectedRow();
				if (selectedRowIndex != -1) {
					String id = (String) tabShow.getModel().getValueAt(selectedRowIndex, 0);
					if (JOptionPane.showConfirmDialog(UserFrame.this, "确定删除序号为：" + id + "的用户吗？") == JOptionPane.OK_OPTION) {
						Map condition = new HashMap();
						condition.put("id", id);
						CommandService.getInstance().execute(new UserServiceCommand("delete", condition));
						refresh();
					}
				}
			}
		}
	}
	
	public void refresh() {
		Map cols = new LinkedHashMap();
		String[] userCol = (String[]) User.getFieldMapLabel().values().toArray(new String[User.getFieldMapLabel().values().size()]);
		String[] guestCol = (String[]) Guest.getFieldMapLabel().values().toArray(new String[Guest.getFieldMapLabel().values().size()]);
		String[] vipCol = (String[]) VIPUser.getFieldMapLabel().values().toArray(new String[VIPUser.getFieldMapLabel().values().size()]);
		for (int i = 0; i < userCol.length; i++) {
			cols.put(userCol[i], "");
		}
		for (int i = 0; i < guestCol.length; i++) {
			cols.put(guestCol[i], "");
		}
		for (int i = 0; i < vipCol.length; i++) {
			cols.put(vipCol[i], "");
		}
		String[] columns = (String[]) cols.keySet().toArray(new String[cols.size()]);
		DefaultTableModel use = new DefaultTableModel((Object[][]) CommandService.getInstance().execute(new UserServiceCommand("getAllAsArray")), columns);
		tabShow.setModel(use);
	}
	
	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

}
