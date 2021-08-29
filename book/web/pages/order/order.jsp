<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>My Orders</title>
		<%-- static includes the base URL, CSS style, jQuery lib --%>
		<%@ include file="/pages/common/head.jsp"%>
		<style type="text/css">
			h1 {
				text-align: center;
				margin-top: 200px;
			}
		</style>
		<script type="text/javascript">
			$(function () {
				// bind action event on receiving package
				$("#receiveOrder").click(function () {
					return confirm("Do you receive your package?");
				});
			});
		</script>
	</head>
	<body>

		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">My Orders</span>

			<%-- static include the menu if user logs in successfully --%>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
		</div>

		<div id="main">

			<table>
				<tr>
					<td>Data</td>
					<td>Total Price</td>
					<td>Package Status</td>
					<td>Details</td>
				</tr>

				<c:forEach items="${requestScope.page.items}" var="order">
					<tr>
						<td>${order.createTime}</td>
						<td>${order.price}</td>
						<td>
							<c:choose>
								<c:when test="${order.status == 0}">preparing</c:when>
								<c:when test="${order.status == 1}">
									<a id="receiveOrder" href="orderServlet?action=receiveOrder&pageNo=${requestScope.page.pageNo}&orderId=${order.orderId}">delivering</a>
								</c:when>
								<c:when test="${order.status == 2}">received</c:when>
							</c:choose>
						</td>
						<td>
							<a href="orderServlet?action=showOrderDetails&orderId=${order.orderId}&pageNo=${requestScope.page.pageNo}">Details</a>
						</td>
					</tr>
				</c:forEach>
			</table>

			<%--paging,分页--%>
			<%@ include file="/pages/common/page_nav.jsp"%>

		</div>

		<!-- static includes the footer page  -->
		<%@ include file="/pages/common/footer.jsp"%>
	</body>
</html>