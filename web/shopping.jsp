<%-- 
    Document   : shopping
    Created on : Feb 24, 2021, 9:36:12 PM
    Author     : nds72
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
        <script src="http://code.jquery.com/jquery-2.1.3.js"></script>
        <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script> <%--http://www.tutorialspark.com/jqueryUI/jQuery_UI_DatePicker_Setting_Animation.php --%>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"><%--https://jqueryui.com/datepicker/--%>
    </head>
    <script>
        $(document).ready(function () {
            $('#from').datepicker({
                duration: "fast",
                showAnim: "drop",
                dateFormat: 'dd/mm/yy',
            });
            $('#to').datepicker({
                duration: "fast",
                showAnim: "drop",
                dateFormat: 'dd/mm/yy',
            });
        });
    </script> 
    <body>



        <c:set value="${requestScope.ERROR_SEARCH}" var="errSearch"/>
        <c:set value="${requestScope.ERROR_DATE}" var="errDate"/>
        <c:set value="${requestScope.ERROR}" var="err"/>
        <form action="MainController" method="post" >
            Name: <input type="text" name="txtName" value="${param.txtName}"> 
            Category: <select name="txtCategory">
                <option value="-1">CATEGORY</option>
                <c:forEach items="${sessionScope.CATEGORY}" var="cate">
                    <option value="${cate.cateID}">${cate.cateName}</option>
                </c:forEach>
            </select>
            From: <input type="text" name="from" id="from" value="${param.from}" />${err.checkIn}
            To: <input type="text" name="to" id="to"  value="${param.to}" /> ${err.checkOut}
            Amout: <input type="number" name="amount" required="true" min="1" <%--value="${param.amount}"--%> />
            <input type="submit" name="action" value="Search"/>  ${errSearch} ${errDate} 

            <c:if test="${sessionScope.LOGIN_USER!=null}">
                <c:if test="${sessionScope.LOGIN_USER.rolID eq 'US'}">
                    <a href="MainController?action=view">CART</a>(${sessionScope.shoppingCart.shoppingCart.size()})
                    <%--  <a href="MainController?action=History&userID=${sessionScope.LOGIN_USER.userID}">Hi ${sessionScope.LOGIN_USER.fullName}</a>--%>
                    <a href="MainController?action=history">Hi ${sessionScope.LOGIN_USER.fullName}</a>
                </c:if>
                <c:if test="${sessionScope.LOGIN_USER.rolID eq 'AD'}">
                    Hi ${sessionScope.LOGIN_USER.fullName}
                </c:if>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER!=null}">
                <c:if test="${sessionScope.LOGIN_USER.rolID eq 'US' or sessionScope.LOGIN_USER.rolID eq 'AD'}">
                    <a href="MainController?action=Logout">Logout</a>
                </c:if>
            </c:if>
        </form>

        <c:set var="listCar" value="${requestScope.CAR}"/>
        <c:if test="${listCar!=null}">
            <c:if test="${not empty listCar}" var="check">
                <table border="1">
                    <thead>
                        <tr>
                            <th>CAR ID</th>
                            <th>CAR NAME</th>
                            <th>COLOR</th>
                            <th>YEAR</th>
                            <th>PRICE</th>
                            <th>IMAGE</th>
                            <th>QUANTITY</th>
                            <th>CATE ID</th>
                            <th>ADD CART</th>
                            <th>Avg FeedBack</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${listCar}">
                            <tr>
                                <td>${dto.carID}</td>
                                <td>${dto.carName}</td>
                                <td>${dto.color}</td>
                                <td>${dto.year}</td>
                                <td>${dto.price}</td>
                                <td>
                                    <img  src="${dto.image}" alt="rend Car" width="100" height="50"> 
                                </td>
                                <td>
                                    ${dto.quantity}
                                </td>
                                <td>${dto.cateID}</td>
                                <td><a href="MainController?action=Add&txtCarID=${dto.carID}&quantity=${dto.quantity}">AddToCart</a></td> 
                                <td>
                                    <c:if test="${dto.point>0}" var="check">${dto.point}/10</c:if>
                                    <c:if test="${!check}">Cars have not been evaluated</c:if>
                                    </td>
                                </tr>
                        </c:forEach>

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


        <c:set var="numPage" value="${requestScope.endPage}"/>

        <c:forEach begin="1" end="${endPage}" var="endPage">
            <c:if test="${numPage==1}" var="check">
            </c:if>
            <c:if test="${!check}">
                <a href="SearchController?index=${endPage}">${endPage}</a>
            </c:if>
        </c:forEach>
        <c:set value="${requestScope.PAYMENT_SUCCESS}" var="payment"/>
        ${payment}
        <c:if test="${not empty payment}">
            <script>
                alert('${payment}');
            </script>
        </c:if>
    </body>

</html>
