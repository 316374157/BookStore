package com.phj.utilis;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName JDBCUtils 数据库工具类
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/10
 * @Version V1.0
 **/
public class JDBCUtils {
    public  static DataSource dataSource = new ComboPooledDataSource("test");
    /**
     * 根据事物特性（有错回滚，无错提交），并保证多线程时的数据安全安全，所以使用threadLocal关联数据库连接
     */
    public static ThreadLocal<Connection> conns = new ThreadLocal<>();

    /**
     * 从连接池中获取数据连接,并和当前线程联系起来
     * @return 连接
     */
    public static Connection getConnection(){
        Connection connection = conns.get();
        if(connection == null){
            try {
                connection = dataSource.getConnection();
                //添加联系
                conns.set(connection);
                //取消自动提交
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 事务成功后提交，并关闭连接
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        //之前如果操作过数据库，得到连接，提交并释放资源
        if (connection!=null){
            try {
                //提交
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //释放资源
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //移除联系
        conns.remove();
    }

    /**
     * 事务失败之后回滚，并关闭连接
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        //获取之前的连接，回滚事务，并释放资源
        if (connection != null) {
            try {
                //回滚事务
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //释放资源
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //移除联系
        conns.remove();
    }


}
