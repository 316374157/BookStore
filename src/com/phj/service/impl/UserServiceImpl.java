package com.phj.service.impl;

import com.phj.bean.User;
import com.phj.dao.UserDao;
import com.phj.dao.impl.UserDaoImpl;
import com.phj.service.UserService;

/**
 * @ClassName UserServiceImpl 实现
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/12
 * @Version V1.0
 **/
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return userDao.getUserByUserNameAndPassWord(user);
    }

    @Override
    public boolean regist(User user) {
        if(userDao.getUserByUserName(user)!=null){
            return false;
        }
        return userDao.regist(user);
    }

    @Override
    public boolean cheekUser(User user) {
        User userName = userDao.getUserByUserName(user);
        if(userName != null){
            return true;
        }
        return false;
    }
}
