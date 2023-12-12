package cn.jdbc.lock;

import cn.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcLock_2 {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);

            String sql = "update t_user set password = 123 where username = ?";
            ps = connection.prepareStatement(sql);

            ps.setString(1,"admin1");

            int count = ps.executeUpdate();

            System.out.println(count);

            connection.commit();

        } catch (SQLException e) {
            if (connection != null){
                try {
                    //回滚事务
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
            e.printStackTrace();
        }finally {

            JdbcUtils.closeConn(connection,ps);
        }

    }
}
