package com.phj.servlet;

import com.phj.bean.Cart;
import com.phj.bean.Order;
import com.phj.bean.OrderItem;
import com.phj.bean.User;
import com.phj.service.OrderItemService;
import com.phj.service.OrderService;
import com.phj.service.impl.OrderItemServiceImpl;
import com.phj.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 订单控制器
 * @author 31637
 */
@WebServlet(name = "OrderServlet",value = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();
    OrderItemService orderItemService = new OrderItemServiceImpl();

    /**
     * 结账
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("pages/user/login.jsp");
            return;
        }
        String orderId = orderService.checkout(cart, user);
        request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("pages/cart/checkout.jsp").forward(request, response);
    }

    /**
     * 通过用户id获取个人订单
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getMyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orderList = orderService.getOrderByUserId(user.getId());
        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
    }

    /**
     * 管理员获取所有订单
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = orderService.getOrderList();
        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("pages/manager/order_manager.jsp").forward(request, response);
    }

    /**
     * 确认发货
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        orderService.updateStatus(orderId, "1");
        getAllOrder(request, response);
    }

    /**
     * 确认收货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void accept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        orderService.updateStatus(orderId, "2");
        getMyOrder(request, response);
    }

    /**
     * 获取订单详情
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void getOrderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = orderItemService.getOrderItem(orderId);
        request.setAttribute("orderItems", orderItems);
        request.getRequestDispatcher("pages/order/orderdetails.jsp").forward(request, response);
    }
}
