package dbtools;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 包含一些常用的字符串处理方法
 */
public class StringTool {
	/**
	 * 看给定的标识是否包含在以逗号分隔的标识串中
	 * 
	 * @param indicate
	 *            标识
	 * @param indicates
	 *            标识串 return 是否包含
	 */
	public static boolean contains(String indicate, String indicates) {
		// 把标识串转换成列表
		Collection result = stringToLinkedList(indicates);
		// 返回列表是否包含串
		return result.contains(indicate);
	}

	/**
	 * 将字符串转为整数
	 */
	public static int stringToInt(String number) {
		try {
			int result = Integer.parseInt(number);
			return result;
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	/**
	 * 整数转为字符串
	 */
	public static String intToString(int number) {
		String result = String.valueOf(number);
		return result;
	}

	/**
	 * 把以给定字符分割的字符串分解成字符串向量
	 */
	public static LinkedList stringToLinkedList(String source, char ch) {
		LinkedList v = new LinkedList();
		if (source == null || source.equals("")) {
			return v;
		}
		int start = 0;
		int end = source.indexOf(ch);
		while (end != -1) {
			String str = source.substring(start, end);
			v.add(str);
			start = end + 1;
			end = source.indexOf(ch, start);
		}
		String str = source.substring(start);
		v.add(str);
		return v;
	}

	/**
	 * 把以','分割的字符串分解成字符串向量
	 */
	public static LinkedList stringToLinkedList(String source) {
		return stringToLinkedList(source, ',');
	}
}