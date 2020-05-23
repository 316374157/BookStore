<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<jsp:include page="../../public/base.jsp"></jsp:include>
	<script type="text/javascript">
        $(function () {
            $(".send").click(function () {
                if(!confirm("是否确认发货？"))
                    return false;
            })
        })
	</script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<jsp:include page="../../public/manager-header.jsp"></jsp:include>
	</div>

	<div id="main">
		<table>
			<tr>
				<th>订单号</th>
				<th>日期</th>
				<th>金额</th>
				<th>状态</th>
				<th>详情</th>
				<th>发货</th>
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
							<td>已发货</td>
						</c:when>
						<c:when test="${order.status == 2}">
							<td>交易完成</td>
						</c:when>
					</c:choose>
					<td><a href="OrderServlet?method=getOrderDetails&orderId=${order.orderId}">查看详情</a></td>
					<c:choose>
						<c:when test="${order.status == 0}">
							<td><a href="OrderServlet?method=send&orderId=${order.orderId}" class="send">发货</a></td>
						</c:when>
						<c:when test="${order.status == 1}">
							<td>已发货</td>
						</c:when>
						<c:when test="${order.status == 2}">
							<td>已收货</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="../../public/bottom.jsp"></jsp:include>
</body>
</html>