<%--
  Created by IntelliJ IDEA.
  User: JiayingXie
  Description: static include the menu if user logs in successfully
                静态包含登录成功后显示的菜单
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span><span class="um_span">${sessionScope.user.username}</span> welcome to bookstore!</span>
    <a href="orderServlet?action=showMyOrdersPage">My Orders</a>&nbsp;&nbsp;&nbsp;
    <a href="userServlet?action=logout">Logout</a>&nbsp;&nbsp;&nbsp;
    <a href="index.jsp">Back</a>
</div>
