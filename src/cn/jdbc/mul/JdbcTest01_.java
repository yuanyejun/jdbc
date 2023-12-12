package cn.jdbc.mul;

import cn.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *  jdbc事务是自动提交的
 *     只要执行任意一条 dml语句 则自动提交一次 默认事务行为
 *
 */
public class JdbcTest01_ {
    public static void main(String[] args) {


        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book",
                    "root",
                    "123456");

            String sql = "update t_user set username=?,password=? where id=?";
            ps = connection.prepareStatement(sql);

            ps.setString(1,"ohhhhh");
            ps.setString(2,"ohhhhh");
            ps.setInt(3,17);

            int i = ps.executeUpdate();
            System.out.println(i);

            ps.setString(1,"ohhhhh222");
            ps.setString(2,"ohhhhh222");
            ps.setInt(3,14);

            i = ps.executeUpdate();
            System.out.println(i);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConn(connection,ps);
        }
    }
}
