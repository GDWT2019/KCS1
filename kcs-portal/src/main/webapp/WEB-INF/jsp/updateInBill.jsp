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
                <form class="layui-form" id="InBillForm" type="post">
                    <input type="hidden" name="operator" value="${user.userID}">
                    <div class="layui-layout-left" style="margin-top: 30px;margin-left: -170px;">
                        <span style="font-size: 25px;">时间：</span>
                        <div class="layui-inline">
                            <input id="InBillTime" type="text" readonly name="InBillTime" style="font-size: 25px;"
                                   value=""/>
                        </div>
                    </div>
                    <div style="text-align: center;margin-top: 30px;">
                        <span style=" margin-top: 30px;font-size: 25px;">供应商：</span>
                        <div class="layui-inline">
                            <select id="providerID" name="providerID" lay-verify="required" lay-search="">
                            </select>
                        </div>
                    </div>
                    <div class="layui-layout-right" style="margin-top: 30px;margin-right: 40px;">
                    <span style="font-size: 25px;">编号：</span>
                    <div class="layui-inline">
                    <input id="InBillID" type="text" class="layui-input" name="InBillID" autocomplete="on" readonly>
                    </div>
                    </div>
                    <div class="layui-bg-gray" style="margin-top:50px;padding:10px;">
                        <div class="layui-table">
                            <table class="layui-hide" id="test" lay-filter="test"></table>
                            <%--<div style="text-align: center;">
                                <div class="layui-inline">
                                    <button onclick="addTr()" type="button" class="layui-btn ">
                                        <i class="layui-icon">&#xe608;</i> 添加
                                    </button>
                                    <button onclick="delTr()" type="button" class="layui-btn  ">
                                        <i class="layui-icon">&#xe640;</i>删除
                                    </button>
                                </div>
                            </div>--%>
                            <div class="layui-row" style="margin: 10px 5px;">
                                <span style="font-size: 22px;">合计：<input id="alTotal" name="alTotal" style="font-size: 22px;" readonly></input>元</span>
                            </div>
                        </div>
                        <div class="layui-bg-gray" style="border-radius: 2px;">
                            <div class="layui-row">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">仓管员：</label>
                                    <div class="layui-input-inline">
                                        <select id="warehouse" name="warehouse" lay-verify="required" lay-search="">

                                        </select>
                                    </div>
                                    <label class="layui-form-label">采购人：</label>
                                    <div class="layui-input-inline">
                                        <select id="buyer" name="buyer" lay-verify="required" lay-search="">

                                        </select>
                                    </div>
                                </div>
                                <div class="layui-row">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">审批人：</label>
                                        <div class="layui-input-inline">
                                            <select id="Approvaler" name="Approvaler" lay-verify="required"
                                                    lay-search="">

                                            </select>
                                        </div>
                                        <label class="layui-form-label">制表人：</label>
                                        <div class="layui-input-inline">
                                            <select id="lister" name="lister" lay-verify="required" lay-search="">

                                            </select>
                                        </div>
                                    </div>
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
            完成修改
        </button>
    </div>
</div>
<script>
    $("#InBillID").val(parent.InBillID);



    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#test'
            , url: "${pageContext.request.contextPath }/itemIn/itemsInData"
            // , toolbar: '#toolbarDemo'
            , title: '入库单'
            , totalRow: true//开启合计行
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'inBillID',title: '商品号',width: 110,fixed: 'left',unresize: true,sort: true,totalRowText: '合计'}
                , {field: 'goodsID', title: '物品名称', width: 110,templet(d){
                        var itemName;
                        $.ajax({
                            method:'post',
                            url:'${pageContext.request.contextPath }/inBill/findGoodsByGoodsID',
                            data:{"goodsID":d.goodsID},
                            async: false,
                            success:function (htq) {
                                console.log(htq[0].goodsID);
                                console.log(htq[0].itemsName);
                                if(d.goodsID==htq[0].goodsID){
                                    itemName= htq[0].itemsName;
                                }
                            }
                        });
                        return itemName;
                    }}
                , {field: 'categoryID', title: '物品类别', width: 110}
                , {field: 'type', title: '物品规格', width: 110}
                , {field: 'itemNum', title: '入库数量', width: 110}
                , {field: 'itemPrice', title: '入库单价', width: 80}
                , {field: 'itemTotal', title: '合计', width: 110}
                , {field: 'storePosition', title: '仓库位置', width: 110}
                , {field: 'note', title: '附注', width: 120}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 180}
            ]]
            , page: false
            , id: 'testInBill'
            ,where :{"InBillID":parent.InBillID}
        });
    });
</script>
<script>
    //将用户的ID和供应商ID和时间回显
    $.ajax({
        method:'post',
        url:"${pageContext.request.contextPath }/itemIn/valueIDandTime",
        data:{"InBillID":parent.InBillID},
        success:function (htq) {
            console.log(htq);
            console.log(htq[0].buyer);
            $("#buyer").val(htq[0].buyer);
            $("#warehouse").val(htq[0].storeManager);
            $("#Approvaler").val(htq[0].checker);
            $("#lister").val(htq[0].tableMaker);
            $("#InBillTime").val(htq[0].buyTime);
            $("#providerID").val(htq[0].providerID);
            $("#alTotal").val(htq[0].allTotal);
        }
    });

    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/provider/getProvider',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#providerID").append("<option value='" + item.providerID + "'>" + item.providerName + "</option>");//往下拉菜单里添加元素
            })
            form.render();//菜单渲染 把内容加载进去
        }
    });

    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/user/getWarehouse',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#warehouse").append("<option value='" + item.userID + "'>" + item.userName + "</option>");//往下拉菜单里添加元素
            })
            form.render();//菜单渲染 把内容加载进去
        }
    });

    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/user/getAlluser',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#buyer").append("<option value='" + item.userID + "'>" + item.userName + "</option>");//往下拉菜单里添加元素
            })
            form.render();//菜单渲染 把内容加载进去
        }
    });

    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/user/getAlluser',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#Approvaler").append("<option value='" + item.userID + "'>" + item.userName + "</option>");//往下拉菜单里添加元素
            })
            form.render();//菜单渲染 把内容加载进去
        }
    });

    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/user/getAlllister',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#lister").append("<option value='" + item.userID + "'>" + item.userName + "</option>");//往下拉菜单里添加元素
            });
            form.render();//菜单渲染 把内容加载进去
        }
    });
</script>
</body>

</html>
