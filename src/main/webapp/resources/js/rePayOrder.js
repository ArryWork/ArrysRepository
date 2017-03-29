$(function () {
    initPage();
});
function initPage() {
    coverit();
    getOrderInfo();
    $("#backToHome").click(function () {
        window.location.href="goHome.do";
    });
}

//获取订单数据,并调用生成订单页面方法
function getOrderInfo() {
      $.ajax({
        url:"showUnpaidOrder.do",
        type:"post",
        sync:"true",
        data:{
            userid:$("#userid").val()
        },
        success:function (data) {
            clearOrderInfo();
            clearCover();
            addOrderInfo(data);
        }
    });
}

/**
 * 前端生成订单页面
 * @param order
 */
function addOrderInfo(order) {
    var orderlist = JSON.parse(order);
    var Week = ['日','一','二','三','四','五','六'];
    for(var i = 0; i<orderlist.length; i++) {
        var time = new Date(orderlist[i].parktime);
        $orderInfo = $("<div class=\"row orderInfo\"></div>");
        $orderTop = $("<div class=\"orderTop\">"+orderlist[i].area+"&nbsp;&nbsp;"+orderlist[i].parkinglot+"</div>");
        $orderMid = $("<div class=\"orderMid\"></div>");
        $startTime = $("<span class=\"startTime\">"+formatTime(orderlist[i].parktime)+"</span>");
        $endTime = $("<span class=\"endTime\">&nbsp;至&nbsp;"+formatTime(orderlist[i].endtime)+"</span>");
        $orderBottom =　$("<div class=\"orderBottom\"></div>");
        $orderAmt = $("<div>￥"+orderlist[i].payamt+"</div>");
        $repayBtn = $("<button type=\"button\" bookInfoId='"+orderlist[i].fid+"' class=\"btn btn-primary paybtn\">支付</button>");

        var OrderDiv = $("#OrderDiv");
        OrderDiv.append($orderInfo);
        $orderInfo.append($orderTop);
        $orderInfo.append($orderMid);
        $orderInfo.append($orderBottom);
        $orderMid.append($startTime);
        $orderMid.append($endTime);
        $orderBottom.append($orderAmt);
        $orderBottom.append($repayBtn);

        $(".paybtn").unbind('click').click(rePay);
    }



}

function clearOrderInfo() {
   $("#OrderDiv").html("");
}

function formatTime (time) {
        var datetime = new Date();
        datetime.setTime(time);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
        var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
        var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
        return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}
function rePay() {
    coverit();
    $.ajax({
        url:"rePay.do",
        type:"post",
        sync:"true",
        data:{
            userid:$("#userid").val(),
            bookInfoId:$(this).attr("bookinfoid")
        },
        success:function (data) {
            data = JSON.parse(data);
            clearCover();
            if(data.error!=null){
                swal("warning",data.error,"warning");
            }else if(data.flag=="1"){
                swal({title:"成功！",text:data.msg,type:"success"},function (isConfirm) {
                    if(isConfirm)
                        window.location.reload();
                });
            }else if(data.flag=='0'){
                swal({title:"失败！",text:data.msg,type:"warning"},function (isConfirm) {
                    if(isConfirm)
                        window.location.href="goHome.do"
                });
            }
        }
    });
}