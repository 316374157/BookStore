package com.phj.test;

import com.phj.bean.Book;
import com.phj.bean.Page;
import com.phj.bean.User;
import com.phj.dao.UserDao;
import com.phj.dao.impl.UserDaoImpl;
import com.phj.service.BookService;
import com.phj.service.impl.BookServiceImpl;
import org.junit.Test;

/**
 * @ClassName test 测试
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/10
 * @Version V1.0
 **/
public class test {


    @Test
    public void test01(){
        UserDao userDao = new UserDaoImpl();
        User admin = userDao.getUserByUserNameAndPassWord(new User(null, "admin", "123456", null));
        System.out.println(admin);
    }

    @Test
    public void test02(){
        BookService bookService = new BookServiceImpl();
        Page<Book> page = bookService.getPage("", "4");
        System.out.println(page);
    }
}
