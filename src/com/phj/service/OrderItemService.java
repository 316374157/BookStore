package com.phj.service;

import com.phj.bean.OrderItem;

import java.util.List;

/**
 * @ClassName OrderItemService 订单项事务接口
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/29
 * @Version V1.0
 **/
public interface OrderItemService {

    /**
     * 根据订单号查询订单项
     * @param orderId 订单号
     * @return 查询到的订单项
     */
    public List<OrderItem> getOrderItem(String orderId);

    /**
     * 保存订单项
     * @param items 要保存的订单项集合
     */
    public void setOrderItem(List<OrderItem> items);
}
