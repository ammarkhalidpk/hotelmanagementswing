package cg;

import java.sql.SQLException;
import java.util.Vector;

import dbtools.DBTools;

public class DatabaseOperate {
	public DatabaseOperate() {
	}

	public Vector<String> getColumnNamesByTabname() {
		Vector<String> cols = new Vector<String>();
		try {
			String strSql = "SELECT * FROM guestroomBase";
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
			String strSql = "SELECT * FROM guestroomBase";
			String[][] result = DBTools.executeQueryWithTableHead(strSql);
			return getVector(result);
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Vector<String> getAllTabelName() {
		Vector<String> vec = new Vector<String>();
		try {
			String strSql = "select 客房编号 from guestroomBase";
			String[][] result = DBTools.executeQueryWithTableHead(strSql);
			return getVector(result);
			/*
			 * ResultSet rs = sta.executeQuery(strSql); while (rs.next()) {
			 * vec.add(rs.getString("客房编号")); } rs.close(); sta.close();
			 */
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Vector getVector(String[][] result) {
		Vector res = new Vector();
		for (int i = 1; i < result.length; i++) {
			Vector res2 = new Vector();
			for (int j = 0; j < result[0].length; j++) {
				res2.add(result[i][j]);
			}
			res.add(res2);
		}
		return res;
	}
}