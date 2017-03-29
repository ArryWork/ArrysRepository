/**
 * Created by 林强 on 2016-12-23.
 */
$(function () {

    //1.初始化Table
    initTable();
    initButton;
});

function initTable() {
    $('#carUserTable').bootstrapTable({
            pagination:true,
            height:485,
            search:true,
            toolbar:"#exampleToolbar",
            sidePagination:'server',
            pageNumber:1,
            pageSize:10,
            columns: [
                {
                    field: 'car_user_id',
                    title: '用户ID'
                },
                {
                    field: 'name',
                    title: '用户名'
                },
                {
                    field: 'account',
                    title: '用户账号'
                },
                {
                    field: 'phone_no',
                    title: '电话号码'
                },
                {
                    field: 'id_card',
                    title: '身份证'
                }],
            onLoadSuccess:function (data) {
                console.log(data);
            },
            onLoadError:function (data) {
                console.log(data);
            }
        }
    );
}
function initButton(){
    $("#chooseArea").onclick = function () {
        $.ajax({
            type : "POST",
            url : "admin/queryArea",
            dataType : 'json',
            success:function (data) {
                
            }
        });
        
    }

}
function query(){
    
}