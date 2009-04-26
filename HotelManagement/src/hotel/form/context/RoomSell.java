package hotel.form.context;

import hotel.form.main.MainFrame;
import hotel.model.dingroom.DingRoom;
import hotel.model.room.Room;
import hotel.service.CommandService;
import hotel.service.dingroom.DingRoomServiceCommand;
import hotel.service.room.RoomServiceCommand;
import hotel.util.ui.mytable.JComponentCellEditor;
import hotel.util.ui.mytable.JComponentCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class RoomSell extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomSell(MainFrame parent) {
		super(parent);
		parent.setTitle("售房工具");
		this.setLayout(new BorderLayout());
		List rooms = (List) CommandService.getInstance().execute(
				new RoomServiceCommand(RoomServiceCommand.getAllCommand()));
		TableModel tableModel = new RoomInfoTableModel(rooms);
		this.tabShow = getMailTable();
		this.tabShow.setModel(tableModel);
		this.tabShow.setRowHeight(150);
		this.add(new JScrollPane(tabShow), BorderLayout.CENTER);

//		this.tabShow = new mailTableModel(msgs.length);
//		this.tabShow.setModel(mailMod);
//		JLabel fileIcon = new JLabel();
//		fileIcon.setIcon(new ImageIcon(getClass().getResource("/outlook_Icon/file.gif")));
//		this.tabShow.setValueAt(i/* 第几栏 */, 0, fileIcon);
	}

	private class RoomInfoTableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Object[][] roomsArray;

		private int columns = 5;

		public RoomInfoTableModel(List rooms) {
			int intRow = rooms.size() / columns;
			if (rooms.size() % columns != 0) {
				intRow = intRow + 1;
			}
			roomsArray = new Object[intRow][columns];
			for (int i = 0; i < roomsArray.length; i++) {
				for (int j = 0; j < columns; j++) {
					roomsArray[i][j] = createRoom(rooms, i, j);
				}
			}
		}

		private Object createRoom(List rooms, int i, int j) {
			int index = i * columns + j;
			if (index < rooms.size()) {
				JTextArea result = null;
				Room room = (Room) rooms.get(index);
				Map condition = new HashMap();
				condition.put("roomNum", room.getRoomNum());
				List dingRooms = (List) CommandService.getInstance().execute(new DingRoomServiceCommand(DingRoomServiceCommand.getUnfootDingRoomByRoomNumCommand(), condition));
				String useState = null;
				if (dingRooms.size() == 1) {
					hotel.model.dingroom.DingRoom dingRoom = (DingRoom) dingRooms.get(0);
					if (dingRoom.getStart() == null) {
						useState = "预订";
						result = new JTextArea();
						result.setBackground(Color.BLUE);
						result.setSize(20, 20);
						String text = "房间号：" + room.getRoomNum() + 
									  "\r\n房间类型：" + room.getType() + 
									  "\r\n房价：" + room.getPrise() + 
									  "\r\n房态：" + "??" + 
									  "\r\n占用情况：" + useState;
						result.setText(text);
					}
					else if (dingRoom.getEnd() != null && dingRoom.getFootState().equals("未结算")) {
						useState = "未结算";
						result = new JTextArea();
						result.setBackground(Color.CYAN);
						result.setSize(20, 20);
						String text = "房间号：" + room.getRoomNum() + 
									  "\r\n房间类型：" + room.getType() + 
									  "\r\n房价：" + room.getPrise() + 
									  "\r\n房态：" + "??" + 
									  "\r\n占用情况：" + useState;
						result.setText(text);
					}
					else if (dingRoom.getEnd() == null) {
						useState = "使用中";
						result = new JTextArea();
						result.setBackground(Color.RED);
						result.setSize(20, 20);
						String text = "房间号：" + room.getRoomNum() + 
									  "\r\n房间类型：" + room.getType() + 
									  "\r\n房价：" + room.getPrise() + 
									  "\r\n房态：" + "??" + 
									  "\r\n占用情况：" + useState;
						result.setText(text);
					}
				}
				else if (dingRooms.size() == 0) {
					useState = "空闲";
					result = new JTextArea();
					result.setBackground(Color.GREEN);
					result.setSize(20, 20);
					String text = "房间号：" + room.getRoomNum() + 
								  "\r\n房间类型：" + room.getType() + 
								  "\r\n房价：" + room.getPrise() + 
								  "\r\n房态：" + "??" + 
								  "\r\n占用情况：" + useState;
					result.setText(text);
				} else {
					useState = "未知";
					result = new JTextArea();
					result.setBackground(Color.GRAY);
					result.setSize(20, 20);
					String text = "房间号：" + room.getRoomNum() + 
								  "\r\n房间类型：" + room.getType() + 
								  "\r\n房价：" + room.getPrise() + 
								  "\r\n房态：" + "??" + 
								  "\r\n占用情况：" + useState;
					result.setText(text);
				}
				return result;
			}
			JTextArea result = new JTextArea();
			result.setBackground(Color.GRAY);
			result.setSize(20, 20);
			return result;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return JButton.class;
		}

		public int getColumnCount() {
			return columns;
		}

		public int getRowCount() {
			return roomsArray.length;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			return roomsArray[rowIndex][columnIndex];
		}

	}

	public void access(MainFrame vistor) {
		vistor.visit(this);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	private JTable getMailTable() {
		JTable mailTable = new JTable() {
			// 让JComponent可以放入Cell中
			public TableCellRenderer getCellRenderer(int row, int column) {
				TableColumn tableColumn = getColumnModel().getColumn(column);
				TableCellRenderer renderer = tableColumn.getCellRenderer();
				if (renderer == null) {
					Class c = getColumnClass(column);
					if (c.equals(Object.class)) {
						Object o = getValueAt(row, column);
						if (o != null)
							c = getValueAt(row, column).getClass();
					}
					renderer = getDefaultRenderer(c);
				}
				return renderer;
			}

			public TableCellEditor getCellEditor(int row, int column) {
				TableColumn tableColumn = getColumnModel().getColumn(column);
				TableCellEditor editor = tableColumn.getCellEditor();
				if (editor == null) {
					Class c = getColumnClass(column);
					if (c.equals(Object.class)) {
						Object o = getValueAt(row, column);
						if (o != null)
							c = getValueAt(row, column).getClass();
					}
					editor = getDefaultEditor(c);
				}
				return editor;
			}

		};
		mailTable.setDefaultRenderer(JComponent.class,
				new JComponentCellRenderer());
		mailTable
				.setDefaultEditor(JComponent.class, new JComponentCellEditor());
		mailTable
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		mailTable.setShowGrid(false);
		mailTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
		// mailTable.setModel(getDefaultTableModel());
		mailTable.setRowSelectionAllowed(true);
		mailTable.setFont(new java.awt.Font("细明体", java.awt.Font.PLAIN, 12));
		mailTable
				.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		mailTable.addMouseListener(new java.awt.event.MouseListener() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				// showMsgContent(msgs,mailTable.getSelectedRow());
			}

			public void mouseClicked(java.awt.event.MouseEvent e) {
				// showMsgContent(msgs,mailTable.getSelectedRow());
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				// showMsgContent("msgs", mailTable.getSelectedRow());
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
			}
		});
		// 设定Table栏位大小
		// looklook.setMailTableCol();
		return mailTable;
	}
}
