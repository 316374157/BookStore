package com.phj.servlet;

import com.phj.bean.Book;
import com.phj.bean.Page;
import com.phj.service.BookService;
import com.phj.service.impl.BookServiceImpl;
import com.phj.utilis.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName BookServlet 和前端的图书对应
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/14
 * @Version V1.0
 **/
@WebServlet(name = "BookServlet",value = "/BookServlet")
public class BookServlet extends BaseServlet {
    /**
     * 图书事务
     */
    private BookService bookService = new BookServiceImpl();

    /**
     * 获取全部图书,首页,分页之前
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getAllHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> bookList = bookService.getBookAll();
        request.setAttribute("bookList",bookList);
        request.getRequestDispatcher("pages/user/home.jsp").forward(request, response);
    }

    /**
     * 获取全部图书,首页,分页之后
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getAllHomeAfter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNow = request.getParameter("pageNow");
        Page<Book> page = bookService.getPage(pageNow, "4");
        request.setAttribute("page",page);
        request.setAttribute("url", "BookServlet?method=getAllHomeAfter&pageNow=");
        request.getRequestDispatcher("pages/user/home.jsp").forward(request, response);
    }

    /**
     * 获取全部图书,首页,带上价格
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getAllHomeByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNow = request.getParameter("pageNow");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        Page<Book> page = bookService.getPageListByPrice(pageNow, "4",min,max);
        request.setAttribute("url", "BookServlet?method=getAllHomeByPrice&min="+min+"&max="+max+"&pageNow=");
        request.setAttribute("page",page);
        request.getRequestDispatcher("pages/user/home.jsp").forward(request, response);
    }

    /**
     * 删除图书
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.paramsToBean(request, new Book());
        bookService.deleteBook(book);
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }

    /**
     * 获取全部图书,管理员,分页之前
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getAllManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> bookList = bookService.getBookAll();
        request.setAttribute("bookList",bookList);
        request.getRequestDispatcher("pages/manager/book_manager.jsp").forward(request, response);
    }

    /**
     * 获取全部图书,管理员,分页之后
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getAllManagerAfter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNow = request.getParameter("pageNow");
        Page<Book> page = bookService.getPage(pageNow, "4");
        request.setAttribute("page",page);
        request.getRequestDispatcher("pages/manager/book_manager.jsp").forward(request, response);
    }


    /**
     * 获取指定的某本图书
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNow = request.getParameter("pageNow");
        Book book = WebUtils.paramsToBean(request, new Book());
        Book oneBook = bookService.getOneBook(book);
        request.setAttribute("book", oneBook);
        request.setAttribute("pageNow", pageNow);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    /**
     * 更新图书数据，然后返回图书列表页面
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void updateOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.paramsToBean(request, new Book());
        bookService.updateBook(book);
        getAllManagerAfter(request,response);
    }

    /**
     * 增加一种图书，然后返回图书列表页面
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.paramsToBean(request, new Book());
        bookService.addBook(book);
        getAllManagerAfter(request,response);
    }
}
