/**
 * Created by 林强 on 2016-12-23.
 */
$(function () {

    //1.初始化Table
    initTable();
    //2.初始化button的点击事件
    initBtn();
});

function initTable() {
    $('#userTable').bootstrapTable({
            url:'queryUser.do',
            pagination:true,
            height:485,
            singleSelect:true,
            clickToSelect: true,
            columns: [
                {
                    radio:true
                },
                {
                    field:'status',
                    title:"用户状态",
                    formatter:function (value,data,index) {
                        if(value =="1")
                            return "<span style = \"color: green\">正常</span>";
                        if(value =="0")
                            return "<span style = \"color: blue\">在停</span>";
                        if(value == "3")
                            return "<span style = \"color: red\">冻结</span>";
                    }
                },
                {
                    field: 'username',
                    title: '用户名'
                },
                {
                    field: 'account',
                    title: '用户账号'

                },
                {
                    field: 'phone',
                    title: '电话号码'
                },
                {
                    field: 'idcard',
                    title: '身份证'
                },{
                    field: 'credit',
                    title: '信用'
                },{
                    field: 'debt',
                    title: '欠款'
                },{
                    field:'balance',
                    title:'余额'
                }],
            pageNumber:1,
            pageSize:10,
            onLoadSuccess:function (data) {
            }
        }
    );
}

function initBtn() {
    $('#detailsBtn').click(function () {
        $('#detailDig').modal('show');
    });
    $("#frozeUser").click(freezeUser);
    $("#resumeUser").click(resumeUser);
    $("#sendToEmail").click(sendToMsg);
    $("#editBtn").click(showEditModal);
    $("#resetPassword").click(resetPassword);
}
function freezeUser() {
    var user = $("#userTable").bootstrapTable("getSelections");
    if(user==null)
        swal("提示","请选择一条记录","warning");
    else if(user!=null) {
        if(user[0].status=="3") swal("提示","该用户已被冻结","warning");
        else {
            swal({title: "提示", text: "是否冻结所选用户", closeOnConfirm: false}, function () {
                $.ajax({
                    url: "freezeUser.do",
                    type: "post",
                    async: false,
                    data: {
                        userid: user[0].fid
                    },
                    success: function (data) {
                        data = JSON.parse(data);
                        $("#userTable").bootstrapTable("refresh");
                    }
                });
                swal("冻结成功", "所选用户已冻结成功", "success");
            })
        }
    }
}

function resumeUser() {
    var user = $("#userTable").bootstrapTable("getSelections");
    if(user==null)
        swal("提示","请选择一条记录","warning");
    else if(user[0].status !=3){
        swal("提示","该用户未被冻结");
    }else{
        swal({title: "提示", text: "是否恢复所选用户", closeOnConfirm: false}, function () {
            $.ajax({
                url: "resumeUser.do",
                type: "post",
                async: false,
                data: {
                    userid: user[0].fid
                },
                success: function (data) {
                    data = JSON.parse(data);
                    $("#userTable").bootstrapTable("refresh");
                }
            });
            swal("恢复成功", "所选用户已恢复", "success");
        })
    }

}

function sendToMsg() {
    var user = $("#userTable").bootstrapTable("getSelections");
    if(user==null)
        swal("提示","请选择一条记录","warning");
    else{
        swal({title: "提示", text: "是否通知该用户", closeOnConfirm: false}, function () {
            $.ajax({
                url: "sendEmail.do",
                type: "post",
                async: false,
                data: {
                    userEmail: user[0].account,
                    userName:user[0].username,
                    dept:user[0].debt,
                    balance:user[0].balance
                },
                success: function (data) {
                    data = JSON.parse(data);
                    $("#userTable").bootstrapTable("refresh");
                }
            });
            swal("通知成功", "通知成功", "success");
        })
    }
}

function showEditModal() {
    var user = $("#userTable").bootstrapTable("getSelections")[0];
    if(user == null)
        swal("warning","请选择一个用户","warning");
    else{
        $("#editName").val(user.username);
        $("#editPhone").val(user.phone);
        $("#editAccount").val(user.account);
        $("#editIdCard").val(user.idcard);
        $("#detailDig").modal("show");
    }
}

function resetPassword(userId,email) {
    var user = $("#userTable").bootstrapTable("getSelections")[0];
    swal({title: "提示", text: "是否重置用户密码", closeOnConfirm: false}, function () {
        $.ajax({
            url: "resetUserPassword.do",
            type: "post",
            async: false,
            data: {
                userId: user.fid,
                mail: user.account
            },
            success: function (data) {
                data = JSON.parse(data);
                $("#userTable").bootstrapTable("refresh");
            }
        });
        swal("修改成功", "所选用户已修改成功", "success");
    })

}