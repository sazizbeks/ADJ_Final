<%@include file="/jsp/index.components/header.jsp"%>
<s:query dataSource = "${snapshot}" var = "clubs">
    SELECT * FROM clubs;
</s:query>

<script><%@include file="../js/club.ajax/club.js"%></script>
<main class="main">
    <div class="grid">
        <h1>Clubs</h1>

        <c:if test="${!empty sessionScope.admin}">
            <div class="btn-group" role="group" aria-label="Basic example">
                <a href="<%=application.getContextPath()%>/jsp/addClub.jsp"><button class="bg-warning border rounded border-warning mb-auto">Add club</button></a>
                <a href="<%=application.getContextPath()%>/jsp/addStudentToClub.jsp"><button class="bg-success border rounded border-success mb-auto">Add student to club</button></a>
            </div>
        </c:if>


        <c:forEach var="row" items="${clubs.rows}">
            <div class="text-center">
                <ul class="list-group">

                    <li class="list-group-item">${row.club_id}</li>
                    <li class="list-group-item">${row.club_name}</li>
                    <c:if test="${!empty sessionScope.admin}">
                        <form action="<c:url value="/club"/>">
                            <input type="hidden" name="club_id" value="${row.club_id}">
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <input type="submit" class="bg-light text-primary border rounded border-primary mt-1 btn-sm edit" value="edit">
                                <button class="bg-light text-danger border rounded border-danger mt-1 btn-sm delete" value="${row.club_id}" id="${row.club_id}">Delete</button>
                            </div>
                        </form>
                    </c:if>
                    </ul>
                <br>
            </div>
        </c:forEach>

    </div>

</main>

<%@include file="/jsp/index.components/footer.jsp"%>