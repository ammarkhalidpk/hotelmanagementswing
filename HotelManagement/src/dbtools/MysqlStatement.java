package dbtools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * Mysql数据库产生建表等SQL语句
 */
public class MysqlStatement implements SQLStatement {
	/**
	 * 产生创建数据库表的语句。
	 * 
	 * @param tableName
	 *            表名
	 * @param fields
	 *            要建立的表的字段列表，包括字段名以及字段值
	 * @return 创建数据库表的SQL语句
	 */
	public String createTable(String tableName, Map fields) {
		// 准备sql语句
		String sql = "create table " + tableName + "(";
		Iterator en = fields.keySet().iterator();
		// 对所有字段取字段
		while (en.hasNext()) {
			String fieldName = this.translateString((String) en.next());
			String stringType = ConnectionPool.stringType;
			sql += fieldName + " " + stringType + ",";
			// 主关键字字段sql语句准备
			if (fieldName.equals("id")) {
				sql = sql.substring(0, sql.length() - 1);
				sql += " not null,";
			}
		}
		return sql.substring(0, sql.length() - 1) + ")";
	}

	/**
	 * 产生给数据库添加字段的语句。
	 * 
	 * @param tableName
	 *            表名
	 * @param fields
	 *            要增加的字段列表，包括字段名以及字段值
	 * @return 给数据库添加字段的语句
	 */
	public String addFields(String tableName, Map fields) {
		// 准备sql语句
		String sql = "ALTER TABLE " + tableName + " ADD(";
		Iterator en = fields.keySet().iterator();
		// 对所有字段取字段
		int i = 1;
		while (en.hasNext()) {
			String fieldName = (String) en.next();
			// 如果不是第一列则用逗号分隔
			if (i != 1) {
				sql = sql + ",";
			}
			String stringType = ConnectionPool.stringType;
			sql = sql + fieldName + " " + stringType;
			i++;
		}
		return sql + ")";
	}

	/**
	 * 产生扩展数据库字段长度的语句。
	 * 
	 * @param tableName
	 *            表名
	 * @param fields
	 *            要扩展的字段列表，包括字段名以及字段值
	 * @return 扩展数据库字段长度的语句
	 */
	public String extendFieldsLength(String tableName, Map fields) {
		// 准备sql语句
		String sql = "ALTER TABLE " + tableName + " MODIFY(";
		Iterator en = fields.keySet().iterator();
		// 对所有字段取字段信息
		int i = 1;
		while (en.hasNext()) {
			String fieldName = (String) en.next();
			// 如果不是第一列则用逗号分隔
			if (i != 1) {
				sql = sql + ",";
			}
			String stringType = ConnectionPool.stringType;
			sql = sql + fieldName + " " + stringType;
			i++;
		}
		return sql + ")";
	}

	/**
	 * 把给定字符串按数据库的大小写规则进行转换
	 */
	public String translateString(String value) {
		return value;
	}

	/**
	 * 建表结构
	 * 
	 */
	public void createTableStruct(String tableName, Hashtable newFields)
			throws Exception {
		// 如果表不存在则建表结构
		if (!(isTableExist(tableName))) {
			DBTools.createTable(tableName, newFields);
		}
		// 否则对原表进行维护
		else {
			Hashtable addFieldsHt = new Hashtable();
			Hashtable modifyFieldsHt = new Hashtable();
			Connection connection = ConnectionPool.getDBConnection();
			// 取原表数据
			DatabaseMetaData dmd = connection.getMetaData();
			String user = PropertiesService.getProperty("Database", "user");
			ResultSet rs = dmd.getColumns(null, translateString(user),
					translateString(tableName), "%");
			String[][] data = DBTools.parseResultWithTableHead(rs);
			Hashtable oldFields = getFieldLength(data);
			// 根据新表对原表进行维护
			Enumeration newEnum = newFields.keys();
			// 逐个取新表字段与旧表字段对比
			while (newEnum.hasMoreElements()) {
				String newField = (String) newEnum.nextElement();
				newField = newField.toLowerCase();
				String newFieldValue = newFields.get(newField).toString();
				// 如果字段名包含在原表中
				if (oldFields.containsKey(newField)) {
					// 准备要扩充的字段哈希表
					String oldFieldValue = (String) oldFields.get(newField);
					if (newFieldValue.length() > Integer
							.parseInt(oldFieldValue)) {
						modifyFieldsHt.put(newField, newFieldValue);
					}
				}
				// 否则字段名不包含在原表中
				else {
					{
						// 准备要增加的字段哈希表
						addFieldsHt.put(newField, newFieldValue);
					}
				}
			}
			// 增如字段
			if (addFieldsHt.size() > 0) {
				DBTools.addFields(tableName, addFieldsHt);
			}
			// 扩充字段长度
			if (modifyFieldsHt.size() > 0) {
				DBTools.extendFieldsLength(tableName, modifyFieldsHt);
			}
			ConnectionPool.putDBConnection(connection);
		}
	}

	/**
	 * 根据表名得到表中字段的数据类型
	 */
	public Hashtable getDataTypes(String tableName) throws Exception {
		Hashtable ht = new Hashtable();
		Connection connection = ConnectionPool.getDBConnection();
		DatabaseMetaData dmd = connection.getMetaData();
		// 得到列信息
		String user = PropertiesService.getProperty("Database", "user");
		ResultSet rs = dmd.getColumns(null, translateString(user),
				translateString(tableName), "%");
		// 得到字段名和字段类型
		while (rs.next()) {
			String fieldName = rs.getString("COLUMN_NAME");
			String fieldType = rs.getString("TYPE_NAME");
			ht.put(fieldName.toUpperCase(), fieldType);
		}
		ConnectionPool.putDBConnection(connection);
		return ht;
	}

	/**
	 * 根据表结构得到字段信息包括（字段名，字段长度）
	 */
	public Hashtable getFieldLength(String[][] data) throws Exception {
		// 返回值为 ht
		Hashtable ht = new Hashtable();
		// 得到字段结构
		// 准备列号
		String[] head = new String[data[0].length];
		for (int i = 0; i < data[0].length; i++) {
			head[i] = data[0][i];
		}
		int fieldname_column = DBTools.indexOf(head, "COLUMN_NAME");
		int fieldSize_column = DBTools.indexOf(head, "COLUMN_SIZE");
		for (int i = 1; i < data.length; i++) {
			String fieldname = data[i][fieldname_column].toLowerCase();
			String fieldSize = data[i][fieldSize_column];
			ht.put(fieldname, fieldSize);
		}
		return ht;
	}

	/**
	 * 得到数据库中所有表名
	 */
	public String[] getTableNames() throws Exception {
		Connection connection = ConnectionPool.getDBConnection();
		// 取出所有的表名
		DatabaseMetaData dmd = connection.getMetaData();
		String name = "%";
		String[] types = { "TABLE" };
		String user = PropertiesService.getProperty("Database", "user");
		ResultSet rs = dmd.getTables(null, translateString(user),
				translateString(name), types);
		String[][] ret = DBTools.parseResultWithTableHead(rs);
		String[] tableNames = new String[ret.length - 1];
		for (int i = 1; i < ret.length; i++) {
			tableNames[i - 1] = ret[i][2];
		}
		ConnectionPool.putDBConnection(connection);
		return tableNames;
	}

	/**
	 * 判断表是否存在
	 */
	public boolean isTableExist(String tableName) throws Exception {
		Connection connection = ConnectionPool.getDBConnection();
		// 取出所有的表名
		DatabaseMetaData dmd = connection.getMetaData();
		String[] types = { "TABLE" };
		String user = PropertiesService.getProperty("Database", "user");
		ResultSet rs = dmd.getTables(null, translateString(user),
				translateString(tableName), types);
		// 如果该表名存在则返回值为真
		if (rs.next()) {
			ConnectionPool.putDBConnection(connection);
			return true;
		}
		// 否则返回值为假
		else {
			ConnectionPool.putDBConnection(connection);
			return false;
		}
	}
}
