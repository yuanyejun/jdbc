package cn.jdbc.mul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *    sql脚本:
 *      drop table if not exists t_act;
 *      create table t_act(
 *          actno bigint,
 *          balance double(7,2)
 *      );
 *      insert into t_act values(111,20000);
 *      insert into t_act values(222,0);
 *      commit;
 *      select * from t_act;
 *
 */
public class JdbcTest02_ {
    public static void main(String[] args) {


        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book",
                    "root",
                    "123456");
            //设置自动提交为false
            connection.setAutoCommit(false); //开启事务 手动提交

            String sql = "update t_act set balance = ? where actno = ?";
            ps = connection.prepareStatement(sql);

            ps.setDouble(1,10000);
            ps.setInt(2,111);

            int i = ps.executeUpdate();
            System.out.println(i);

            ps.setDouble(2,10000);
            ps.setInt(2,222);
            i += ps.executeUpdate();

            System.out.println(i == 2?"成功":"失败");

            //程序走到这里说明程序没有问题
            connection.commit(); //提交事务

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (ps != null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
