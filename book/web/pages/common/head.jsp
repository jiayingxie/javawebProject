<%--
  Created by IntelliJ IDEA.
  User: JiayingXie
  Description: static includes the base URL, CSS style, jQuery lib
                静态包含base标签、css样式、jQuery样式
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- to get the dynamic base URL --%>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";

    pageContext.setAttribute("basePath",basePath);
%>


<!-- base is used to set the relative path of page -->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

