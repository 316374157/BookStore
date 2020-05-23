package com.phj.dao.impl;

import com.phj.bean.User;
import com.phj.dao.BaseDao;
import com.phj.dao.UserDao;

/**
 * @ClassName UserDaoImpl
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/10
 * @Version V1.0
 **/
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User getUserByUserNameAndPassWord(User user) {
        String sql = "select * from bs_user where username=? and password=?";
        return getBean(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean regist(User user) {
        String sql = "insert into bs_user(username,password,email) values(?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User getUserByUserName(User user) {
        String sql = "select * from bs_user where username=?";
        return getBean(sql, user.getUsername());
    }
}
