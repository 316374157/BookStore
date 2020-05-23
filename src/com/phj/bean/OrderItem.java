package com.phj.bean;

/**
 * @ClassName OrderItem 订单中的每一项
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/28
 * @Version V1.0
 **/
public class OrderItem {
    /**
     * 主键，自增
     */
    private Integer id;
    /**
     * 买的书名
     */
    private String title;
    /**
     * 数目，买了几本书
     */
    private int count;
    /**
     * 当时的书的价格
     */
    private double price;
    /**
     * 图书的总价格
     */
    private double totalPrice;
    /**
     * 订单编号，外键，和订单关联
     */
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(Integer id, String title, int count, double price, double totalPrice, String orderId) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
