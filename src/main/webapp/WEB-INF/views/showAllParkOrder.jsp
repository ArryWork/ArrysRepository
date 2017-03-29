<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="${basePath}/resources/css/pub/cover.css" rel="stylesheet">

    <style type="text/css">
        body{
            background-color: #6b7d86;
        }
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
        #dataSelect{
            width: 30%;
            margin-top: 10px;
            float: right;
            font-size: 15px;
        }
        .orderLeft{
            padding: 15px 30px;
            font-size: 13px;
            float: left;
            height:100%;
            width: 26%;
        }
        .orderMid{
            padding: 15px;
            height:100%;
            width: 55%;
            display: inline-block;
            font-size: 15px;
        }
        .orderAmt{
            padding: 15px;
            height: 100%;
            width: 19%;
            float: right;
        }
        .orderInfo{
            border-bottom:#e0e0e0 solid 1px;
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
        <div class="orderTitle">查看订单</div>
        <select id="dataSelect">
        </select>
    </div>
    <div class="row clearfix " id="OrderDiv"></div>
</div>
<script src="${basePath}/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${basePath}/resources/js/showAllParkOrder.js"></script>
<script src="${basePath}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${basePath}/resources/js/pub/getLocation.js"></script>
<script src="${basePath}/resources/js/pub/cover.js"></script>
<div id="cover"></div>
<div id="coverShow">
    <div class="sk-spinner sk-spinner-wave">
        <div class="sk-rect1"></div>
        <div class="sk-rect2"></div>
        <div class="sk-rect3"></div>
        <div class="sk-rect4"></div>
        <div class="sk-rect5"></div>
    </div>
</div>
</body>


</html>


