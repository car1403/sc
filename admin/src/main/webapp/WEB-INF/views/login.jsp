
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Admin Login</h1>
    <form action="/loginimpl" method="post">
        ID<input type="text" name="id"><br>
        PWD<input type="password" name="pwd"><br>
        <input type="submit" value="LOGIN"><br>
    </form>
</body>
</html>
