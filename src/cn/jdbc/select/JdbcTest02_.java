package cn.jdbc.select;

import cn.jdbc.utils.JdbcUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *  模拟用户登陆实现
 */
public class JdbcTest02_ {

    /**
     *  sql注入问题：(安全隐患)
     *          根本原因: 用户输入的信息中含有sql语句的关键字 并且这些关键字参与了sql语句的编译过程
     *                  导致sql语句的原意被扭曲
     *          解决:
     * @param args
     */
    public static void main(String[] args) {


        Map<String,String> userLogInfo = initUI();

        boolean loginSuccess  = login(userLogInfo);

        System.out.println(loginSuccess ? "登陆成功":"登陆失败");

    }

    /**
     *  用户登陆信息
     * @param userLogInfo
     * @return true 成功 false 失败
     */



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
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);

            stmt = connection.createStatement();

            String sql = "select * from t_user where username = '"+ loginName +"' and password = '"+ loginPwd +"' ";

            rs = stmt.executeQuery(sql);

            if (rs.next()){
                loginSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConn(rs,connection,stmt);
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
