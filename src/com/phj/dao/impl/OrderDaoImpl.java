package com.phj.dao.impl;

import com.phj.bean.Order;
import com.phj.dao.BaseDao;
import com.phj.dao.OrderDao;

import java.util.List;

/**
 * @ClassName OrderDaoImpl
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/29
 * @Version V1.0
 **/
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public boolean saveOrder(Order order) {
        String sql = "insert into bs_order(order_id,create_date,status,total_money,user_id) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateDate(),order.getStatus(),order.getTotalMoney(),order.getUserId());
    }

    @Override
    public boolean updateStatus(Order order) {
        String sql = "update bs_order set status=? where order_id=?";
        return update(sql, order.getStatus(),order.getOrderId());
    }

    @Override
    public List<Order> getOrderList() {
        String sql = "select order_id as orderId,create_date as createDate,status,total_money as totalMoney ,user_id as userId from bs_order";
        return getBeanList(sql);
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        String sql = "select order_id as orderId,create_date as createDate,status,total_money as totalMoney ,user_id as userId from bs_order where user_id=?";
        return getBeanList(sql, userId);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        String sql = "select order_id as orderId,create_date as createDate,status,total_money as totalMoney ,user_id as userId from bs_order where order_id=?";
        return getBean(sql, orderId);
    }
}
