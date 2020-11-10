$(document).ready(function (){
    $("#retype").keyup(function () {
        let password = $("#password").val();
        let re_password = $("#retype").val();

        if (password !== re_password) {
            $("#msg").css('display','block');
            $("#reg").prop('disabled', true);
        } else {
            $("#msg").css('display','none');
            $("#reg").prop('disabled', false);
        }
    });
});