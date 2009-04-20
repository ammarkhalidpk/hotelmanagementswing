package hotel.form.context;

import hotel.form.main.MainFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public abstract class BasePanel extends JPanel implements Accessable {
	protected JFrame parent;
	
	protected JTable tabShow;
	
	public abstract void refresh();
	
	public BasePanel(MainFrame parent) {
		this.parent = parent;
	}
}
