/*帮助模块：  	包括此系统的一些版本信息等。*/

package hotel.form.context;

import hotel.form.main.MainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class HelpFrame extends BasePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lab, lab1;

	public HelpFrame(MainFrame parent) {
		super(parent);

		this.setLayout(new BorderLayout());

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

		this.parent.setTitle("帮助");
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		JImagePane panel = new JImagePane(new ImageIcon("./resources/welcome.JPG").getImage(), JImagePane.SCALED);
		p.add(panel);
		panel = new JImagePane(new ImageIcon("./resources/room_01_big.jpg").getImage(), JImagePane.SCALED);
		p.add(panel);
		panel = new JImagePane(new ImageIcon("./resources/room_02_big.jpg").getImage(), JImagePane.SCALED);
		p.add(panel);
		panel = new JImagePane(new ImageIcon("./resources/room_04_big.jpg").getImage(), JImagePane.SCALED);
		p.add(panel);
		panel = new JImagePane(new ImageIcon("./resources/room_05_big.jpg").getImage(), JImagePane.SCALED);
		p.add(panel);
		JScrollPane jsp = new JScrollPane(p, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(jsp, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}