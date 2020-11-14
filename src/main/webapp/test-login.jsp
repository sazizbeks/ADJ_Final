<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: Az1zbek
  Date: 14.11.2020
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Test</title>
</head>
<body>
<c:if test="${cookie.get('error')!=null}">
    <p style="color: red">
        ${cookie.get('error').value}
    </p>
</c:if>
<form action="<c:url value="/login"/>" method="post">
    <input type="text" placeholder="Username" name="username" required="required"><br>
    <input type="password" placeholder="Password" name="password" required="required"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
