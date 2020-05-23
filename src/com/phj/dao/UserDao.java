package com.phj.dao;

import com.phj.bean.User;

/**
 * @ClassName UserDao 操作用户表
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/10
 * @Version V1.0
 **/
public interface UserDao {
    /**
     * 通过账号密码从数据库中获取并封装一个user对象
     * @param user 上层传入的user对象
     * @return 数据库中获取并封装好的
     */
    public User getUserByUserNameAndPassWord(User  user);

    /**
     * 注册用户
     * @param user 要注册的用户
     * @return  true表示注册成功，false表示注册失败
     */
    public boolean regist(User user);

    /**
     * 通过账号从数据库中获取并封装一个user对象
     * @param user 上层传入的user对象
     * @return 数据库中获取并封装好的
     */
    public User getUserByUserName(User  user);
}
