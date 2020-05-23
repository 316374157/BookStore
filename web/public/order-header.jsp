<%--
  Created by IntelliJ IDEA.
  User: 31637
  Date: 2020/4/23
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
	<span>欢迎<span class="um_span">${user.username}</span>光临尚硅谷书城</span>
	<a href="CartServlet?method=getItems">购物车</a>
	<a href="OrderServlet?method=getMyOrder">我的订单</a>
	<a href="index.jsp">注销</a>&nbsp;&nbsp;
	<a href="index.jsp">返回首页</a>
</div>