package cx;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import cg.DatabaseOperate;

//客房基本信息模块
public class BaseInfo extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jpanel1;
	private JLabel lab1;
	JTable table1;
	private JScrollPane scrollPane1;
	private JButton btnAdd, btnDel, btnReplace, btnSelect, btnUpdate, btnBack,
			btnExit;
	private Container me;
	static BaseInfoAdd bAdd;
	// static BaseInfoSel bSel;
	private cg.DatabaseOperate Mdbo = new DatabaseOperate();

	public BaseInfo() {

		try {
			// setDefaultCloseOperation(EXIT_ON_CLOSE);
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		me = this.getContentPane();
		me.setLayout(null);

		lab1 = new JLabel();
		lab1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		lab1.setBounds(new Rectangle(304, 10, 181, 47));
		lab1.setForeground(Color.red);
		lab1.setHorizontalAlignment(SwingConstants.CENTER);
		lab1.setText("客房基本信息");

		btnAdd = new JButton();
		btnAdd.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		btnAdd.setBounds(new Rectangle(61, 69, 68, 26));
		btnAdd.setForeground(Color.magenta);
		btnAdd.setText("添加");

		btnDel = new JButton();
		btnDel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		btnDel.setBounds(new Rectangle(193, 69, 68, 26));
		btnDel.setForeground(Color.magenta);
		btnDel.setText("删除");

		btnReplace = new JButton();
		btnReplace.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		btnReplace.setBounds(new Rectangle(335, 69, 68, 26));
		btnReplace.setForeground(Color.magenta);
		btnReplace.setText("修改");

		btnSelect = new JButton();
		btnSelect.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		btnSelect.setBounds(new Rectangle(335, 69, 68, 26));
		btnSelect.setForeground(Color.magenta);
		btnSelect.setText("查询");

		btnUpdate = new JButton();
		btnUpdate.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		btnUpdate.setBounds(new Rectangle(726, 433, 64, 25));
		btnUpdate.setText("刷新");

		btnBack = new JButton();
		btnBack.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		btnBack.setBounds(new Rectangle(726, 481, 64, 25));
		btnBack.setText("返回");

		btnExit = new JButton();
		btnExit.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		btnExit.setBounds(new Rectangle(726, 528, 64, 25));
		btnExit.setText("退出");

		jpanel1 = new JPanel();
		jpanel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		jpanel1.setBorder(BorderFactory.createLoweredBevelBorder());
		jpanel1.setBounds(new Rectangle(46, 113, 667, 441));

		table1 = new JTable();
		table1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
		table1.setForeground(Color.blue);

		scrollPane1 = new JScrollPane(table1);
		this.scrollPane1.setBounds(new Rectangle(46, 113, 667, 441));

		me.add(scrollPane1);
		// jpanel1.add(table1);
		// jpanel1.add(scrollPane1);
		me.add(lab1);
		me.add(btnAdd);
		me.add(btnDel);
		// me.add(btnReplace);
		me.add(btnSelect);
		// me.add(btnUpdate);
		me.add(btnBack);
		me.add(btnExit);
		// scrollPane1.add(table1);
		// new Add.Add(this.me);

		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnReplace.addActionListener(this);
		btnSelect.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnBack.addActionListener(this);
		btnExit.addActionListener(this);

		btnAdd.setActionCommand("add");
		btnDel.setActionCommand("del");
		btnReplace.setActionCommand("replace");
		btnSelect.setActionCommand("select");
		btnUpdate.setActionCommand("update");
		btnBack.setActionCommand("back");
		btnExit.setActionCommand("exit");

		this.setTitle("客房基本信息");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(this);// 居中显示
		this.setVisible(true);

	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("add")) {
			int selectedRow = this.table1.getSelectedRow();
			Map selected = new HashMap();
			int colCount = this.table1.getModel().getColumnCount();
			for (int i = 0; selectedRow != -1 && i < colCount; i++) {
				String colName = this.table1.getModel().getColumnName(i);
				String value = (String) this.table1.getModel().getValueAt(selectedRow, i);
				selected.put(colName, value);
			}
			
			bAdd = new BaseInfoAdd(this, selected);
			bAdd.BaseInfoAdd();
			// this.dispose();
		}
		if (str.equals("del")) {
			new cg.BaseInfoDel();
		}
		if (str.equals("replace")) {

		}
		if (str.equals("select")) {
			this.refresh();
		}
		if (str.equals("update")) {

		}
		if (str.equals("back")) {
			this.dispose();
		}
		if (str.equals("exit")) {
			this.dispose();
		}
	}

	void refresh() {
		DefaultTableModel dtModel = new DefaultTableModel(this.Mdbo
				.getDataByTabname(), Mdbo.getColumnNamesByTabname());
		table1.setModel(dtModel);
	}
}
