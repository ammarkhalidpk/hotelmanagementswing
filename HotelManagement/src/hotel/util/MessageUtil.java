package hotel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageUtil {
	
	private static Properties messages = null;

	static {
		messages = new Properties();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("./messages_zh_CN.properties");
		try {
			messages.load(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getMessage(String key) {
		return messages.getProperty(key);
	}
	

}
