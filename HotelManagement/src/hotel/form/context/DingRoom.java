package hotel.form.context;

import hotel.form.main.MainFrame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
			btnSelect2, btnExit;
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
			// btnUpdate = new JButton("修改");
			btnSelect = new JButton("查询所有房");
			btnSelect1 = new JButton("正使用房");
			btnSelect2 = new JButton("空闲房");
			btnExit = new JButton("退出");

			this.add(btnInsert);
			this.add(btnDelete);
			// this.add(btnUpdate);
			this.add(btnSelect);
			this.add(btnSelect1);
			this.add(btnSelect2);
			this.add(btnExit);

			btnSelect.addActionListener(this);
			btnInsert.addActionListener(this);
			btnSelect1.addActionListener(this);
			btnSelect2.addActionListener(this);
			// btnUpdate.addActionListener(this);
			btnDelete.addActionListener(this);
			btnExit.addActionListener(this);

			btnSelect.setActionCommand("select");
			btnInsert.setActionCommand("insert");
			btnSelect1.setActionCommand("select1");
			btnSelect2.setActionCommand("select2");
			// btnUpdate.setActionCommand("update");
			btnDelete.setActionCommand("delete");
			btnExit.setActionCommand("exit");
		}

		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			/*if (str.equals("select")) {
				DefaultTableModel dtm = new DefaultTableModel(dbo
						.getDataByTabname(), dbo.getColumnNamesByTabname());
				tabShow.setModel(dtm);
			}
			if (str.equals("insert")) {
				new dingRoomFrame1();
			}
			if (str.equals("select1")) {
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

}