package cdu.pjm.util;

import java.sql.*;
import java.util.concurrent.LinkedBlockingQueue;
public class JDBCUtils {
    public static String url="jdbc:mysql://127.0.0.1:3306/homework2?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
    public static String root="root";
    public static String ps="pujinmin3344";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection=null;
        try {
             connection=DriverManager.getConnection(url,root,ps);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection){
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public static void close(Connection connection, PreparedStatement statement){
        try {
            if (statement!=null) {
                statement.close();
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(connection);
    }
    public static void close(Connection connection, PreparedStatement statement, ResultSet set){
        try {
            if (set!=null) {
                set.close();
            }
            if (statement!=null) {
                statement.close();
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(connection);
    }

//    public static void destroy(){
////        while (pool.size()>0){
////            try {
////                pool.poll().close();
////            } catch (SQLException throwables) {
////                throwables.printStackTrace();
////            }
////        }
//    }
}
