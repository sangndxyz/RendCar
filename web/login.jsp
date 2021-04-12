<%-- 
    Document   : login
    Created on : Feb 20, 2021, 8:36:56 PM
    Author     : nds72
--%>
<!--xe ngay thue ngay tra-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
    </head>
    <body>
        <a href="GetCategoryController">Shopping</a>
        <h4>LOGIN PAGE</h4>
        <form action="MainController" method="POST">
            Email<input type="text" name="txtEmail" value="${requestScope.EMAIL}"/>${requestScope.ERROR}</br>
            Password: <input type="password" name="txtPassword"/>
            <div class="g-recaptcha" data-sitekey="6LeT-GgaAAAAAACyECOSmY9G9Oy9YLHFYy8Y1gRG"></div> 
            <!--<div class="g-recaptcha" data-sitekey="6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI"></div>-->
            <input type="submit" name="action" value="Login"/>
            <a href="MainController?action=registerAccount">Register</a>
        </form>

            

        <%--     <c:set value="${requestScope.ERROR}" var="Err"></c:set>
             <c:if test="${not empty Err}" >
                 <script>
                     alert('${Err}');
                 </script>
             </c:if>   --%>


        <c:set value="${requestScope.NOT_LOGIN}"  var="NotLogin"></c:set>

        <c:if test="${NotLogin}" >
            <script>
                alert('${NotLogin}');
            </script>
        </c:if>
        <c:set value="${requestScope.SUCCESS}" var="success"/>
        <c:if test="${not empty success}">
            <script>
                alert('${success}');
            </script>
        </c:if>
        <c:set value="${requestScope.ADMIN_LOGIN}" var="admin"></c:set>
        <c:if test="${not empty admin}">
            <script>
                alert('${admin}');
            </script>
        </c:if>

        <c:set value="${requestScope.NOTIFICATION}" var="notification"></c:set>
        <c:if test="${not empty notification}">
            <script>
                alert('${notification}');
            </script>
        </c:if>
    </body>
</html>
