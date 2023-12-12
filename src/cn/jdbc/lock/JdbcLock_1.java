package cn.jdbc.lock;

import cn.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  这个程序开始一个事务 专门进行查询 并且使用悲观锁 锁住相关的记录
 */
public class JdbcLock_1 {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);

            String sql = "select username,password from t_user where username = ? for update";
            ps = connection.prepareStatement(sql);

            ps.setString(1,"admin1");

            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("username") + ":"
                        + rs.getString("password"));
            }

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

            JdbcUtils.closeConn(rs,connection,ps);
        }

    }
}
