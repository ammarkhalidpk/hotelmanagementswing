package hotel.form.context;

import hotel.model.footinfo.FootInfo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CashierFrame extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean cashed = false;

	public CashierFrame(FootInfo footInfo) {
		super();
		//footInfo = (FootInfo) CommandService.getInstance().open().getCurrentExecutionContext().getSession().load(FootInfo.class, footInfo.getId());
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(5, 2));
		center.add(new JLabel("开始时间"));
		JTextField start = new JTextField();
		start.setColumns(20);
		start.setText(footInfo.getDingRoom().getStart().toString());
		start.setEditable(false);
		center.add(start);
		
		center.add(new JLabel("结束时间"));
		JTextField end = new JTextField();
		end.setColumns(20);
		end.setText(footInfo.getDingRoom().getEnd().toString());
		end.setEditable(false);
		center.add(end);
		
		center.add(new JLabel("房间"));
		JTextField room = new JTextField();
		room.setColumns(20);
		room.setText(footInfo.getDingRoom().getRoom().toString());
		room.setEditable(false);
		center.add(room);
		
		center.add(new JLabel("天数"));
		JTextField days = new JTextField();
		days.setColumns(20);
		days.setText(footInfo.getDingRoom().getDays() + "");
		days.setEditable(false);
		center.add(days);
		
		center.add(new JLabel("应付款(￥)"));
		JTextField paied = new JTextField();
		paied.setColumns(20);
		paied.setText(footInfo.getPaied() + "");
		paied.setEditable(false);
		center.add(paied);
		
		this.setLayout(new BorderLayout());
		this.add(center, BorderLayout.CENTER);
		
		JPanel south = new JPanel();
		JButton ok = new JButton("已付款");
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		south.add(ok);
		JButton cancel = new JButton("取消");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		south.add(cancel);
		this.add(south, BorderLayout.SOUTH);
		
		this.setSize(300, 200);
		this.setLocationRelativeTo(this);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("ok")) {
			if (JOptionPane.showConfirmDialog(CashierFrame.this, "收银确认", "消息提示", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				cashed = true;
				dispose();
			}
		}
		if (actionCommand.equals("cancel")) {
			dispose();
		}
	}

	public boolean isCashed() {
		return cashed;
	}
}
