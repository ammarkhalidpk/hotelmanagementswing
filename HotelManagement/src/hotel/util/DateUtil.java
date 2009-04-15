package hotel.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class DateUtil {
	
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}
	
	public static String getNowAsString() {
		return getNowAsString("yyyy-MM-dd hh:mm:ss");
	}
	
	public static String getNowAsString(String pattern) {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(getNow());
	}
	
	public static String getString(Date date) {
		return getString(date, "yyyy-MM-dd hh:mm:ss");
	}
	
	public static String getString(Date date, String pattern) {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}
	
	public static Date getDate(String dateString) {
		return getDate(dateString, "yyyy-MM-dd hh:mm:ss");
	}
	
	public static Date getDate(String dateString, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = "yyyy-MM-dd hh:mm:ss";
		}
		Map map = new LinkedHashMap();
		map.put("y", new ArrayList());
		map.put("M", new ArrayList());
		map.put("d", new ArrayList());
		map.put("h", new ArrayList());
		map.put("m", new ArrayList());
		map.put("s", new ArrayList());
		String pat = pattern;
		for (;!pat.equals("");) {
			String token = pat.substring(0, 1);
			pat = pat.substring(1);
			if (isMatch(token, map)) {
				putInMap(token, map);
			}
		}
		//yyyy
		int yyyy = 0;
		int ySize = ((List)map.get("y")).size();
		if (ySize > 0) {
			yyyy = Integer.parseInt(dateString.substring(pattern.indexOf("y"), pattern.indexOf("y") + ySize));
		} else {
			yyyy = getNow().getYear();
		}
		
		//mm
		int MM = 0;
		int MSize = ((List)map.get("M")).size();
		if (MSize > 0) {
			MM = Integer.parseInt(dateString.substring(pattern.indexOf("M"), pattern.indexOf("M") + MSize));
		} else {
			MM = getNow().getYear();
		}
		
		//dd
		int dd = 0;
		int dSize = ((List)map.get("d")).size();
		if (dSize > 0) {
			dd = Integer.parseInt(dateString.substring(pattern.indexOf("d"), pattern.indexOf("d") + dSize));
		} else {
			dd = getNow().getDate();
		}
		
		//hh
		int hh = 0;
		int hSize = ((List)map.get("h")).size();
		if (hSize > 0) {
			hh = Integer.parseInt(dateString.substring(pattern.indexOf("h"), pattern.indexOf("h") + hSize));
		} else {
			hh = 0;
		}
		
		//mm
		int mm = 0;
		int mSize = ((List)map.get("m")).size();
		if (mSize > 0) {
			mm = Integer.parseInt(dateString.substring(pattern.indexOf("m"), pattern.indexOf("m") + mSize));
		} else {
			mm = 0;
		}
		
		//ss
		int ss = 0;
		int sSize = ((List)map.get("s")).size();
		if (sSize > 0) {
			ss = Integer.parseInt(dateString.substring(pattern.indexOf("s"), pattern.indexOf("s") + sSize));
		} else {
			ss = 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, yyyy);
		cal.set(Calendar.MONTH, MM - 1);
		cal.set(Calendar.DAY_OF_MONTH, dd);
		cal.set(Calendar.HOUR, hh);
		cal.set(Calendar.MINUTE, mm);
		cal.set(Calendar.SECOND, ss);
		return cal.getTime();
		//return new Date(yyyy, MM, dd, hh, mm, ss);
	}

	private static void putInMap(String token, Map map) {
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
			Entry entry = (Entry) iter.next();
			String key = (String) entry.getKey();
			List tokens = (List) entry.getValue();
			if (key.equals(token)) {
				tokens.add(token);
			}
		}
	}

	private static boolean isMatch(String token, Map map) {
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
			Entry entry = (Entry) iter.next();
			String key = (String) entry.getKey();
			if (key.equals(token)) {
				return true;
			}
		}
		return false;
	}

}
