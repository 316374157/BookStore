<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<jsp:include page="../../public/base.jsp"></jsp:include>
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
			<span class="wel_word">订单详情</span>
			<c:if test="${!empty sessionScope.user}">
				<jsp:include page="../../public/order-header.jsp"></jsp:include>
			</c:if>
			<c:if test="${empty sessionScope.user}">
				<jsp:include page="../../public/manager-header.jsp"></jsp:include>
			</c:if>
	</div>
	
	<div id="main">

			<table>
				<tr>
					<th>订单号</th>
					<th>图书名称</th>
					<th>购买数量</th>
					<th>单价</th>
					<th>总价</th>
				</tr>
				<c:forEach items="${orderItems}" var="orderItem">
					<tr>
						<td>${orderItem.orderId}</td>
						<td>${orderItem.title}</td>
						<td>${orderItem.count}</td>
						<td>${orderItem.price}</td>
						<td>${orderItem.totalPrice}</td>
					</tr>
				</c:forEach>
			</table>

	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>