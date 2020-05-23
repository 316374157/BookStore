package com.phj.dao;

import com.phj.utilis.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName BaseDao dao模板 封装一些方法
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/10
 * @Version V1.0
 **/
public class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    /**
     * 获取传入的类型
     */
    private Class<T> type;

    public BaseDao(){
        //获取带参数的父类类型，即带着<T>的父类类型
        ParameterizedType superclass = (ParameterizedType)this.getClass().getGenericSuperclass();
        //getActualTypeArguments获取<T>集合
        type = (Class<T>) superclass.getActualTypeArguments()[0];
    }

    /**
     * 从数据库中获取一条数据并封装成相应的bean，遇到异常时捕获，并抛出，方便回滚或者提交处理
     * @param sql sql语句
     * @param params 可变参数
     * @return 封装好的bean对象
     */
    public T getBean(String sql,Object... params){
        Connection connection = JDBCUtils.getConnection();
        //要从数据库取出并封装的对象
        T query = null;
        try {
            //第三个参数是和数据库中表对应的bean模型
            query = queryRunner.query(connection, sql, new BeanHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
            //抛出运行异常，方便上层捕获，并处理异常
            throw new RuntimeException(e);
        }
        return query;
    }

    /**
     * 从数据库中取出一些数据并封装成list，遇到异常时捕获，并抛出，方便回滚或者提交处理
     * @param sql sql语句
     * @param params 可变参数
     * @return 封装好的list
     */
    public List<T> getBeanList(String sql,Object... params){
        Connection connection = JDBCUtils.getConnection();
        //要从数据库取出并封装的对象
        List<T> query = null;
        try {
            //第三个参数是和数据库中表对应的bean模型
            query = queryRunner.query(connection, sql, new BeanListHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
            //抛出运行异常，方便上层捕获，并处理异常
            throw new RuntimeException(e);
        }
        return query;
    }

    /**
     * 对数据表的增删改都用这个方法，遇到异常时捕获，并抛出，方便回滚或者提交处理
     * @param sql sql语句
     * @param params 可变参数
     * @return true表示成功，false表示失败
     */
    public boolean update(String sql,Object... params){
        Connection connection = JDBCUtils.getConnection();
        int update = 0 ;
        try {
            update = queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            //抛出运行异常，方便上层捕获，并处理异常
            throw new RuntimeException(e);
        }
        return update>0;
    }

    /**
     * 获取数据库中的单个值的方法
     * @param sql sql语句
     * @param params 可变参数
     * @return 查询到的值，可能是多种类型
     */
    public Object getSingleValue(String sql,Object... params){
        Connection connection = JDBCUtils.getConnection();
        Object value = null;
        try {
            //第三个参数可以不传入泛型，封装成Object，封装单个值
            value = queryRunner.query(connection, sql, new ScalarHandler<>(),params);
        } catch (SQLException e) {
            e.printStackTrace();
            //抛出运行异常，方便上层捕获，并处理异常
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * 批量处理sql的方法
     * @param sql 需要批量处理的sql
     * @param params 二维对象数组，第一维表示需要处理的次数，第二维表示每次处理时附加的参数
     */
    public void batch(String sql,Object[][] params){
        Connection connection = JDBCUtils.getConnection();
        try {
            queryRunner.batch(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            //抛出运行异常，方便上层捕获，并处理异常
            throw new RuntimeException(e);
        }
    }


}
