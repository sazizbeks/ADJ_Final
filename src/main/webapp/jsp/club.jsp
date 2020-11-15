<%@include file="/jsp/index.components/header.jsp"%>
<s:query dataSource = "${snapshot}" var = "clubs">
    SELECT * FROM clubs;
</s:query>
<main class="main">

    <div class="grid">

        <h1>Clubs</h1>

        <c:forEach var="row" items="${clubs.rows}">
            <div class="text-center">
                <ul class="list-group">
                    <li class="list-group-item">${row.club_id}</li>
                    <li class="list-group-item">${row.club_name}</li>
                    <input type="submit" class="bg-light text-primary border rounded border-primary" value="edit">
                    <input type="submit" class="bg-light text-danger border rounded border-danger mt-1" value="delete">
                </ul>
                <br>
            </div>
        </c:forEach>

    </div>

</main>

<%@include file="/jsp/index.components/footer.jsp"%>