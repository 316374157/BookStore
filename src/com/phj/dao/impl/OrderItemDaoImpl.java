package com.phj.dao.impl;
import com.phj.bean.OrderItem;
import com.phj.dao.BaseDao;
import com.phj.dao.OrderItemDao;

import java.util.List;

/**
 * @ClassName OrderItemDaoImpl
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/29
 * @Version V1.0
 **/
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public List<OrderItem> getOrderItem(String orderId) {
        String sql = "select id,title,count,price,total_price as totalPrice,order_id as orderId from bs_order_item where order_id=?";
        return getBeanList(sql, orderId);
    }

    @Override
    public void setOrderItem(List<OrderItem> items) {
        String sql = "insert into bs_order_item(title,count,price,total_price,order_id) values(?,?,?,?,?)";
        //数组下标
        int count = 0;
        //第一维是执行次数，第二维是每次需要的参数
        Object[][] params = new Object[items.size()][5];
        for (OrderItem item: items) {
            params[count++] = new Object[]{item.getTitle(),item.getCount(),item.getPrice(),item.getTotalPrice(),item.getOrderId()};
        }
        batch(sql, params);
    }

}
