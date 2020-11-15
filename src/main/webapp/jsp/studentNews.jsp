<%@include file="/jsp/index.components/header.jsp" %>

<main class="main">

    <div class="grid">

        <h1>My news</h1>
        <c:forEach var="news" items="${myNews}">
            <div class="text-center">
                <ul class="list-group">
                    <li class="list-group-item">${news.news_id}</li>
                    <li class="list-group-item">${news.news_title}</li>
                    <li class="list-group-item">${news.news_description}</li>
                    <li class="list-group-item">${news.moderator_id}</li>

                    <input type="submit" class="bg-light text-primary border rounded border-primary" value="edit">
                    <input type="submit" class="bg-light text-danger border rounded border-danger mt-1"
                           value="delete">

                </ul>
                <br>
            </div>
        </c:forEach>
    </div>
</main>

<%@include file="/jsp/index.components/footer.jsp" %>