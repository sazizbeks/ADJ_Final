<%@include file="/jsp/index.components/header.jsp" %>
<s:query dataSource="${snapshot}" var="news">
    SELECT * FROM news WHERE moderator_id=${sessionScope.user.id};
</s:query>
<main class="main">

    <div class="grid">

        <h1>My news</h1>
        <c:forEach var="news" items="${news.rows}">
            <div class="text-center">
                <ul class="list-group">
                    <li class="list-group-item">${news.news_id}</li>
                    <li class="list-group-item">${news.news_title}</li>
                    <li class="list-group-item">${news.news_description}</li>
                    <li class="list-group-item">${news.moderator_id}</li>

                    <form action="<c:url value="/news"/>">
                        <input type="hidden" name="newsId" value="${news.news_id}">
                        <input type="submit" class="bg-light text-primary border rounded border-primary" name="btnVal"
                               value="edit">
                    </form>

                    <form action="<c:url value="/news"/>" method="post">
                        <input type="hidden" name="id" value="${news.news_id}">
                        <input type="submit" class="bg-light text-danger border rounded border-danger mt-1" name="btnVal"
                               value="delete">
                    </form>

                </ul>
                <br>
            </div>
        </c:forEach>
    </div>
</main>

<%@include file="/jsp/index.components/footer.jsp" %>