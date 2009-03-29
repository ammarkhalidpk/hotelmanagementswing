package hotel.form.context;

import hotel.form.main.MainFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class BasePanel extends JPanel implements Accessable {
	protected JFrame parent;
	
	public BasePanel(MainFrame parent) {
		this.parent = parent;
	}
}
