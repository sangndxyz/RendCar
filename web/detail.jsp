<%-- 
    Document   : detail
    Created on : Mar 5, 2021, 8:22:53 AM
    Author     : nds72
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER!=null}">
            <c:if test="${sessionScope.LOGIN_USER.rolID eq 'US' or sessionScope.LOGIN_USER.rolID eq 'AD'}">
                <a href="MainController?action=Logout">Logout</a>
                Hi ${sessionScope.LOGIN_USER.fullName}
                <a href="MainController?action=shopping">Shopping Page</a>
            </c:if>
        </c:if>
        <a></a>
        <h4>Deatail Order</h4>
        <form action="" method="post">
            <table border="1">
                <thead>
                    <tr>
                        <th>OrderDetail ID</th>
                        <th>CarName</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>CheckIn</th>
                        <th>CheckOut</th>
                        <th>FeedBack</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.HISTORY_DETAIL}" var="dto"> 
                        <tr>
                            <td>${dto.orderDetailID}</td>
                            <td>${dto.carName}</td>
                            <td>${dto.price}</td>
                            <td>${dto.quantity}</td>
                            <td>
                                <fmt:formatDate pattern = "dd-MM-yyyy"  value ="${dto.checkIn}" var="checkIn"/>
                                ${checkIn}
                            </td>
                            <td>
                                <fmt:formatDate pattern = "dd-MM-yyyy"  value ="${dto.checkOut}"/>
                            </td>    
                            <td>
                                <input type="hidden" name="carID" value="${dto.carID}"/>
                                <c:set var = "now" value = "<%=new java.util.Date()%>" />
                                <fmt:formatDate pattern = "dd-MM-yyyy"  value ="<%=new java.util.Date()%>" var = "parseNow" />
                                <c:if test="${checkIn<=parseNow}" var="check">
                                    <a href="MainController?action=feedback&carID=${dto.carID}&orderID=${param.orderID}">Feedback</a>
                                </c:if>
                                <c:if test="${!check}">
                                    You not yet get car
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
        <c:set value="${requestScope.FEEDBACK_NOTIFY}" var="notify"/>
        ${notify}
        <c:if test="${not empty notify}">
            <script>
                alert('${notify}');
            </script>
        </c:if>

    </body>
</html>
