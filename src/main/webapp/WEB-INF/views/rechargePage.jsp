<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:16:41 GMT -->
<head>
    <meta charset="utf-8">
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
    <link rel="stylesheet" href="${basePath}/resources/css/plugins/iosSelect.css">
    <style type="text/css">
        .topNav{
            font-size: 20px;
            color: #1ab394;
            border-bottom:#1ab394 solid 1px;
            margin-bottom: 10px;
        }
        .orderTitle{
            display: inline-block;
            text-align: center;
            line-height: 50px;
            color: #5e5d5d;
            margin-left: 20%;

        }
        #backToHome{
            opacity:0.5;
            margin-top: 1px;
            padding: 10px 20px;
            float: left;
            border-right: #1ab394 1px solid;
        }
        .payway{
            background-color: white;
            padding: 15px;
        }
        .bankImg{
            width: 40%;
        }
        .lastNumber{
            float: right;
            line-height: 40px;
        }
        .payamt{
            margin-top: 10px;
        }
        .payamt .form-control{
            border: none;
        }
        .midfuck{
            height: 20px;
        }
    </style>
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden; ">
<input id="userid" type="hidden" value="${sessionScope.user.fid }">
<div class="container-fluid ">
    <div class="row clearfix topNav">
        <div id="backToHome" class="clearfix">
            <i class="fa fa-arrow-left"></i>
        </div>
        <div class="orderTitle">充值</div>
    </div>
    <div class="row payway">
        <img class="bankImg"  src="/resources/img/bank/PBC.jpg"/>
        <div class="lastNumber">尾数6666</div>
    </div>
    <div class="row payamt payway">
        金额:&nbsp;&nbsp;
        <label>
            <input id="payamt" type="number" class="form-control"/>
        </label>
    </div>
    <div class="row" style="text-align: center">
        <div class="midfuck"></div>
        <button type = "button"  class="bgbutton btn btn-white  btn-lg" id="nextBtn" style="outline:none; background-color: #1ab394" >
           <span style="color: white; ">下一步</span>
        </button>
    </div>

    <div class="modal  inmodal" id="inputPassword" keyboard="true">
        <div class="modal-dialog modal-md">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                </div>
                <div class="modal-body">
                    <span>请输入支付密码:</span><input id="password" type="password" class="form-control" title="密码">
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary" id="sureBtn" >确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="http://api.map.baidu.com/api?v=2.0&ak=zSoKpoAGCKY235hHSpGOUdscASAn0R35" type="text/javascript"></script>
<script src="${basePath}/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${basePath}/resources/js/recharge.js"></script>
<script src="${basePath}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${basePath}/resources/js/plugins/iosSelect.js"></script>
<script src="${basePath}/resources/js/plugins/zepto.js"></script>
<script src="${basePath}/resources/js/pub/dateselect.js"></script>
</body>
</html>


