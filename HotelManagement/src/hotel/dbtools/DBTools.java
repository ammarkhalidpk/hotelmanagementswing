package hotel.dbtools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * 数据库工具类，提供了简单访问数据库的方法
 */
public class DBTools {
	/**
	 * 产生建表等各种SQL语句的SQL语句产生器
	 */
	private static SQLStatement sqlStatement = null;

	/**
	 * 对应的连接
	 */
	private BSConnection connection = null;

	static {
		try {
			// 根据属性文件创建SQL语句产生器
			String driver = PropertiesService.getProperty("Database",
					"SQLStatement");
			sqlStatement = (SQLStatement) Class.forName(driver).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SQLStatement getSQLStatement() {
		return sqlStatement;
	}

	/**
	 * 空构造
	 */
	public DBTools() {
		try {
			this.connection = new BSConnection(
					ConnectionPool.getDBConnection(),
					ConnectionPool.stringType, ConnectionPool.numberType,
					ConnectionPool.characterset);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用数据库连接构造
	 * 
	 * @param connection
	 *            数据库连接
	 */
	public DBTools(BSConnection connection) {
		this.connection = connection;
	}

	/**
	 * 关闭
	 */
	public void close() {
		try {
			ConnectionPool.putDBConnection(this.connection.getConnection());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据表名得到表中字段的数据类型
	 * 
	 * @param tableName
	 *            表名，大小写不敏感
	 * @return 哈息表 字段名：字段类型 表的所有字段及其类型，字段名为大写
	 */
	public static Hashtable getDataTypes(String tableName) throws Exception {
		return sqlStatement.getDataTypes(tableName);
	}

	/**
	 * 执行删除语句
	 * 
	 * @param tableName
	 *            表名，大小写不敏感
	 * @param ID
	 *            要删除的对象的ID号
	 */
	public static void delete(String tableName, String ID) throws Exception {
		String sql = "delete from " + tableName + " where ID = " + ID;
		Connection connection = ConnectionPool.getDBConnection();
		// 创建语句
		Statement state = connection.createStatement();
		// 执行语句
		try {
			sql = new String(sql.getBytes(), ConnectionPool.characterset);
			state.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("SQL错误：" + sql);
			throw e;
		} finally {
			state.close();
			ConnectionPool.putDBConnection(connection);
		}
	}

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @throws SQLException
	 */
	public static void excuteUpdate(String sql) throws Exception {
		Connection connection = ConnectionPool.getDBConnection();
		// 创建语句
		Statement state = connection.createStatement();
		// 执行语句
		try {
			sql = new String(sql.getBytes(), ConnectionPool.characterset);
			state.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("SQL错误：" + sql);
			throw e;
		} finally {
			state.close();
			ConnectionPool.putDBConnection(connection);
		}
	}

	/**
	 * 执行SQL语句，取得二维字符串数组表示的结果集
	 * 
	 * @param sql
	 *            SQL语句，表名、字段名等SQL标识应当大小可以混用
	 * @return 结果集，表头的字段名为小写，字段内容大小写敏感
	 */
	public static String[][] executeQueryWithTableHead(String sql)
			throws Exception {
		Connection conn = ConnectionPool.getDBConnection();
		// 创建语句
		Statement state = conn.createStatement();
		// 执行语句
		ResultSet set = null;
		String[][] result = null;
		try {
			sql = new String(sql.getBytes(), ConnectionPool.characterset);
			// 得到结果集
			set = state.executeQuery(sql);
			// 分解结果为带表头的二维数组
			result = parseResultWithTableHead(set);
		} catch (SQLException e) {
			System.out.println("SQL错误:" + sql);
			throw e;
		} finally {
			try {
				set.close();
			} catch (Exception ex) {
			}
			try {
				state.close();
			} catch (Exception ex1) {
			}
			// 释放连接
			ConnectionPool.putDBConnection(conn);
		}
		return result;
	}

	/**
	 * 执行SQL语句，取得二维字符串数组表示的结果集
	 * 
	 * @param sql
	 *            SQL语句，表名、字段名等SQL标识应当大小可以混用
	 * @return 结果集，表头的字段名为小写，字段内容大小写敏感
	 */
	public static String[][] executePageQueryWithTableHead(String sql,
			int start, int end) throws Exception {
		Connection conn = ConnectionPool.getDBConnection();
		// 创建语句
		Statement state = conn.createStatement();
		// 执行语句
		ResultSet set = null;
		try {
			sql = new String(sql.getBytes(), ConnectionPool.characterset);
			long time = System.currentTimeMillis();
			// 得到结果集
			set = state.executeQuery(sql);
			System.out.println("query:" + (System.currentTimeMillis() - time));
		} catch (SQLException e) {
			System.out.println("SQL错误:" + sql);
			throw e;
		}
		// 分解结果为带表头的二维数组
		String[][] result = parsePageResultWithTableHead(set, start, end);
		set.close();
		state.close();
		// 释放连接
		ConnectionPool.putDBConnection(conn);
		return result;
	}

	/**
	 * 执行SQL语句，返回结果集
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSet querySet(String sql) {
		try {
			// 创建语句
			Statement state = this.connection.getConnection().createStatement();
			// 执行语句
			sql = new String(sql.getBytes(), this.connection.getCharacterset());
			// 得到结果集
			return state.executeQuery(sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 执行SQL语句，返回数据迭代
	 * 
	 * @param sql
	 *            SQL语句，表名、字段名等SQL标识应当大小可以混用
	 * @return 结果集，表头的字段名为小写，字段内容大小写敏感
	 */
	public Iterator query(String sql) {
		try {
			// 创建语句
			Statement state = this.connection.getConnection().createStatement();
			// 执行语句
			sql = new String(sql.getBytes(), this.connection.getCharacterset());
			// 得到结果集
			ResultSet set = state.executeQuery(sql);
			// 返回由结果集创建的游标
			return new QueryIterator(set);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把结果集转换为带表头的二维字符串数组
	 * 
	 * @param set
	 *            结果集
	 * @return 带表头的二维字符串数组表示的结果
	 */
	public static String[][] parseResultWithTableHead(ResultSet set)
			throws Exception {
		// 得到列名
		String[] names = getColNames(set);
		int colCount = names.length;
		// 把结果集转换成向量
		LinkedList v = new LinkedList();
		for (int i = 0; set.next(); i++) {
			v.add(getOneRecord(set, colCount));
		}
		// 把向量转换成数组
		String result[][] = new String[v.size() + 1][colCount];
		Iterator iter = v.iterator();
		for (int i = 1; iter.hasNext(); i++) {
			String[] item = (String[]) iter.next();
			result[i] = item;
		}
		// 把列名添加到数组中
		for (int i = 0; i < colCount; i++) {
			result[0][i] = names[i];
		}
		return result;
	}

	/**
	 * 把结果集转换为带表头的二维字符串数组
	 * 
	 * @param set
	 *            结果集
	 * @return 带表头的二维字符串数组表示的结果
	 */
	public static String[][] parsePageResultWithTableHead(ResultSet set,
			int start, int end) throws Exception {
		// 得到列名
		String[] names = getColNames(set);
		int colCount = names.length;
		// 读去开始的记录
		for (int i = 0; i < start; i++) {
			set.next();
		}
		// 把结果集转换成向量
		LinkedList v = new LinkedList();
		for (int i = start; set.next() && i < end; i++) {
			v.add(getOneRecord(set, colCount));
		}
		// 把向量转换成数组
		String result[][] = new String[v.size() + 1][colCount];
		Iterator iter = v.iterator();
		for (int i = 1; iter.hasNext(); i++) {
			String[] item = (String[]) iter.next();
			result[i] = item;
		}
		// 把列名添加到数组中
		for (int i = 0; i < colCount; i++) {
			result[0][i] = names[i];
		}
		return result;
	}

	/**
	 * 得到列名
	 * 
	 * @param set
	 *            结果集
	 * @return 列名数组
	 */
	public static String[] getColNames(ResultSet set) {
		try {
			// 取得列数
			ResultSetMetaData metadata = set.getMetaData();
			int colCount = metadata.getColumnCount();
			// 取得列名
			String names[] = new String[colCount];
			for (int i = 0; i < colCount; i++) {
				names[i] = metadata.getColumnName(i + 1);
			}
			return names;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 得到一条记录
	 * 
	 * @param set
	 *            结果集
	 * @param colCount
	 *            字段数
	 * @return 以数组表示的记录
	 */
	public static String[] getOneRecord(ResultSet set, int colCount) {
		try {
			String object[] = new String[colCount];
			for (int i = 0; i < colCount; i++) {
				Object obj = set.getObject(i + 1);
				if (obj != null) {
					object[i] = new String(obj.toString().getBytes(
							ConnectionPool.characterset));
				} else {
					object[i] = "";
				}
			}
			return object;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ＊建表结构 如果原来表中没有当前字段，则添加，如果原字段的长度不够，则扩充长度
	 * 
	 * @param tableName
	 *            表名
	 * @param newFields
	 *            表的字段信息
	 */
	public static void createTableStruct(String tableName, Hashtable newFields)
			throws Exception {
		sqlStatement.createTableStruct(tableName, newFields);
	}

	/**
	 * 执行插入语句
	 * 
	 * @param tableName
	 *            表名，大小写混用
	 * @param field
	 *            按属性表示的字段名及值，字段名大小写混用
	 * 
	 * 改进内容： 1、如果表不存在，则创建表。 2、如果表中字段不存在，则创建字段，按字符型创建。
	 * 3、如果表中字段长度不够，则自动扩充，一次扩充1000个字节。
	 * 
	 * 可用资源： 1、JDBC中有取得表以及字段信息的类。 2、DBTools中有些方法可以使用。
	 */
	public static void insert(String tableName, Hashtable field)
			throws Exception {
		// 建表结构,是否有要新添加的字段
		createTableStruct(tableName, field);
		// 取字段类型
		Hashtable fieldTypes = getDataTypes(tableName);
		// 不带建立表结果的插入记录
		insertNoCreateTable(tableName, field, fieldTypes);
	}

	public static void insertNoCreateTable(String tableName, Hashtable field,
			Hashtable fieldTypes) throws Exception {
		// 要插入的字段名串
		StringBuffer fieldName = new StringBuffer();
		// 要插入的字段值串
		StringBuffer fieldValue = new StringBuffer();
		// 得到所有字段名称
		Enumeration ne = field.keys();
		while (ne.hasMoreElements()) {
			// 得到字段名和字段值
			Object key = ne.nextElement();
			String name = key.toString();
			if (fieldName.length() != 0) {
				fieldName.append(", ");
			}
			fieldName.append(name);
			// 取出字段类型根据大写
			String fieldType = (String) fieldTypes.get(sqlStatement
					.translateString(name.toUpperCase()));
			// 取出字段类型根据小写
			if (fieldType == null) {
				fieldType = (String) fieldTypes.get(sqlStatement
						.translateString(name.toLowerCase()));
			}
			if (fieldType == null) {
				throw new Exception("表中字段不存在，表名：" + tableName + "，字段名：" + name);
			}
			String value = field.get(key).toString();
			if (fieldValue.length() != 0) {
				fieldValue.append(", ");
			}
			// 字符型
			if (StringTool.contains(fieldType, ConnectionPool.stringType)
					|| StringTool.contains(fieldType, ConnectionPool.stringType
							.toUpperCase())) {
				value = value.replaceAll("\\\\", "\\\\\\\\");
				value = value.replaceAll("'", "''");
				fieldValue.append("'").append(value.trim()).append("'");
			}
			// 数字型
			else if (fieldType.equals(ConnectionPool.numberType)
					|| fieldType
							.equals(ConnectionPool.numberType.toUpperCase())) {
				try {
					double dValue = Double.parseDouble(value);
					fieldValue.append(dValue);
				} catch (Exception e) {
					fieldValue.append(0);
				}
			}
		}
		// 拼写sql
		String sql = "insert into " + tableName + "(" + fieldName
				+ ") values (" + fieldValue + ")";
		Connection connection = ConnectionPool.getDBConnection();
		// 创建语句
		Statement state = connection.createStatement();
		try {
			sql = new String(sql.getBytes(), ConnectionPool.characterset);
			// 执行语句
			state.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException("sql语句错误:" + sql);
		} finally {
			state.close();
			ConnectionPool.putDBConnection(connection);
		}
	}

	/**
	 * 判断表是否存在
	 * 
	 * @parm tablename 表名
	 * @return 表是否存在
	 * @throws SQLException
	 */
	public static boolean isTableExist(String tableName) throws Exception {
		return sqlStatement.isTableExist(tableName);
	}

	/**
	 * 删除指定的表
	 * 
	 * @param tableName
	 * @throws Exception
	 */
	public static void dropTable(String tableName) throws Exception {
		DBTools.excuteUpdate("drop table " + tableName);
	}

	/**
	 * 根据表结构得到字段信息（字段名，字段长度）
	 * 
	 * @param String[][]
	 *            表结构
	 * @return Hashtable 以（字段名(小写)，字段长度）为表项的哈希表
	 * @throws Exception
	 */
	public static Hashtable getFieldLength(String[][] data) throws Exception {
		return sqlStatement.getFieldLength(data);
	}

	/**
	 * 更改表中数据
	 * 
	 * @param tableName
	 *            表名，大小写混用
	 * @param ID
	 *            要更新的对象的ID号
	 * @param field
	 *            以属性集方式表示的字段名及其值，字段名大小写混用
	 * @throws java.sql.SQLException
	 */
	public static void update(String tableName, String ID, Hashtable field)
			throws Exception {
		// 建表结构,是否有要新添加的字段
		createTableStruct(tableName, field);
		// 得到所有字段类型
		Hashtable fieldTypes = getDataTypes(tableName);
		// 以不创建表结构的方式更新
		updateNoCreateTable(tableName, ID, field, fieldTypes);
	}

	/**
	 * 更改表中数据，不创建表结构
	 * 
	 * @param tableName
	 *            表名，大小写混用
	 * @param ID
	 *            要更新的对象的ID号
	 * @param field
	 *            以属性集方式表示的字段名及其值，字段名大小写混用
	 * @throws java.sql.SQLException
	 */
	public static void updateNoCreateTable(String tableName, String ID,
			Hashtable field, Map fieldTypes) throws Exception {
		// set fieldName=fieldValue串
		String fieldValue = "";
		// 得到所有字段
		Enumeration ne = field.keys();
		while (ne.hasMoreElements()) {
			// 得到字段名称和值
			Object key = ne.nextElement();
			String name = key.toString();
			String value = field.get(key).toString();
			// 不是第一项，添加","号
			if (!fieldValue.equals("")) {
				fieldValue += ',';
			}
			// 取出字段类型根据大写
			String fieldType = (String) fieldTypes.get(sqlStatement
					.translateString(name.toUpperCase()));
			// 取出字段类型根据小写
			if (fieldType == null) {
				fieldType = (String) fieldTypes.get(sqlStatement
						.translateString(name.toLowerCase()));
			}
			// 如果没有得到
			if (fieldType == null) {
				// 抛出字段不存在异常
				throw new RuntimeException("字段" + name + "不存在");
			}
			// 字符型
			if (StringTool.contains(fieldType, ConnectionPool.stringType)
					|| StringTool.contains(fieldType, ConnectionPool.stringType
							.toUpperCase())) {
				value = value.replaceAll("\\\\", "\\\\\\\\");
				value = value.replaceAll("'", "''");
				fieldValue += name + "='" + value + "'";
			}
			// 数字型
			else if (fieldType.equals(ConnectionPool.numberType)
					|| fieldType
							.equals(ConnectionPool.numberType.toUpperCase())) {
				if (!value.equals("")) {
					value = value.replaceAll("\\\\", "\\\\\\\\");
					value = value.replaceAll("'", "''");
					fieldValue += name + "=" + value;
				} else {
					fieldValue += name + "=" + "-1";
				}
			}
		}
		String idType = (String) fieldTypes.get("ID");
		if (idType == null) {
			idType = (String) fieldTypes.get("id");
		}
		// 如果没有得到
		if (idType == null) {
			// 抛出字段不存在异常
			throw new RuntimeException("字段ID不存在");
		}
		// 字符型
		if (StringTool.contains(idType, ConnectionPool.stringType)
				|| StringTool.contains(idType, ConnectionPool.stringType
						.toUpperCase())) {
			ID = "'" + ID + "'";
		}
		// 拼写sql
		String sql = "update " + tableName + " set " + fieldValue
				+ " where id=" + ID;
		Connection connection = ConnectionPool.getDBConnection();
		// 创建语句
		Statement state = connection.createStatement();
		try {
			sql = new String(sql.getBytes(), ConnectionPool.characterset);
			// 执行语句
			state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("sql语句错误:" + sql);
			throw e;
		} finally {
			state.close();
			ConnectionPool.putDBConnection(connection);
		}
	}

	/**
	 * 根据列名从表头中得到列索引
	 * 
	 * @param head
	 *            表头，表头中的字段名大小写混用
	 * @param name
	 *            列名，大小写混用
	 * @return 列索引，没有找到，返回-1
	 * @throws java.lang.Exception
	 */
	public static int indexOf(String[] head, String name) throws Exception {
		for (int i = 0; i < head.length; i++) {
			if (head[i].toLowerCase().equals(name.toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 将一行记录转换成字段名=字段值表示的Hash表
	 * 
	 * @param data[][]
	 *            要转换的带表头的结果集，表头大小写混用
	 * @param row
	 *            要转换的行
	 * @return 表中字段名全部为小写
	 */
	public static Hashtable getAttributes(String data[][], int row)
			throws Exception {
		Hashtable ht = new Hashtable();
		for (int j = 0; j < data[0].length; j++) {
			String name = data[0][j];
			String value = data[row][j];
			ht.put(name.toLowerCase(), value);
		}
		return ht;
	}

	/**
	 * 创建表
	 * 
	 * @param tableName
	 *            表名
	 * @param fields
	 *            字段描述 字段名：字段值
	 */
	public static void createTable(String tableName, Hashtable fields)
			throws Exception {
		// 得到SQL语句
		String sql = sqlStatement.createTable(tableName, fields);
		// 执行sql语句
		Connection connection = ConnectionPool.getDBConnection();
		Statement state = connection.createStatement();
		try {
			sql = new String(sql.getBytes(), ConnectionPool.characterset);
			state.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("SQL错误：" + sql);
			throw e;
		} finally {
			state.close();
			ConnectionPool.putDBConnection(connection);
		}
	}

	/**
	 * 创建字段
	 * 
	 * @param tableName
	 *            表名
	 * @param fields
	 *            哈息表 字段名:字段值
	 * @throws TableMakerException
	 */
	public static void addFields(String tableName, Hashtable fields)
			throws Exception {
		// 得到SQL语句
		String sql = sqlStatement.addFields(tableName, fields);
		// 执行sql语句
		Connection connection = ConnectionPool.getDBConnection();
		Statement state = connection.createStatement();
		try {
			sql = new String(sql.getBytes(), ConnectionPool.characterset);
			state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL错误：" + sql);
			throw e;
		} finally {
			state.close();
			ConnectionPool.putDBConnection(connection);
		}
	}

	/**
	 * 扩充字段长度
	 * 
	 * @param fields
	 *            哈息表 字段名：字段值
	 * @parm tableName 表名
	 */
	public static void extendFieldsLength(String tableName, Hashtable fields)
			throws Exception {
		// 得到SQL语句
		String sql = sqlStatement.extendFieldsLength(tableName, fields);
		// 执行sql语句
		Connection connection = ConnectionPool.getDBConnection();
		Statement state = connection.createStatement();
		try {
			sql = new String(sql.getBytes(), ConnectionPool.characterset);
			state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL错误：" + sql);
			throw e;
		} finally {
			state.close();
			ConnectionPool.putDBConnection(connection);
		}
	}

	/**
	 * 取出数据库中所有的表名 用于mySql,sybase,SQLServer等
	 */
	public static String[] getTableNames() throws Exception {
		return sqlStatement.getTableNames();
	}

	/**
	 * 把Hashtable表示的条件转换成sql语句的条件，如果条件中有 >， >=等比较运算符，则按用户输入的处理, 否则，按相当处理
	 * 
	 * @param condition
	 *            hashtable表示的条件
	 * @param isLike
	 *            是否模糊查询
	 * @return SQL语句表示的条件
	 */
	public static String hashtableToSQLCondition(Map condition, boolean isLike) {
		return hashtableToSQLCondition(condition, isLike, "and");
	}

	/**
	 * 把Hashtable表示的条件转换成sql语句的条件，如果条件中有 >， >=等比较运算符，则按用户输入的处理, 否则，按相当处理
	 * 
	 * @param condition
	 *            hashtable表示的条件
	 * @param isLike
	 *            是否模糊查询
	 * @param logic
	 *            使用and或者or连接条件
	 * @return SQL语句表示的条件
	 */
	public static String hashtableToSQLCondition(Map condition, boolean isLike,
			String logic) {
		String result = "";
		// 对于每一个条件
		Iterator iter = condition.keySet().iterator();
		while (iter.hasNext()) {
			// 得到名字和值
			String name = (String) iter.next();
			String value = (String) condition.get(name);
			String con = name + "='" + value + "'";
			// 处理逻辑运算符
			value = processLogic(name, value, "and");
			value = processLogic(name, value, "or");
			// 如果包含条件运算符
			if (value.indexOf('>') != -1 || value.indexOf(">=") != -1
					|| value.indexOf('<') != -1 || value.indexOf("<=") != -1) {
				con = name + value;
			}
			// 如果是模糊查询
			else if (isLike) {
				con = name + " like '" + value + "'";
			}
			// 添加一个条件
			result = DBTools.connectCondition(result, con, logic);
		}
		return result;
	}

	/**
	 * 处理用户输入条件中的逻辑运算符，直接在逻辑运算符后面添加字段名
	 * 
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值，其中可能含有逻辑运算符
	 * @param logic
	 *            要处理的逻辑运算符
	 * @return 处理结果
	 */
	private static String processLogic(String name, String value, String logic) {
		StringBuffer result = new StringBuffer(value);
		// 对于每一个逻辑运算符
		int pos = result.indexOf(logic);
		while (pos != -1) {
			result.insert(pos + logic.length() + 1, name);
			pos = result.indexOf(logic, pos + logic.length() + 1);
		}
		return result.toString();
	}

	/**
	 * 把Hashtable表示的条件转换成sql语句的条件,字符串类型模糊查询
	 * 
	 * @param condition
	 *            hashtable表示的条件
	 * @return SQL语句表示的条件
	 */
	public static String hashtableToSQLLikeCondition(Map condition) {
		return hashtableToSQLCondition(condition, true);
	}

	/**
	 * 连接两个sql条件
	 * 
	 * @param sql1
	 *            条件1
	 * @param sql2
	 *            条件2
	 * @return 条件的连接结果
	 */
	public static String connectCondition(String sql1, String sql2) {
		return connectCondition(sql1, sql2, "and");
	}

	/**
	 * 连接两个sql条件
	 * 
	 * @param sql1
	 *            条件1
	 * @param sql2
	 *            条件2
	 * @param logic
	 *            连接符号
	 * @return 条件的连接结果
	 */
	public static String connectCondition(String sql1, String sql2, String logic) {
		// 如果条件1为空
		if (sql1 == null || sql1.equals("")) {
			// 返回sql2
			return sql2;
		}
		// 否则如果条件2为空
		else if (sql2 == null || sql2.equals("")) {
			// 返回sq11
			return sql1;
		}
		// 否则，都不为空
		else {
			return sql1 + " " + logic + " " + sql2;
		}
	}

	/**
	 * 查询结果所使用的迭代器
	 */
	private class QueryIterator implements Iterator {
		/**
		 * 查询的结果集
		 */
		private ResultSet set = null;

		/**
		 * 列名数组
		 */
		private String[] colNames = null;

		/**
		 * 使用结果集构造
		 * 
		 * @param set
		 *            结果集
		 */
		public QueryIterator(ResultSet set) {
			this.set = set;
			// 得到列名
			this.colNames = DBTools.getColNames(this.set);
		}

		/**
		 * 看有没有下一个
		 */
		public boolean hasNext() {
			try {
				return this.set.next();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 取下一个
		 */
		public Object next() {
			try {
				// 从结果集当前游标中得到数据
				String[] datas = DBTools.getOneRecord(this.set,
						this.colNames.length);
				return this.datasToMap(datas);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 把数据依照字段名转换成map
		 * 
		 * @param datas
		 *            数据
		 * @return 以map形式表现的属性以及值
		 */
		private Map datasToMap(String[] datas) {
			Map result = new HashMap();
			// 对于每一个字段
			for (int i = 0; i < this.colNames.length; i++) {
				// 如果数据中有空值
				if (datas[i] == null) {
					datas[i] = "";
				}
				// 把属性名以及值放到结果中
				result.put(this.colNames[i].toLowerCase(), datas[i]);
			}
			return result;
		}

		/**
		 * 移走一个对象
		 * 
		 * @param Object
		 *            对象
		 */
		public void remove() {
		}
	}

	/**
	 * 根据表名取得列名
	 * 
	 * @param tableName
	 *            表名
	 * @return 列名
	 */
	public static Object[] getColumnNames(String tableName) {
		try {
			// 根据表名得到字段类型
			Map map = getDataTypes(tableName);
			// 对于每一个字段类型
			return map.keySet().toArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {

		Hashtable ht = new Hashtable();
		ht.put("f1", "a");
		ht.put("f2", "b");
		ht.put("f3", "中文");
		ht.put("f4", "哈哈");
		try {
			DBTools.insert("test_table", ht);
			String[][] result = DBTools
					.executeQueryWithTableHead("select * from test_table");
			for (int i = 1; i < result.length; i++) {
				Hashtable row = DBTools.getAttributes(result, i);
				System.out.println(row);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
