/*用户管理模块：  	添加、修改和删除用户。*/

package hotel.form.context;

import hotel.form.main.MainFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	private javax.swing.JButton btnInsert, btnDelete, btnSelect, btnExit;
	private javax.swing.JComboBox cob;

	public UserFrame(MainFrame parent) {
		super(parent);
		//dbo = new DatabaseUser();
		tabShow = new JTable();

		this.setLayout(new BorderLayout());
		// me.add(new NorthPanel(),BorderLayout.NORTH);
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
			// btnExit = new JButton("退出");

			add(btnInsert);
			add(btnDelete);
			add(btnSelect);
			// add(btnExit);

			btnInsert.addActionListener(this);
			btnInsert.setActionCommand("inser");

			btnDelete.addActionListener(this);
			btnDelete.setActionCommand("dele");

			// btnExit.addActionListener(this);
			// btnExit.setActionCommand("exit");

			btnSelect.addActionListener(this);
			btnSelect.setActionCommand("sele");

		}

		public void actionPerformed(ActionEvent e) {
			String strcmd = e.getActionCommand();
			if (strcmd.equals("sele")) {
//				DefaultTableModel use = new DefaultTableModel(dbo
//						.getDataByTabname(), dbo.getColumnNamesByTabname());
//				tabShow.setModel(use);
			}
			if (strcmd.equals("inser")) {
				//new UseraddFrame();
			}
			if (strcmd.equals("dele")) {
				//new UserdelFrame();
			}
			// if(strcmd.equals("exit")){
			//	         		
			// }
			//            	
		}

	}

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

}
