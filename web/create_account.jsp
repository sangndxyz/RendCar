<%-- 
    Document   : create_account
    Created on : Feb 26, 2021, 11:09:27 AM
    Author     : nds72
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <a href="MainController?action=loginAccount">Login</a>
        <h5>REGISTER ACCOUNT</h5>
        <c:set var="account"  value="${requestScope.ACCOUNT}"/>
        <c:set var="error" value="${requestScope.ERROR_CREATE}"/>
        <form action="MainController" method="POST">
            User ID: <input type="text" name="txtUserId" value="${account.userID}"/> ${error.userIDError} </br>
            Full Name: <input type="text" name="txtFullName" value="${account.fullName}"/>${error.fullNameError}</br>
            Password: <input type="password" name="txtPassword" value="${account.password}"/>${error.passwordError}</br>
            Confirm: <input type="password" name="txtConfirm" value="${requestScope.confirm}"/>${error.confirmError}</br>
            Phone: <input type="text" name="txtPhone" value="${account.phone}"/>${error.phoneError}</br>
            Addrress: <input type="text" name="txtAddress" value="${account.address}"/>${error.addressError}</br>
            <input type="submit" name="action" value="Register"/>
        </form>
    </body>
</html>
