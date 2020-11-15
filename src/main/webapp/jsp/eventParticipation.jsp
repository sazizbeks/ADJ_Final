<%@include file="/jsp/index.components/header.jsp"%>
<s:query dataSource = "${snapshot}" var = "events">
    SELECT DISTINCT e.*
    FROM events_students
    INNER JOIN events e on e.event_id = events_students.event_id
    WHERE student_id = ${sessionScope.user.id}
    ORDER BY 1;
</s:query>
<main class="main">

    <div class="grid">

        <h1>Event</h1>

        <a href="<c:url value="/jsp/studentEvents.jsp"/>">
            <button class="btn btn-primary">
                My events
            </button>
        </a>

        <a href="<c:url value="/jsp/studentEvents.jsp"/>">
            <button class="btn btn-primary">
                My participation
            </button>
        </a>
        <c:forEach var="row" items="${events.rows}">
            <div class="text-center">
                <ul class="list-group">
                    <li class="list-group-item">${row.event_id}</li>
                    <li class="list-group-item">${row.event_name}</li>
                    <li class="list-group-item">${row.event_start_date}</li>
                    <li class="list-group-item">${row.event_end_date}</li>
                    <li class="list-group-item">${row.event_creator_id}</li>
                </ul>
                <br>
            </div>
        </c:forEach>

    </div>

</main>

<%@include file="/jsp/index.components/footer.jsp"%>