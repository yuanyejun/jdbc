package cn.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest04_ {
    public static void main(String[] args) {

        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/book";
        String username = "root";
        String passwd = "123456";
        try {
            connection = DriverManager.getConnection(url, username, passwd);
            System.out.println(connection);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
