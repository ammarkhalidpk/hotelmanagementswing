package cx;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BaseInfoAdd extends JFrame implements ActionListener {

	private JLabel lab1, lab2, lab3, lab4, lab5, lab6;
	JTextField txtNum, txtType, txtPrice, txtPlace;
	private JComboBox cboType, cboPlace;
	private JButton btnReAdd, btnBack, btnExit, btnOk;
	private JScrollPane jScrollPane1;
	JTextArea txtArea;
	private Map init;
	private BaseInfo parent;

	public BaseInfoAdd() {
		// try {
		// //setDefaultCloseOperation(EXIT_ON_CLOSE);
		// //this.usePack();//
		// }
		// catch (Exception exception) {
		// exception.printStackTrace();
		// }
	}
	
	public BaseInfoAdd(BaseInfo parent, Map init) {
		this.parent = parent;
		this.init = init;
	}

	void BaseInfoAdd() {
		try {
			Container me = this.getContentPane();
			me.setLayout(null);

			lab1 = new JLabel();
			lab1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
			lab1.setForeground(Color.red);
			lab1.setHorizontalAlignment(SwingConstants.CENTER);
			lab1.setText("添加客房基本信息");
			lab1.setBounds(new Rectangle(206, 27, 206, 34));

			lab2 = new JLabel();
			lab2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			lab2.setForeground(Color.blue);
			lab2.setHorizontalAlignment(SwingConstants.CENTER);
			lab2.setText("客房编号：");
			lab2.setBounds(new Rectangle(73, 70, 84, 34));

			lab3 = new JLabel();
			lab3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			lab3.setForeground(Color.blue);
			lab3.setHorizontalAlignment(SwingConstants.CENTER);
			lab3.setText("客房类型：");
			lab3.setBounds(new Rectangle(73, 122, 84, 34));

			lab4 = new JLabel();
			lab4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			lab4.setForeground(Color.blue);
			lab4.setHorizontalAlignment(SwingConstants.CENTER);
			lab4.setText("客房位置：");
			lab4.setBounds(new Rectangle(73, 174, 84, 34));

			lab5 = new JLabel();
			lab5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			lab5.setForeground(Color.blue);
			lab5.setHorizontalAlignment(SwingConstants.CENTER);
			lab5.setText("客房单价：");
			lab5.setBounds(new Rectangle(73, 225, 84, 34));

			lab6 = new JLabel();
			lab6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			lab6.setForeground(Color.blue);
			lab6.setHorizontalAlignment(SwingConstants.CENTER);
			lab6.setText("备注信息：");
			lab6.setBounds(new Rectangle(73, 277, 84, 34));

			txtNum = new JTextField();
			txtNum.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			txtNum.setBounds(new Rectangle(159, 77, 136, 26));

			txtPrice = new JTextField();
			txtPrice.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			txtPrice.setBounds(new Rectangle(159, 232, 130, 28));

			txtType = new JTextField();
			txtType.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			txtType.setBounds(new Rectangle(159, 129, 130, 28));

			txtPlace = new JTextField();
			txtPlace.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			txtPlace.setBounds(new Rectangle(159, 174, 130, 28));

			cboType = new JComboBox();
			cboType.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			cboType.setBounds(new Rectangle(344, 181, 69, 26));

			cboPlace = new JComboBox();
			cboPlace.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			cboPlace.setBounds(new Rectangle(344, 129, 69, 26));

			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(151, 278, 257, 206));

			txtArea = new JTextArea();
			txtArea.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			txtArea.setBorder(BorderFactory.createLoweredBevelBorder());

			btnReAdd = new JButton();
			btnReAdd.setBounds(new Rectangle(473, 311, 78, 30));
			btnReAdd.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			btnReAdd.setText("重置");

			btnBack = new JButton();
			btnBack.setBounds(new Rectangle(473, 407, 78, 30));
			btnBack.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			btnBack.setText("返回");

			btnExit = new JButton();
			btnExit.setBounds(new Rectangle(473, 455, 78, 30));
			btnExit.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			btnExit.setText("退出");

			btnOk = new JButton();
			btnOk.setBounds(new Rectangle(473, 359, 78, 30));
			btnOk.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
			btnOk.setText("确定");

			/*
			 * jMenuFile.setText("File"); jMenuFileExit.setText("Exit");
			 * jMenuFileExit.addActionListener(new
			 * BaseInfoAdd_jMenuFileExit_ActionAdapter(this));
			 * jMenuHelp.setText("Help"); jMenuHelpAbout.setText("About");
			 * jMenuHelpAbout.addActionListener(new
			 * BaseInfoAdd_jMenuHelpAbout_ActionAdapter(this));
			 * jToolBar.setBounds(new Rectangle(27, 4, 400, 29));
			 * jMenuBar1.add(jMenuFile); jMenuFile.add(jMenuFileExit);
			 * jMenuBar1.add(jMenuHelp); jMenuHelp.add(jMenuHelpAbout);
			 */

			me.add(lab2);
			me.add(lab6);
			me.add(lab4);
			me.add(lab5);
			me.add(lab3);
			me.add(lab1);
			me.add(txtNum);
			me.add(txtPrice);
			me.add(txtType);
			me.add(txtPlace);
			// me.add(cboType);
			// me.add(cboPlace);
			me.add(jScrollPane1);
			me.add(btnExit);
			me.add(btnBack);
			me.add(btnOk);
			me.add(btnReAdd);
			jScrollPane1.getViewport().add(txtArea);
			// me.add(jToolBar, null);
			// setJMenuBar(jMenuBar1);

			btnBack.addActionListener(this);
			btnReAdd.addActionListener(this);
			btnExit.addActionListener(this);
			btnOk.addActionListener(this);

			btnExit.setActionCommand("exit");
			btnBack.setActionCommand("back");
			btnReAdd.setActionCommand("reAdd");
			btnOk.setActionCommand("ok");
			
			
			if (this.init != null) {
				txtNum.setText((String) this.init.get("客房编号"));
				txtType.setText((String) this.init.get("客房类型"));
				txtPrice.setText((String) this.init.get("客房单价"));
				txtPlace.setText((String) this.init.get("客房位置"));
				txtArea.setText((String) this.init.get("备注信息"));
			}
			

			setSize(new Dimension(580, 560));
			setTitle("添加客房基本信息");
			this.setLocationRelativeTo(this);// 居中显示
			this.setResizable(false);
			this.setVisible(true);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		String str = e.getActionCommand();
		System.out.println(str);// 测试
		String num = txtNum.getText().trim();
		String type = txtType.getText().trim();
		String place = txtPlace.getText().trim();
		String price = txtPrice.getText().trim();
		boolean numAreLetters = false, priceIsFloat = false;
		if (num.length() != 0) {
			try {
				char[] arr = new char[num.length()];
				arr = num.toCharArray();
				for (char c : arr) {
					Character chr = new Character(c);
					if (chr.isLetter(c)) {
						System.out.println(c);
						numAreLetters = true;
						break;
					}
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		if (price.length() != 0) {
			try {
				Float ff = Float.parseFloat(price);
			} catch (Exception ae) {
				priceIsFloat = true;
			}
		}

		if (str.equals("ok")) {

			if (num.equals("")) {
				JOptionPane.showMessageDialog(this, "请输入客房编号！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				txtNum.requestFocus(true);
			} else if (numAreLetters) {
				JOptionPane.showMessageDialog(this, "客房编号中不能含有除数子(0~9)以外的字符!",
						"提示", JOptionPane.INFORMATION_MESSAGE);
				txtNum.requestFocus(true);
			} else
			// if(compare()){//调用compare方法
			// JOptionPane.showMessageDialog(this, "此客房编号已经存在!", "提示",
			// JOptionPane.INFORMATION_MESSAGE);
			// }else
			if (type.equals("")) {
				JOptionPane.showMessageDialog(this, "请输入客房类型！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				txtType.requestFocus(true);
			} else if (place.equals("")) {
				JOptionPane.showMessageDialog(this, "请输入客房位置！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				txtPlace.requestFocus(true);
			} else if (price.equals("")) {
				JOptionPane.showMessageDialog(this, "请输入客房单价！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				txtPrice.requestFocus(true);
			} else if (priceIsFloat) {
				JOptionPane.showMessageDialog(this, "客房单价处请输入相关数字！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				txtPrice.requestFocus(true);
			} else {
				try {
					boolean add = new OnData().OnData("add");
					if (add) {
						JOptionPane.showMessageDialog(this, "操作成功", "成功",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(this, "操作失败", "失败",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ea) {
					ea.printStackTrace();
					JOptionPane.showMessageDialog(this, "操作失败", "失败",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}
		if (str.equals("reAdd")) {
			txtArea.setText("");
			txtType.setText("");
			txtPlace.setText("");
			txtPrice.setText("");
			txtNum.setText("");
			txtNum.requestFocus(true);
		}
		if (str.equals("back")) {
			this.dispose();
			parent.refresh();
		}
		if (str.equals("exit")) {
			this.dispose();
		}
	}

}