<%-- 
    Document   : welcomeHeader
    Created on : Mar 18, 2023, 12:45:06 AM
    Author     : ADMIN
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
    </head>
    <body>
    <c:if test="${not empty sessionScope.ACCOUNT.fullName}">
        <font color="red">
        Welcome, ${sessionScope.ACCOUNT.fullName}
        </font>
        <a href="logoutController"> <input type="button" value="Logout" /> </a>
    </c:if>
    </body>
</html>