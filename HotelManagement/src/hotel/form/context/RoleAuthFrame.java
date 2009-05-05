package hotel.form.context;

import hotel.form.main.MainFrame;
import hotel.model.resource.Function;
import hotel.model.resource.RoleFunction;
import hotel.model.user.Role;
import hotel.service.CommandService;
import hotel.service.resource.ResourceServiceCommand;
import hotel.service.user.RoleServiceCommand;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.hibernate.HibernateException;

public class RoleAuthFrame extends BasePanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FunctionPanel funcPanel;
	
	private JComboBox roleBox;

	public RoleAuthFrame(MainFrame parent) {
		super(parent);
		parent.setTitle("角色权限管理");
		this.setLayout(new BorderLayout());
		JSplitPane splitPane = new JSplitPane();
		this.add(splitPane, BorderLayout.CENTER);
		splitPane.setOneTouchExpandable(true);// 是该分隔面板的分隔条显示出箭头
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		roleBox = new JComboBox();
		initRoleBox(roleBox);
		roleBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				fireItemChange();
			}
		});
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(roleBox, BorderLayout.NORTH);
		splitPane.setLeftComponent(leftPanel);// same as: splitPane.setTopComponent(button);
		funcPanel = new FunctionPanel(roleBox);
		JScrollPane jsp = new JScrollPane(funcPanel);
		splitPane.setRightComponent(jsp);// same as: splitPane.setBottomComponent(label);
		// splitPane.setDividerSize (5);//设置分隔条的粗细
		splitPane.setDividerLocation(200);// 设置分隔条的位置，基于setPreferredSize方法中的值，//此处为200/500，大概在中间靠左

		JButton ok = new JButton("确定");
		ok.setActionCommand("ok");
		ok.addActionListener(this);
		this.add(ok, BorderLayout.SOUTH);
	}

	private void initRoleBox(JComboBox roleBox) {
		List allRole = (List) CommandService.getInstance().execute(
				new RoleServiceCommand(RoleServiceCommand.getAllCommand()));
		for (Iterator iter = allRole.iterator(); iter.hasNext();) {
			Role role = (Role) iter.next();
			roleBox.addItem(role);
		}
	}
	
	private void fireItemChange() {
		funcPanel.initData((Role) roleBox.getSelectedItem());
	}
	
	private class FunctionPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		JPanel mainPanel = new JPanel();
		JButton add = new JButton();
		JButton left = new JButton();
		JButton right = new JButton();
		JLabel label = new JLabel();
		JTextField field = new JTextField();
		DefaultListModel leftModel = new DefaultListModel();
		DefaultListModel rightMOdel = new DefaultListModel();
		JList leftList = new JList(leftModel);
		JList rightList = new JList(rightMOdel);

		JPanel left_Right_Panel = new JPanel();
		
		private JComboBox roleBox;

		public FunctionPanel(JComboBox roleBox) {
			this.roleBox = roleBox;
			//this.setPreferredSize(new Dimension(640, 480));//设置此组件的首选大小。如果 preferredSize 为 null，则要求 UI 提供首选大小。
			this.initComponent(roleBox);
			this.initData((Role) roleBox.getSelectedItem());
			this.addData();
		}
		
		public void initData(Role selectedRole) {
			mainPanel.setBorder(BorderFactory.createTitledBorder(selectedRole + "权限设置"));
			((DefaultListModel) leftList.getModel()).clear();
			((DefaultListModel) rightList.getModel()).clear();
			
			selectedRole = (Role) CommandService.getInstance().open().getCurrentExecutionContext().getSession().load(Role.class, selectedRole.getId());
			List authedFunc = selectedRole.getFunctions();
			//首先的到当前角色的授权功能
			for (Iterator iter = authedFunc.iterator(); iter.hasNext(); ) {
				((DefaultListModel) rightList.getModel()).addElement(iter.next());
			}
			
			
			// 初始化可用功能
			List allFunc = (List) CommandService.getInstance().execute(new ResourceServiceCommand(ResourceServiceCommand.getAllCommand()));
			List usableFunc = new ArrayList();
			for (Iterator iter = allFunc.iterator(); iter.hasNext(); ) {
				Function f = (Function) iter.next();
				if (!in(authedFunc, f)) {
					usableFunc.add(f);
					((DefaultListModel) leftList.getModel()).addElement(f);
				}
			}
			CommandService.getInstance().close();
		}

		private boolean in(List authedFunc, Function f) {
			if (authedFunc == null || authedFunc.size() == 0) {
				return false;
			}
			for (Iterator iter = authedFunc.iterator(); iter.hasNext(); ) {
				RoleFunction item = (RoleFunction) iter.next();
				if (item.getFunction().getId() == f.getId()) {
					return true;
				}
			}
			return false;
		}

		/**
		 * 初始化组件
		 */
		public void initComponent(JComboBox roleBox) {
			Role selectdRole = (Role) roleBox.getSelectedItem();
			label.setText("添加选项：");
			add.setText("添加");
//			leftList.setPreferredSize(new Dimension(340, 300));
//			rightList.setPreferredSize(leftList.getPreferredSize());
			left.setText("删除权限");
			right.setText("添加权限");
			mainPanel.setLayout(new GridBagLayout());

			GridBagConstraints c = new GridBagConstraints();

			c.gridx = 0; // 0行0列
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.weighty = 0;
			mainPanel.add(new JLabel("可用功能"), c);

			c.gridx++;
			c.weightx = 1;
			//mainPanel.add(field, c);

			c.gridx++;
			c.weightx = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			// c.fill = GridBagConstraints.HORIZONTAL;
			//mainPanel.add(add, c);
			
			
			c.gridx++;
			mainPanel.add(new JLabel("授权功能"), c);

			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.gridwidth = 2;
			c.gridheight = 2;
			c.fill = GridBagConstraints.BOTH;
			JScrollPane ljsp = new JScrollPane(leftList);
			ljsp.setPreferredSize(new Dimension(350, 30));
			mainPanel.add(ljsp, c);

			c.gridx = 2;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0;
			c.weighty = 0.5;
			c.anchor = GridBagConstraints.SOUTH;
			c.fill = GridBagConstraints.HORIZONTAL;
			mainPanel.add(left, c);

			c.gridx = 2;
			c.gridy = 2;
			c.anchor = GridBagConstraints.NORTH;
			c.fill = GridBagConstraints.HORIZONTAL;
			mainPanel.add(right, c);

			c.gridx = 3;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 2;
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			JScrollPane rjsp = new JScrollPane(rightList);
			rjsp.setPreferredSize(new Dimension(350, 350));
			mainPanel.add(rjsp, c);

			this.add(mainPanel);

		}

		private void addData() {
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addItem();
				}

			});

			left.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					leftItem();
				}

			});

			right.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					rightItem();
				}

			});
		}

		/**
		 * 增加项
		 */
		private void addItem() {
			if (field.getText() != null && !field.getText().equals("")) {
				((DefaultListModel) leftList.getModel()).addElement(field
						.getText());
				field.setText("");
			}
		}

		/**
		 * 左移项
		 */
		private void leftItem() {
			if (rightList.getSelectedIndex() != -1) {
				Object o = rightList.getSelectedValue();
				((DefaultListModel) rightList.getModel()).remove(rightList
						.getSelectedIndex());
				((DefaultListModel) leftList.getModel()).addElement(o);
			}
		}

		/**
		 * 右移项
		 */
		private void rightItem() {
			if (leftList.getSelectedIndex() != -1) {
				Object o = leftList.getSelectedValue();
				((DefaultListModel) leftList.getModel()).remove(leftList
						.getSelectedIndex());
				((DefaultListModel) rightList.getModel()).addElement(o);
			}

		}

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("ok")) {
			try {
				Role selectedRole = (Role) roleBox.getSelectedItem();
				selectedRole = (Role) CommandService.getInstance().open().getCurrentExecutionContext().getSession().load(Role.class, selectedRole.getId());
				selectedRole.getFunctions().clear();
				CommandService.getInstance().close();
				selectedRole = (Role) CommandService.getInstance().open().getCurrentExecutionContext().getSession().load(Role.class, selectedRole.getId());
				Object[] funcs = ((DefaultListModel)funcPanel.rightList.getModel()).toArray();
				for (int i = 0; i < funcs.length; i++) {
					Object item = funcs[i];
					if (item instanceof Function) {
						Function func = (Function) item;
						RoleFunction roleFunction = new RoleFunction();
						roleFunction.setFunction(func);
						CommandService.getInstance().assignId(roleFunction);
						selectedRole.addFunction(roleFunction);
					}
					else if (item instanceof RoleFunction) {
						selectedRole.addFunction((RoleFunction) item);
					}
				}
				CommandService.getInstance().close();
				JOptionPane.showMessageDialog(RoleAuthFrame.this, "设置成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(RoleAuthFrame.this, "设置失败", "错误提示", JOptionPane.ERROR_MESSAGE);
				throw new RuntimeException(e1);
			} 
		}
	}

}
