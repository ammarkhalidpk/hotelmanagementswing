package hotel.form.context;

import hotel.form.main.MainFrame;
import hotel.model.dingroom.DingRoom;
import hotel.service.AbstractServiceCommand;
import hotel.service.CommandService;
import hotel.service.dingroom.DingRoomServiceCommand;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class FormQuery extends BasePanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CenterPanel centerPanel = new CenterPanel();
	
	private static Map commandMap = new HashMap();
	
	private JButton query = new JButton("查询");
	
	static {
		commandMap.put("按时间分类", "qbt");
		commandMap.put("按房态分类", "qbrs");
	}

	public FormQuery(MainFrame parent) {
		super(parent);
		this.setLayout(new BorderLayout());
		this.add(new QueryCommandPanel(this), BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(query, BorderLayout.SOUTH);
		query.setActionCommand("query");
		query.addActionListener(this);
		this.parent.setTitle("客单查询");
	}

	private class QueryCommandPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JComboBox queryType;
		public QueryCommandPanel(final FormQuery formQuery) {
			super();
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.add(new JLabel("查询类型"));
			queryType = new JComboBox();
			this.add(queryType);
			queryType.addItem("按时间分类");
			queryType.addItem("按房态分类");
			queryType.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					fireChange(formQuery);
				}
			});
		}
		
		private void fireChange(FormQuery formQuery) {
			formQuery.buildUI(queryType.getSelectedItem());
		}

	}

	private class CenterPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String queryType = "qbt";
		
		private JComboBox qType;
		
		private JTextField idCard;

		public CenterPanel() {
			buildQBT();
		}

		public String getQueryType() {
			return queryType;
		}

		public void setQueryType(String queryType) {
			this.queryType = queryType;
		}

		public void buildQBT() {
			this.queryType = "qbt";
			this.removeAll();
			JPanel conditionPanel = new JPanel();
			
			conditionPanel.setBorder(new TitledBorder("按时间分类查询"));
			conditionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			conditionPanel.add(new JLabel("查询类型"));
			qType = new JComboBox();
			conditionPanel.add(qType);
			qType.addItem("当前客单");
			qType.addItem("历史客单");
			conditionPanel.add(new JLabel("客户身份证号码"));
			idCard = new JTextField();
			conditionPanel.add(idCard);
			idCard.setColumns(20);
			
			this.setLayout(new BorderLayout());
			this.add(conditionPanel, BorderLayout.NORTH);
			this.add(new JScrollPane(tabShow), BorderLayout.CENTER);
			DefaultTableModel dingRoom = new DefaultTableModel(null, hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
			tabShow.setModel(dingRoom);
			this.validate();
		}

		public void buildQBRS() {
			this.queryType = "qbrs";
			this.removeAll();
			JPanel conditionPanel = new JPanel();
			
			conditionPanel.setBorder(new TitledBorder("按房态分类查询"));
			conditionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			conditionPanel.add(new JLabel("查询类型"));
			qType = new JComboBox();
			conditionPanel.add(qType);
			qType.addItem("入住客单");
			qType.addItem("预订客单");
			qType.addItem("离店客单");
			qType.addItem("欠挂客单");
			conditionPanel.add(new JLabel("客户身份证号码"));
			idCard = new JTextField();
			conditionPanel.add(idCard);
			idCard.setColumns(20);
			
			this.setLayout(new BorderLayout());
			this.add(conditionPanel, BorderLayout.NORTH);
			this.add(new JScrollPane(tabShow), BorderLayout.CENTER);
			DefaultTableModel dingRoom = new DefaultTableModel(null, hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
			tabShow.setModel(dingRoom);
			this.validate();
		}
	}

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

	public void buildUI(Object selectedItem) {
		String queryCommand = (String) commandMap.get(selectedItem);
		if (queryCommand.equals("qbt")) {
			centerPanel.buildQBT();
		} else {
			centerPanel.buildQBRS();
		}
	}

	@Override
	public void refresh() {
		String queryType = centerPanel.getQueryType();
		String idCard = centerPanel.idCard.getText().trim();
		String qType = centerPanel.qType.getSelectedItem().toString();
		Map condition = new HashMap();
		condition.put("idCard", idCard);
		List dataList = (List) CommandService.getInstance().execute(new DingRoomServiceCommand(DingRoomServiceCommand.getAllByIdCardCommand(), condition), false);
		if (queryType.equals("qbt")) {
			if (qType.equals("当前客单")) {
				for (Iterator iter = dataList.iterator(); iter.hasNext(); ) {
					hotel.model.dingroom.DingRoom dingRoom = (DingRoom) iter.next();
					//所有未结算的
					if (dingRoom.getEnd() == null || (dingRoom.getEnd() != null && dingRoom.getFootState().equals("未结算"))) {
						
					} else {
						iter.remove();
					}
				}
				Object[][] datas = AbstractServiceCommand.toArray(dataList); 
				DefaultTableModel dingRoom = new DefaultTableModel(datas, hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
				tabShow.setModel(dingRoom);
			}
			else if (qType.equals("历史客单")) {
				for (Iterator iter = dataList.iterator(); iter.hasNext(); ) {
					hotel.model.dingroom.DingRoom dingRoom = (DingRoom) iter.next();
					//所有已结算的
					if (dingRoom.getEnd() == null || (dingRoom.getEnd() != null && dingRoom.getFootState().equals("未结算"))) {
						iter.remove();
					}
				}
				Object[][] datas = AbstractServiceCommand.toArray(dataList); 
				DefaultTableModel dingRoom = new DefaultTableModel(datas, hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
				tabShow.setModel(dingRoom);
			}
		} 
		else if (queryType.equals("qbrs")) {
			if (qType.equals("入住客单")) {
				for (Iterator iter = dataList.iterator(); iter.hasNext(); ) {
					hotel.model.dingroom.DingRoom dingRoom = (DingRoom) iter.next();
					if (dingRoom.getStart() == null || ((dingRoom.getEnd() != null && dingRoom.getFootState().equals("已结算")))) {
						iter.remove();
					}
				}
				Object[][] datas = AbstractServiceCommand.toArray(dataList); 
				DefaultTableModel dingRoom = new DefaultTableModel(datas, hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
				tabShow.setModel(dingRoom);
			}
			else if (qType.equals("预订客单")) {
				for (Iterator iter = dataList.iterator(); iter.hasNext(); ) {
					hotel.model.dingroom.DingRoom dingRoom = (DingRoom) iter.next();
					if (dingRoom.getStart() != null) {
						iter.remove();
					}
				}
				Object[][] datas = AbstractServiceCommand.toArray(dataList); 
				DefaultTableModel dingRoom = new DefaultTableModel(datas, hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
				tabShow.setModel(dingRoom);
			}
			else if (qType.equals("离店客单")) {
				for (Iterator iter = dataList.iterator(); iter.hasNext(); ) {
					hotel.model.dingroom.DingRoom dingRoom = (DingRoom) iter.next();
					//啥意思？？？
				}
				Object[][] datas = AbstractServiceCommand.toArray(dataList); 
				DefaultTableModel dingRoom = new DefaultTableModel(datas, hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
				tabShow.setModel(dingRoom);
			}
			else if (qType.equals("欠挂客单")) {
				for (Iterator iter = dataList.iterator(); iter.hasNext(); ) {
					hotel.model.dingroom.DingRoom dingRoom = (DingRoom) iter.next();
					//不太清楚？？？？
				}
				Object[][] datas = AbstractServiceCommand.toArray(dataList); 
				DefaultTableModel dingRoom = new DefaultTableModel(datas, hotel.model.dingroom.DingRoom.getFieldMapLabel().values().toArray());
				tabShow.setModel(dingRoom);
			}
		}
		CommandService.getInstance().close();
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("query")) {
			refresh();
		}
	}

}
