<%--
  Created by IntelliJ IDEA.
  User: 林强
  Date: 2017-03-09
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>停车结束</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link href="/resources/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/resources/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>
<body>
<h1 style="text-align: center">订单信息
    <c:if test="${bookInfo.ispayed==1}"><span style="color: green; ">(已付款)</span>
    </c:if>
    <c:if test="${bookInfo.ispayed==0}"><span style="color: red; ">(未付款)</span> </c:if></h1>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="row">
                <div class="col-sm-2 control-label">姓名
                </div>
                <div class="col-sm-4">
                    <input type="text" value="${sessionScope.user.username}" class="form-control"   readonly="readonly"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">电话
                </div>
                <div class="col-sm-4">
                    <input type="text" value="${sessionScope.user.phone}" class="form-control" readonly="readonly"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">预约时间
                </div>
                <div class="col-sm-4">
                    <input value="<fmt:formatDate type="date" value="${bookInfo.booktime}"  pattern="yyyy年MM月dd日 HH:mm:ss"/>" class="form-control" readonly="readonly"/>
                </div>
                <div class="col-sm-2">停车时间
                </div>
                <div class="col-sm-4">
                    <input value="<fmt:formatDate type="date" value="${bookInfo.parktime}"  pattern="yyyy年MM月dd日 HH:mm:ss"/>" class="form-control" readonly="readonly"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">结束时间
                </div>
                <div class="col-sm-4">
                    <input value="<fmt:formatDate type="date" value="${bookInfo.endtime}"  pattern="yyyy年MM月dd日 HH:mm:ss"/>" class="form-control" readonly="readonly"/>
                </div>
                <div class="col-sm-2">停车时长
                </div>
                <div class="col-sm-4">
                    <input value="${bookInfo.hasParkedTime}" class="form-control" readonly="readonly"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">停车位置
                </div>
                <div class="col-sm-10">
                    <input value="${bookInfo.parkinglot}" class="form-control" readonly="readonly"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">收费
                </div>
                <div class="col-sm-10">
                    <input value="${bookInfo.payamt} " class="form-control" readonly="readonly"/>
                </div>
            </div>
        </div>
    </div>
  </div>
<div style="height: 20px"></div>
<div style="text-align: center">
    <c:if test="${bookInfo.ispayed==0}"><button class="btn btn-primary dim btn-lg" style="width: 100px" type="button">付款</button></c:if>
    <a class="btn btn-primary dim btn-lg" style="width: 100px" type="button" href="goHome.do">返回主页</a>
</div>
<script src="/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/resources/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>
