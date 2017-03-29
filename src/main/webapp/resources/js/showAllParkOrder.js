$(function () {
    initPage();
});
function initPage() {
    coverit();
    getOrderInfo(new Date().getMonth()+1,new Date().getFullYear());
    $("#backToHome").click(function () {
        window.location.href="goHome.do";
    });
    initDataSelect();
}
/**
 * 生成最近十二个月的下拉框
 */
function  initDataSelect() {
    var date = new Date();
    var month = date.getMonth()+1;
    var year = date.getFullYear();
    var select = $("#dataSelect");
    select.change(function () {
        var option1=$(this).children('option:selected').val();
        var year = option1.substring(0,4);
        var month = option1.substring(4);
        getOrderInfo(month,year);
    });
    for(var i = 0; i<12 ;i++){
        if(month==0) {
            year = year-1;
            month=12;
        }
        $option = $("<option value='"+year+""+month+"'>"+year+"年"+month+"月"+"</option>");
        select.append($option);
        month--;
    }
}
//获取订单数据,并调用生成订单页面方法
function getOrderInfo(month,year) {
    $.ajax({
        url:"showAllOrder.do",
        type:"post",
        sync:"true",
        data:{
            userid:$("#userid").val(),
            month:month,
            year:year
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
        $orderInfoDiv = $("<div class=\"row clearfix orderInfo\"></div>");
        $orderLeft = $("<div class=\"orderLeft\"></div>");
        $orderDay = $("<div class=\"orderDay\">星期"+Week[time.getDay()]+"</div>");
        $parkTime = $("<div class=\"parktime\">"+(time.getMonth()+1)+"-"+(time.getDate())+"</div>");
        $orderMid = $("<div class=\"orderMid\"></div>");
        $parkArea = $("<div class=\"parkArea\">"+orderlist[i].area+"</div>");
        $parkinglot = $("<div class=\"parkinglot\">"+orderlist[i].parkinglot+"</div>");
        $orderAmt = $("<div class=\"orderAmt\">"+orderlist[i].payamt+"元</div>");
        var OrderDiv = $("#OrderDiv");
        OrderDiv.append($orderInfoDiv);
        $orderInfoDiv.append($orderLeft);
        $orderInfoDiv.append($orderMid);
        $orderInfoDiv.append($orderAmt);
        $orderLeft.append($orderDay);
        $orderLeft.append($parkTime);
        $orderMid.append($parkArea);
        $orderMid.append($parkinglot);
    }



}

function clearOrderInfo() {
   $("#OrderDiv").html("");
}