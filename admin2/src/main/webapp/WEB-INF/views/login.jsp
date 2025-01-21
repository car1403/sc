
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Admin Login</h1>
    <form action="/auth/login-process" method="post">
        ID<input type="text" name="userid"><br>
        PWD<input type="password" name="pwd"><br>
        <input type="submit" value="LOGIN"><br>
    </form>
    <c:if test="${proc == '2'}">
        <h4>LOGIN FAIL Someone is logged in.</h4>
    </c:if>
    <c:if test="${proc == '1'}">
        <h4>LOGIN FAIL Try Again.</h4>
    </c:if>
</body>
</html>
