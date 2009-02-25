/*用户管理模块：  	添加、修改和删除用户。*/

package cg;

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

public class UserFrame extends JFrame {

	private javax.swing.JLabel lab, lab1, lab2;
	private javax.swing.JTextField txtName, txaType;
	private javax.swing.JPasswordField pwd;
	private JTable tabShow;
	private javax.swing.JButton btnInsert, btnDelete, btnSelect, btnExit;
	private javax.swing.JComboBox cob;
	private DatabaseUser dbo;

	public UserFrame() {

		super("");
		dbo = new DatabaseUser();
		tabShow = new JTable();
		java.awt.Container me = this.getContentPane();

		me.setLayout(new BorderLayout());
		// me.add(new NorthPanel(),BorderLayout.NORTH);
		me.add(new JScrollPane(tabShow), BorderLayout.CENTER);
		me.add(new SouthPanel(), BorderLayout.SOUTH);

		this.addWindowListener(new WindowCloseEvent());

		this.setTitle("用户管理");
		this.setSize(600, 400);
		this.setLocationRelativeTo(this);
		this.setResizable(false);
		this.setVisible(true);
	}

	// private class NorthPanel extends JPanel{
	//		
	// public NorthPanel(){
	//
	// this.setLayout(new FlowLayout());
	//			
	// lab = new JLabel("用户名");
	// txtName = new JTextField(10);
	// lab1 = new JLabel("密码");
	// pwd = new JPasswordField(10);
	// lab2 = new JLabel("类型");
	// //txaType = new JTextField(10);
	// cob = new JComboBox();
	// cob.addItem("普通用户");
	// cob.addItem("超级用户");
	//			
	// this.add(lab);
	// this.add(txtName);
	// this.add(lab1);
	// this.add(pwd);
	// this.add(lab2);
	// this.add(cob);
	// }
	// }

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
				DefaultTableModel use = new DefaultTableModel(dbo
						.getDataByTabname(), dbo.getColumnNamesByTabname());
				tabShow.setModel(use);
			}
			if (strcmd.equals("inser")) {
				new UseraddFrame();
			}
			if (strcmd.equals("dele")) {
				new UserdelFrame();
			}
			// if(strcmd.equals("exit")){
			//	         		
			// }
			//            	
		}

	}

	private class WindowCloseEvent extends WindowAdapter {

		public void windowClosing(WindowEvent e) {

		}
	}
}
