<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员首页</title>
<jsp:include page="../../public/base.jsp"></jsp:include>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<jsp:include page="../../public/user-header.jsp"></jsp:include>
		<div id="main">
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
		</div>
		<jsp:include page="/public/bottom.jsp"></jsp:include>
</body>
</html>