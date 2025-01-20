
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Admin Main</h1>
    <c:choose>
        <c:when test="${sessionScope.loginid == null}">
            <h3><a href="/login">LOGIN</a></h3>
        </c:when>
        <c:otherwise>
            <h3>${sessionScope.loginid}</h3>
            <h3><a href="/logout">LOGOUT</a></h3>
        </c:otherwise>
    </c:choose>

    <h2><a href="/order">Order & Product</a></h2>
    <c:if test="${sessionScope.loginid != null}">
        <h2><a href="/orderall">Orders</a></h2>
    </c:if>

</body>
</html>
