<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>
</head>
<table class="layui-hide" id="test" lay-filter="test"></table>





<script src="${pageContext.request.contextPath }/static/layui/layui.all.js" charset="utf-8"></script>

<script>

    var goodsID = parent.goodsID;
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:"${pageContext.request.contextPath }/inBill/ItemInRecord"
            ,title: '入库单'
            ,totalRow: true//开启合计行
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'inBillID', title:'入库单号', width:110, fixed: 'left', unresize: true, sort: true, totalRowText: '合计'}
                ,{field:'timeIn', title:'日期', width:160}
                ,{field:'itemsName', title:'物品名称', width:110}
                ,{field:'type', title:'物品规格', width:110}
                ,{field:'storePosition', title:'仓库位置', width:110}
                ,{field:'itemNum', title:'入库数量', width:110,edit:'text'}
                ,{field:'itemPrice', title:'入库单价', width:110,edit:'text'}
                ,{field:'itemTotal', title:'合计', width:110}
                ,{field:'userName', title:'入库人', width:120}
                ,{field:'checkStatus', title:'审核状态', width:150,templet:function (d) {
                        if(d.checkStatus==0) return  '<span>等待审核</span>'
                        else if(d.checkStatus ==1) return  '<span style="color: #009688;">审核通过</span>'
                        else if(d.checkStatus ==2) return  '<span style="color: #FF5722;">审核未通过</span>'
                    }}
                ,{field:'note', title:'备注', width:150}
            ]]
            ,where: {goodsID:goodsID}
            ,page: true
            ,limit:10
            ,limits:[5,10,20,30,50]
            ,id:'testInBill'
        });
        });



</script>
</body>
</html>
