package hotel.form.context;

import hotel.form.main.MainFrame;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import util.MessageUtil;

public class WelcomeFrame extends BasePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WelcomeFrame(MainFrame parent) {
		super(parent);
		this.parent.setTitle(MessageUtil.getMessage("MainFrame.WelcomeFrame.title"));
		this.setLayout(new BorderLayout());
		
		//Image img = Toolkit.getDefaultToolkit().getImage("./imgload.jpg");
		/*ImageIcon img = new ImageIcon("./imgload.jpg"); 
		JLabel imgLabel = new JLabel(img); 
		this.add(imgLabel, new Integer(Integer.MIN_VALUE)); 
		//imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight()); 
		imgLabel.setBounds(0,0,this.parent.getWidth(), this.parent.getHeight());
		// 将contentPane设置成透明的
		this.setOpaque(false); */
		
		JImagePane panel = new JImagePane(new ImageIcon("./resources/welcome.JPG").getImage(), JImagePane.SCALED);
		//或者
//		JImagePane panel = new JImagePane();
//		panel.setBackgroundImage(new ImageIcon("003.png");
		
		this.add(panel, BorderLayout.CENTER);
	}

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}
}
