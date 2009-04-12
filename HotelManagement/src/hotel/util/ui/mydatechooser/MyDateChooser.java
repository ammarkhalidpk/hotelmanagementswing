package hotel.util.ui.mydatechooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.JTextComponent;

/**
 * DateTable,日期列表，日历界面 可得到所选择的时间
 * 
 * ConfigPanel,时间调整面板 事件发布者，发布时间变化的事件
 * 
 * DateChooserModel,时间算则器的模型 时间变化事件监听者，根据时间变化时间改变它自己的状
 * 
 * 态; 同时将它自己改变的事件发布给它的观察者DateTableModel
 * 
 * 
 * 目前想到的不完善的地方：目前还不能根据设定不同的可调整时间精度产不同宽度的界面。
 * 
 * @author ytr
 * 
 */
public class MyDateChooser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3064658102569136946L;

	private JTable dateTable;

	/**
	 * 日历界面的模型，此模型应该是DateChooserModel的观察者
	 */
	private DateTableModel dateTableModel;

	private DateChooserModel dateChooserModel;

	private String precision = "yyyymmddhhttss";

	private String resultFormat = "yyyy-MM-dd hh:mm:ss";

	private JTextComponent targetComponent;

	public MyDateChooser() {
		super();
		dateChooserModel = new DateChooserModel();
		initDateChooser();
		createUI();
	}

	public MyDateChooser(Frame owner) {
		super(owner, "DateChooser", true);
		dateChooserModel = new DateChooserModel();
		initDateChooser();
		createUI();
	}

	public MyDateChooser(Frame owner, String precision) {
		super(owner, "DateChooser", true);
		dateChooserModel = new DateChooserModel();
		this.precision = precision;
		initDateChooser();
		createUI();
	}

	public MyDateChooser(JFrame owner, JTextComponent targetComponent,
			String precision) {
		super(owner, "DateChooser", true);
		dateChooserModel = new DateChooserModel();
		this.precision = precision;
		this.targetComponent = targetComponent;
		initDateChooser();
		createUI();
	}

	public MyDateChooser(Frame owner, JTextComponent targetComponent,
			String precision, String resultFormat) {
		super(owner, "DateChooser", true);
		this.precision = precision;
		this.resultFormat = resultFormat;
		this.targetComponent = targetComponent;
		dateChooserModel = new DateChooserModel();
		initDateChooser();
		createUI();
	}

	public void setResultFormat(String resultFormat) {
		this.resultFormat = resultFormat;
	}

	protected void initDateChooser() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(410, 175);
		this.setLocation(this.targetComponent.getLocationOnScreen().x,
				this.targetComponent.getLocationOnScreen().y
						+ this.targetComponent.getHeight());
		this.getContentPane().setLayout(new BorderLayout());
	}

	protected void createUI() {
		createDateTable();
		createConfigPanel();
	}

	protected void createDateTable() {
		dateTableModel = new DateTableModel(dateChooserModel);
		this.dateTable = new DateTable(this, dateTableModel);
		dateTableModel.setTable(dateTable);
		dateChooserModel.addDateChangeListener(dateTableModel);
		this.getContentPane().add(this.dateTable, BorderLayout.CENTER);
	}

	protected void createConfigPanel() {
		ConfigPanel configPanel = new ConfigPanel(dateChooserModel, precision);
		dateChooserModel.addDateChangeListener(configPanel);
		this.getContentPane().add(configPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		final JFrame frame = new JFrame("Test MyDateChooser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 200, 55);
		final JTextField targetComponent = new JTextField();
		targetComponent.setColumns(17);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.add(targetComponent);
		frame.setVisible(true);
		targetComponent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// 使用方法：
					// frame：该对话框的所有者；
					// targetComponent：目标组件，接收结果时间串的组件；
					// "yyyymmddhhttss"：可调整的时间精度；
					// "yyyy年MM月dd日 hh点mm分ss秒"：得到的时间串格式
					MyDateChooser dateChooser = new MyDateChooser(frame,
							targetComponent, "yyyymmddhhttss",
							"yyyy年MM月dd日 hh点mm分ss秒");
					dateChooser.setVisible(true);
				}
			}
		});
	}

	public void completedTask(Calendar currentTime) {
		SimpleDateFormat format = new SimpleDateFormat(this.resultFormat);
		targetComponent.setText(format.format(currentTime.getTime()));
		this.dispose();
	}

}

class DateTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7571077481483980294L;

	private DateTableModel model;

	public DateTable(final MyDateChooser parent, DateTableModel dateTableModel) {
		super(dateTableModel);
		this.model = dateTableModel;

		// table 表现器构造--------------------//

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1451195281907827190L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {

				// 单元格文字居中对齐
				setHorizontalAlignment(JLabel.CENTER);

				// 设置 背景 颜色-----------//
				if (row == 0)
					// 星期文字行背景色
					setBackground(Pallet.backGroundColor);
				else if (("" + Calendar.getInstance()
						.get(Calendar.DAY_OF_MONTH)).equals(model.getValueAt(
						row, column))) {
					// 今天格背景色
					setBackground(Pallet.todayBackColor);
				} else
					// 普通格背景色
					setBackground(Pallet.palletTableColor);

				// 设置 前景 颜色-----------//
				if ((column == 0 && row != 0) || (column == 6 && row != 0))
					// 周末格字体色
					setForeground(Pallet.weekendFontColor);
				else if (row != 0 && column != 0 && column != 6)
					// 普通格字体色
					setForeground(Pallet.dateFontColor);
				else
					// 星期文字格字体色
					setForeground(Pallet.weekFontColor);

				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);

			}
		};

		// 设置列表现器------------------------//
		String[] tableHead = new String[] { "日", "一", "二", "三", "四", "五", "六" };
		for (int i = 0; i < tableHead.length; i++) {
			this.getColumn(tableHead[i]).setCellRenderer(tcr);
		}

		// table 配置--------------------------//

		// 设置表是否绘制单元格之间的水平线
		this.setShowHorizontalLines(false);
		// 设置表是否绘制单元格之间的垂直线
		this.setShowVerticalLines(false);

		// 设置是否可以选择此模型中的行
		this.setRowSelectionAllowed(false);
		// 设置是否可以选择此模型中的列
		this.setColumnSelectionAllowed(false);

		// 指定单元格之间新高度和宽度
		this.setIntercellSpacing(new Dimension(0, 0));

		// 添加监听器
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = getSelectedRow();
					int column = getSelectedColumn();
					if (row > 0) {
						String day = (String) model.getValueAt

						(row, column);
						if (!day.equals("")) {
							Calendar currentTime =

							model.getCurrentTime();
							currentTime.set

							(Calendar.DAY_OF_MONTH, Integer.parseInt

							(day));
							parent.completedTask

							(currentTime);
						}
					}
				}
			}
		});
	}

	public void updateUI() {
		super.updateUI();
	}

}

class DateTableModel extends AbstractTableModel implements DateChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6469747274565443229L;

	private String[] tableHead = new String[] { "日", "一", "二", "三", "四", "五",
			"六" };

	private String[][] days;

	private JTable dateTable;

	private Calendar currentTime;

	public DateTableModel(DateChooserModel dateChooserModel) {
		Calendar defaultTime = dateChooserModel.getDefaultTime();
		// 用defaultTime进行初始化日历主界面数据
		initDays(defaultTime);
	}

	private void initDays(Calendar defaultTime) {
		this.currentTime = defaultTime;
		// 确定当前月的最大天数
		int maxDayOfMonth = defaultTime.getActualMaximum

		(Calendar.DAY_OF_MONTH);
		// 找到当月的第一天是星期几
		defaultTime.set(Calendar.DAY_OF_MONTH, 1);
		int firstWeekStart = defaultTime.get(Calendar.DAY_OF_WEEK);
		// 确定days数组最大行数
		int maxRow = 0;
		if ((maxDayOfMonth + firstWeekStart - 1) % 7 == 0) {
			maxRow = (maxDayOfMonth + firstWeekStart - 1) / 7;
		} else {
			maxRow = (maxDayOfMonth + firstWeekStart - 1) / 7 + 1;
		}
		// 初始化数组
		this.days = new String[maxRow][7];
		for (int i = 0; i < days.length; i++) {
			Arrays.fill(days[i], "");
		}
		// 填充数据
		int dayCount = 1;
		for (int i = firstWeekStart - 1; i < (maxDayOfMonth + firstWeekStart -

		1); i++) {
			days[i / 7][i % 7] = (dayCount++) + "";
		}

		String[][] tempDays = new String[days.length + 1][];
		tempDays[0] = tableHead;
		for (int i = 1; i < tempDays.length; i++) {
			tempDays[i] = days[i - 1];
		}
		days = tempDays;
	}

	public void setTable(JTable dateTable) {
		this.dateTable = dateTable;
	}

	public String getColumnName(int column) {
		return this.tableHead[column];
	}

	public int getColumnCount() {
		return this.tableHead.length;
	}

	public int getRowCount() {
		return days.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return days[rowIndex][columnIndex];
	}

	@SuppressWarnings("unchecked")
	public void update(Hashtable changeEvent) {
		Calendar newTime = (Calendar) changeEvent.get("newTime");
		this.updateUI(newTime);
	}

	private void updateUI(Calendar newTime) {
		initDays(newTime);
		this.dateTable.updateUI();
	}

	public Calendar getCurrentTime() {
		return (Calendar) this.currentTime.clone();
	}

}

class ConfigPanel extends JPanel implements DateChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3468093529305634205L;

	private boolean flag = true;

	private JTextField year;
	private JTextField month;
	private JTextField day;
	private JTextField hour;
	private JTextField minute;
	private JTextField second;

	private JButton yearUp = new JButton("+");
	private JButton yearDown = new JButton("-");
	private JButton monthUp = new JButton("+");
	private JButton monthDown = new JButton("-");
	private JButton dayUp = new JButton("+");
	private JButton dayDown = new JButton("-");
	private JButton hourUp = new JButton("+");
	private JButton hourDown = new JButton("-");
	private JButton minuteUp = new JButton("+");
	private JButton minuteDown = new JButton("-");
	private JButton secondUp = new JButton("+");
	private JButton secondDown = new JButton("-");

	public ConfigPanel(final DateChooserModel model, String precision) {

		this.setBackground(Color.gray);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		Font txtFont = new Font("宋体", Font.PLAIN, 12);
		if (precision.indexOf("yyyy") != -1) {
			yearUp.setPreferredSize(new Dimension(15, 7));
			yearUp.setBorder(new LineBorder(yearUp.getForeground(), 1));
			yearUp.setFont(txtFont);
			yearDown.setPreferredSize(new Dimension(15, 7));
			yearDown.setBorder(new LineBorder(yearDown.getForeground(), 1));
			yearDown.setFont(txtFont);

			year = new JTextField(model.getDefaultTime().get(Calendar.YEAR)
					+ "");
			year.setColumns(4);
			this.add(year);
			JPanel yearButton = new JPanel(new BorderLayout());
			yearButton.add(yearUp, BorderLayout.NORTH);
			yearButton.add(yearDown, BorderLayout.SOUTH);
			this.add(yearButton);
			this.add(new JLabel("年"));

			yearUp.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getBackwardYear

								();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}
			});
			yearDown.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getForwardYear();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			year.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							model.setNewTime

							(getCurrentTime());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog

							(null, e1.getMessage

							(), "错误提示",

							JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}

		if (precision.indexOf("mm") != -1) {
			monthUp.setPreferredSize(new Dimension(15, 7));
			monthUp.setBorder(new LineBorder(monthUp.getForeground(), 1));
			monthUp.setFont(txtFont);
			monthDown.setPreferredSize(new Dimension(15, 7));
			monthDown.setBorder(new LineBorder(monthDown.getForeground(),

			1));
			monthDown.setFont(txtFont);

			month = new JTextField((model.getDefaultTime().get(Calendar.MONTH) +

			1) + "");
			month.setColumns(2);
			this.add(month);
			JPanel monthButton = new JPanel(new BorderLayout());
			monthButton.add(monthUp, BorderLayout.NORTH);
			monthButton.add(monthDown, BorderLayout.SOUTH);
			this.add(monthButton);
			this.add(new JLabel("月"));

			monthUp.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getBackwardMonth

								();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			monthDown.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getForwardMonth

								();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			month.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							model.setNewTime

							(getCurrentTime());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog

							(null, e1.getMessage

							(), "错误提示",

							JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}

		if (precision.indexOf("dd") != -1) {
			dayUp.setPreferredSize(new Dimension(15, 7));
			dayUp.setBorder(new LineBorder(dayUp.getForeground(), 1));
			dayUp.setFont(txtFont);
			dayDown.setPreferredSize(new Dimension(15, 7));
			dayDown.setBorder(new LineBorder(dayDown.getForeground(), 1));
			dayDown.setFont(txtFont);

			day = new JTextField(model.getDefaultTime().get(
					Calendar.DAY_OF_MONTH)
					+ "");
			day.setColumns(2);
			this.add(day);
			JPanel dayButton = new JPanel(new BorderLayout());
			dayButton.add(dayUp, BorderLayout.NORTH);
			dayButton.add(dayDown, BorderLayout.SOUTH);
			this.add(dayButton);
			this.add(new JLabel("日"));

			dayUp.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getBackwardDay();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			dayDown.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getForwardDay();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			day.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							model.setNewTime

							(getCurrentTime());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog

							(null, e1.getMessage

							(), "错误提示",

							JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}

		if (precision.indexOf("hh") != -1) {
			hourUp.setPreferredSize(new Dimension(15, 7));
			hourUp.setBorder(new LineBorder(hourUp.getForeground(), 1));
			hourUp.setFont(txtFont);
			hourDown.setPreferredSize(new Dimension(15, 7));
			hourDown.setBorder(new LineBorder(hourDown.getForeground(),

			1));
			hourDown.setFont(txtFont);

			hour = new JTextField(model.getDefaultTime().get(
					Calendar.HOUR_OF_DAY)
					+ "");
			hour.setColumns(2);
			this.add(hour);
			JPanel hourButton = new JPanel(new BorderLayout());
			hourButton.add(hourUp, BorderLayout.NORTH);
			hourButton.add(hourDown, BorderLayout.SOUTH);
			this.add(hourButton);

			hourUp.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getBackwardHour

								();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			hourDown.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getForwardHour();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			hour.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							model.setNewTime

							(getCurrentTime());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog

							(null, e1.getMessage

							(), "错误提示",

							JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}

		if (precision.indexOf("tt") != -1) {
			minuteUp.setPreferredSize(new Dimension(15, 7));
			minuteUp.setBorder(new LineBorder(minuteUp.getForeground(),

			1));
			minuteUp.setFont(txtFont);
			minuteDown.setPreferredSize(new Dimension(15, 7));
			minuteDown.setBorder(new LineBorder(minuteDown.getForeground(),

			1));
			minuteDown.setFont(txtFont);

			this.add(new JLabel(":"));
			minute = new JTextField(model.getDefaultTime().get

			(Calendar.MINUTE) + "");
			minute.setColumns(2);
			this.add(minute);
			JPanel minuteButton = new JPanel(new BorderLayout());
			minuteButton.add(minuteUp, BorderLayout.NORTH);
			minuteButton.add(minuteDown, BorderLayout.SOUTH);
			this.add(minuteButton);

			minuteUp.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}

								model.getBackwardMinute();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			minuteDown.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getForwardMinute

								();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			minute.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							model.setNewTime

							(getCurrentTime());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog

							(null, e1.getMessage

							(), "错误提示",

							JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}

		if (precision.indexOf("ss") != -1) {
			secondUp.setPreferredSize(new Dimension(15, 7));
			secondUp.setBorder(new LineBorder(secondUp.getForeground(),

			1));
			secondUp.setFont(txtFont);
			secondDown.setPreferredSize(new Dimension(15, 7));
			secondDown.setBorder(new LineBorder(secondDown.getForeground(),

			1));
			secondDown.setFont(txtFont);

			this.add(new JLabel(":"));
			second = new JTextField(model.getDefaultTime().get

			(Calendar.SECOND) + "");
			second.setColumns(2);
			this.add(second);
			JPanel secondButton = new JPanel(new BorderLayout());
			secondButton.add(secondUp, BorderLayout.NORTH);
			secondButton.add(secondDown, BorderLayout.SOUTH);
			this.add(secondButton);

			secondUp.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}

								model.getBackwardSecond();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			secondDown.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					new Thread(new Runnable() {

						public void run() {
							for (;;) {
								if (!flag) {
									break;
								}
								model.getForwardSecond

								();
								try {
									Thread.sleep

									(200);
								} catch

								(InterruptedException e) {
									throw new

									RuntimeException(e);
								}
							}
							flag = true;
						}

					}).start();

				}

				public void mouseReleased(MouseEvent e) {
					flag = false;
				}

			});
			second.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							model.setNewTime

							(getCurrentTime());
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog

							(null, e1.getMessage

							(), "错误提示",

							JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}
	}

	private Calendar getCurrentTime() throws NumberFormatException {
		Calendar result = Calendar.getInstance();
		if (year != null) {
			try {
				result.set(Calendar.YEAR, Integer.parseInt

				(year.getText()));
			} catch (NumberFormatException e) {
				throw e;
			}
		}
		if (month != null) {
			try {
				result.set(Calendar.MONTH,
						Integer.parseInt(month.getText()) - 1);
			} catch (NumberFormatException e) {
				throw e;
			}
		}
		if (day != null) {
			try {
				result.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day
						.getText()));
			} catch (NumberFormatException e) {
				throw e;
			}
		}
		if (hour != null) {
			try {
				result.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour
						.getText()));
			} catch (NumberFormatException e) {
				throw e;
			}
		}
		if (minute != null) {
			try {
				result.set(Calendar.MINUTE, Integer.parseInt

				(minute.getText()));
			} catch (NumberFormatException e) {
				throw e;
			}
		}
		if (second != null) {
			try {
				result.set(Calendar.SECOND, Integer.parseInt

				(second.getText()));
			} catch (NumberFormatException e) {
				throw e;
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void update(Hashtable changeEvent) {
		Calendar newTime = (Calendar) changeEvent.get("newTime");
		this.updateUI(newTime);
	}

	private void updateUI(Calendar newTime) {
		if (year != null) {
			year.setText(newTime.get(Calendar.YEAR) + "");
		}
		if (month != null) {
			month.setText((newTime.get(Calendar.MONTH) + 1) + "");
		}
		if (day != null) {
			day.setText(newTime.get(Calendar.DAY_OF_MONTH) + "");
		}
		if (hour != null) {
			hour.setText(newTime.get(Calendar.HOUR_OF_DAY) + "");
		}
		if (minute != null) {
			minute.setText(newTime.get(Calendar.MINUTE) + "");
		}
		if (second != null) {
			second.setText(newTime.get(Calendar.SECOND) + "");
		}
	}
}

class Pallet {
	public static Color backGroundColor = Color.gray; // 底色

	// 月历表格配色----------------//
	public static Color palletTableColor = Color.white; // 日历表底色
	public static Color todayBackColor = Color.gray;// Color.orange; // 今天背景色

	public static Color weekFontColor = Color.white; // 星期文字色
	public static Color dateFontColor = Color.black; // 日期文字色
	public static Color weekendFontColor = Color.red; // 周末文字色

	// 控制条配色------------------//
	public static Color configLineColor = Color.pink; // 控制条底色
	public static Color cfgTextColor = Color.white; // 控制条标签文字色

	public static Color rbFontColor = Color.white; // RoundBox文字色
	public static Color rbBorderColor = Color.red; // RoundBox边框色
	public static Color rbButtonColor = Color.pink; // RoundBox按钮色
	public static Color rbBtFontColor = Color.red; // RoundBox按钮文字色
}

interface DateChangeListener {
	@SuppressWarnings("unchecked")
	public void update(Hashtable changeEvent);
}