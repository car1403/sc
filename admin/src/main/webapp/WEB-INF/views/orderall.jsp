
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Order</h1>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${orders}">
            <tr>
                <td><a href="/cust/detail?id=${c.id}">${c.id}</a></td>
                <td>${c.name}</td>
                <td>${c.price}</td>

                <td><fmt:parseDate value="${ c.createdAt }"
                                   pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="yyyy년MM월dd일 HH시mm분" value="${ parsedDateTime }" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
