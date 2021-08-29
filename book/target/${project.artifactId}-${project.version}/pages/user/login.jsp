<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
		<%-- static includes the base URL, CSS style, jQuery lib --%>
		<%@ include file="/pages/common/head.jsp"%>
	</head>
	<body>
			<div id="login_header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
			</div>

				<div class="login_banner">

					<div id="l_content">
						<span class="login_word">Welcome to log in</span>
					</div>

					<div id="content">
						<div class="login_form">
							<div class="login_box">
								<div class="tit">
									<h1>Account</h1>
									<a href="pages/user/regist.jsp">Register now</a>
								</div>
								<div class="msg_cont">
									<b></b>
									<span class="errorMsg">
										<%-- display hint --%>
										${empty requestScope.msg ? "Please enter username and password" : requestScope.msg}
									</span>
								</div>
								<div class="form">
									<form action="userServlet" method="post">
										<%-- to determine which method(method has the same name with the action's value) to use in Servlet --%>
										<%-- 方便调用Servlet中对应的方法，方法名要和action的value的值一致 --%>
										<input type="hidden" name="action" value="login" />
										<label>Username：</label>
										<input class="itxt" type="text" placeholder="Please enter username"
											   autocomplete="off" tabindex="1" name="username"
											   value="${requestScope.username}"/>
										<br />
										<br />
										<label>Password：</label>
										<input class="itxt" type="password" placeholder="Please enter password"
											   autocomplete="off" tabindex="1" name="password" />
										<br />
										<br />
										<input type="submit" value="login" id="sub_btn" />
									</form>
								</div>

							</div>
						</div>
					</div>
				</div>
			<!-- static includes the footer page  -->
			<%@ include file="/pages/common/footer.jsp"%>
	</body>
</html>