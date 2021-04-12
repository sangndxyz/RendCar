<%-- 
    Document   : feedback
    Created on : Mar 7, 2021, 1:02:30 AM
    Author     : nds72
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FeedBack Page</title>
    </head>
    <script>
        function updateTextInput(val) {
            document.getElementById('textInput').value = val;
        }
        function updateRangInput(val) {
            document.getElementById('textRange').value = val;
        }
    </script>
    <body>
        <c:if test="${sessionScope.LOGIN_USER!=null}">
            <c:if test="${sessionScope.LOGIN_USER.rolID eq 'US' or sessionScope.LOGIN_USER.rolID eq 'AD'}">
                <a href="MainController?action=Logout">Logout</a>
            </c:if>
        </c:if>
        <h4>FeedBack</h4>
        <form action="MainController" method="post">
            <h4>FeedBack Car</h4>
            <input type="range" id="textRange" name="rangeInput" min="0" max="10" onchange="updateTextInput(this.value);"><!--https://stackoverflow.com/questions/10004723/html5-input-type-range-show-range-value-->
            <input type="number" id="textInput" name="txtPoint" oninput ="updateRangInput(this.value)"  size="5" min="1" max="10" ></br>
            <!--<input type="text" name="txtComment" size="32" rows = "5" cols = "60"/>--> </br>
            <textarea rows = "5" cols = "32" name = "comment"></textarea><br>
            <input type="text" name="carID" value="${param.carID}"/>
            <input type="text" name="orderID" value="${param.orderID}"/>
            <input type="submit" name="action" value="FeedBack"/>
        </form>
        <c:set value="${requestScope.ERROR_POINT}" var="errPoint"/>
        ${errPoint}
        <c:if test="${not empty errPoint}">
            <script>
                alert('${errPoint}');
            </script>
        </c:if>
    </body>
</html>
