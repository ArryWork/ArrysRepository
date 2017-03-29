
$(function () {
    initTable();
    buildParkSelect($("#adminAreaid").val());
    $("#chooseParkSlt").change(getCurrentMsgByParkinglot)

});

function initTable() {
    $('#currentParkMsgTable').bootstrapTable({
            url: 'showOrder.do',
            pagination: true,
            height: 485,
            singleSelect: true,
            clickToSelect: true,
            search:true,
            toolbar:"#toolbar",
            queryParams:{areaid:$("#adminAreaid").val()},
            columns: [
                {
                    radio: true
                },
                {
                    field: 'status',
                    title: "当前状态",
                    formatter: function (value, data, index) {
                        if (value == "0")
                            return "<span style = \"color: green\">停车结束</span>";
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
                    field:"endtime",
                    title:"结束时间",
                    formatter: formatTime

                },{
                    field:"payamt",
                    title:"应付金额",
                    formatter:function (value, data, index) {
                        return "￥"+value;
                    }
                },{
                    field:"hasParkedTime",
                    title:"停车时长",
                    formatter:function (value, data, index) {
                        if(value<0)
                            return "取消预约";
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
                },
                {
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
        query:{parkinglotid: $("#chooseParkSlt option:selected").val()}
    })
}


