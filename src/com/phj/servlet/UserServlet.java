package com.phj.servlet;

import com.google.code.kaptcha.Constants;
import com.phj.bean.User;
import com.phj.service.UserService;
import com.phj.service.impl.UserServiceImpl;
import com.phj.utilis.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 31637 和前端的用户对应
 */
@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    /**
     * 登陆
     * @param request  请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException      异常
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = userService.login(WebUtils.paramsToBean(request, new User()));
        if (user == null) {
            request.setAttribute("msg", "账号或密码错误");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        session.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");

    }

    /**
     * 注册
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        //获取存放到session中的验证码信息，谷歌自己存放的
        String sessionCode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        User user = WebUtils.paramsToBean(request, new User());
        if(!code.equals(sessionCode)){
            request.setAttribute("msg", "验证码错误！");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }
        boolean regist = userService.regist(user);
        if (!regist) {
            request.setAttribute("msg", "账号已存在！");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }
        session.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
    }

    /**
     * 登出
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    /**
     * 检查用户名是否存在
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    protected void cheekUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.paramsToBean(request, new User());
        boolean cheekUser = userService.cheekUser(user);
        if(cheekUser){
            response.getWriter().write("用户名已存在！");
            return;
        }
        response.getWriter().write("用户名可用!");
    }
}
