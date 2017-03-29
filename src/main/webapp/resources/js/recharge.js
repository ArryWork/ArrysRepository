$(document).ready(function () {
$("#nextBtn").click(nextStep);
$("#sureBtn").click(validate);
});
function validate() {
    $.ajax({
        url:"validatePassword.do",
        async:false,
        data:{
            userid: $("#userid").val(),
            password:$("#password").val(),
            famt:$("#payamt").val()
        },
        success:function () {
            window.location.href="goHome.do"
        }
    })
}
function nextStep() {
    var payamt = $("#payamt").val();
    if(payamt==""){
        swal({title: "错误提示", text: "请输入充值金额"});
    }else {
        $("#inputPassword").modal("show");
    }
}