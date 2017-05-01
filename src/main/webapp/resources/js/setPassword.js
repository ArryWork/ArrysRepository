$(function () {
    $("#validateBtn").click(validatePassword);
});
function validatePassword() {
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "progressBar": false,
        "positionClass": "toast-top-full-width",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "2000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    if($("#currentPassword").val()=="")
        toastr.warning("请输入当前密码","提示");
    else if($("#newPassword").val()=="")
        toastr.warning("请输入修改候的密码","提示");
    else if($("#confirmPassword").val()=="")
        toastr.warning("请再次输入密码","提示");
    else if($("#newPassword").val()!=$("#confirmPassword").val()){
        toastr.warning("两次输入的密码不一致，请重新输入");
    }
    else{
        $.ajax({
            type : "POST",
            url : "setPassword.do",
            dataType : 'json',
            data:{
                userId:$("#userid").val(),
                password:$("#currentPassword").val(),
                newPassword:$("#newPassword").val()
            },
            success:function (data) {
                if(data.error!=null){
                    toastr.warning(data.error,"提示");
                }else{
                    swal({title: "成功", text: "修改密码成功，点击确认回到主页", closeOnConfirm: false},function () {
                        window.location.href="goHome.do";
                    })
                }
            }
        });

    }
}