
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Order</h1>
    <h2>${order.id}</h2>
    <h2>${order.name}</h2>
    <h2>${order.price}</h2>
    <h2><fmt:parseDate value="${ order.createdAt }"
                       pattern="yyyy-MM-dd'T'HH:mm" var="createdDateTime" type="both" />
        <fmt:formatDate pattern="yyyy년MM월dd일 HH시mm분" value="${ createdDateTime }" /></h2>
    <h2><fmt:parseDate value="${ order.updatedAt }"
                       pattern="yyyy-MM-dd'T'HH:mm" var="updatedDateTime" type="both" />
        <fmt:formatDate pattern="yyyy년MM월dd일 HH시mm분" value="${ updatedDateTime }" /></h2>
    <hr>
    <h2>${product}</h2>
</body>
</html>
