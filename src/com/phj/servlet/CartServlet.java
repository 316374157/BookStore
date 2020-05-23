package com.phj.servlet;

import com.google.gson.Gson;
import com.phj.bean.Book;
import com.phj.bean.Cart;
import com.phj.bean.CartItem;
import com.phj.service.BookService;
import com.phj.service.impl.BookServiceImpl;
import com.phj.utilis.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BookServlet 和前端的图书对应
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/14
 * @Version V1.0
 **/
@WebServlet(name = "CartServlet",value = "/CartServlet")
public class CartServlet extends BaseServlet {

    /**
     * 将图书添加到购物车
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.paramsToBean(request, new Book());
        HttpSession session = request.getSession();
        BookService bookService = new BookServiceImpl();
        Book oneBook = bookService.getOneBook(book);
        Cart cart = WebUtils.getCart(request);
        cart.addBookToCart(oneBook);
        session.setAttribute("cart", cart);
        Map<String,Object> map = new HashMap<>(2);
        map.put("title", oneBook.getTitle());
        map.put("total", cart.getTotalCount());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }

    /**
     * 删除购物项
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        Cart cart = WebUtils.getCart(request);
        cart.deleteItem(id);
        session.setAttribute("cart", cart);
        getItems(request, response);
    }

    /**
     * 清空购物车
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = WebUtils.getCart(request);
        cart.clear();
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("/pages/cart/cart.jsp").forward(request, response);
    }

    /**
     * 修改购物项
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String count = request.getParameter("count");
        HttpSession session = request.getSession();
        Cart cart = WebUtils.getCart(request);
        cart.updateCount(id,count);
        session.setAttribute("cart", cart);
        CartItem cartItem = cart.getItem(id);
        Map<String,Object> map = new HashMap<>(5);
        map.put("totalCount", cart.getTotalCount());
        map.put("totalMoney", cart.getTotalMoney());
        map.put("totalPrice",cartItem.getTotalPrice());
        map.put("id",cartItem.getBook().getId());
        map.put("count",cartItem.getCount());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }

    /**
     * 获取购物车
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = WebUtils.getCart(request);
        List<CartItem> items = cart.getItems();
        request.setAttribute("items", items);
        request.getRequestDispatcher("/pages/cart/cart.jsp").forward(request, response);
    }
}
