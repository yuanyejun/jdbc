package cn.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest03_ {
    public static void main(String[] args) {

        Statement stmt = null;
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/book";
        String username = "root";
        String passwd = "123456";
        try {

            connection = DriverManager.getConnection(url, username, passwd);
            stmt = connection.createStatement();
            String sql = "update t_book set name = '1111',author = '2222'  where id = 22 ";
            int i = stmt.executeUpdate(sql);

            System.out.println(i);

        } catch (SQLException e) {
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
        }

    }
}
