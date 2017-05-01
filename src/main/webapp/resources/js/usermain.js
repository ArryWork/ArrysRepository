/**
 * Created by 林强 on 2017-02-26.
 */
$(document).ready(function () {
    $("#parkBtn").click(usergetLocal);
    $("#chooseParkSlt").change(function () {
        console.log($("#chooseParkSlt option:selected").val());
    });
    $("#ensureParkBtn").click(ParkBtn);
    $("#queryAllOrderInfo").click(goToOrderPage);
    $("#bookPark").click(bookParkSpace);
    $("#ensureBookBtn").click(bookParkStart);
    $("#recharge").click(rechargePage);
    $("#unpaidOrderPage").click(function () {
        window.location.href="rePayOrderPage.do";
    });
    $("#setPasswordBtn").click(function () {
        window.location.href="setPasswordPage.do";
    });
    initCitySelect();
});

function usergetLocal() {
    getLocal(showPosition);
}
//获取所在区域并展示
function showPosition(position) {
    $.ajax({
        url: "../pub/getArea.do",
        type: "POST",
        datatype: "json",
        data: {
            longtitude: position.point.lng,
            latitude: position.point.lat
        },
        success: function (area) {
            var local = JSON.parse(area);
            $("#parkArea").html(local.areaname);
            buildParkSelect(local.fid);
        }
    });

//根据区域ID生成停车场下拉框
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
                        $option = $("<option value=" + parkList[i].fid + ">" + parkList[i].detailaddress + "(空余车位：" + parkList[i].availablespace + ")&nbsp;&nbsp;&nbsp;" + parkList[i].chargscale + "元/小时</option>");
                        var input  = "<input type='hidden' id='"+parkList[i].fid+"' value='"+parkList[i].chargscale+"'/>";
                        $("#scaleList").append(input);
                    }
                    parkselect.append($option);
                }
            }
        })
    }
}
//发送停车请求
function ParkBtn() {
    var park = $("#chooseParkSlt option:selected").val();
    $.ajax({
        url: "parkStart.do",
        type: "POST",
        datatype: "json",
        sync: true,
        data: {
            parkid: park,
            userid: $("#userid").val(),
            scale:$("#"+park).val()
        },
        success: function (result) {
            $("#chooseParklot").modal("hide");
            var result = JSON.parse(result);
            if (result.flag == "0")
                swal({title: "错误提示", text: result.msg});
            else if(result.flag == "1") {
                $("#showBookInfo").modal("show");
                var bookInfo = result.bookInfo;
                $("#parkPosition").html($("#parkArea").html()+":<span style=\"color: red; \">"+$("#chooseParkSlt option:selected").html()+"</span>");
                $("#parkingSpaceOrd").html(bookInfo.spaceord+"号车位处停车");
            }
        }
    })
}
//订单信息页面
function goToOrderPage() {
    window.location.href="goToShowPage.do";
}
//预约车位
function bookParkSpace() {
    $("#bookParkSpace").modal("show");
}

function initCitySelect() {
    var cantonSlt = $("#cantonSlt");
    var cantonlist = [
       {name:"鼓楼区",postcode:"350102"},
       {name:"台江区",postcode:"350103"},
       {name:"仓山区",postcode:"350104"},
       {name:"马尾区",postcode:"350105"},
       {name:"晋安区",postcode:"350111"},
       {name:"闽侯县",postcode:"350121"},
       {name:"连江县",postcode:"350122"},
       {name:"罗源县",postcode:"350123"},
       {name:"闽清县",postcode:"350124"},
       {name:"永泰县",postcode:"350125"},
       {name:"平潭县",postcode:"350128"},
       {name:"福清市",postcode:"350181"},
       {name:"长乐市",postcode:"350182"}
       ];
    for(var i=0; i<cantonlist.length;i++){
       $option = $("<option value="+cantonlist[i].postcode+">"+cantonlist[i].name+"</option>");
        cantonSlt.append($option);
    }
    cantonSlt.change(function () {
      var p = cantonSlt.children('option:selected').val();
      var parkinglotContent = $("#tab-content");
      parkinglotContent.html("");
      $.ajax({
          url:"/pub/getAreaListByCanton.do",
          type:"post",
          async: false,
          data:{
              cantoncode:p
          },
          success:function (arealist) {
              arealist = JSON.parse(arealist);
              $("#tabname").html("");
              for (var i = 0; i < arealist.length; i++) {
                  {
                      if (i == 0) {
                          $li = $("<li id='"+arealist[i].fid+"' class='active'><a data-toggle=\"tab\" href=\"#tab-" + arealist[i].fid + "\">" + arealist[i].areaname + "</a> </li>");
                          $contentDiv = $("<div id=\"tab-" + arealist[i].fid+"\" class=\"tab-pane active\"></div>");
                      }
                      else {
                          $li = $("<li id='"+arealist[i].fid+"' ><a data-toggle=\"tab\" href=\"#tab-" + arealist[i].fid + "\">" + arealist[i].areaname + "</a> </li>");
                          $contentDiv = $("<div id=\"tab-" + arealist[i].fid+"\" class=\"tab-pane\"></div>");
                      }
                      $("#tabname").append($li);
                      parkinglotContent.append($contentDiv);
                      creatParkinglotTab(arealist[i].fid);

                  }

              }
              $("#tab-content .parkinglotBtn").click(function () {
                  var areaid = $("#tabname .active").attr("id");
                  var spaceord = $(this).html().substring(1,2);
                  var parkinglotid = $("#"+areaid+""+spaceord).val();
                  $("#parkinglotid").val(parkinglotid);
                  $("#bookParkArea").html($(this).html());
                  $("#sureBookPark").modal("show");
              });
          }
      })
    })
}

function creatParkinglotTab(areaid) {
    $contentBody = $(" <div class=\"panel-body\"></div>");
    $.ajax({
        url:"/pub/getParkList.do",
        async:false,
        type:"post",
        data:{
            areaid:areaid
        },
        success:function (parkinglotList) {
            parkinglotList = JSON.parse(parkinglotList);
            for(var i = 0; i<parkinglotList.length; i++){
                $parkinglotBtn = $("<button type=\"button\" class=\"btn btn-primary btn-lg parkinglotBtn\">["+parkinglotList[i].lotorder+"]"+parkinglotList[i].detailaddress+"</button>");
                $parkinglotid = $("<input type='hidden' id=\""+areaid+""+parkinglotList[i].lotorder+"\" value = \""+parkinglotList[i].fid+"\"/>");
                $contentBody.append($parkinglotBtn);
                $contentBody.append($parkinglotid);
            }
        }
    });
    $("#tab-"+areaid).append($contentBody);
}

function bookParkStart() {
    var year = $("#showDate").attr('data-year');
    var month = $("#showDate").attr('data-month');
    var date = $("#showDate").attr('data-date');
    var hour = $("#showDate").attr('data-hour');
    var minute = $("#showDate").attr('data-minute');
    var d = new Date(year,month-1,date,hour,minute,"0");
    $("#parktime").val(d.getTime());
    $("#bookParkForm").submit();
}

function rechargePage() {
    window.location.href="rechargePage.do";
}

function unpaidPage(){
    windows.location.href="unpaidPage.do";
}