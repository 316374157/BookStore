package com.phj.dao;

import com.phj.bean.Order;

import java.util.List;

/**
 * @ClassName OrderDao 订单数据库操作接口
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/29
 * @Version V1.0
 **/
public interface OrderDao {
    /**
     * 保存订单
     * @param order 要保存的订单
     * @return true表示保存成功
     */
    public boolean saveOrder(Order order);

    /**
     * 更新订单状态
     * @param order 数据更改后的订单
     * @return true表示更新成功
     */
    public boolean updateStatus(Order order);

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
