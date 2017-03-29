$(function () {
    $("#loginBtn").click(login)
});
function login() {
    $.ajax({
        type : "POST",
        url : "user/login.do",
        dataType : 'json',
        data:{
            account:$("#account").val(),
            password:$("#password").val()
        },
        success:function (data) {
            if(data.error!=null){
                toastr.options = {
                    "closeButton": false,
                    "debug": false,
                    "progressBar": false,
                    "positionClass": "toast-top-full-width",
                    "onclick": null,
                    "showDuration": "400",
                    "hideDuration": "1000",
                    "timeOut": "1000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                    };
                toastr.warning(data.error,"提示");

            }else if (data.url!=null){
                window.location.href=data.url;
            }
        }
    });
}


