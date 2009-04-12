package hotel.util.ui.mydatechooser;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DateChooserModel {

	private Calendar calendar;

	@SuppressWarnings("unchecked")
	private List dateChangeListener = new LinkedList();

	public DateChooserModel() {
		// 默认系统当前时间
		this.calendar = Calendar.getInstance();
		this.calendar.setLenient(true);
	}

	public Calendar getDefaultTime() {
		Calendar result = Calendar.getInstance();
		result.setLenient(true);
		return result;
	}

	public Calendar getCurrentTime() {
		return calendar;
	}

	private synchronized void fireDateChange() {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss");
		// System.out.println(format.format(calendar.getTime()));
		new Thread(new Runnable() {

			@SuppressWarnings("unchecked")
			public void run() {
				Iterator iter = dateChangeListener.iterator();
				while (iter.hasNext()) {
					DateChangeListener item = (DateChangeListener)

					iter.next();
					Hashtable changeEvent = new Hashtable();
					changeEvent.put("newTime", calendar.clone());
					item.update(changeEvent);
				}
			}

		}).start();

	}

	@SuppressWarnings("unchecked")
	public void addDateChangeListener(DateChangeListener listener) {
		this.dateChangeListener.add(listener);
	}

	/**
	 * 前一年
	 */
	public void getForwardYear() {
		this.calendar.add(Calendar.YEAR, -1);
		fireDateChange();
	}

	/**
	 * 后一年
	 */
	public void getBackwardYear() {
		this.calendar.add(Calendar.YEAR, 1);
		fireDateChange();
	}

	/**
	 * 前一个月
	 */
	public void getForwardMonth() {
		this.calendar.add(Calendar.MONTH, -1);
		fireDateChange();
	}

	/**
	 * 后一个月
	 */
	public void getBackwardMonth() {
		this.calendar.add(Calendar.MONTH, 1);
		fireDateChange();
	}

	/**
	 * 前一天
	 */
	public void getForwardDay() {
		this.calendar.add(Calendar.DAY_OF_MONTH, -1);
		fireDateChange();
	}

	/**
	 * 后一天
	 */
	public void getBackwardDay() {
		this.calendar.add(Calendar.DAY_OF_MONTH, 1);
		fireDateChange();
	}

	/**
	 * 前一小时(24时)
	 */
	public void getForwardHour() {
		this.calendar.add(Calendar.HOUR_OF_DAY, -1);
		fireDateChange();
	}

	/**
	 * 后一小时(24时)
	 */
	public void getBackwardHour() {
		this.calendar.add(Calendar.HOUR_OF_DAY, 1);
		fireDateChange();
	}

	/**
	 * 前一分钟
	 */
	public void getForwardMinute() {
		this.calendar.add(Calendar.MINUTE, -1);
		fireDateChange();
	}

	/**
	 * 后一分钟
	 */
	public void getBackwardMinute() {
		this.calendar.add(Calendar.MINUTE, 1);
		fireDateChange();
	}

	/**
	 * 前一秒
	 */
	public void getForwardSecond() {
		this.calendar.add(Calendar.SECOND, -1);
		fireDateChange();
	}

	/**
	 * 后一秒
	 */
	public void getBackwardSecond() {
		this.calendar.add(Calendar.SECOND, 1);
		fireDateChange();
	}

	public void setNewTime(Calendar currentTime) {
		this.calendar = currentTime;
		fireDateChange();
	}

}