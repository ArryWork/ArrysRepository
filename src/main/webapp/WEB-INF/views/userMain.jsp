<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:16:41 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>SmartParking</title>


    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->


    <link href="${basePath}/resources/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${basePath}/resources/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${basePath}/resources/css/animate.min.css" rel="stylesheet">
    <link href="${basePath}/resources/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${basePath}/resources/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${basePath}/resources/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
    <link href="${basePath}/resources/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${basePath}/resources/css/plugins/mobiscroll.custom-2.6.2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/resources/css/plugins/iosSelect.css">


    <style type="text/css">
    .midspace{
        text-align: center;
        height: 39%;
        background-color: #1ab394; 
    }
    .container-fluid{
        height:100%;
    }
    .menu{
        height: 61%;
    }
    .menu_item{
        margin-top: 10px;
        border-bottom:gainsboro solid 1px;
        height: 17%;
        line-height: 60px;
    }
    .menu_item_de{
        margin-left: 10%;
        font-size: 20px;
    }
    .menu_item_de i{
        font-size:35px;
        color: #1ab394;
    }
    .menu_icon{
        width: 65px;
        height:60px;
        display: inline-block;
        text-align: center;
    }
    #midfuck{
        height: 30%;
    }
    #parkBtn{
        box-shadow: 0px 4px 5px #000; ;
    }
    .dw-modal,
    .dw-persp,
    .ios-select-widget-box,
    .olay{
        z-index: 999999;
    }
    </style>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden; ">
<input id="userid" type="hidden" value="${sessionScope.user.fid }">
<div class="container-fluid ">
    <div class="row clearfix midspace">
        <div id="midfuck"></div>
        <button type = "button" id="parkBtn" class="bgbutton btn btn-white  btn-lg btn-rounded " data-toggle="modal" data-target="#chooseParklot" style="outline:none;" >
            <i class="glyphicon glyphicon-hand-up"></i><span style="color: #1ab394; ">&nbsp;我要停车</span>
        </button>
        <%--<button type="button" id="testBtn">test</button>--%>
    </div>
    <div class="row menu clearfix">
        <%--预约车辆--%>
        <div class="menu_item" id="bookPark">
            <div class="menu_item_de">
                <div class="menu_icon"><i class="fa fa-clock-o"></i></div>&nbsp;&nbsp;预定停车位
            </div>
        </div>
        <%--查询订单信息--%>
        <div class="menu_item" id="queryAllOrderInfo">
            <div class="menu_item_de">
                <div class="menu_icon"><i class="fa fa-file-text-o" ></i></div>&nbsp;&nbsp;查看所有订单
            </div>
        </div>
        <%--未支付的账单--%>
        <div class="menu_item" id="unpaidOrderPage">
            <div class="menu_item_de">
                <div class="menu_icon"><i class="fa fa-file-excel-o"></i></div>&nbsp;&nbsp;未支付订单
            </div>
        </div>
        <%--充值--%>
        <div class="menu_item" id="recharge">
            <div class="menu_item_de">
                <div class="menu_icon"><i class="fa fa-cny"></i></div>&nbsp;&nbsp;充值
               <span style="color: #1b6d85; font-size: 20px; float: right; margin-right: 20px">账户余额${sessionScope.user.balance}</span>
            </div>
        </div>
    </div>

<%--选择停车场模态框--%>
<div  class="modal inmodal" id="chooseParklot"  keyboard="true">
    <div class="modal-dialog modal-md">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">选择停车场</h4>
                <span>当前所在区域：</span><span id="parkArea"></span>
            </div>
            <div class="modal-body">
                <div>
                    <select class="form-control" id="chooseParkSlt">
                    </select>
                </div>
                <div id="scaleList">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="ensureParkBtn">确定</button>
            </div>
        </div>
    </div>
</div>
<%--展示停车位--%>
<div class="modal  inmodal" id="showBookInfo" keyboard="true">
    <div class="modal-dialog modal-md">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">停车信息</h4>
            </div>
            <div class="modal-body">
                <h2>请至：</h2><h2 id="parkPosition"></h2>
                <h2 id="parkingSpaceOrd"></h2>
            </div>
            <div class="modal-footer">
                <form method="post" action="/user/showParkingMsg.do">
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                    <input type="hidden" name="userid" value="${sessionScope.user.fid }">
                <button type="submit" class="btn btn-primary" >确定</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%--预约停车位模态框--%>
<div  class="modal inmodal" id="bookParkSpace"  keyboard="true">
        <div class="modal-dialog modal-md">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title">选择停车场</h4>
                    <label for="citySlt"></label><select id="citySlt">
                        <option>福州</option>
                    </select>
                    <label for="cantonSlt"></label>
                    <select id="cantonSlt">
                        <option value="0">请选择地区</option>
                    </select>
                </div>
                <div class="modal-body">
                    <div id="bookParkTab" class="tabs-container">
                        <div class="tabs-left">
                            <ul id="tabname" class="nav nav-tabs">
                            </ul>
                            <div id="tab-content" class="tab-content ">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="bookParkSpaceBtn">确定</button>
                </div>
            </div>
        </div>
    </div>

<form id="showAllOrderForm" method="post" action="showAllOrder.do">
    <input type="hidden" name="userid" value="${sessionScope.user.fid}"/>
</form>

<div  class="modal inmodal" id="sureBookPark"  keyboard="true">
        <div class="modal-dialog modal-md">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="col-sm-4">
                        <div class="form-item item-line" id="selectDate">
                            <label>请选择停车时间</label>
                            <div class="pc-box">
                                <span data-year="" data-month="" data-date="" id="showDate">点击这里选择时间</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <form id="bookParkForm" method="post" action="bookPark.do">
                        <input type="hidden" name="parkinglotid" id="parkinglotid"/>
                        <input type="hidden" name="userid" value="${sessionScope.user.fid}"/>
                        <input type="hidden" id="parktime" name="parktime" />
                        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="ensureBookBtn">确定</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


<script src="http://api.map.baidu.com/api?v=2.0&ak=zSoKpoAGCKY235hHSpGOUdscASAn0R35" type="text/javascript"></script>
<script src="${basePath}/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${basePath}/resources/js/usermain.js"></script>
<script src="${basePath}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${basePath}/resources/js/pub/getLocation.js"></script>
<script src="${basePath}/resources/js/plugins/mobiscroll.custom-2.6.2.min.js"></script>
<script src="${basePath}/resources/js/plugins/iosSelect.js"></script>
<script src="${basePath}/resources/js/plugins/zepto.js"></script>
<script src="${basePath}/resources/js/pub/dateselect.js"></script>

</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:17:11 GMT -->
</html>


