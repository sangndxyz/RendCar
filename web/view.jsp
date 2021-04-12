<%-- 
    Document   : view
    Created on : Mar 1, 2021, 10:48:50 PM
    Author     : nds72
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER!=null}">
            <c:if test="${sessionScope.LOGIN_USER.rolID eq 'US'}">
                Hi ${sessionScope.LOGIN_USER.fullName}
                <a href="MainController?action=shopping">Shopping Page</a>    
            </c:if>
        </c:if>
        <c:set var="shoppingCart" value="${sessionScope.shoppingCart}"></c:set>
        <c:if test="${shoppingCart.shoppingCart.size()>0}" var="check">
            <h4>Shopping Cart</h4>
            <table border="1">
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>NAME</th>
                        <th>PRICE</th>
                        <th>QUANTITY</th>
                        <th>CHECK IN</th>
                        <th>CHECK OUT</th>
                        <th>DELETE</th>
                    </tr>
                </thead>
                <tbody>
                <form action="MainController" method="post">
                    <%--    <c:forEach items="${sessionScope.shoppingCart.shoppingCart.values()}" var="dto" varStatus="counter">--%>
                    <c:forEach items="${sessionScope.shoppingCart.shoppingCart}" var="dto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.carName}</td>
                            <td>${dto.price}</td>
                            <td>
                                <input type="number" name="txtQuantity" min="1" value="${dto.quantity}"/> 
                            </td>
                     
                        <td><fmt:formatDate pattern = "dd/MM/yyyy"  value="${dto.checkIn}"/></td>

                       
                        <td><fmt:formatDate pattern =  "dd/MM/yyyy" value="${dto.checkOut}" /></td>

                        <td>
                            <a href="MainController?action=Remove&txtCarID=${dto.carID}&index=${counter.count-1}"
                               onclick="return confirm('Are you want to delete this item');"
                               >Delete</a>
                        </td>
                        </tr>
                        <input type="hidden" name="txtcarID" value="${dto.carID}"/>
                         <input type="hidden" name="index" value="${counter.count-1}"/> 
                    </c:forEach>
                    <tr>
                        <td> <input type="submit" name="action" value="Update"/></td>
                        <td><input type="submit" name="action" value="Order"/></td>
                        <td>Total: ${sessionScope.shoppingCart.getTotal(sessionScope.discountPercent)}</td>
                        <td><a href="MainController?action=shopping">Continue Rend Car</a>    </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
    <br/>
    <c:if test="${!check}">
        CART is null
    </c:if>

    <c:set value="${requestScope.OUT_OF_STOCK}" var="out_of_stock"></c:set>
    ${out_of_stock}
    <c:if test="${out_of_stock}">
        <script>
            alert('${out_of_stock}');
        </script>
    </c:if>
</body>
</html>
