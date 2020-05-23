package com.phj.dao;

import com.phj.bean.Book;

import java.util.List;

/**
 * @ClassName BookDao 操纵图书表的dao接口
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/13
 * @Version V1.0
 **/
public interface BookDao {
    /**
     * 增加一本图书
     * @param book 要增加的图书对象
     * @return true表示增加成功，false表示增加失败
     */
    public boolean addBook(Book book);

    /**
     * 删除一本图书
     * @param book 要删除的图书对象
     * @return  true表示删除成功，false表示删除失败
     */
    public boolean deleteBook(Book book);

    /**
     * 修改一本图书
     * @param book 要修改的图书对象
     * @return  true表示修改成功，false表示修改失败
     */
    public boolean updateBook(Book book);

    /**
     * 更新图书的销量和库存
     * @param id 要更新的图书id
     * @param stock 更新后的销量
     * @param sales 更新后的库存
     */
    public void updateStockAndSales(Integer id,Integer stock,Integer sales);

    /**
     * 获取一本图书
     * @param book 要获取的图书对象
     * @return 获取到得图书对象
     */
    public Book getOneBook(Book book);

    /**
     * 获取所有的图书
     * @return 全部的图书封装的list对象
     */
    public List<Book> getBookAll();

    /**
     * 获取当前页面的图书
     * @param index 起始索引
     * @param size 查询数目
     * @return 页面封装的图书
     */
    public List<Book> getPageList(int index,int size);

    /**
     * 获取当前页面符合条件的图书（在价格区间的）
     * @param index 起始索引
     * @param size 查询数目
     * @param minPrice 最小价格
     * @param maxPrice 最大价格
     * @return 页面封装的图书
     */
    public List<Book> getPageListByPrice(int index,int size,double minPrice,double maxPrice);

    /**
     * 获取数据库中的图书数据总数
     * @return 图书总数
     */
    public Object getTotalCount();

    /**
     * 获取所有需要的图书数据（指定价格区间）
     * @param minPrice 最小价格
     * @param maxPrice 最大价格
     * @return 全部的图书封装的list对象
     */
    public Object getTotalCountByPrice(double minPrice,double maxPrice);



}
