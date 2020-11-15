<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<s:setDataSource var="snapshot" driver="org.postgresql.Driver"
                 url="jdbc:postgresql://localhost:5432/ADJ_Final"
                 user="postgres" password="87779548066"/>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

    <link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <style>
        <%@include file="../css/index.css"%>
    </style>
</head>
<body>
<s:query dataSource="${snapshot}" var="clubs">
    SELECT * FROM clubs WHERE club_id = ${cookie.get('club_id').value};
</s:query>
<!-- main -->
<div class="main-w3layouts wrapper">
    <h1>Edit club</h1>
    <div class="main-agileinfo">
        <div class="agileits-top">
            <%-->
           Sign in form
            <--%>
            <form action="<c:url value="/edit/club"/>" method="post">
                    <input type="hidden" name="club_Id" value="${cookie.get('club_id').value}"><br>
                    <p style="color: white;">New club name:</p>
                    <input class="text" type="text" name="club_name">
                    <br>
                    <input type="submit" name="btnVal" value="EDIT">
            </form>
        </div>
    </div>
</div>
<%@include file="../js/bootstrap.js"%>
</body>
</html>