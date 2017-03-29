<%--
  Created by IntelliJ IDEA.
  User: 林强
  Date: 2016-12-23
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>车位信息</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/resources/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/resources/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${basePath}/resources/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>
<body>
<input id="adminAreaid" type="hidden" value="${sessionScope.admin.areaid}"/>
<div class="col-sm-12">
    <div class="example-wrap">
        <h4 class="roolbar">工具条</h4>
        <div class="toolbar">
            <div class="btn-group hidden-xs" id="exampleToolbar" role="group">
                <button type="button" class="btn btn-outline btn-default" id="query">
                    <i class="glyphicon glyphicon-search" aria-hidden="true"></i>查看
                </button>
                <button type="button" class="btn btn-outline btn-default">
                    <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>删除
                </button>
                <button type="button" class="btn btn-outline btn-default" id="checkStatus">
                    <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>修正状态
                </button>
                <button type="button" class="btn btn-outline btn-default" id="endParkByAdmin">
                    <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>结束停车
                </button>
                <span class="dropdown" style="float: right">
                   <select class="form-control" id="chooseParkSlt">
                    </select>
                </span>
            </div>
    </div>
        <table id="currentParkMsgTable">
        </table>
        <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content animated bounceInRight">
                    <div class="modal-body">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary">确定</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script src="/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js?v=1.0.0"></script>
<script src="/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/resources/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script type="text/javascript" src="/resources/js/showCurrentParkMsg.js" ></script>
<script src="${basePath}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>

</body>
</html>
