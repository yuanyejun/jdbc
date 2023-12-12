package cn.jdbc.select;

import cn.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  模糊查询...
 */
public class JdbcTest07_ {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = JdbcUtils.getConnection();
            String sql = "select username from t_user where username like ? ";
            ps = connection.prepareStatement(sql);

            ps.setString(1,"_a%");

            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            JdbcUtils.closeConn(rs,connection,ps);
        }
    }
}
