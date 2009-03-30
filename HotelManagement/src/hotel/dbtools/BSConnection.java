package hotel.dbtools;

import java.sql.Connection;

/**
 * 对数据库连接进行包装，多了数据类型以及字符集两个属性
 */
public class BSConnection {
	/**
	 * 数据库的连接
	 */
	private Connection connection = null;

	/**
	 * 字符串字段的描述符
	 */
	private String stringType = null;

	/**
	 * 数字字段的描述符
	 */
	private String numberType = null;

	/**
	 * 字符集
	 */
	private String characterset = null;

	/**
	 * 默认构造
	 */
	public BSConnection() {
		try {
			this.connection = ConnectionPool.getDBConnection();
			this.stringType = ConnectionPool.stringType;
			this.numberType = ConnectionPool.numberType;
			this.characterset = ConnectionPool.characterset;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用连接，字符串字段描述符，数字字段描述符以及字符集构造
	 */
	public BSConnection(Connection connection, String stringType,
			String numberType, String characterset) {
		this.connection = connection;
		this.stringType = stringType;
		this.numberType = numberType;
		this.characterset = characterset;
	}

	/**
	 * 得到连接
	 */
	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * 得到字符串字段描述符
	 */
	public String getStringType() {
		return this.stringType;
	}

	/**
	 * 得到数字字段描述符
	 */
	public String getNumberType() {
		return this.numberType;
	}

	/**
	 * 得到字符集
	 */
	public String getCharacterset() {
		return this.characterset;
	}
}