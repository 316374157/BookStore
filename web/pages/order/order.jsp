<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<jsp:include page="../../public/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function () {
			$(".accept").click(function () {
				if(!confirm("是否确认收货？"))
				    return false;
            })
        })
	</script>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<jsp:include page="../../public/order-header.jsp"></jsp:include>
	</div>
	
	<div id="main">
		<c:if test="${!empty orderList}">
			<table>
				<tr>
					<th>订单号</th>
					<th>日期</th>
					<th>金额</th>
					<th>状态</th>
					<th>详情</th>
				</tr>
				<c:forEach items="${orderList}" var="order">
					<tr>
						<td>${order.orderId}</td>
						<td>${order.createDate}</td>
						<td>${order.totalMoney}</td>
						<c:choose>
							<c:when test="${order.status == 0}">
								<td>未发货</td>
							</c:when>
							<c:when test="${order.status == 1}">
								<td><a href="OrderServlet?method=accept&orderId=${order.orderId}" class="accept">收货</a></td>
							</c:when>
							<c:when test="${order.status == 2}">
								<td>交易完成</td>
							</c:when>
						</c:choose>
						<td><a href="OrderServlet?method=getOrderDetails&orderId=${order.orderId}">查看详情</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<c:if test="${empty orderList}">
			<h2 style="margin:15% 35%">订单为空，赶紧<a href="index.jsp" style="color: darkblue">去购买吧</a></h2>
		</c:if>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>