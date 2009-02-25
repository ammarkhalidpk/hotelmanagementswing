// package cg;
//
// import java.sql.*;
//
// public class LoginDataBase{
// private Connection con;
// public LoginDataBase()
// {
// con = getConnection();
// }
//    
// private Connection getConnection()
// {
// Connection conn = null;
// try
// {
// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
// conn = DriverManager.getConnection("jdbc:odbc:myodbc", "sa", "");
// }
// catch (ClassNotFoundException cnfe)
// {
// cnfe.printStackTrace();
// }
// catch (SQLException sqle)
// {
// sqle.printStackTrace();
// }
//        
// return (conn);
// }
//    
// public void closeConnection() {
// try
// {
// con.close();
// }
// catch (SQLException sqle)
// {
// sqle.printStackTrace();
// }
// }
//    
//
// }
