<%-- 
Document   : payment
Created on : Mar 5, 2021, 10:20:01 AM
Author     : nds72
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER!=null}">
            <c:if test="${sessionScope.LOGIN_USER.rolID eq 'US' or sessionScope.LOGIN_USER.rolID eq 'AD'}">
                <a href="MainController?action=Logout">Logout</a>
            </c:if>
        </c:if>
        <form action="MainController" method="post">
            UserId: <input type="text" name="txtUserID" value="${sessionScope.LOGIN_USER.userID}" readonly="true"/></br>
            FullName: <input type="text" name="txtFullName" value="${sessionScope.LOGIN_USER.fullName}" readonly="true"/></br>
            Phone: <input type="text" name="txtPhone" required="true" value="${sessionScope.LOGIN_USER.phone}"/></br>
            Address: <input type="text" name="txtAddress" value="${sessionScope.LOGIN_USER.address}"  required="true"/></br>


            Total: ${sessionScope.shoppingCart.getTotal(sessionScope.discountPercent)}
            <%--<input type="hidden" name="" value=""/>--%>
            </br><input type="submit" name="action" value="Payment"/></br>
        </form>
        <form action="MainController" method="post">
            Discount: <input type="text" name="txtDiscount"/></br>
            <input type="submit" name="action" value="Use Discount"/>
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>NAME</th>
                    <th>QUANTITY</th>
                    <th>PRICE</th>
                </tr>
            </thead>
            <tbody>
             <%--   <c:forEach items="${sessionScope.shoppingCart.shoppingCart.values()}" var="dto" varStatus="counter">--%>
                    <c:forEach items="${sessionScope.shoppingCart.shoppingCart}" var="dto" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${dto.carName}</td>
                        <td>${dto.quantity}</td>
                        <td>${dto.price}</td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
        <h3 style="color: red;">${requestScope.NOTIFY_EXPIRY}</h3>
        <h3  style="color: red;">${requestScope.NOTIFY_DISCOUNT_ERROR}</h3>





    </body>
</html>
