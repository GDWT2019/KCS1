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
            ,url:"${pageContext.request.contextPath }/outBill/ItemOutRecord"
            ,title: '出库单'
            ,totalRow: true//开启合计行
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{type:'numbers',title:'序号'}
                ,{field:'outBillID', title:'单号', width:80,sort:true}
                ,{field:'outTime', title:'日期', width:110,sort:true}
                ,{field:'itemsName', title:'物品名称', width:110}
                ,{field:'itemsType', title:'物品规格', width:100}
                ,{field:'storePosition', title:'位置', width:60}
                ,{field:'itemNum', title:'出库数量',sort:true, width:110}
                ,{field:'takerName', title:'领用人', width:100}
                ,{field:'remark', title:'备注', width:120}
                ,{field:'checkStatus', title:'审批状态',sort:true, width:120,templet:function (d) {
                        if(d.checkStatus==0) return '待审批';
                        else if(d.checkStatus ==1) return '<span style="color: #009688;">审核通过</span>';
                        else if(d.checkStatus == 2) return '<span style="color: #FF5722;">审核未通过</span>';
                        else return '审批错误'
                    }}
                ,{field:'checkerName', title:'审批人', width:120}
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
