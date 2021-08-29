<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login Success</title>

		<%-- static includes the base URL, CSS style, jQuery lib --%>
		<%@ include file="/pages/common/head.jsp"%>
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
			<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >

				<%-- static include the menu if user logs in successfully --%>
				<%@ include file="/pages/common/login_success_menu.jsp"%>
			</div>

			<div id="main">

				<h1>Welcome back <a href="index.jsp">Go to home page</a></h1>

			</div>

			<!-- static includes the footer page  -->
			<%@ include file="/pages/common/footer.jsp"%>
	</body>
</html>