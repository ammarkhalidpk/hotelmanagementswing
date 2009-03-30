package hotel.dbtools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import org.w3c.dom.Element;

/**
 * 带连接池的数据库连接管理器
 */
public class ConnectionPool {
	/**
	 * 连接池，存放连接
	 */
	private static LinkedList list = new LinkedList();

	/**
	 * 最大连接数
	 */
	private static int maxCount = 0;

	/**
	 * 进行数据库连接时的用户名
	 */
	private static String user = "";

	/**
	 * 进行数据库连接时的用户密码
	 */
	private static String pw = "";

	/**
	 * 进行数据库连接时的驱动程序的URL
	 */
	private static String dbURL = "";

	/**
	 * 字串类型
	 */
	public static String stringType = "";

	/**
	 * 数字类型
	 */
	public static String numberType = "";

	/**
	 * 字符集
	 */
	public static String characterset = "";

	private static boolean init = false;

	/**
	 * 将使用后的连接放到连接缓冲池中，如果缓冲池已满，关闭连接
	 * 
	 * @param conn
	 *            连接
	 */
	public static synchronized void putDBConnection(Connection conn)
			throws SQLException {
		// 缓冲池没有满，添加
		if (list.size() < maxCount) {
			list.add(conn);
			// 否则，关闭
		} else {
			conn.close();
		}
	}

	/**
	 * 从连接池取得数据库连接，如果连接池已空，生成新连接
	 * 
	 * @return 数据库连接
	 * @throws Exception
	 */
	public static synchronized Connection getDBConnection() throws Exception {
		while (list.size() != 0) {
			// 取得第一项连接
			Connection conn = (Connection) list.remove(0);
			// 没有关闭，返回
			if (!conn.isClosed()) {
				return conn;
			}
		}
		if (!init) {
			load();
			init = true;
		}
		// 缓冲池为空，创建新连接, 并添加到连接池中
		return DriverManager.getConnection(dbURL, user, pw);
	}

	/**
	 * 根据属性文件的内容装载连接
	 */
	public static void load() throws Exception {
		// 最大连接数
		maxCount = Integer.parseInt(PropertiesService.getProperty("Database",
				"maxCount"));
		// URL
		dbURL = PropertiesService.getProperty("Database", "URL");
		// 用户名
		user = PropertiesService.getProperty("Database", "user");
		// 密码
		pw = PropertiesService.getProperty("Database", "password");
		// 字串类型
		stringType = PropertiesService.getProperty("Database", "stringtype");
		// 数字类型
		numberType = PropertiesService.getProperty("Database", "numbertype");
		// 字符集
		characterset = PropertiesService.getProperty("characterset", "type");
		// 得到驱动程序
		String jdbcDriver = PropertiesService.getProperty("Database", "driver");
		Class.forName(jdbcDriver);
		// 连接数
		int connCount = Integer.parseInt(PropertiesService.getProperty(
				"Database", "connCount"));
		// 生成默认的连接
		for (int i = 0; i < connCount; i++) {
			System.out.println("url:" + dbURL);
			Connection conn = DriverManager.getConnection(dbURL, user, pw);
			putDBConnection(conn);
		}
	}

	/**
	 * 创建临时连接，使用完后由外层自己进行关闭
	 */
	public static Connection createTempConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(dbURL, user, pw);
			return conn;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据给定的元素描述得到一条连接
	 * 
	 * @param element
	 *            描述连接属性的元素
	 * @return 一条数据库连接
	 */
	public static BSConnection getDBConnection(Element element) {
		try {
			// URL
			String dbURL = element.getAttribute("URL");
			// 用户名
			String user = element.getAttribute("user");
			// 密码
			String pw = element.getAttribute("password");
			// 得到驱动程序
			String jdbcDriver = element.getAttribute("driver");
			Class.forName(jdbcDriver);
			Connection connection = DriverManager
					.getConnection(dbURL, user, pw);
			// 字串类型
			String stringType = element.getAttribute("stringtype");
			// 数字类型
			String numberType = element.getAttribute("numbertype");
			// 字符集
			String characterset = element.getAttribute("characterset");
			return new BSConnection(connection, stringType, numberType,
					characterset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}