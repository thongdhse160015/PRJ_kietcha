<%-- 
    Document   : CreatenewAccount
    Created on : Mar 17, 2023, 2:23:16 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="createNewAccountController" method="POST">
            <c:set var="errors" value ="${requestScope.CREATE_ERR}"/>

            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /> (e.g 6-30 characters)<br>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}<br>
                </font>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}<br>
                </font>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (e.g 6-30 characters)<br>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}<br>
                </font>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                ${errors.confirmNotMatched}<br>
                </font>
            </c:if>
            Full Name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(e.g 2-50 characters) <br>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                ${errors.fullnameLengthError}<br>
                </font>
            </c:if>
            <input type="submit" value="Create new account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
