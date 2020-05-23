package com.phj.bean;

import java.math.BigDecimal;

/**
 * @ClassName CartItem 购物车的购物项
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/27
 * @Version V1.0
 **/
public class CartItem {
    /**
     * 当前购买的图书
     */
    private Book book;
    /**
     * 购买的数目
     */
    private int count;
    /**
     * 总价格
     */
    private double totalPrice;

    public CartItem() {
    }

    public CartItem(Book book, int count, double totalPrice) {
        this.book = book;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        //解决double运算产生的精度问题，用BigDecimal包装（传入string）用到的计算量，然后用BigDecimal自带的运算方法
        BigDecimal price = new BigDecimal(getBook().getPrice() + "");
        BigDecimal count = new BigDecimal(getCount());
        return price.multiply(count).doubleValue();
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
