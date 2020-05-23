package com.phj.service.impl;

import com.phj.bean.Book;
import com.phj.bean.Page;
import com.phj.dao.BookDao;
import com.phj.dao.impl.BookDaoImpl;
import com.phj.service.BookService;

import java.util.List;

/**
 * @ClassName BookServiceImpl 图书事务实现
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/14
 * @Version V1.0
 **/
public class BookServiceImpl implements BookService {

    /**
     * 图书doa 操纵图书表
     */
    BookDao bookDao = new BookDaoImpl();

    @Override
    public boolean addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public boolean deleteBook(Book book) {
        return bookDao.deleteBook(book);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public void updateStockAndSales(Integer id, Integer stock, Integer sales) {
        bookDao.updateStockAndSales(id, stock, sales);
    }

    @Override
    public Book getOneBook(Book book) {
        return bookDao.getOneBook(book);
    }

    @Override
    public List<Book> getBookAll() {
        return bookDao.getBookAll();
    }

    @Override
    public Page<Book> getPage(String pageNow,String pageSize) {
        Page<Book> bookPage = new Page<>();
        //先要有总记录数，然后算出总页数，然后才能判断当前传入页面是否是不正确的

        //获取并设置总记录数，并转换类型，可能引发转型异常
        int totalCount = 0;
        try {
            totalCount = Integer.parseInt(bookDao.getTotalCount().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        bookPage.setTotalCount(totalCount);
        //获取并设置页面显示数据数目
        int size = bookPage.getPageSize();
        try {
            size = Integer.parseInt(pageSize);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        bookPage.setPageSize(size);
        //获取并设置现在页码
        int now = 1;
        try {
            now = Integer.parseInt(pageNow);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        bookPage.setPageNow(now);
        //获取并设置当前页面要显示的数据
        List<Book> bookList = bookDao.getPageList(bookPage.getIndex(), bookPage.getPageSize());
        bookPage.setPageData(bookList);

        return bookPage;
    }

    @Override
    public Page<Book> getPageListByPrice(String pageNow, String pageSize, String minPrice, String maxPrice) {
        double min = 0;
        try {
            min = Double.parseDouble(minPrice);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        double max = Double.MAX_VALUE;
        try {
            max = Double.parseDouble(maxPrice);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Page<Book> bookPage = new Page<>();
        //先要有总记录数，然后算出总页数，然后才能判断当前传入页面是否是不正确的
        //获取并设置总记录数，并转换类型，可能引发转型异常
        int totalCount = 0;
        try {
            totalCount = Integer.parseInt(bookDao.getTotalCountByPrice(min, max).toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        bookPage.setTotalCount(totalCount);
        //获取并设置页面显示数据数目
        int size = bookPage.getPageSize();
        try {
            size = Integer.parseInt(pageSize);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        bookPage.setPageSize(size);
        //获取并设置现在页码
        int now = 1;
        try {
            now = Integer.parseInt(pageNow);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        bookPage.setPageNow(now);
        //获取并设置当前页面要显示的数据
        List<Book> bookList = bookDao.getPageListByPrice(bookPage.getIndex(), bookPage.getPageSize(),min,max);
        bookPage.setPageData(bookList);
        return bookPage;
    }
}
