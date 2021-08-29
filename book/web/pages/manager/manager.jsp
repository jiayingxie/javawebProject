<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Admin Management</title>
		<%-- static includes the base URL, CSS style, jQuery lib --%>
		<%@ include file="/pages/common/head.jsp"%>
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
			<span class="wel_word">Admin Management</span>
			<!-- static include manager menu page -->
			<%@ include file="/pages/common/manager_menu.jsp"%>
		</div>

		<div id="main">
			<h1>Admin, welcome to the Admin Management!</h1>
		</div>

		<!-- static includes the footer page  -->
		<%@ include file="/pages/common/footer.jsp"%>
	</body>
</html>