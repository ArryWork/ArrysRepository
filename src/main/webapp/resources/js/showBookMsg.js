$(function () {
    $("#cancleBookBtn").click(cancleBook)
});

function cancleBook() {
    $.ajax({
        url: "cancleBook.do",
        type: "POST",
        datatype: "json",
        data: {
            bookInfoId:$("#bookInfoId").val()
        },
        success: function (data) {
            if(data=="true") {
                window.location.href = "goHome.do";
            }else {
                swal({title: "错误提示", text: "已过预定时间，将跳往主页"},function () {
                    window.location.href = "goHome.do";
                });
            }

        }
    });
}