$(document).ready(function () {
    $('.radioButton').click(function () {
        const radVal = $(this).val();
        if(radVal == "showOne"){
            $('#IShowOne').removeClass('noneShow').addClass("show");
            $('#IShowByGroup').removeClass('show').addClass("noneShow");
            $('#IShowByMajor').removeClass('show').addClass("noneShow");
            $('#IShowByYear').removeClass('show').addClass("noneShow");
            $('#submit').removeClass('noneShow').addClass("show");
        }
        else if(radVal == "showByGroup"){
            $('#IShowByGroup').removeClass('noneShow').addClass("show");
            $('#IShowOne').removeClass('show').addClass("noneShow");
            $('#IShowByMajor').removeClass('show').addClass("noneShow");
            $('#IShowByYear').removeClass('show').addClass("noneShow");
            $('#submit').removeClass('noneShow').addClass("show");
        }
        else if(radVal == "showByMajor"){
            $('#IShowByMajor').removeClass('noneShow').addClass("show");
            $('#IShowByGroup').removeClass('show').addClass("noneShow");
            $('#IShowOne').removeClass('show').addClass("noneShow");
            $('#IShowByYear').removeClass('show').addClass("noneShow");
            $('#submit').removeClass('noneShow').addClass("show");
        }
        else if(radVal == "showByYear"){
            $('#IShowByYear').removeClass('noneShow').addClass("show");
            $('#IShowByMajor').removeClass('show').addClass("noneShow");
            $('#IShowByGroup').removeClass('show').addClass("noneShow");
            $('#IShowOne').removeClass('show').addClass("noneShow");
            $('#submit').removeClass('noneShow').addClass("show");
        }
        else if(radVal == "showAll"){
            $('#IShowOne').removeClass('show').addClass("noneShow");
            $('#IShowByMajor').removeClass('show').addClass("noneShow");
            $('#IShowByGroup').removeClass('show').addClass("noneShow");
            $('#IShowByYear').removeClass('show').addClass("noneShow");
            $('#submit').removeClass('noneShow').addClass("show");
        }
        else{
            $('#IShowOne').removeClass('show').addClass("noneShow");
            $('#IShowByMajor').removeClass('show').addClass("noneShow");
            $('#IShowByGroup').removeClass('show').addClass("noneShow");
            $('#IShowByYear').removeClass('show').addClass("noneShow");
            $('#submit').removeClass('show').addClass("noneShow");
        }

        $('#submit').click(function (){
            let xhttp = new XMLHttpRequest();
            let search = radVal;
            let inputVal = "";
            if(radVal == "showOne"){
                inputVal = document.getElementById("IShowOne").value;
            }
            else if(radVal == "showByGroup"){
                inputVal = document.getElementById("IShowByGroup").value;
            }
            else if(radVal == "showByMajor"){
                inputVal = document.getElementById("IShowByMajor").value;
            }
            else if(radVal == "showByYear"){
                inputVal = document.getElementById("IShowByYear").value;
            }
            else if(radVal == "showAll"){
                inputVal = "all";
            }
            else{
                inputVal = "";
            }

            if (search.length==0) {
                document.getElementById("badResult").innerHTML = "No such student in database";
                document.getElementById("goodResult").innerHTML = "";
                return;
            }

            xhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    let res = JSON.parse(this.responseText);
                    if (res.length>0) {
                        document.getElementById("information").className = "show";
                        var html_list = "<div class='row container-fluid'>";
                        for(var i = 0; i<res.length; i++){
                            html_list += "<div class=\"col-12 col-md-12 col-lg-8\">\n" +
                                "                        <div class=\"card-header\"><b id=\"username\">"+res[i].username+"</b></div>\n" +
                                "                        <div class=\"card-body py-2\">\n" +
                                "                            <ul class=\"list-group list-group-flush row\">\n" +
                                "                                <li class=\"list-group-item py-1\">\n" +
                                "                                    <dl class=\"row m-0\">\n" +
                                "                                        <dt class=\"text-dark mr-4\">Fisrt name:</dt>\n" +
                                "                                        <dd id=\"fname\">"+res[i].fname+"</dd>\n" +
                                "                                    </dl>\n" +
                                "                                </li>\n" +
                                "                                <li class=\"list-group-item py-1\">\n" +
                                "                                    <dl class=\"row m-0\">\n" +
                                "                                        <dt class=\"text-dark mr-4\">Last name:</dt>\n" +
                                "                                        <dd id=\"lname\">"+res[i].lname+"</dd>\n" +
                                "                                    </dl>\n" +
                                "                                </li>\n" +
                                "                                <li class=\"list-group-item py-1\">\n" +
                                "                                    <dl class=\"row m-0\">\n" +
                                "                                        <dt class=\"text-dark mr-4\">Birth year:</dt>\n" +
                                "                                        <dd id=\"year\">"+res[i].year+"</dd>\n" +
                                "                                    </dl>\n" +
                                "                                </li>\n" +
                                "                                <li class=\"list-group-item py-1\">\n" +
                                "                                    <dl class=\"row m-0\">\n" +
                                "                                        <dt class=\"text-dark mr-4\">Major:</dt>\n" +
                                "                                        <dd id=\"major\">"+res[i].major_id+"</dd>\n" +
                                "                                    </dl>\n" +
                                "                                </li>\n" +
                                "                                <li class=\"list-group-item py-1\">\n" +
                                "                                    <dl class=\"row m-0\">\n" +
                                "                                        <dt class=\"text-dark mr-4\">Group:</dt>\n" +
                                "                                        <dd id=\"group\">"+res[i].group_number+"</dd>\n" +
                                "                                    </dl>\n" +
                                "                                </li>\n" +
                                "                            </ul>\n" +
                                "                        </div>\n" +
                                "                    </div>";
                            html_list+="</div>";
                            document.getElementById("badResult").innerHTML = "";
                            document.getElementById("goodResult").innerHTML = "Successfully!";
                        }
                        $('#data').html(html_list);
                    }

                }
                else{
                    document.getElementById("badResult").innerHTML = "No such student in database";
                    document.getElementById("goodResult").innerHTML = "";
                    document.getElementById("information").className = "noneShow";
                }
            };
            let toServ = search.toJSONString;
            xhttp.open("POST", "${pageContext.request.contextPath}/show/student?search=" + search + "&inputVal=" + inputVal, true);
            xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xhttp.send(toServ);
        });

        $('#reset').click(function (){
            document.getElementById("badResult").innerHTML = "";
            document.getElementById("goodResult").innerHTML = "";
            document.getElementById("information").className = "noneShow";
            $('#IShowOne').removeClass('show').addClass("noneShow");
            $('#IShowByMajor').removeClass('show').addClass("noneShow");
            $('#IShowByGroup').removeClass('show').addClass("noneShow");
            $('#IShowByYear').removeClass('show').addClass("noneShow");
            $('#submit').removeClass('show').addClass("noneShow");
        });
    });
});
