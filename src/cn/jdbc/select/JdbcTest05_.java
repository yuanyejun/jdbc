package cn.jdbc.select;

import cn.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcTest05_ {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book",
                    "root",
                    "123456");
            String sql = "delete from t_user where id = ?";

            ps = connection.prepareStatement(sql);

            ps.setInt(1,15);

            int i = ps.executeUpdate();

            System.out.println(i);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConn(connection,ps);
        }

    }
}
