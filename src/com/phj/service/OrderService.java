package com.phj.service;

import com.phj.bean.Cart;
import com.phj.bean.Order;
import com.phj.bean.User;

import java.util.List;

/**
 * @ClassName OrderService 订单事务接口
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/29
 * @Version V1.0
 **/
public interface OrderService {

    /**
     * 根据购物车生成订单，并保存到数据库中，并修改库存和销量
     * @param cart 购物车
     * @param user 和订单对应的用户对象
     * @return 返回订单号
     */
    public String checkout(Cart cart, User user);

    /**
     * 更新订单状态
     * @param orderId 要更新的订单号
     * @param status 已经修改好的，需要保存的状态好
     */
    public void updateStatus(String orderId,String status);

    /**
     * 获取所有订单
     * @return 所有订单的集合
     */
    public List<Order> getOrderList();

    /**
     * 获取某个用户的订单
     * @param userId 该用户id
     * @return 获取到的订单
     */
    public List<Order> getOrderByUserId(Integer userId);

    /**
     * 根据订单号获取订单信息
     * @param orderId 订单号
     * @return 获取到的订单
     */
    public Order getOrderByOrderId(String orderId);
}
