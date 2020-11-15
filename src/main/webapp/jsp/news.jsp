<%@include file="/jsp/index.components/header.jsp" %>
<s:query dataSource="${snapshot}" var="news">
    SELECT * FROM news ORDER BY 1;
</s:query>
<main class="main">

    <div class="grid">

        <h1>News</h1>
        <form action="<c:url value="/news"/>" method="get">
            <button type="submit" name="btnVal" value="myNews">
                My news
            </button>
        </form>
        <c:forEach var="row" items="${news.rows}">
            <div class="text-center">
                <ul class="list-group">
                    <li class="list-group-item">${row.news_id}</li>
                    <li class="list-group-item">${row.news_title}</li>
                    <li class="list-group-item">${row.news_description}</li>
                    <li class="list-group-item">${row.moderator_id}</li>
                </ul>
                <br>
            </div>
        </c:forEach>

    </div>

</main>

<%@include file="/jsp/index.components/footer.jsp" %>