
$(function () {
    initTable();
    buildParkSelect($("#adminAreaid").val());
    $("#checkStatus").click(checkStatus);
    $("#endParkByAdmin").click(endPark);
    $("#chooseParkSlt").change(getCurrentMsgByParkinglot)
});

function initTable() {
    $('#currentParkMsgTable').bootstrapTable({
            url: 'showCurrentParkMsg.do',
            pagination: true,
            height: 485,
            singleSelect: true,
            clickToSelect: true,
            queryParams:{areaid:$("#adminAreaid").val()},
            method:"post",
            columns: [
                {
                    radio: true
                },
                {
                    field: 'status',
                    title: "当前状态",
                    formatter: function (value, data, index) {
                        if (value == "1")
                            return "<span style = \"color: chocolate\">在停</span>";
                        if (value == "2")
                            return "<span style = \"color: green\">预定</span>";
                        if (value == "3")
                            return "<span style = \"color: red\">取消预约</span>";
                    }
                },
                {
                    field: 'username',
                    title: '用户名'
                },
                {
                    field: 'phone',
                    title: '电话号码'
                },
                {
                    field:"parkinglot",
                    title:'停车场'
                },
                {
                    field:"spaceord",
                    title:'车位号'
                },{
                    field:"booktime",
                    title:"预约时间",
                    formatter: formatTime
                },
                {
                    field:"parktime",
                    title:"开始时间",
                    formatter: formatTime

                },{
                    field:"hasParkedTime",
                    title:"已停时长",
                    formatter:function (value, data, index) {
                        if(value<0)
                            return "未开始停车";
                        var day = parseInt(value / (24*60*60));//计算整数天数
                        var afterDay = value - day*24*60*60;//取得算出天数后剩余的秒数
                        var hour = parseInt(afterDay/(60*60));//计算整数小时数
                        var afterHour = value - day*24*60*60 - hour*60*60;//取得算出小时数后剩余的秒数
                        var min = parseInt(afterHour/60);//计算整数分
                        var resultdate ;
                        if(day != 0) {
                            resultdate = day + "天";
                            resultdate+=hour+"小时"+min+"分钟";
                        }
                        else
                            resultdate=hour+"小时"+min+"分钟";
                        return resultdate;
                    }
                },{
                    field:"scale",
                    title:"收费标准",
                    formatter:function (value , data, index) {
                        return value + "元/小时"
                    }
                }
            ],
            pageNumber: 1,
            pageSize: 10,
            onLoadSuccess: function (data) {
            }
        }
    );
}

function formatTime(value,data,index) {
    var date = new Date(value);
    var hour = date.getHours();
    var minute = date.getMinutes();
    if(hour<10)
        hour="0"+hour;
    if(minute<10)
        minute="0"+minute;
    var resultDate = (date.getMonth()+1)+"月"+date.getDate()+"日&nbsp;&nbsp;"+hour+":"+minute;
    return resultDate;
}
function buildParkSelect(arerid) {
    $.ajax({
        url: "../pub/getParkList.do",
        type: "POST",
        datatype: "json",
        data: {
            areaid: arerid
        },
        success: function (parkList) {
            $("#scaleList").html("");
            parkList = JSON.parse(parkList);
            var parkselect = $("#chooseParkSlt");
            $("#chooseParkSlt").html("");
            for (var i = 0; i < parkList.length; i++) {
                if(parkList[i].availablespace==0) {
                    $option = $("<option disabled=\"disabled\" value=" + parkList[i].fid + ">" + parkList[i].detailaddress + "(空余车位：" + parkList[i].availablespace + ")&nbsp;&nbsp;&nbsp;" + parkList[i].chargscale + "元/小时</option>");
                }
                else {
                    $option = $("<option value=" + parkList[i].fid + ">" + parkList[i].detailaddress+"</option>");
                    var input  = "<input type='hidden' id='"+parkList[i].fid+"' value='"+parkList[i].chargscale+"'/>";
                    $("#scaleList").append(input);
                }
                parkselect.append($option);
            }
        }
    })
}

function getCurrentMsgByParkinglot() {
    $("#currentParkMsgTable").bootstrapTable("refresh",{
        query:{parkid: $("#chooseParkSlt option:selected").val()}
    })
}

function checkStatus() {
    var bookInfo = $("#currentParkMsgTable").bootstrapTable("getSelections")[0];
    if(bookInfo==null){
        swal("警告","请选择一条记录","warning");
    }
    else{
        var st;
        var beforestatus;
        var afterstatus;
        if(bookInfo.status == 1) {
            st = 2;
            beforestatus="在停";
            afterstatus="预定";
        }
        else if(bookInfo.status == 2)
        {
            if(bookInfo.parktime>new Date()) {
                swal("提示", "还未到用户预定的停车时间","warning");
                return;
            }
            st = 1;
            beforestatus="预定";
            afterstatus="在停";
        }
        swal({title:"提示", text:"当前状态为"+beforestatus+"，是否修正为"+afterstatus,
            confirmButtonText:"是的",
            cancelButtonText:"取消",
            closeOnConfirm:false,
            showCancelButton:true,confirmButtonColor:"#1ab394"
            },
            function(isConfirm){
            if(isConfirm){
                $.ajax({
                    url:"checkStatus.do",
                    type:"post",
                    data:{
                      bookInfoId:bookInfo.fid,
                      status:st
                    },
                    success:function (data) {
                        swal("成功！",data.msg,"success");
                        $("#currentParkMsgTable").bootstrapTable("refresh");
                    }
                });
            }
            });
    }
    console.log(bookInfo);
}

function endPark() {
    var bookInfo = $("#currentParkMsgTable").bootstrapTable("getSelections")[0];
    if(bookInfo.status=="2"){
        swal("提示","该用户未开始停车，无法强制结束停车","warning");
        return;
    }
    var type;
    var tips;
    if(bookInfo.endtime!=null){
        type = 1;
        tips = "该用户停车已结束，是否更新订单状态"
    }else{
        type = 2
        tips = "该用户未主动结束停车，是否结束该用户此次停车"
    }

    swal({
            title:"提示",
            text:tips,
            confirmButtonText:"确认",
            confirmButtonColor:"#1ab394",
            closeOnConfirm:false
        },
        function (isConfirm) {
        if (isConfirm) {
            $.ajax({
                url: "endParkByAdmin.do",
                type: "post",
                data: {
                    bookInfoId: bookInfo.fid,
                    type: type
                }
                , success: function (data) {
                    swal("成功！",data.msg,"success");
                    $("#currentParkMsgTable").bootstrapTable("refresh");
                }
            })
        }
    })}
    
function search() {
    
}