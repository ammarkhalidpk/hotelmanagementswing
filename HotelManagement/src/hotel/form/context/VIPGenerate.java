package hotel.form.context;

import hotel.form.main.MainFrame;
import hotel.model.user.Guest;
import hotel.model.user.User;
import hotel.model.user.VIPUser;
import hotel.service.CommandService;
import hotel.service.user.UserServiceCommand;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class VIPGenerate extends BasePanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MainFrame parent;

	public VIPGenerate(MainFrame parent) {
		super(parent);
		this.parent = parent;
		this.parent.setTitle("VIP管理");
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tabShow), BorderLayout.CENTER);
		this.add(new SouthPanel(), BorderLayout.SOUTH);
		this.refresh();
	}

	private class SouthPanel extends JPanel implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SouthPanel() {
			this.setLayout(new FlowLayout());
			JButton btnInsert, btnDelete;
			btnInsert = new JButton("升级VIP");
			btnDelete = new JButton("删除");

			add(btnInsert);
			add(btnDelete);

			btnInsert.addActionListener(this);
			btnInsert.setActionCommand("update");

			btnDelete.addActionListener(this);
			btnDelete.setActionCommand("delete");

		}

		public void actionPerformed(ActionEvent e) {
			String strcmd = e.getActionCommand();
			if (strcmd.equals("update")) {
				int selectedRowIndex = tabShow.getSelectedRow();
				if (selectedRowIndex != -1) {
					String id = (String) tabShow.getModel().getValueAt(selectedRowIndex, 0);
					Map condition = new HashMap();
					condition.put("id", id);
					User user = (User) CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getByIdCommand(), condition));
					if (user.getPoint() >= 5) {
						if (JOptionPane.showConfirmDialog(VIPGenerate.this, "确定要将序号为：" + id + "的用户升级吗？") == JOptionPane.OK_OPTION) {
							User.upgrade(user);
							refresh();
						}
					} else {
						JOptionPane.showMessageDialog(VIPGenerate.this, "该用户不符合升级条件", "信息提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			if (strcmd.equals("delete")) {
				int selectedRowIndex = tabShow.getSelectedRow();
				if (selectedRowIndex != -1) {
					String id = (String) tabShow.getModel().getValueAt(selectedRowIndex, 0);
					if (JOptionPane.showConfirmDialog(VIPGenerate.this, "确定删除序号为：" + id + "的用户吗？") == JOptionPane.OK_OPTION) {
						Map condition = new HashMap();
						condition.put("id", id);
						CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getDeleteByIdCommand(), condition));
						refresh();
					}
				}
			}
		}
	}
	
	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

	@Override
	public void refresh() {
		Map cols = new LinkedHashMap();
		String[] guestCol = (String[]) Guest.getFieldMapLabel().values().toArray(new String[Guest.getFieldMapLabel().values().size()]);
		String[] vipCol = (String[]) VIPUser.getFieldMapLabel().values().toArray(new String[VIPUser.getFieldMapLabel().values().size()]);
		for (int i = 0; i < guestCol.length; i++) {
			cols.put(guestCol[i], "");
		}
		for (int i = 0; i < vipCol.length; i++) {
			cols.put(vipCol[i], "");
		}
		String[] columns = (String[]) cols.keySet().toArray(new String[cols.size()]);
		Object[][] guests = (Object[][]) CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getAllGuestAsArray()));
		guests = filterUpgraded(guests);
		Object[][] vip = (Object[][]) CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getAllVipAsArray()));
		Object[][] all = null;
		if (guests != null) {
			if (vip != null) {
				all = new Object[guests.length + vip.length][];
				int count = 0;
				for (int i = 0; i < guests.length; i++) {
					all[count++] = guests[i];
				}
				for (int i = 0; i < vip.length; i++) {
					all[count++] = vip[i];
				}
			} else {
				all = new Object[guests.length][];
				int count = 0;
				for (int i = 0; i < guests.length; i++) {
					all[count++] = guests[i];
				}
			}
		} else if (vip != null) {
			all = new Object[vip.length][];
			int count = 0;
			for (int i = 0; i < vip.length; i++) {
				all[count++] = vip[i];
			}
		} else {
			all = new Object[0][];
		}
		
		DefaultTableModel use = new DefaultTableModel(all, columns);
		tabShow.setModel(use);
	}

	private Object[][] filterUpgraded(Object[][] guests) {
		List list = new ArrayList();
		for (int i = 0; i < guests.length; i++) {
			String[] guest = (String[]) guests[i];
			if (!isUpgraded(guest[2])) {
				list.add(guest);
			}
		}
		return (Object[][]) list.toArray(new Object[list.size()][]);
	}

	private boolean isUpgraded(String string) {
		Map condition = new HashMap();
		condition.put("idCard", string);
		VIPUser vip = (VIPUser) CommandService.getInstance().execute(new UserServiceCommand(UserServiceCommand.getVipByIdCardCommand(), condition));
		if (vip != null) {
			return true;
		}
		return false;
	}

}
