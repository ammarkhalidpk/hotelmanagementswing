package hotel.form.context;

import hotel.form.main.MainFrame;
import hotel.util.MessageUtil;
import hotel.util.ResourceReader;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class WelcomeFrame extends BasePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WelcomeFrame(MainFrame parent) {
		super(parent);
		this.parent.setTitle(MessageUtil.getMessage("MainFrame.WelcomeFrame.title"));
		this.setLayout(new BorderLayout());
		//*/
		Image image = ResourceReader.getImageFromJar("resources/welcome.JPG", WelcomeFrame.class.getClassLoader());
		JImagePane panel = new JImagePane(image, JImagePane.SCALED);
		//或者
//		JImagePane panel = new JImagePane();
//		panel.setBackgroundImage(new ImageIcon("003.png");
		
		this.add(panel, BorderLayout.CENTER);
		//*/
	}

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
