package cn.jdbc.select;

import cn.jdbc.utils.JdbcUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *  解决sql注入的问题
 *      用户提供的信息不参与sql语句的编译过程 问题就解决了
 *      即使用户提供的信息中含有sql语句的关键字 但是没有参与编译 就不起作用
 *      PreparedStatement 继承了Statement 是个接口
 *      PreparedStatement 预先对sql语句的框架进行编译 然后再给sql语句传值 "值"
 *
 *      PreparedStatement 编译一次 执行n次
 *      Statement 编译一次 执行一次
 *
 *      PreparedStatement 编译阶段会安全检查
 *
 *      使用较多  PreparedStatement
 *      极少数使用  Statement
 *
 */
public class JdbcTest03_ {

    public static void main(String[] args) {


        Map<String,String> userLogInfo = initUI();

        boolean loginSuccess  = login(userLogInfo);

        System.out.println(loginSuccess ? "登陆成功":"登陆失败");

    }

    private static boolean login(Map<String, String> userLogInfo) {

        boolean loginSuccess = false;

        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String url = resourceBundle.getString("url");
        String username = resourceBundle.getString("username");
        String password = resourceBundle.getString("password");
        String driver = resourceBundle.getString("driver");
        String loginName = userLogInfo.get("loginName");
        String loginPwd = userLogInfo.get("loginPwd");


        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            // 获取预编译的数据库操作对象
            //sql语句的框子 ?表示一个占位符 一个?接受一个值 不能使用单引号括起来
            String sql = "select * from t_user where username = ? and password = ? ";
            // 程序执行到此处 会发送sql语句框子给dbms 然后dbms进行sql语句的预先编译
            ps = connection.prepareStatement(sql);
            // 给占位符传值
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);
            //执行sql语句
            rs = ps.executeQuery();

            if (rs.next()){
                loginSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConn(rs,connection,ps);
        }

        return loginSuccess;
    }

    /**
     *  初始化用户界面
     * @return 返回用户名和用户密码
     */
    private static Map<String,String> initUI() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("用户名:");
        String username = scanner.nextLine();
        System.out.println("密码:");
        String passwd = scanner.nextLine();

        HashMap<String,String> userLogInfo = new HashMap<>();

        userLogInfo.put("loginName",username);
        userLogInfo.put("loginPwd",passwd);
        return userLogInfo;
    }
}
