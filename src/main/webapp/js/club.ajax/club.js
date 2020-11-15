$(document).ready(function () {
    $('.delete').click(function () {
        const club_id = $(this).attr("id");
        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                setTimeout(function() {
                    window.location.href = '../jsp/club.jsp'
                }, 200);
            }
        }

        let toServ = club_id.toJSONString;
        xhttp.open("POST", "${pageContext.request.contextPath}/delete/club?club_id=" + club_id, true);
        xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhttp.send(toServ);
    });

});