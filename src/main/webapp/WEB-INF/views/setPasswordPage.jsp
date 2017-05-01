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
    <style>
        #formcontainer{
            margin-top: 20%;
        }
    </style>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <link href="${basePath}/resources/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${basePath}/resources/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${basePath}/resources/css/animate.min.css" rel="stylesheet">
    <link href="${basePath}/resources/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${basePath}/resources/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="/resources/css/plugins/toastr/toastr.min.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden; ">
<input id="userid" type="hidden" value="${sessionScope.user.fid }">
<div id="formcontainer" class="col-md-12">
    <div class="form-group">
        <label class="col-sm-3 control-label">当前密码：</label>
        <div class="col-sm-9">
            <input type="password" id="currentPassword" class="form-control" placeholder="请输入当前密码">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">新密码：</label>
        <div class="col-sm-9">
            <input type="password" id="newPassword" class="form-control" placeholder="请输入新密码">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">确认密码：</label>
        <div class="col-sm-9">
            <input type="password" id="confirmPassword" class="form-control" placeholder="请在输入一次密码">
        </div>
    </div>
    <div class="form-group" style="text-align: center">
        <button class="btn btn-primary" id="validateBtn" >确定</button>
    </div>
</div>
<div class="container-fluid ">
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
<script src="${basePath}/resources/js/setPassword.js"></script>
<script src="${basePath}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${basePath}/resources/js/pub/getLocation.js"></script>
<script src="${basePath}/resources/js/plugins/toastr/toastr.min.js"></script>

</body>


</html>


