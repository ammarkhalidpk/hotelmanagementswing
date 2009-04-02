package hotel.form.main;

import hotel.form.context.Accessable;
import hotel.form.context.AllQuery;
import hotel.form.context.BaseInfo;
import hotel.form.context.Cashier;
import hotel.form.context.DingRoom;
import hotel.form.context.FootInfo;
import hotel.form.context.FormQuery;
import hotel.form.context.FrontDeskCheck;
import hotel.form.context.GuestroomStandardFrame;
import hotel.form.context.HelpFrame;
import hotel.form.context.HistoryTreaty;
import hotel.form.context.Receive;
import hotel.form.context.RoomSell;
import hotel.form.context.Statistics;
import hotel.form.context.UserFrame;
import hotel.form.context.VIPGenerate;
import hotel.form.context.WelcomeFrame;
import hotel.form.context.dingRoomFrame1;
import hotel.util.MessageUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;




public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnstaRoom, btnbaseInfo, btndingRoom,
			btnfootInfo, btnExit;
	private JButton btnUser, btnHelp;
	private JPanel pan;
	private JLabel lab;
	private CenterPanel center;
	private MainFrame mainFrame;
	private Map funCommandMapFun;

	public MainFrame() {
		mainFrame = this;
		java.awt.Container me = this.getContentPane();
		lab = new JLabel(MessageUtil.getMessage("MainFrame.title"));
		lab.setForeground(Color.red);
		lab.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		lab.setHorizontalAlignment(SwingConstants.CENTER);
		lab.setBounds(new Rectangle(165, 10, 260, 29));

		me.setLayout(new BorderLayout());
		me.add(new WestPanel(), BorderLayout.WEST);
		center = new CenterPanel();
		me.add(center, BorderLayout.CENTER);
		me.add(lab, BorderLayout.NORTH);

		this.addWindowListener(new WindowCloseEvent());

		this.setTitle(MessageUtil.getMessage("MainFrame.hotel.name"));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)screenSize.getWidth(), ((int)screenSize.getHeight() - 30));
		// this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(this);// ������ʾ
		this.setLocation(0, 0);
		this.setVisible(true);
	}

	private class WindowCloseEvent extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	private class CenterPanel extends JPanel {
		public CenterPanel() {
			super();
			this.setLayout(new BorderLayout());
			this.add(new WelcomeFrame(mainFrame), BorderLayout.CENTER);
		}
	}

	private class WestPanel extends JPanel implements ActionListener {
		public WestPanel() {
			funCommandMapFun = new LinkedHashMap();
			this.registerFunction("sta", "MainFrame.room.stand.make", GuestroomStandardFrame.class);
			
			this.registerFunction("base", "MainFrame.room.stand.query", BaseInfo.class);
			
			this.registerFunction("dingfang", "MainFrame.room.ding", DingRoom.class);
				
			this.registerFunction("btnfootInfo", "MainFrame.room.jiesuan", FootInfo.class);
				
			this.registerFunction("user", "MainFrame.user.manage", UserFrame.class);
			
			//ͳ�Ʋ�ѯ
			this.registerFunction("statistics", "MainFrame.btnStatistics", Statistics.class);
			//�Ӵ����
			this.registerFunction("btnReceive", "MainFrame.btnReceive", Receive.class);
			//�۷�����
			this.registerFunction("btnSell", "MainFrame.btnSell", RoomSell.class);
			//���ܲ���
			this.registerFunction("btnQuery", "MainFrame.btnQuery", AllQuery.class);
			//�͵�����
			this.registerFunction("btnFormQuery", "MainFrame.btnFormQuery", FormQuery.class);
			//�������
			this.registerFunction("btnCashier", "MainFrame.btnCashier", Cashier.class);
			//ǰ̨��˹���
			this.registerFunction("btnFrontDeskCheck", "MainFrame.btnFrontDeskCheck", FrontDeskCheck.class);
			//�ͻ���ʷ��Լ����
			this.registerFunction("btnHistoryTreaty", "MainFrame.btnHistoryTreaty", HistoryTreaty.class);
			//VIP��ɹ���
			this.registerFunction("btnVIPGenerate", "MainFrame.btnVIPGenerate", VIPGenerate.class);

			this.registerFunction("help", "MainFrame.help", HelpFrame.class);
			
			this.registerFunction("welcome", "MainFrame.btnWelcome", WelcomeFrame.class);
			
			this.registerFunction("btnExit", "MainFrame.exit", BaseInfo.class);
			
			this.setLayout(new GridLayout(funCommandMapFun.keySet().size(), 1));
			for (Iterator iter = funCommandMapFun.entrySet().iterator(); iter.hasNext(); ) {
				Entry entry = (Entry) iter.next();
				this.add((Component) entry.getKey());
				((AbstractButton) entry.getKey()).addActionListener(this);
			}
		}

		private void registerFunction(String commandName, String butName, Class funClass) {
			JButton funButton = new JButton(MessageUtil.getMessage(butName));
			funButton.setActionCommand(commandName);
			funCommandMapFun.put(funButton, funClass);
		}

		public void actionPerformed(java.awt.event.ActionEvent ea) {

			String strcmd = ea.getActionCommand();
			for (Iterator iter = funCommandMapFun.entrySet().iterator(); iter.hasNext(); ) {
				Entry entry = (Entry) iter.next();
				if (strcmd.equals(((JButton)entry.getKey()).getActionCommand())) {
					if (strcmd.equals("btnExit")) {
						System.exit(0);
					}
					try {
						Class c = (Class) entry.getValue();
						Constructor constructor = c.getConstructor(new Class[] {MainFrame.class});
						Accessable acc = (Accessable) constructor.newInstance(new Object[]{mainFrame});
						center.removeAll();
						acc.access(mainFrame);
						center.validate();
					} catch (SecurityException e) {
						throw new RuntimeException(e);
					} catch (NoSuchMethodException e) {
						throw new RuntimeException(e);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(e);
					} catch (InstantiationException e) {
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

	public void visit(HelpFrame helpFrame) {
		center.add(helpFrame, BorderLayout.CENTER);
	}

	public void visit(UserFrame userFrame) {
		center.add(userFrame, BorderLayout.CENTER);
	}

	public void visit(BaseInfo baseInfo) {
		center.add(baseInfo, BorderLayout.CENTER);
	}

	public void visit(DingRoom dingRoom) {
		center.add(dingRoom, BorderLayout.CENTER);
	}

	public void visit(FootInfo footInfo) {
		center.add(footInfo, BorderLayout.CENTER);
	}

	public void visit(GuestroomStandardFrame guestroomStandardFrame) {
		center.add(guestroomStandardFrame, BorderLayout.CENTER);
	}

	public void visit(WelcomeFrame welcomeFrame) {
		center.add(welcomeFrame, BorderLayout.CENTER);
	}

	public void visit(VIPGenerate generate) {
		center.add(generate, BorderLayout.CENTER);
	}

	public void visit(dingRoomFrame1 dingRoomFrame1) {
		center.add(dingRoomFrame1, BorderLayout.CENTER);
	}
}