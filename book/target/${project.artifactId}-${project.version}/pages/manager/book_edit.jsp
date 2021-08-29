<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edit Book</title>
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

			input {
				text-align: center;
			}
		</style>
	</head>

	<body>
			<div id="header">
				<img class="logo_img" alt="" src="../../static/img/logo.gif" >
				<span class="wel_word">Edit Book</span>
				<!-- static include manager menu page -->
				<%@ include file="/pages/common/manager_menu.jsp"%>
			</div>

			<div id="main">
				<form action="manager/bookServlet" method="get">
					<%-- to determine which method(method has the same name with the action's value) to use in Servlet --%>
					<%-- 方便调用Servlet中对应的方法，方法名要和action的value的值一致 --%>
					<input type="hidden" name="action" value="${param.method}"/>
					<input type="hidden" name="id" value="${requestScope.book.id}"/>
					<input type="hidden" name="pageNo" value="${param.pageNo}">
					<table>
						<tr>
							<td>Name</td>
							<td>Price</td>
							<td>Author</td>
							<td>Sales</td>
							<td>Stock</td>
							<td colspan="2">操作</td>
						</tr>
						<tr>
							<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
							<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
							<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
							<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
							<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
							<td><input type="submit" value="Submit"/></td>
						</tr>
					</table>
				</form>


			</div>

			<!-- static includes the footer page  -->
			<%@ include file="/pages/common/footer.jsp"%>
	</body>
</html>