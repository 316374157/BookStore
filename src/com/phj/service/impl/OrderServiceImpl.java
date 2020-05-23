package com.phj.service.impl;
import com.phj.bean.*;
import com.phj.dao.OrderDao;
import com.phj.dao.impl.OrderDaoImpl;
import com.phj.service.BookService;
import com.phj.service.OrderItemService;
import com.phj.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description: TODO
 * @Author 31637
 * @Date 2020/5/10
 * @Version V1.0
 **/
public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemService orderItemService = new OrderItemServiceImpl();
    BookService bookService = new BookServiceImpl();

    @Override
    public String checkout(Cart cart, User user) {
        //1.封装订单
        Order order = new Order();
        order.setTotalMoney(cart.getTotalMoney());
        order.setStatus(0);
        order.setUserId(user.getId());
        order.setCreateDate(new Date());
        //生成订单号的小算法
        long millis = System.currentTimeMillis();
        String orderId = millis + "" + user.getId();
        order.setOrderId(orderId);
        //2.生成订单项
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem(null, cartItem.getBook().getTitle(), cartItem.getCount(), cartItem.getBook().getPrice()
                    , cartItem.getTotalPrice(), orderId);
            orderItems.add(orderItem);
        }
        //3.保存订单
        orderDao.saveOrder(order);
        //4.保存订单项
        orderItemService.setOrderItem(orderItems);
        //5.修改数据库的库存和销量
        for (CartItem cartItem : cart.getItems()) {
            Book book = cartItem.getBook();
            int stock = book.getStock() - cartItem.getCount();
            int sales = book.getSales() + cartItem.getCount();
            bookService.updateStockAndSales(cartItem.getBook().getId(), stock, sales);
        }
        return orderId;
    }

    @Override
    public void updateStatus(String orderId, String status) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(Integer.parseInt(status));
        orderDao.updateStatus(order);
    }

    @Override
    public List<Order> getOrderList() {
        return orderDao.getOrderList();
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        return orderDao.getOrderByUserId(userId);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        return orderDao.getOrderByOrderId(orderId);
    }
}
