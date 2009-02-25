package cg;

import java.awt.BorderLayout;
import java.awt.Container;
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

public class GuestroomStandardFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnInsert, btnDelete, btnSelect;

	private DatabaseOperate dbo;

	// private JComboBox cboChioce;
	private JTable tabShow;

	public GuestroomStandardFrame() {
		super("");

		dbo = new DatabaseOperate();

		tabShow = new JTable();

		Container me = getContentPane();
		me.setLayout(new BorderLayout());
		// me.add(new WestPanel(), BorderLayout.WEST);
		me.add(new JScrollPane(tabShow), BorderLayout.CENTER);
		me.add(new SouthPanel(), BorderLayout.SOUTH);

		this.addWindowListener(new WindowCloseEvent());

		this.setTitle("客房标准设置");
		this.setSize(600, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(this);// 居中显示
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
				new GuestroomStandardFrame1();
			}
			if (strcmd.equals("del")) {
				new GuestroomStandardFrame2();
			}
			if (strcmd.equals("sel")) {
				DefaultTableModel dtm = new DefaultTableModel(dbo
						.getDataByTabname(), dbo.getColumnNamesByTabname());
				tabShow.setModel(dtm);
			}

		}
	}

	private class WindowCloseEvent extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
		}
	}

}