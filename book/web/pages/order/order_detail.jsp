<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Details</title>
        <%-- static includes the base URL, CSS style, jQuery lib --%>
        <%@ include file="/pages/common/head.jsp"%>
        <style type="text/css">
            h1 {
                text-align: center;
                margin-top: 200px;
            }
            #backToOrders {
                text-align: center;
                margin-top: 50px;
            }
            #backToOrders a {
                color: blue;
                font-weight: bolder;
            }
            #main table {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>

    <div id="header">
        <img class="logo_img" alt="" src="static/img/logo.gif" >
        <span class="wel_word">Order Details</span>

        <%-- static include the menu if user logs in successfully --%>
        <%@ include file="/pages/common/login_success_menu.jsp"%>
    </div>

    <div id="main">
        <div id="backToOrders">
            <a href="orderServlet?action=${param.isAdmin ? "showAllOrdersPage" : "showMyOrdersPage"}&pageNo=${param.pageNo}">Back to order page</a>
        </div>

        <table>
            <tr>
                <td>Book Name</td>
                <td>Price</td>
                <td>Count</td>
                <td>Total Price</td>
            </tr>

            <c:forEach items="${requestScope.orderDetails}" var="orderItem">
                <tr>
                    <td>${orderItem.name}</td>
                    <td>${orderItem.price}</td>
                    <td>${orderItem.count}</td>
                    <td>${orderItem.totalPrice}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <!-- static includes the footer page  -->
    <%@ include file="/pages/common/footer.jsp"%>
    </body>
</html>