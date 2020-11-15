<%@include file="/jsp/index.components/header.jsp" %>
<s:query dataSource="${snapshot}" var="news">
    SELECT * FROM news WHERE moderator_id=${sessionScope.user.id};
</s:query>
<main class="main">

    <div class="grid">

        <h1>My news</h1>

        <a href="<c:url value="/jsp/addNews.jsp"/>">
            <button class="btn btn-primary">
                Add news
            </button>
        </a>

        <c:choose>
            <c:when test="${news.rows[0]==null}">
                <div class="text-center">
                    <ul class="list-group">
                        <li class="list-group-item">You have no news</li>
                    </ul>
                    <br>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="news" items="${news.rows}">
                    <div class="text-center">
                        <ul class="list-group">
                            <li class="list-group-item">${news.news_id}</li>
                            <li class="list-group-item">${news.news_title}</li>
                            <li class="list-group-item">${news.news_description}</li>
                            <li class="list-group-item">${news.moderator_id}</li>

                            <form action="<c:url value="/news"/>">
                                <input type="hidden" name="newsId" value="${news.news_id}">
                                <input type="submit" class="bg-light text-primary border rounded border-primary"
                                       name="btnVal"
                                       value="edit">
                            </form>

                            <form action="<c:url value="/news"/>" method="post">
                                <input type="hidden" name="id" value="${news.news_id}">
                                <input type="submit" class="bg-light text-danger border rounded border-danger mt-1"
                                       name="btnVal"
                                       value="delete">
                            </form>

                        </ul>
                        <br>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>


    </div>
</main>

<%@include file="/jsp/index.components/footer.jsp" %>