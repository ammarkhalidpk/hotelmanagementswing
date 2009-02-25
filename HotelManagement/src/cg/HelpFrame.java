/*帮助模块：  	包括此系统的一些版本信息等。*/

package cg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HelpFrame extends JFrame {
	private javax.swing.JLabel lab, lab1;

	public HelpFrame() {

		java.awt.Container me = this.getContentPane();

		me.setLayout(null);

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

		me.add(lab);
		me.add(lab1);

		this.setTitle("帮助");
		this.setSize(450, 300);
		this.setLocationRelativeTo(this);
		this.setResizable(false);

		this.setVisible(true);
	}
}