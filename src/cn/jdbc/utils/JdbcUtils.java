package cn.jdbc.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcUtils {


    final static String URL = ResourceBundle.getBundle("db").getString("url");
    final static String USERNAME = ResourceBundle.getBundle("db").getString("username");
    final static String PASSWD = ResourceBundle.getBundle("db").getString("password");

    /**
     *  静态代码块在类加载中执行 并且只执行一次
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private JdbcUtils(){}
    /**
     *  关闭链接
     * @param rs
     * @param connection
     * @param stmt
     */
    public static  void closeConn(ResultSet rs, Connection connection, Statement stmt){

        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  关闭链接
     * @param connection
     * @param ps
     */
    public static  void closeConn(Connection connection, PreparedStatement ps){


        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  获取连接
     * @return
     */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USERNAME,PASSWD);

    }

}
