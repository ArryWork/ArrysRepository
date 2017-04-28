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
    <title>用户管理</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/resources/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/resources/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="${basePath}/resources/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="/resources/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body>
<div class="col-sm-12">
    <div class="example-wrap">
        <h4 class="example-title">工具条</h4>
        <div class="example">
            <div class="btn-group hidden-xs" id="exampleToolbar" role="group">
                <button type="button" class="btn btn-outline btn-default">
                    <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>添加
                </button>
                <button type="button" id="editBtn" class="btn btn-outline btn-default">
                    <i class="fa fa-edit" aria-hidden="true"></i>修改
                </button>
                <button type="button" class="btn btn-outline btn-default" id="frozeUser">
                    <i class="fa  fa-lock" aria-hidden="true"></i>冻结
                </button>

                <button type="button" class="btn btn-outline btn-default" id="resumeUser">
                    <i class="fa fa-unlock" aria-hidden="true"></i>恢复用户
                </button>

                <button type="button" class="btn btn-outline btn-default" id="sendToEmail">
                    <i class="glyphicon  glyphicon-pencil" aria-hidden="true"></i>通知缴款
                </button>
            </div>
        </div>
    <!-- Example Pagination -->
    <div class="example-wrap">
        <div>
            <table id="userTable">
            </table>
        </div>
    </div>
    <!-- End Example Pagination -->
</div>
    <div class="modal inmodal" id="detailDig" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <h4 class="modal-title">用户信息</h4>
                </div>
                <div class="modal-body">
                    <div class="span12">
                        <div class="row">
                            <div class="col-sm-2 control-label">姓名
                            </div>
                            <div class="col-sm-4">
                                <input type="text"  id="editName" class="form-control" />
                            </div>
                            <div class="col-sm-2">电话
                            </div>
                            <div class="col-sm-4">
                                <input type="text" id="editPhone" class="form-control" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 control-label">账号
                        </div>
                            <div class="col-sm-4">
                                <input type="text" id="editAccount" class="form-control" readonly="readonly"/>
                            </div>
                            <div class="col-sm-2 control-label">身份证
                            </div>
                            <div class="col-sm-4">
                                <input type="text" id="editIdCard" class="form-control" readonly="readonly"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="resetPassword" type="button" class="btn btn-white" >重置用户密码</button>
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">保存</button>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="${basePath}/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${basePath}/resources/js/content.min.js?v=1.0.0"></script>
<script src="${basePath}/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${basePath}/resources/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${basePath}/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script src="${basePath}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/js/usermanage.js" ></script>
</body>
</html>
