package cg;

import java.sql.SQLException;
import java.util.Vector;

import dbtools.DBTools;

public class DatabaseUser {

	public DatabaseUser() {
	}

	public Vector<String> getColumnNamesByTabname() {

		Vector<String> cols = new Vector<String>();
		try {

			String strSql = "SELECT * FROM user";
			String[][] result = DBTools.executeQueryWithTableHead(strSql);
			for (int i = 0; i < result[0].length; i++) {
				cols.add(result[0][i]);
			}
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return (cols);
	}

	public Vector<Object> getDataByTabname() {

		Vector<Object> data = new Vector<Object>();
		try {
			String strSql = "SELECT * FROM user";
			String[][] result = DBTools.executeQueryWithTableHead(strSql);
			return DatabaseOperate.getVector(result);
			/*
			 * ResultSet rs = sta.executeQuery(strSql); int cols =
			 * rs.getMetaData().getColumnCount();
			 * 
			 * while (rs.next()){
			 * 
			 * Vector<Object> vec = new Vector<Object>(); for (int i = 1; i <=
			 * cols; i++){
			 * 
			 * vec.add(rs.getObject(i)); } data.add(vec); } rs.close();
			 * sta.close();
			 */
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Vector<String> getAllTabelName() {

		Vector<String> vec = new Vector<String>();
		try {
			String strSql = "select 用户名 from user";
			String[][] result = DBTools.executeQueryWithTableHead(strSql);
			return DatabaseOperate.getVector(result);
			/*
			 * ResultSet rs = sta.executeQuery(strSql); while (rs.next()) {
			 * vec.add(rs.getString("用户名")); } rs.close(); sta.close();
			 */
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}