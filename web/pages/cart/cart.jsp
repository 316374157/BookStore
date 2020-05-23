<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<jsp:include page="../../public/base.jsp"/>
	<script type="text/javascript">
		$(function () {
			$("#clean").click(function () {
				if(!confirm('是否清空购物车？')){
				    return false;
				}
            })
			$(".count").change(function () {
				$.getJSON("CartServlet?method=updateCount&id="+$(this).attr("updataid")+"&count="+$(this).val(),function (data) {
					$(".b_count").text(data.totalCount);
					$(".b_price").text(data.totalMoney);
					$("#price_"+data.id).text(data.totalPrice);
					$(".count[updataid="+data.id+"]").val(data.count);
                })
            })
			$("#checkout").click(function () {
				if(`${sessionScope.user}` == null){
				    window.location.href = "pages/user/login.jsp";
				    return false;
                }
            })
        })
	</script>
</head>
<body>
	<jsp:include page="../../public/user-header.jsp"/>
	
	<div id="main">
		<c:if test="${empty items}">
			<h2 style="margin:15% 35%">购物车为空，赶紧<a href="index.jsp" style="color: darkblue">去购买吧</a></h2>
		</c:if>
		<c:if test="${!empty items}">
			<table>
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${items}" var="cartItem">
					<tr>
						<td>${cartItem.book.title}</td>
						<td>
							<input class="count" style="width: 40px" name="count" value="${cartItem.count}" updataid="${cartItem.book.id}">
						</td>
						<td>${cartItem.book.price}</td>
						<td id="price_${cartItem.book.id}">${cartItem.totalPrice}</td>
						<td><a href="CartServlet?method=deleteItem&id=${cartItem.book.id}">删除</a></td>
					</tr>
				</c:forEach>
			</table>

			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${cart.totalMoney}</span>元</span>
				<span class="cart_span"><a href="CartServlet?method=clearCart" id="clean">清空购物车</a></span>
				<span class="cart_span"><a href="OrderServlet?method=checkout" id="checkout">去结账</a></span>
			</div>
		</c:if>
	</div>
	
	<jsp:include page="../../public/bottom.jsp"/>
</body>
</html>