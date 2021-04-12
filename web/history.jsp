<%-- 
    Document   : history
    Created on : Mar 5, 2021, 12:24:46 AM
    Author     : nds72
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>
    <script>
        $(document).ready(function () {
            $('#date').datepicker({
                dateFormat: 'yy-mm-dd'
            });
        });
    </script>
    <body>
        <c:if test="${sessionScope.LOGIN_USER!=null}">
            <c:if test="${sessionScope.LOGIN_USER.rolID eq 'US'}">
                <a href="MainController?action=Logout">Logout</a>
                Hi ${sessionScope.LOGIN_USER.fullName}
                  <a href="MainController?action=shopping">Shopping Page</a>    
            </c:if>
        </c:if>
                  <h3>History</h3>
        <form action="MainController" method="post">
            Name of Car: <input type="text" name="txtCarName" id="name"/>  
            Date: <input type="text" name="txtDate" id="date" />
            <input type="submit" name="action" value="Search Order" onclick="return checkSumit();"/>
        </form>

        <c:set value="${requestScope.LIST_ORDER}" var="order"/>
        <c:if test="${order!=null}">
            <c:if test="${not empty order}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Create Date</th>
                            <th>Total</th>
                            <th>Detail</th>
                            <th>Delete Order</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${requestScope.LIST_ORDER}" var="dto">
                            <tr>
                                <td>${dto.orderID}</td>
                                <td>${dto.creatDate}</td>
                                <td>${dto.total}</td>
                                ${dto.status}
                                <td><a href="MainController?action=Detail&orderID=${dto.orderID}">Detail</a></td>
                                <td> 
                                    <c:if test="${dto.status == 'active'}" var="check">
                                        <form action="MainController">
                                            <input type="hidden" name="txtCarName" value="${param.txtCarName}"/>
                                            <input type="hidden" name="txtDate" value="${param.txtDate}"/>
                                            <input type="hidden" name="orderID" value="${dto.orderID}"/>
                                            <input type="submit" name="action" value="Delete"/>
                                        </form>
                                    </c:if>
                                    <c:if test="${!check}">
                                        you already deleted this order
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </form>
                    </tbody>
                </table>
            </c:if>
        </c:if>
        <c:set value="${requestScope.NO_RECORD}" var="record"/>
        ${record}
        <c:if test="${not empty record}">
            <script>
                alert('${record}');
            </script>
        </c:if>
        <c:set value="${requestScope.NOTIFY}" var="notify" ></c:set>
        ${notify}
        <c:if test="${not empty notify}">
            <script>
                alert('${notify}');
            </script>
        </c:if>
        <script>
            function  checkSumit() {
                var name = document.getElementById('name').value;
                var date = document.getElementById('date').value;
                var check = true;
                if (name == '' && date == '') {
                    alert('Please input data to Search');
                    check = false;
                }
                return check;
            }
        </script>
    </body>
</html>
