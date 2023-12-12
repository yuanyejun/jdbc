package cn.jdbc.select;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcTest01_ {
    public static void main(String[] args) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String url = resourceBundle.getString("url");
        String username = resourceBundle.getString("username");
        String password = resourceBundle.getString("password");
        String driver = resourceBundle.getString("driver");
        String sql = resourceBundle.getString("sql");

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            stmt = connection.createStatement();
            // executeUpdate(insert/delete/update) int
            // executeQuery(sql)  ResultSet
            rs = stmt.executeQuery(sql); //专门执行DQL的方法

            while (rs.next()){
                //所有下标从1开始
                String s1 = rs.getString("name");
                String s2 = rs.getString("price");
                String s3 = rs.getString("author");

                System.out.println(s1 + ":" + s2 + ":" + s3);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (stmt != null){

                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null){

                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (rs != null){

                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
