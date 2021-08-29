<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Orders Management</title>
		<%-- static includes the base URL, CSS style, jQuery lib --%>
		<%@ include file="/pages/common/head.jsp"%>
		<script type="text/javascript">
			$(function () {
				// bind action event on delivering package
				$("#deliverOrder").click(function () {
					return confirm("Deliver this package?");
				});
			});
		</script>
	</head>

	<body>

		<div id="header">
			<img class="logo_img" alt="" src="/static/img/logo.gif" >
			<span class="wel_word">Orders Management</span>
			<!-- static include manager menu page -->
			<%@ include file="/pages/common/manager_menu.jsp"%>
		</div>

		<div id="main">
			<table>
				<tr>
					<td>Date</td>
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
								<c:when test="${order.status == 0}"><a id="deliverOrder" href="orderServlet?action=deliverOrder&pageNo=${requestScope.page.pageNo}&orderId=${order.orderId}">click to deliver</a></c:when>
								<c:when test="${order.status == 1}">delivering</c:when>
								<c:when test="${order.status == 2}">received</c:when>
							</c:choose>
						</td>
						<td><a href="orderServlet?action=showOrderDetails&orderId=${order.orderId}&isAdmin=true&pageNo=${requestScope.page.pageNo}">Details</a></td>
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