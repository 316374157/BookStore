<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<jsp:include page="../../public/base.jsp"></jsp:include>
	<script type="text/javascript">
        $(function () {
            $("#pn_input").keydown(function (e) {
                if(e.keyCode === 13)
                    window.location.href = "BookServlet?method=getAllManagerAfter&pageNow="+ $("#pn_input").val();
            })
        })
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<jsp:include page="../../public/manager-header.jsp"></jsp:include>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		

			<c:forEach items="${page.pageData}" var="book">
				<tr>
					<td>${book.title}</td>
					<td>￥${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="BookServlet?method=getBook&pageNow=${page.pageNow}&id=${book.id}">修改</a></td>
					<td><a href="BookServlet?method=delete&id=${book.id}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>

		<div id="page_nav">
			<a href="BookServlet?method=getAllManagerAfter&pageNow=1">首页</a>
			<c:if test="${page.hasPrevious}">
				<a href="BookServlet?method=getAllManagerAfter&pageNow=${page.pageNow-1}">上一页</a>
			</c:if>
			<c:choose>
				<c:when test="${page.totalPages <= 5}">
					<c:set var="begin" scope="page" value="1"></c:set>
					<c:set var="end" scope="page" value="${page.totalPages}"></c:set>
				</c:when>
				<c:when test="${page.totalPages > 5}">
					<c:choose>
						<c:when test="${page.pageNow<=3}">
							<c:set var="begin" scope="page" value="1"></c:set>
							<c:set var="end" scope="page" value="5"></c:set>
						</c:when>
						<c:when test="${page.pageNow +2 >=page.totalPages}">
							<c:set var="begin" scope="page" value="${page.totalPages-4}"></c:set>
							<c:set var="end" scope="page" value="${page.totalPages}"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="begin" scope="page" value="${page.pageNow-2}"></c:set>
							<c:set var="end" scope="page" value="${page.pageNow+2}"></c:set>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<c:forEach begin="${begin}" end="${end}" var="pnum">
				<c:if test="${pnum == page.pageNow}">
					【${page.pageNow}】
				</c:if>
				<c:if test="${pnum != page.pageNow}">
					<a href="BookServlet?method=getAllManagerAfter&pageNow=${pnum}">${pnum}</a>
				</c:if>
			</c:forEach>

			<c:if test="${page.hasNext}">
				<a href="BookServlet?method=getAllManagerAfter&pageNow=${page.pageNow+1}">下一页</a>
			</c:if>
			<a href="BookServlet?method=getAllManagerAfter&pageNow=${page.totalPages}">末页</a>
			共${page.totalPages}页，${page.totalCount}条记录 到第<input value="${page.pageNow}" name="pageNow" id="pn_input"/>页
		</div>
	</div>

	<jsp:include page="../../public/bottom.jsp"></jsp:include>
</body>
</html>