package cn.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest05_ {
    public static void main(String[] args) {

        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/book";
        String username = "root";
        String passwd = "123456";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, passwd);

            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
