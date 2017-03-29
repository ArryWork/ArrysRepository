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
    <style type="text/css">
    .midspace{
        height: 39%;
        background-color: #1ab394;
        color: #ffffff;
    }
    .container-fluid{
        height:100%;
    }
    .userInfo{
        width: 90%;
        float: right;
    }
    #midfuck{
        height: 15%;
    }
    #parkBtn{
        box-shadow: 0px 4px 5px #000; ;
    }
    </style>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden; ">
<input id="userid" type="hidden" value="${user.fid }">
<div class="container-fluid ">
    <div class="row clearfix midspace">
        <div id="midfuck"></div>
        <div class="userInfo">
            <h2>
                ${sessionScope.user.username}
            </h2>
            <ul class="list-unstyled m-t-md">
                <li>
                    <span class="fa fa-envelope m-r-xs"></span>
                    <label>我的钱包:</label>
                    <span id="balance">${sessionScope.user.balance}&nbsp; 元</span>
                </li>
                <li>
                    <span class="fa fa-phone m-r-xs"></span>
                    <label>电话号码:</label>
                    ${sessionScope.user.phone}
                </li>
            </ul>
        </div>
    </div>
</div>




<script src="${basePath}/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${basePath}/resources/js/showParkingMsg.js"></script>
<script src="${basePath}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${basePath}/resources/js/pub/getLocation.js"></script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:17:11 GMT -->
</html>


