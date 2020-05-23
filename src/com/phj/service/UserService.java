package com.phj.service;

import com.phj.bean.User;

/**
 * @ClassName UserService 处理用户事务的接口
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/12
 * @Version V1.0
 **/
public interface UserService {
    /**
     * 用户登陆
     * @param user 前端传入的用户
     * @return 数据库中的用户
     */
    public User login(User user);

    /**
     * 注册用户
     * @param user 要注册的用户
     * @return  true表示注册成功，false表示注册失败
     */
    public boolean regist(User user);

    /**
     * 通过账号检查用户是否存在
     * @param user 要检查的用户
     * @return true表示存在，false表示不存在
     */
    public boolean cheekUser(User user);
}
