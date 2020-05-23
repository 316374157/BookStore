package com.phj.dao.impl;

import com.phj.bean.Book;
import com.phj.dao.BaseDao;
import com.phj.dao.BookDao;

import java.util.List;

/**
 * @ClassName BookDaoImpl
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/13
 * @Version V1.0
 **/
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public boolean addBook(Book book) {
        String sql = "insert into bs_book(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql, book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public boolean deleteBook(Book book) {
        String sql = "delete from bs_book where id=?";
        return update(sql, book.getId());
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "update bs_book set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql, book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public void updateStockAndSales(Integer id, Integer stock, Integer sales) {
        String sql = "update bs_book set sales=?,stock=? where id=?";
        update(sql, sales,stock,id);
    }

    @Override
    public Book getOneBook(Book book) {
        String sql = "select id,title,author,price,sales,stock,img_path as imgPath from bs_book where id=?";
        return getBean(sql, book.getId());
    }

    @Override
    public List<Book> getBookAll() {
        String sql = "select id,title,author,price,sales,stock,img_path as imgPath from bs_book";
        return getBeanList(sql);
    }


    @Override
    public List<Book> getPageList(int index, int size) {
        String sql = "select id,title,author,price,sales,stock,img_path as imgPath from bs_book limit ?,?";
        return getBeanList(sql, index,size);
    }

    @Override
    public List<Book> getPageListByPrice(int index, int size, double minPrice, double maxPrice) {
        String sql = "select id,title,author,price,sales,stock,img_path as imgPath from bs_book where price between ? and ?limit ?,? ";
        return getBeanList(sql, minPrice,maxPrice,index,size);
    }

    @Override
    public Object getTotalCount() {
        String sql = "select count(*) from bs_book";
        return getSingleValue(sql);
    }

    @Override
    public Object getTotalCountByPrice(double minPrice, double maxPrice) {
        String sql = "select count(*) from bs_book where price between ? and ?";
        return getSingleValue(sql,minPrice,maxPrice);
    }
}
