<%--
  Created by IntelliJ IDEA.
  User: JiayingXie
  Description: static include manager menu page
                静态包含manager管理模块的菜单
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <%--  action=list means it will call list method in BookServlet  --%>
    <a href="manager/bookServlet?action=page">Books Management</a>&nbsp;&nbsp;&nbsp;
    <a href="orderServlet?action=showAllOrdersPage">Orders Management</a>&nbsp;&nbsp;&nbsp;
    <a href="index.jsp">Home Page</a>
</div>
