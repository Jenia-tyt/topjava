<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<table border="1" cellpadding="10" cellspacing="0">
    <thead>
        <tr>
            <th>№</th>
            <th>Name User</th>
            <th>Passowrd</th>
            <th>Email</th>
            <th>Enable</th>
            <th>roles</th>
        </tr>
    </thead
    <jsp:useBean id="user" scope="request" type="java.util.List<ru.javawebinar.topjava.model.User>"/>
    <c:forEach items="${user}" var="user">
        <tr>
            <th>${user.id}</th>
            <th><a href="meals?action=toUser&userName=${user.name}">${user.name}</a></th>
            <th>${user.password}</th>
            <th>${user.email}</th>
            <th>${user.enabled}</th>
            <th>${user.roles}</th>
        </tr>
    </c:forEach>
</table>
</body>
</html>