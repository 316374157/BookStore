package com.phj.service.impl;

import com.phj.bean.OrderItem;
import com.phj.dao.OrderItemDao;
import com.phj.dao.impl.OrderItemDaoImpl;
import com.phj.service.OrderItemService;

import java.util.List;

/**
 * @ClassName OrderItemServiceImpl
 * @Description: TODO
 * @Author 31637
 * @Date 2020/5/10
 * @Version V1.0
 **/
public class OrderItemServiceImpl implements OrderItemService {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public List<OrderItem> getOrderItem(String orderId) {
        return orderItemDao.getOrderItem(orderId);
    }

    @Override
    public void setOrderItem(List<OrderItem> items) {
        orderItemDao.setOrderItem(items);
    }
}
