package dbtools;

import java.util.Hashtable;
import java.util.Map;

/**
 * 由于各数据库建表等SQL语句不同，所以建立此接口以满足针对不同数据库 给相同的数据库操作产生不同SQL语句的需求。
 */
public interface SQLStatement {
	/**
	 * 产生创建数据库表的语句。
	 * 
	 * @param tableName
	 *            表名
	 * @param fields
	 *            要建立的表的字段列表，包括字段名以及字段值
	 * @return 创建数据库表的SQL语句
	 */
	public String createTable(String tableName, Map fields);

	/**
	 * 产生给数据库添加字段的语句。
	 * 
	 * @param tableName
	 *            表名
	 * @param fields
	 *            要增加的字段列表，包括字段名以及字段值
	 * @return 给数据库添加字段的语句
	 */
	public String addFields(String tableName, Map fields);

	/**
	 * 产生扩展数据库字段长度的语句。
	 * 
	 * @param tableName
	 *            表名
	 * @param fields
	 *            要扩展的字段列表，包括字段名以及字段值
	 * @return 扩展数据库字段长度的语句
	 */
	public String extendFieldsLength(String tableName, Map fields);

	/**
	 * 把给定字符串按数据库的大小写规则进行转换
	 */
	public String translateString(String value);

	/**
	 * 建表结构
	 * 
	 */
	public void createTableStruct(String tableName, Hashtable newFields)
			throws Exception;

	/**
	 * 根据表名得到表中字段的数据类型
	 */
	public Hashtable getDataTypes(String tableName) throws Exception;

	/**
	 * 根据表结构得到字段信息包括（字段名，字段长度）
	 */
	public Hashtable getFieldLength(String[][] data) throws Exception;

	/**
	 * 得到数据库中所有表名
	 */
	public String[] getTableNames() throws Exception;

	/**
	 * 判断表是否存在
	 */
	public boolean isTableExist(String tableName) throws Exception;
}