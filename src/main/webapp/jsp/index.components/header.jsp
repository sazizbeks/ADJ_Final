<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>

<%--<s:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"--%>
<%--                 url = "jdbc:mysql://localhost:3306/project_sdp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"--%>
<%--                 user = "root"  password = ""/>--%>

<html>
<head>
    <title>Astana IT University Bank</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <style>
        <%@include file="../../css/header.css"%>
        <%@include file="../../css/footer.css"%>
    </style>
    <%@include file="../../js/bootstrap.js" %>
</head>
<body class="d-flex flex-column h-100">
<header class="header sticky sticky--top js-header">
    <div class="grid">
        <nav class="navigation">
            <ul class="navigation__list navigation__list--inline">
                <li class="navigation__item"><a href="<c:url value="/index.jsp"/>" class="is-active">Home</a></li>
                <c:if test="${empty sessionScope.user}">
                    <li class="navigation__item"><a class="is-active" href="<%=application.getContextPath()%>/jsp/login.jsp">Login</a>
                    </li>
                    <li class="navigation__item"><a class="is-active"
                                                    href="<%=application.getContextPath()%>/jsp/register.jsp">Register</a></li>
                </c:if>
                <c:if test="${!empty sessionScope.user}">
                    <li class="navigation__item is-active"><c:out value="${sessionScope.user.fname}"/></li>
                    <li class="navigation__item is-active"><a href="<c:url value="/jsp/club.jsp"/>">Club</a></li>
                    <li class="navigation__item is-active"><a href="<c:url value="/jsp/event.jsp"/>">Event</a></li>
                    <li class="navigation__item is-active"><a href="<c:url value="/jsp/news.jsp"/>">News</a></li>
                    <li class="navigation__item is-active"><a href="<c:url value="/jsp/students.jsp"/>">Students</a></li>
                    <li class="navigation__item is-active"><a href="<c:url value="/logout"/>" class="is-active">Logout</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
</header>
