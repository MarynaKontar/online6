<%--
  Created by IntelliJ IDEA.
  User: andreymi
  Date: 8/17/2017
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>User</title>
</head>
<body>
<p>This is search results for user</p>
<table>
    <tr>
        <td>Login</td>
        <td>Email</td>
        <td>Admin</td>
        <s:authorize access="hasRole('ADMIN')">
            <td>Password</td>
        </s:authorize>
    </tr>
    <c:if test="${not empty user}">
        <tr>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.administrator}</td>
            <s:authorize access="hasRole('ADMIN')">
                <td>${user.password}</td>
            </s:authorize>
        </tr>
    </c:if>
</table>
</body>
</html>
