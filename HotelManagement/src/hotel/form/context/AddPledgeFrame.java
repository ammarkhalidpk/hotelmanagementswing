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

public class AddPledgeFrame extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField addPledge;
	
	private String roomNum;

	private boolean confirmAdd = false;

	public AddPledgeFrame(String roomNum) {
		super();
		this.roomNum = roomNum;
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2, 2));
		center.add(new JLabel("房号"));
		JTextField room = new JTextField();
		room.setColumns(20);
		room.setText(roomNum);
		room.setEditable(false);
		center.add(room);
		
		center.add(new JLabel("增加押金(￥)"));
		addPledge = new JTextField();
		addPledge.setColumns(20);
		center.add(addPledge);
		
		this.setLayout(new BorderLayout());
		this.add(center, BorderLayout.CENTER);
		
		JPanel south = new JPanel();
		JButton ok = new JButton("确定");
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
			if (JOptionPane.showConfirmDialog(AddPledgeFrame.this, "确认在房间号为：" + roomNum + "的客单增加押金：" + getAddPledge(), "消息提示", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				confirmAdd = true;
				dispose();
			}
		}
		if (actionCommand.equals("cancel")) {
			dispose();
		}
	}

	public float getAddPledge() {
		String aPledge = addPledge.getText().trim();
		if (aPledge != null && !aPledge.equals("")) {
			return Float.parseFloat(aPledge);
		}
		return 0;
	}

	public boolean getConfirmAdd() {
		return confirmAdd ;
	}

}
