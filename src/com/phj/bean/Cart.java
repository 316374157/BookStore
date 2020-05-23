package com.phj.bean;

import java.math.BigDecimal;
import	java.util.ArrayList;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Cart 购物车
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/27
 * @Version V1.0
 **/
public class Cart {
    /**
     * 每一个购物项，通过图书id辨识
     */
    private Map<Integer,CartItem> items = new LinkedHashMap<>();
    /**
     * 总商品数
     */
    private int totalCount;
    /**
     * 总金额
     */
    private double totalMoney;


    public Cart(Map<Integer, CartItem> items, int totalCount, double totalMoney) {
        this.items = items;
        this.totalCount = totalCount;
        this.totalMoney = totalMoney;
    }

    public Cart() {
    }

    /**
     * 获取商品总数
     * @return 商品总数
     */
    public int getTotalCount() {
        List<CartItem> itemList = getItems();
        int count = 0;
        for (CartItem item:itemList) {
            count += item.getCount();
        }
        return count;
    }

    /**
     * 获取总价格
     * @return 总价格
     */
    public double getTotalMoney() {
        List<CartItem> itemList = getItems();
        BigDecimal money = new BigDecimal("0");
        for (CartItem item:itemList) {
            BigDecimal bigDecimal = new BigDecimal(item.getTotalPrice() + "");
            money = money.add(bigDecimal);
        }
        return money.doubleValue();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", totalCount=" + totalCount +
                ", totalMoney=" + totalMoney +
                '}';
    }

    /**
     * 向购物车中添加图书
     * @param book 要添加的图书
     */
    public void addBookToCart(Book book){
        //如果有当前图书，则图书数目++，如果没有则新建
        CartItem item = items.get(book.getId());
        if(item == null){
            CartItem cartItem = new CartItem(book,1,book.getPrice());
            items.put(book.getId(),cartItem);
        }else {
            item.setCount(item.getCount()+1);
        }
    };

    /**
     * 删除购物项
     * @param id 图书id
     */
    public void deleteItem(String id){
        int key = Integer.parseInt(id);
        items.remove(key);
    };


    /**
     * 将购物项转变成list，方便前端遍历
     * @return 购物项list
     */
    public List<CartItem> getItems(){
        //返回map中的所有值，返回值是一个集合
        Collection<CartItem> values = items.values();
        return new ArrayList<>(values);
    };

    /**
     * 通过id获取某一购物项
     * @return 购物项
     */
    public CartItem getItem(String id){
        int parseInt = Integer.parseInt(id);
        CartItem cartItem = items.get(parseInt);
        return  cartItem;
    };

    /**
     * 更新图书数目
     * @param id 图书id
     * @param count 要更新的数目
     */
    public void updateCount(String id,String count){
        int key = Integer.parseInt(id);
        CartItem cartItem = items.get(key);
        int i = cartItem.getCount();
        try {
            i = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(i<=0){
            i = cartItem.getCount();
        }
        cartItem.setCount(i);
    };


    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    };

}
