<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<jsp:include page="../../public/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function () {
			$("#codeImg").click(function () {
				//点击切换验证码，为了防止缓存，所以使每一次的请求不同
				const url = "code.jpg?r="+Math.random();
				$(this).prop("src",url);
			});
			$("input[name='username']").blur(function () {
				$.get("UserServlet?method=cheekUser&username="+$("input[name='username']").val(),function (data) {
					$(".errorMsg").text(data);
				})
			});
			$("#sub_btn").click(function () {
				//获取表单项
				const username = $("input[name='username']").val();
				const password = $("input[name='password']").val();
				const repwd = $("input[name='repwd']").val();
				const email = $("input[name='email']").val();
				const code = $("input[name='code']").val();

				//进行正则表达式验证,先创建一个正则表达式，格式：const usernameTest = /^[a-zA-Z0-9]{5,16}$/;
				//用户名验证规格：长度 5-16，可以包括合法字符（数字，字母）
				const usernameTest = /^[a-zA-Z0-9]{5,16}$/;
				//密码验证规格：长度 5-16，可以包括合法字符（数字，字母,.,_,-）
				const passwordTest = /^[a-zA-Z0-9._-]{5,16}$/;
				//密码验证规格：长度 5-16，可以包括合法字符（数字，字母,.,_,-）
				const emailTest = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				if(!usernameTest.test(username))
				{
					$(".errorMsg").text("用户名不合法！");
					return false;
				}
				if(!passwordTest.test(password))
				{
					$(".errorMsg").text("密码不合法！");
					return false;
				}
				if(repwd !== password)
				{
					$(".errorMsg").text("两次输入密码不同！");
					return false;
				}
				if(!emailTest.test(email))
				{
					$(".errorMsg").text("邮箱格式不正确！");
					return false;
				}
				if(code == null)
				{
					$(".errorMsg").text("验证码不能为空！");
					return false;
				}
			});
		})
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${msg}</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<input type="hidden" name="method" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" value="${param.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" value="${param.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 120px;" name="code" />
									<img alt="" src="code.jpg" id="codeImg" style="float: right; margin-right: 40px;width: 100px;height: 40px;" >
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<jsp:include page="../../public/bottom.jsp"></jsp:include>
</body>
</html>