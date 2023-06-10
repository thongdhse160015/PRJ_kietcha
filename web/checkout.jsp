<%-- 
    Document   : checkout
    Created on : Mar 17, 2023, 12:55:25 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <h1>CheckOut Page</h1>
        <c:set var="orderlist" value="${sessionScope.orderlist}"/>
        <c:set var="totalOrderlist" value="${sessionScope.sumOrderlist}"/>
        <c:if test="${empty orderlist || empty totalOrderlist}">
            <font color="red" style="font-weight:bold; font-size: 20px"> You don't have anything to checkout!</font>
        </c:if>
        <c:if test="${not empty orderlist && not empty totalOrderlist}">

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Price (per 1)</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orderlist}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${order.title}</td>
                            <td>${order.quantity}</td>
                            <td>${order.price}</td>
                            <td>${order.total}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5" style="font-weight: bold; color:red">You need to pay for all: ${totalOrderlist}</td>
                    </tr> 
                </tbody>

            </table>
        </c:if>
        <br>
        <a href="shoppingController"><input type="button" value="Back to Shopping" /> </a>
    </body>
</html>
