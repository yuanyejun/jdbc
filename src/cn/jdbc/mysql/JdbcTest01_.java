package cn.jdbc.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest01_ {
    public static void main(String[] args) {

        Statement stmt = null;
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/book";
        String username = "root";
        String passwd = "123456";
        String sql = "INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) VALUES(NULL , 'java 从入门到放弃' , '国哥' , 80 , 9999 , 9 , 'static/img/default.jpg')";
        try {
//            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(url, username, passwd);
            System.out.println(connection);
            stmt = connection.createStatement();
            int dml = stmt.executeUpdate(sql);
            System.out.println(dml);
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
