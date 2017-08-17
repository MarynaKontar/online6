<%--
  Created by IntelliJ IDEA.
  User: andreymi
  Date: 8/15/2017
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Users list</title>
</head>
<body>
<table>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <a href="/user/find/${user}">${user}</a>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="/user/logout" method="post">
    <input type="submit" value="logout">
</form>

</body>
</html>
