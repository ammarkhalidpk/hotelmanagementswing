package hotel.form.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import hotel.form.main.MainFrame;

public class VIPGenerate extends BasePanel {
	
	private MainFrame parent;

	public VIPGenerate(MainFrame parent) {
		super(parent);
		this.parent = parent;
		this.parent.setTitle("VIP管理");
		this.setLayout(new BorderLayout());
		JLabel lab, lab1;
		lab = new JLabel("欢迎使用本系统");
		lab.setForeground(Color.BLUE);
		lab.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		lab.setHorizontalAlignment(SwingConstants.CENTER);
		lab.setBounds(new Rectangle(80, 82, 260, 29));

		lab1 = new JLabel("本系统测试阶段暂时不提供帮助");
		lab1.setForeground(Color.BLUE);
		lab1.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		lab1.setHorizontalAlignment(SwingConstants.CENTER);
		lab1.setBounds(new Rectangle(20, 120, 400, 29));

		this.add(lab, BorderLayout.NORTH);
		this.add(lab1, BorderLayout.NORTH);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
