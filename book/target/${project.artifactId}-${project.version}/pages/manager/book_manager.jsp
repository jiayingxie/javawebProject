<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Books Management</title>
		<%-- static includes the base URL, CSS style, jQuery lib --%>
		<%@ include file="/pages/common/head.jsp"%>

		<script type="text/javascript">
			$(function (){
				// bond delete event for a.deleteClass, so people will not delete book by mistake
				$("a.deleteClass").click(function () {
					// this（a标签）的parent是<td>，<td>的parent是<tr>，<tr>的find("td:first")是<td>book.name</td>
					return confirm("Delete the book " + $(this).parent().parent().find("td:first").text() + "?");
				})
			});
		</script>
	</head>

	<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">Books Management</span>
			<!-- static include manager menu page -->
			<%@ include file="/pages/common/manager_menu.jsp"%>
		</div>

		<div id="main">
			<table>
				<tr>
					<td>Name</td>
					<td>Price</td>
					<td>Author</td>
					<td>Sales</td>
					<td>Stock</td>
					<td colspan="2">操作</td>
				</tr>

				<c:forEach items="${requestScope.page.items}" var="book">
					<tr>
						<td>${book.name}</td>
						<td>${book.price}</td>
						<td>${book.author}</td>
						<td>${book.sales}</td>
						<td>${book.stock}</td>
						<%-- a tag calls doGet method, so I have to override doGet() --%>
						<td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">edit</a></td>
						<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">delete</a></td>
					</tr>
				</c:forEach>


				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">Add new book</a></td>
				</tr>
			</table>

			<%--paging,分页--%>
			<%@ include file="/pages/common/page_nav.jsp"%>
		</div>

		<!-- static includes the footer page  -->
		<%@ include file="/pages/common/footer.jsp"%>
	</body>
</html>