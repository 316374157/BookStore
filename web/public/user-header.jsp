<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 31637
  Date: 2020/4/11
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${empty sessionScope.user}">
        <div id="header">
            <img class="logo_img" alt="" src="static/img/logo.gif" >
            <span class="wel_word">网上书城</span>
            <div>
                <a href="pages/user/login.jsp">登录</a> |
                <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
                <a href="CartServlet?method=getItems">购物车</a>
                <a href="pages/manager/manager.jsp">后台管理</a>
                <a href="index.jsp">返回</a>
            </div>
        </div>
    </c:when>
    <c:when test="${!empty sessionScope.user}">
        <div id="header">
            <img class="logo_img" alt="" src="static/img/logo.gif" >
            <div>
                <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
                <a href="CartServlet?method=getItems">购物车</a>
                <a href="OrderServlet?method=getMyOrder">我的订单</a>
                <a href="UserServlet?method=logout">注销</a>&nbsp;&nbsp;
                <a href="index.jsp">返回</a>
            </div>
        </div>
    </c:when>
</c:choose>
