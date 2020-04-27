<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath }/static/tablePlug/tablePlug.css" type="text/css"/>--%>
</head>

<script type="text/html" id="toolbarDemo">

    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
            </div>
        </div>
    </div>


</script>
<!--导出表 不展示-->
<div style="display: none;">
    <table id="data_export" lay-filter="data_export" >
    </table>
</div>

<div class="demoTable" style="white-space: nowrap">
    <div class="layui-inline">
        <button class="layui-btn layui-btn-sm" id="newInBill">添加入库</button>
    </div>
    时间范围：
    <div class="layui-inline">
        <input type="text" class="layui-input" id="timeRange" placeholder="请选择时间段">
    </div>
    物品名：
    <div class="layui-inline">
        <input type="text" class="layui-input" id="itemName" placeholder="请输入物品名">
    </div>
    发票号：
    <div class="layui-inline">
        <input type="text" class="layui-input" id="Invoice" placeholder="请输入发票号">
    </div>
    入库人：
    <div class="layui-inline">
        <input type="text" class="layui-input" id="username" placeholder="请填写入库人">
    </div>
    审核状态：
    <div class="layui-inline layui-form">
        <select id="checkStatus" name="checkStatus" lay-verify="required" lay-search="">
            <option value="">请选择审核状态</option>
            <option value="1">等待审核</option>
            <option value="2">审核通过</option>
            <option value="3">审核未通过</option>
        </select>
    </div>
    <input type="button" class="layui-btn" id="search" value="搜索">
    <button class="layui-btn " id="export">导出</button>
    <%--<button class="layui-btn" data-type="reload">搜索</button>--%>
</div>

<table class="layui-hide" id="test" lay-filter="test"></table>


<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="check">审批</a>
    <a class="layui-btn layui-btn-xs" lay-event="update">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<script src="${pageContext.request.contextPath }/static/layui/layui.all.js" charset="utf-8"></script>
<%--<script src="${pageContext.request.contextPath }/static/tablePlug/tablePlug.js" charset="utf-8"></script>--%>

<script>

    layui.config({
        base: '${pageContext.request.contextPath }/static/tablePlug/'
    }).extend({ //设定组件别名
        tablePlug: 'tablePlug'
    });
    layui.use(['table', 'layer', 'tablePlug'], function () {
        <%--layui.config({base:'${pageContext.request.contextPath }/static/tablePlug/'}).use(['table','tablePlug'], function(){--%>
        var table = layui.table;

        var tablePlug = layui.tablePlug;
        tablePlug.smartReload.enable(true);//处理不闪动的关键代码
        table.render({
            elem: '#test'
            , url: "${pageContext.request.contextPath }/inBill/inBillShowData"
            , toolbar: '#toolbarDemo'
            , title: '入库单'
            , totalRow: true//开启合计行
            , cols: [[
                {field: 'inBillID', title: '入库单号',  sort: true}
                , {field: 'invoiceID', title: '发票号'}
                , {field: 'timeIn', title: '日期'}
                , {field: 'itemsName', title: '物品名称'}
                , {field: 'type', title: '物品规格'}
                , {field: 'storePosition', title: '仓库位置'}
                , {field: 'itemNum', title: '入库数量'}
                , {field: 'itemPrice', title: '入库单价'}
                , {field: 'itemTotal', title: '合计'}
                // , {field: 'allTotal', title: '合计金额'}
                , {field: 'taxTotal', title: '含税金额',totalRow: true}
                , {field: 'userName', title: '入库人'}
                , {
                    field: 'checkStatus', title: '审核状态', templet: function (d) {
                        if (d.checkStatus == 0) return '<span>等待审核</span>'
                        else if (d.checkStatus == 1) return '<span style="color: #009688;">审核通过</span>'
                        else if (d.checkStatus == 2) return '<span style="color: #FF5722;">审核未通过</span>'
                    }
                }
                , {field: 'note', title: '备注'}
                , {field: 'invoiceTime', title: '发票时间'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
            , where: {"time1": null, "time2": null, "itemName": null,"Invoice": null, "username": null, "checkStatus": null}
            , page: true
            , limit: 10
            , limits: [5, 10, 20, 30, 50]
            , smartReloadModel: true
            , id: 'testInBill'
        });

        $('body').on('click', "#search", function () {
            // 搜索条件
            var time1 = null;
            var time2 = null;
            var itemName = null;
            var Invoice = null;
            var username = null;
            var timeRange = $('#timeRange').val();
            var checkStatus = $("#checkStatus").val();
            if (($("#itemName").val()) != null && ($("#itemName").val()) != "") {
                itemName = $("#itemName").val();
            }
            if (($("#Invoice").val()) != null && ($("#Invoice").val()) != "") {
                Invoice = $("#Invoice").val();
            }

            if (($("#username").val()) != null && ($("#username").val()) != "") {
                username = $("#username").val();
            }
            if (timeRange != null && timeRange != "") {
                time1 = timeRange.substring(0, 10);
                time2 = timeRange.substring(13, 23);
            }
            console.log(time1 + " " + time2 + " " + itemName)
            table.reload('testInBill', {
                method: 'post'
                , where: {
                    "time1": time1,
                    "time2": time2,
                    "itemName": itemName,
                    "Invoice": Invoice,
                    "username": username,
                    "checkStatus": checkStatus,
                }
                , page: {
                    curr: 1
                }
            });
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                //日期范围
                //日期时间范围
                laydate.render({
                    elem: '#timeRange'
                    , type: 'date'
                    , range: true
                });
            });
        });
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            var value = obj.value;
            if (layEvent === 'update') { //编辑
                window.InBillID = data.inBillID;
                layer.open({
                    type: 2,
                    title: "修改入库",
                    content: '${pageContext.request.contextPath }/itemIn/updateInBill?inBillID=' + data.inBillID,
                    area: ['1500px', '668px'],
                    end: function () {
                        table.reload('testInBill');
                        table.reload('exportTable');
                    }
                });
            } else if (layEvent === 'check') { //审批
                // layer.alert(data.inBillID);
                window.InBillID = data.inBillID;

                var checklayer = layer.open({
                    type: 2,
                    title: "入库审核",
                    content: '${pageContext.request.contextPath }/inBill/checkInBill',
                    area: ['1500px', '668px'],
                    end: function () {
                        $.ajax({
                            url: '${pageContext.request.contextPath }/inBill/getCheckMessageByID'
                            , data: {"InBillID": data.inBillID}
                            , success: function (data) {
                                /*obj.update({
                                    checkStatus:data.checkStatus
                                });*/
                                table.reload('testInBill');
                                table.reload('exportTable');
                            },
                            error: function () {
                                layer.msg("审核失败！");
                            }
                        });
                    }
                });

            } else if (layEvent === 'del') { //删除
                layer.confirm('确定删除吗？', {
                    btn: ['确定', '取消'] //可以无限个按钮
                    , btn1: function (index, layero) {
                        $.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath }/inBill/checkInAmountbiggerOutAmonutForDel",
                            data: {"ItemsInID": data.itemsInID},
                            success: function (htq) {
                                if (htq == 1) {
                                    $.ajax({
                                        url: '${pageContext.request.contextPath }/itemIn/delItem'
                                        , data: {"ItemsInID": data.itemsInID, "inBillID": data.inBillID},
                                        success: function () {
                                            // location.reload();
                                            table.reload('testInBill');
                                            table.reload('exportTable');
                                            layer.close(index);
                                        },
                                        error: function () {
                                            layer.msg("删除失败！");
                                        }
                                    });
                                } else {
                                    layer.msg("入库数小于出库数！删除失败！");
                                }
                            },
                            error: function () {
                                layer.alert("删除失败！");
                            }
                        });

                    }
                    , btn2: function (index, layero) {
                        layer.close();
                    }
                });
            }
        });
    });

    layui.use(['form', 'table', 'layer', 'tablePlug'], function () {
        var table = layui.table,
            form = layui.form,
            layer = layui.layer;

        $("#newInBill").on("click", function () {
            layer.open({
                type: 2,
                title: "添加入库",
                content: '${pageContext.request.contextPath }/inBill/addInBill',
                area: ['1500px', '668px'],
                end: function () {
                    table.reload('testInBill');
                    table.reload('exportTable');
                }
            });
        });

        layui.use('laydate', function () {
            var laydate = layui.laydate;
            //日期范围
            //日期时间范围
            laydate.render({
                elem: '#timeRange'
                , type: 'date'
                , range: true
            });
        });
        //导出表格
        var exportData = null;
        var tablePlug = layui.tablePlug;
        tablePlug.smartReload.enable(true);//处理不闪动的关键代码
        var ins1 = table.render({
            elem: '#data_export',
            url: "${pageContext.request.contextPath }/inBill/InBillPrint",
            method: 'post',
            title: '入库单',
            smartReloadModel: true,
            cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'inBillID', title: '序号', fixed: 'left', sort: true}
                , {field: 'invoiceID', title: '发票号'}
                , {field: 'timeIn', title: '日期'}
                , {field: 'itemsName', title: '物品名称'}
                , {field: 'type', title: '物品规格'}
                , {field: 'storePosition', title: '仓库位置'}
                , {field: 'itemNum', title: '入库数量'}
                , {field: 'itemPrice', title: '入库单价'}
                , {field: 'itemTotal', title: '合计'}
                , {field: 'taxTotal', title: '含税金额'}
                , {field: 'userName', title: '入库人'}
                , {
                    field: 'checkStatus', title: '审核状态', templet: function (d) {
                        if (d.checkStatus == 0) return '<span>等待审核</span>'
                        else if (d.checkStatus == 1) return '<span style="color: #009688;">审核通过</span>'
                        else if (d.checkStatus == 2) return '<span style="color: #FF5722;">审核未通过</span>'
                    }
                }
                , {field: 'note', title: '备注'}
                , {field: 'invoiceTime', title: '发票时间'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
            , id: 'exportTable'
            , done: function (res, curr, count) {
                exportData = res.data;
            }
        });
        //导出按钮
        /* $(".export").click(function () {
             table.exportFile(ins1.config.id, exportData, 'xls');
         });*/

        $('body').on('click', "#export", function () {

            table.exportFile(ins1.config.id, exportData, 'xls');
        });
    })
</script>
</body>
</html>
