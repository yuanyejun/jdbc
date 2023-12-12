package cn.jdbc.select;

import cn.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcTest04_ {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book",
                    "root",
                    "123456");
            String sql = "insert into t_user values(?,?,?,?)";

            ps = connection.prepareStatement(sql);

            ps.setString(1,null);
            ps.setString(2,"okok1");
            ps.setString(3,"okok");
            ps.setString(4,"okok");

            int i = ps.executeUpdate();

            System.out.println(i);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConn(connection,ps);
        }

    }
}
