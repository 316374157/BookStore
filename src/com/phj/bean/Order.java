package com.phj.bean;


import java.util.Date;

/**
 * @ClassName Order 订单
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/28
 * @Version V1.0
 **/
public class Order {
    /**
     * 订单号,主键
     */
    private String orderId;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 订单状态
     */
    private int status;
    /**
     * 订单总金额
     */
    private double totalMoney;
    /**
     * 用户id，外键
     */
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createDate, int status, double totalMoney, Integer userId) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.status = status;
        this.totalMoney = totalMoney;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                ", totalMemory=" + totalMoney +
                ", bookId=" + userId +
                '}';
    }
}
