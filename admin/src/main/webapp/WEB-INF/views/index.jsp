
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Admin Main</h1>
    <sec:authorize access="isAuthenticated()">
        <p>username: <sec:authentication property="principal.username"/></p>
        <sec:authentication property="principal" var="prc"/>
        <c:forEach items="${prc.authorities  }" var="auth">
            ${auth }
        </c:forEach>
    </sec:authorize>
    <br>
    <sec:authorize access="isAnonymous()"><a href="/auth/login">로그인</a></sec:authorize>
    <sec:authorize access="isAnonymous()"><a href="/auth/signup">회원가입</a></sec:authorize>
    <sec:authorize access="isAuthenticated()"><a href="/auth/logout">로그아웃</a></sec:authorize>


    <sec:authorize access="hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')"><a href="/info">내정보</a></sec:authorize><br>
    <sec:authorize access="hasAuthority('ROLE_ADMIN')"><a href="/order">GET Order & Product</a></sec:authorize><br>
    <sec:authorize access="hasAuthority('ROLE_ADMIN')"><a href="/orderall">GET Order All</a></sec:authorize><br>
</body>
</html>
