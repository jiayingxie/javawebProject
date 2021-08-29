<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Bookstore Home Page</title>
		<%-- static includes the base URL, CSS style, jQuery lib --%>
		<%@ include file="/pages/common/head.jsp"%>
<%--		<style type="text/css">--%>
<%--			#mean_bar {--%>
<%--				margin-top: 0px;--%>
<%--			}--%>
<%--		</style>--%>
		<script type="text/javascript">
			$(function () {
				// 给 “加入购物车”按钮绑定单击事件
				$("button.addToCart").click(function () {
					// 在事件响应的function 函数中，有一个this 对象，这个this 对象，是当前正在响应事件的dom 对象
					let bookId = $(this).attr("bookId");
					// the below is the normal request to the cart
					// location.href = "http://localhost:8080/book/cartServlet?action=addItem&id=" + bookId;

					// ajax request
					// $.getJSON(url, data, callback);
					$.getJSON("http://localhost:8080/book/cartServlet", "action=ajaxAddItem&id=" + bookId, function (data) {
						// $(".cartTotalCount").text("Your shopping cart has "+ data.totalCount +" items.");
						totalCntMsg = "Your shopping cart has "+ data.totalCount;
						if (data.totalCount > 1) {
							totalCntMsg += " items.";
						} else {
							totalCntMsg += " item.";
						}
						$("#cartTotalCount").text(totalCntMsg);

						//
						if (data.msg != "") {
							$("#cartLastName").text(data.msg);
						} else {
							$("#cartLastName").text("You just added " + data.lastName + " to shopping cart.");
						}
					});
				});
			});
		</script>
	</head>

	<body>

		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<span class="wel_word">Bookstore</span>
<%--				<div id="mean_bar">--%>
				<div>
					<%-- if user is not logging in, show the login and register menu --%>
					<c:if test="${empty sessionScope.user}">
						<a href="pages/user/login.jsp">Login</a> |
						<a href="pages/user/regist.jsp">Register</a> &nbsp;&nbsp;
					</c:if>
					<%--otherwise, show user's data--%>
					<c:if test="${not empty sessionScope.user}">
						<span><span class="um_span">${sessionScope.user.username}</span>, welcome to the Bookstore.</span>
						<a href="orderServlet?action=showMyOrdersPage">My Orders</a>&nbsp;&nbsp;&nbsp;
						<a href="userServlet?action=logout">Logout</a>&nbsp;&nbsp;&nbsp;
					</c:if>
					<a href="pages/cart/cart.jsp">Shopping Cart</a>&nbsp;&nbsp;&nbsp;
					<%-- only admin has the access to the manager page --%>
					<c:if test="${sessionScope.user.username.equals('admin')}" >
						<a href="pages/manager/manager.jsp">Admin Management</a>
					</c:if>
				</div>
		</div>
		<div id="main">
			<div id="book">
				<div class="book_cond">
					<form action="client/bookServlet" method="get">
						<input type="hidden" name="action" value="pageByPrice">
						Price：￥<input id="min" type="text" name="min" value="${requestScope.min}"> -
							￥<input id="max" type="text" name="max" value="${requestScope.max}">
							<input type="submit" value="search" />
					</form>
				</div>
				<div style="text-align: center">
					<%-- if the cart is empty --%>
					<c:if test="${empty sessionScope.cart.items}">
						<span id="cartTotalCount"> </span>
						<div>
							<%-- 用了两个 id，因为这里是个if，所以相当于只有一个id --%>
							<span id="cartLastName" style="color: red">Your shopping cart is empty.</span>
						</div>
					</c:if>
					<%-- if the cart is not empty --%>
					<c:if test="${not empty sessionScope.cart.items}">
						<span id="cartTotalCount"></span>
						<div>
							<span style="color: red" id="cartLastName">You just added to the shopping cart.</span>
						</div>
					</c:if>

				</div>

				<c:forEach items="${requestScope.page.items}" var="book">
					<div class="b_list">
						<div class="img_div">
							<img class="book_img" alt="" src="${book.imgPath}" />
						</div>
						<div class="book_info">
							<div class="book_name">
								<span class="sp1">Name: </span>
								<span class="sp2">${book.name}</span>
							</div>
							<div class="book_author">
								<span class="sp1">Author: </span>
								<span class="sp2">${book.author}</span>
							</div>
							<div class="book_price">
								<span class="sp1">Price: </span>
								<span class="sp2">￥${book.price}</span>
							</div>
							<div class="book_sales">
								<span class="sp1">Sales: </span>
								<span class="sp2">${book.sales}</span>
							</div>
							<div class="book_amount">
								<span class="sp1">Stock: </span>
								<span class="sp2">${book.stock}</span>
							</div>
							<div class="book_add">
								<button class="addToCart" bookId="${book.id}">add to shopping cart</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			<%--paging,分页--%>
			<%@ include file="/pages/common/page_nav.jsp"%>

		</div>

	<!-- static includes the footer page  -->
	<%@ include file="/pages/common/footer.jsp"%>
	</body>
</html>