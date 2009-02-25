package cg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cx.BaseInfo;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginDataBase dbo;
	private LoginFrame lf;
	private javax.swing.JButton btnstaRoom, btnbaseInfo, btndingRoom,
			btnfootInfo, btnExit;
	private javax.swing.JButton btnUser, btnHelp;
	private javax.swing.JPanel pan;
	private javax.swing.JLabel lab;

	public MainFrame() {// 主窗体的构造方法

		LoginDataBase dbo = new LoginDataBase();
		// connection = consd.LoginDa;

		System.out.println("ok");

		java.awt.Container me = this.getContentPane();

		lab = new JLabel("请按键选择下面功能");
		lab.setForeground(Color.red);
		lab.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		lab.setHorizontalAlignment(SwingConstants.CENTER);
		lab.setBounds(new Rectangle(165, 10, 260, 29));

		me.setLayout(new BorderLayout());
		me.add(new WestPanel(), BorderLayout.WEST);
		me.add(new EastPanel(), BorderLayout.CENTER);
		me.add(lab, BorderLayout.NORTH);

		this.addWindowListener(new WindowCloseEvent());

		this.setTitle("水星酒店");
		this.setSize(800, 400);
		// this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this);// 居中显示
		this.setVisible(true);
	}

	private class WindowCloseEvent extends WindowAdapter {

		public void windowClosing(WindowEvent e) {

			// dbo.closeConnection();

		}
	}

	private class EastPanel extends JPanel {
		public EastPanel() {

			// pan = new JPanel();

		}
	}

	private class WestPanel extends JPanel implements ActionListener {

		public WestPanel() {

			// pan = new JPanel();

			// pan.setLayout(null);
			this.setLayout(new java.awt.GridLayout(7, 1));

			btnstaRoom = new JButton("客房标准的制定");
			// btnstaRoom.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
			// btnstaRoom.setHorizontalAlignment(SwingConstants.CENTER);
			// btnstaRoom.setBounds(new Rectangle(82,60,200,29));
			//			
			btnbaseInfo = new JButton("客房基本信息");
			// btnbaseInfo.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
			// btnbaseInfo.setHorizontalAlignment(SwingConstants.CENTER);
			// btnbaseInfo.setBounds(new Rectangle(150,120,200,29));
			//			
			btndingRoom = new JButton("订房信息");
			// btndingRoom.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
			// btndingRoom.setHorizontalAlignment(SwingConstants.CENTER);
			// btndingRoom.setBounds(new Rectangle(82,210,200,29));
			//			
			btnfootInfo = new JButton("结算信息");
			// btnfootInfo.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
			// btnfootInfo.setHorizontalAlignment(SwingConstants.CENTER);
			// btnfootInfo.setBounds(new Rectangle(82,270,200,29));
			//			
			btnUser = new JButton("用户管理");
			// btnUser.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
			// btnUser.setHorizontalAlignment(SwingConstants.CENTER);
			// btnUser.setBounds(new Rectangle(82,330,200,29));

			btnHelp = new JButton("帮助信息");
			// btnHelp.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
			// btnHelp.setHorizontalAlignment(SwingConstants.CENTER);
			// btnHelp.setBounds(new Rectangle(82,390,200,29));

			btnExit = new JButton("退出");
			// btnExit.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
			// btnExit.setHorizontalAlignment(SwingConstants.CENTER);
			// btnExit.setBounds(new Rectangle(82,510,80,29));
			//	

			this.add(btnstaRoom);
			this.add(btnbaseInfo);
			this.add(btndingRoom);
			this.add(btnfootInfo);
			this.add(btnUser);
			this.add(btnHelp);

			this.add(btnExit);

			btnstaRoom.addActionListener(this);
			btnbaseInfo.addActionListener(this);
			btndingRoom.addActionListener(this);
			btnfootInfo.addActionListener(this);
			btnUser.addActionListener(this);
			btnHelp.addActionListener(this);

			btnExit.addActionListener(this);

			btnstaRoom.setActionCommand("sta");
			btnbaseInfo.setActionCommand("base");
			btndingRoom.setActionCommand("dingfang");
			btnfootInfo.setActionCommand("tuifang");
			btnUser.setActionCommand("user");
			btnHelp.setActionCommand("help");

			btnExit.setActionCommand("exit");

		}

		public void actionPerformed(java.awt.event.ActionEvent ea) {
			// lf = new LoginFrame();
			// this.dispose();//登陆窗体与主窗体唯一显示

			String strcmd = ea.getActionCommand();
			if (strcmd.equals("sta")) {

				new GuestroomStandardFrame();

			}
			if (strcmd.equals("base")) {
				new BaseInfo();
			}
			if (strcmd.equals("dingfang")) {
				new dingRoom();
			}
			if (strcmd.equals("tuifang")) {
				new footInfo();
			}
			if (strcmd.equals("user")) {

				new UserFrame();

			}
			if (strcmd.equals("help")) {

				new HelpFrame();
			}

			if (strcmd.equals("exit")) {
				System.exit(0);
			}
		}
	}
}