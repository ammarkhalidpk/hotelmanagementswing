package cg;

import java.sql.Connection;
import java.sql.SQLException;

import dbtools.ConnectionPool;

public class LoginDataBase {
	private Connection con;

	public LoginDataBase() {
		con = getConnection();
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			/*
			 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); conn =
			 * DriverManager.getConnection("jdbc:odbc:myodbc", "sa", "");
			 */
			conn = ConnectionPool.getDBConnection();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return (conn);
	}

	public void closeConnection() {
		try {
			ConnectionPool.putDBConnection(con);
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}

}