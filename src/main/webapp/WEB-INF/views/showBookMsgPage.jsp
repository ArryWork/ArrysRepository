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
    <style type="text/css">
    .midspace{
        height: 55%;
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
    .parkingSpan{
        font-size: 15px;
        color: #f49000;
        font-weight: bold;
    }
    .midfuck{
        height: 15%;
    }
    #parkBtn{
        box-shadow: 0px 4px 5px #000; ;
    }
    .bottomspace{
            height: 100%;
            text-align: center;
        }
    </style>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden; ">
<input id="userid" type="hidden" value="${sessionScope.user.fid }">
<div class="container-fluid ">
    <div class="row clearfix midspace">
        <div class="midfuck"></div>
        <div class="userInfo">
            <h2>
                ${sessionScope.user.username}
            </h2>
            <ul class="list-unstyled m-t-md">
                <li>
                    <span class="fa fa-phone m-r-xs"></span>
                    <label>电话号码:</label>
                    ${sessionScope.user.phone}
                </li>
                <li>
                    <span class="fa fa-car m-r-xs"></span>
                    <label>已预约车位:</label>
                    <span class="parkingSpan">${bookInfo.area}</span>
                    <span class="parkingSpan">${bookInfo.parkinglot}</span>:${bookInfo.spaceord}号停车位
                </li>
                <li>
                    <label>预计停车时间:</label><span id="parkTime"><fmt:formatDate type="date" value="${bookInfo.parktime}"  pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
                </li>
            </ul>
        </div>
    </div>
    <div class="row clearfix bottomspace">
        <div class="midfuck"></div>
        <button class="btn btn-primary dim btn-lg" style="width: 180px" id="showCancleModal" data-toggle="modal" data-target="#cancleBookModal" type="button">取消预约</button>
    </div>
    <div  class="modal inmodal" id="cancleBookModal"   keyboard="true">
        <div class="modal-dialog modal-md">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                </div>
                <div class="modal-body">
                    确定取消预约？
                </div>
                <div class="modal-footer">
                    <form method="POST" action="cancleBook.do">
                        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="cancleBookBtn" >确定</button>
                        <input type="hidden" name="bookInfoId" id="bookInfoId" value="${bookInfo.fid}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>




<script src="${basePath}/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${basePath}/resources/js/showBookMsg.js"></script>
<script src="${basePath}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${basePath}/resources/js/pub/getLocation.js"></script>
</body>


</html>


