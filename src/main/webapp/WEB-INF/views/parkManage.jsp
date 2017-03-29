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
    <title>停车场管理</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/resources/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/resources/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${basePath}/resources/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <style>
        #detailMap {
            width: 600px;
            height: 350px;
        }
    </style>
</head>
<body>
<input id="adminAreaid" type="hidden" value="${sessionScope.admin.areaid}"/>
<div class="col-sm-12">
    <div class="example-wrap">
        <h4 class="roolbar">工具条</h4>
        <div class="toolbar">
            <div class="btn-group hidden-xs" id="exampleToolbar" role="group">
                <button type="button" class="btn btn-outline btn-default" id="parkDetail">
                    <i class="glyphicon glyphicon-search" aria-hidden="true"></i>查看
                </button>
                <button type="button" class="btn btn-outline btn-default" id="addBtn">
                    <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>新增
                </button>
            </div>
        </div>
        <table id="parkingLotTable">
        </table>
        <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content animated bounceInRight">
                    <div class="modal-header">
                        <h4>停车场详细信息</h4>
                    </div>
                    <div class="modal-body">
                        <div class="col-md-6">
                            <label for="parkName" class="col-sm-3 control-label">停车场名称</label>
                            <div class="col-sm-9">
                                <input id="parkName" readonly="readonly" type="text" class="form-control"/>
                            </div>
                            <label for="parkArea" class="col-sm-3 control-label">所属区域</label>
                            <div class="col-sm-9">
                                <input id="parkArea" type="text" readonly="readonly" class="form-control"/></div>
                        </div>
                        <div class="col-md-6">
                            <label for="spaceNumber" class="col-sm-3 control-label">车位数量</label>
                            <div class="col-sm-9"><input type="number" class="form-control" id="spaceNumber"/></div>
                            <label for="avaliableSpace" class="col-sm-3 control-label">可用数量</label>
                            <div class="col-sm-9"><input type="number" readonly="readonly" class="form-control"
                                                         id="avaliableSpace"/></div>
                        </div>
                        <input id="parkId" type="hidden"/>
                        <div class="clearfix" id="detailMap">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="alterBtn">修改</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal inmodal" id="addModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" style="width: 820px">
                <div class="modal-content animated bounceInRight">
                    <div class="modal-header">
                        <h4>新增停车场</h4>
                    </div>
                    <form id="addParkForm">
                    <div class="modal-body" style="height: 150px">
                        <div class="text-center m-t-md">
                            <div class="col-md-6">
                                <label for="parkName" class="col-sm-3">车场名称</label>
                                <div class="col-sm-9">
                                    <input id="addParkName" name="detailaddress" type="text" class="form-control required"/>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label for="spaceNumber" class="col-sm-3 ">车位数量</label>
                                <div class="col-sm-9"><input id="addSpaceNumber" name="spacenumber" type="number" class="form-control required"/></div>
                            </div>
                            <div class="col-md-6">
                                <label for="spaceNumber" class="col-sm-3 ">收费标准</label>
                                <div class="col-sm-9"><input id="addchargscale" name="chargscale" type="number"  oninput="if(value.length>2)value=value.slice(0,2)"  class="form-control required"/></div>
                            </div>
                            <div class="col-md-6">
                                <label for="spaceNumber" class="col-sm-3 ">车位顺序</label>
                                <div class="col-sm-9"><input id="addlotorder" name="lotorder" type="number"  oninput="if(value.length>2)value=value.slice(0,2)"  class="form-control required"/></div>
                            </div>
                        </div>
                        <div class="text-center m-t-md">
                            <div class="col-md-6">
                                <label for="parkName" class="col-sm-3">经度</label>
                                <div class="col-sm-9">
                                    <input id="longtitude" name="longtitude" readonly="readonly" type="text" class="form-control required"/>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label for="spaceNumber" class="col-sm-3 ">纬度</label>
                                <div class="col-sm-9"><input id="latitude" name="latitude" readonly="readonly" type="input" class="form-control required"/></div>
                            </div>
                            <input type="hidden" name="areaid" value="${sessionScope.admin.areaid}"/>
                        </div>
                    </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="addParkBtn">新增</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal inmodal" id="mapModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" style="width: 820px">
                <div class="modal-content animated bounceInRight">
                    <div class="modal-header">
                        <h4>选择位置</h4>
                    </div>
                    <div id="mapBody" class="modal-body">
                        <div id="locationMap" style="width:750px; height: 400px"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="ensure">确认</button>
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
<script src="/resources/js/plugins/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=zSoKpoAGCKY235hHSpGOUdscASAn0R35" type="text/javascript"></script>
<script type="text/javascript" src="/resources/js/parkManage.js"></script>
<script src="${basePath}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
</body>
</html>
