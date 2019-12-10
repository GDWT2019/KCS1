<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
</head>
<body>
<div class="">
    <div class="layui-col-lg12" style="padding: 10px 10px;">
        <div class=" layui-col-lg12" style="padding: 10px 15px; border-radius: 5px;">
            <div class="layui-col-lg12" style="text-align: center; font-size: 30px;"><span>入库单</span></div>
            <div class="layui-col-lg12 " style="margin:30px 0;padding:10px;border-radius: 5px;">
                <form class="layui-form" id="InBillForm" action="getInBillData" type="post">
                    <input type="hidden" name="operator" value="${user.userID}">
                    <div class="layui-layout-left" style="margin-top: 30px;margin-left: -170px;">
                        <span style="font-size: 25px;">时间：</span>
                        <div class="layui-inline">
                            <input id="InBillTime" type="text" class="layui-input" readonly name="InBillTime" name="InBillTime"
                                   style="font-size: 25px;" value="${loadtime}"/>
                        </div>
                    </div>
                    <<%--div style="text-align: center;margin-top: 30px;">
                        <span style=" margin-top: 30px;font-size: 25px;">审批人：</span>
                        <div class="layui-inline">
                            <select id="providerID" name="providerID" lay-verify="required" lay-search="">
                            </select>
                        </div>
                    </div>--%>
                    <div class="layui-layout-right" style="margin-top: 30px;margin-right: 40px;">
                        <span style="font-size: 25px;">编号：</span>
                        <div class="layui-inline">
                            <input readonly id="InBillID" type="text" class="layui-input InBillID" name="InBillID" autocomplete="on" value="">
                        </div>
                    </div>
                    <div class="layui-bg-gray" style="margin-top:50px;padding:10px;">
                        <div class="layui-table">

                            <table class="layui-hide" id="test" lay-filter="test"></table>

                            <div class="layui-row" style="margin: 10px 5px;">
                                <label class="layui-form-label">合计：</label>
                                <div class="layui-input-inline">
                                    <input id="alTotal" name="alTotal" class="layui-input" type="text"
                                           readonly="readonly"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="layui-layout-right" style="margin-top: 30px;margin-right: 40px;">
        <button onclick="addBill()" class="layui-btn layui-btn-radius layui-btn-normal" style="margin-right: 40px;">
            一键审核
        </button>
    </div>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">审核</a>
</script>

<script>

    $("#InBillID").val(parent.InBillID);


    layui.use('table', function () {
        var table = layui.table;
        console.log(parent.PartitionId);

        table.render({
            elem: '#test'
            , url: "${pageContext.request.contextPath }/itemIn/itemsInData"
            // , toolbar: '#toolbarDemo'
            , title: '入库单'
            , totalRow: true//开启合计行
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'inBillID',title: '入库单号',width: 110,fixed: 'left',unresize: true,sort: true,totalRowText: '合计'}
                , {field: 'timeIn', title: '日期', width: 160}
                , {field: 'goodsID', title: '物品名称', width: 110}
                , {field: 'type', title: '物品规格', width: 110}
                , {field: 'itemNum', title: '入库数量', width: 110}
                , {field: 'itemPrice', title: '入库单价', width: 80}
                , {field: 'itemTotal', title: '合计', width: 110}
                , {
                    field: 'checkStatus', title: '审核状态', width: 150, templet: function (d) {
                        if (d.checkStatus == 0) return '<span>等待审核</span>'
                        else if (d.checkStatus == 1) return '<span style="color: #009688;">审核通过</span>'
                        else if (d.checkStatus == 2) return '<span style="color: #FF5722;">审核未通过</span>'
                    }
                }
                // , {field: 'allTotal', title: '合计金额', width: 110}
                , {field: 'storePosition', title: '仓库位置', width: 110}
                , {field: 'operator', title: '入库人', width: 120}
                , {field: 'checker', title: '审核人', width: 120}
                , {field: 'storeManager', title: '仓管员', width: 120}
                , {field: 'buyer', title: '采购人', width: 120}
                , {field: 'tableMaker', title: '制表人', width: 120}
                // , {field: 'note', title: '备注', width: 150}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 180}
            ]]
            , page: false
            , id: 'testInBill'
            ,where :{"InBillID":parent.InBillID}
        });
    });
</script>
</body>
</html>
