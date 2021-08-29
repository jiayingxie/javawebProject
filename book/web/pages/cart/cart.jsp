<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Shopping Cart</title>
		<%-- static includes the base URL, CSS style, jQuery lib --%>
		<%@ include file="/pages/common/head.jsp"%>

		<script type="text/javascript">
			$(function () {
				// bind action event on deleting cart's item
				$("a.deleteItem").click(function () {
					return confirm("Are you sure to delete the item " + $(this).parent().parent().find("td:first").text() + "?" );
				});

				// bind action event on clearing cart
				$("#clearCart").click(function () {
					return confirm("Are you sure to clear the cart?");
				});

				// bind action event on updating cartitem's count
				$("input.updateCount").change(function () {
					let name = $(this).parent().parent().find("td:first").text();
					let count = this.value;
					let id = $(this).attr("bookId");
					if (confirm("Are you sure to change the count of book " + name + " as " + count + "?")) {
						// 发起请求给服务器保存修改
						location.href = "http://localhost:8080/book/cartServlet?action=updateCount&id=" + id + "&count=" + count;
					} else {
						// defaultValue 属性是表单项Dom 对象的属性。它表示默认的value 属性值。
						this.value = this.defaultValue;
					}
				});

				// bind action event on checking out
				$("#checkout").click(function () {
					return confirm("Go to checkout.");
				});
			});

		</script>
	</head>

	<body>

		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">Shopping Cart</span>

			<%-- static include the menu if user logs in successfully --%>
			<%@ include file="/pages/common/login_success_menu.jsp"%>

		</div>

		<div id="main">

			<table>
				<tr>
					<td>Product Name</td>
					<td>Count</td>
					<td>Price</td>
					<td>Total Price</td>
					<td>操作</td>
				</tr>

				<%-- if cart is empty --%>
				<c:if test="${empty sessionScope.cart.items}">
					<tr>
						<td colspan="5"><a href="index.jsp">Your cart is empty. Go to the home page and browse books.</a></td>
					</tr>
				</c:if>
				<%-- if cart is not empty --%>
				<c:if test="${not empty sessionScope.cart.items}">
					<c:forEach items="${sessionScope.cart.items}" var="entry">
						<tr>
							<td>${entry.value.name}</td>
							<td>
								<input class="updateCount" type="number" value="${entry.value.count}" bookId="${entry.value.id}" style="width: 60px">
							</td>
							<td>${entry.value.price}</td>
							<td>${entry.value.totalPrice}</td>
							<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">delete</a></td>
						</tr>
					</c:forEach>
				</c:if>


			</table>

			<c:if test="${not empty sessionScope.cart.items}">
				<div class="cart_info">
					<span class="cart_span">Your shopping cart has<span class="b_count">${sessionScope.cart.totalCount}</span>${sessionScope.cart.totalCount==1 ? "item" : "items"}.</span>
					<span class="cart_span">Total Price ￥<span class="b_price">${sessionScope.cart.totalPrice}</span></span>
					<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">Clear Cart</a></span>
					<span class="cart_span"><a id="checkout" href="orderServlet?action=createOrder">Checkout</a></span>
				</div>
			</c:if>

		</div>

		<!-- static includes the footer page  -->
		<%@ include file="/pages/common/footer.jsp"%>
	</body>
</html>