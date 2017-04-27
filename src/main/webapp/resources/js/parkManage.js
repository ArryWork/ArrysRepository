
$(function () {
    initTable();
    $("#parkDetail").click(showParkDetail);
    $("#alterBtn").click(alterParkinglot);
    $("#addBtn").click(showAddModal);
    $("#longtitude").click(chooseLocation);
    $("#latitude").click(chooseLocation);
    $("#addParkBtn").click(addParkinglot);
    initMap();
});

function initTable() {
    $('#parkingLotTable').bootstrapTable({
            url: '/park/showParkinglot.do',
            pagination: true,
            height: 485,
            width:2000,
            method:"POST",
            contentType : "application/x-www-form-urlencoded",
            singleSelect: true,
            clickToSelect: true,
            queryParams:{areaid:$("#adminAreaid").val()},
            columns: [
                {
                    radio: true
                },
                {
                    field: 'fid',
                    title: '停车场ID'
                },
                {
                    field: 'detailaddress',
                    title: '停车场名称'
                },
                {
                    field:"area",
                    title:'所在区域'
                },
                {
                    field:"spacenumber",
                    title:'车位总数'
                },{
                    field:"availablespace",
                    title:"当前可用数量"
                }
            ],
            pageNumber: 1,
            pageSize: 10,
            onLoadSuccess: function (data) {
            }
        }
    );
}
function showParkDetail() {
    var parkinglot = $("#parkingLotTable").bootstrapTable("getSelections")[0];
    if(parkinglot==null)
        swal("提示","请选择一条记录","warning");
    else {
        $("#parkName").val(parkinglot.detailaddress);
        $("#parkArea").val(parkinglot.area);
        $("#spaceNumber").val(parkinglot.spacenumber);
        $("#avaliableSpace").val(parkinglot.availablespace);
        $("#parkId").val(parkinglot.fid);
        var longtitude = parkinglot.longtitude;
        var latitude = parkinglot.latitude;
        var mapImg = $("<img style=\"margin:20px\" width=\"750\" height=\"290\" src=\"http://api.map.baidu.com/staticimage/v2?ak=zSoKpoAGCKY235hHSpGOUdscASAn0R35&width=750&height=290&zoom=17&scale=1&center=" + longtitude + "," + latitude + "&markers=" + longtitude + "," + latitude + "\"/>");
        $("#detailMap").html("");
        $("#detailMap").append(mapImg);
        $("#myModal").modal("show");
    }
}

function alterParkinglot() {
    var spaceNumber = $("#spaceNumber").val();
    var parkId = $("#parkId").val();
    $.ajax({
        url:"/park/updateParkNum.do",
        async:false,
        type:"post",
        data:{
            spaceNumber:spaceNumber,
            parkId:parkId
        },
        success:function (data) {
            if(data.error!=null){
                swal("错误",data.error,"error");
            }else{
                swal("成功",data.msg,"success");
                $("#parkingLotTable").bootstrapTable("refresh");
                $("#myModal").modal("hide");
            }
        }
    });
}

function showAddModal() {
    $("#addModal").modal("show");
}

function chooseLocation() {
    $("#mapModal").modal("show");
}
function initMap(){
    var map = new BMap.Map("locationMap");
    map.centerAndZoom(new BMap.Point(119.246319,26.090365), 17);
    map.addEventListener("click", function(e){
        swal({title: "提示", text: "确定新停车场的位置在这吗", closeOnConfirm: true}, function () {
            $("#longtitude").val(e.point.lng);
            $("#latitude").val(e.point.lat);
            $("#mapModal").modal("hide");
        })

    });
}

function addParkinglot() {
    swal({title: "提示", text: "确定新增该停车位吗？", closeOnConfirm: false}, function () {
        $.ajax({
            url:"/park/addParkinglot.do",
            async:false,
            type:"post",
            data:$("#addParkForm").serialize(),
            dataType:"json",
            success:function (data) {
                if(data.error!=null){
                    swal("错误",data.error,"error");
                }else{
                    swal("成功",data.msg,"success");
                    $("#parkingLotTable").bootstrapTable("refresh");
                    $("#addModal").modal("hide");
                }
            }
        });
    })
}