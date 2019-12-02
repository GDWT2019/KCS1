<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>

</head>
<body>
<div class="layui-body" style="margin-left: -190px">
    <div class="layui-form" style="margin: 10px 200px">
        <div class="layui-form-item" style="text-align: center">
            <h1>出库单</h1>
        </div>
        <form>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">日期</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="outBillDate" placeholder="yyyy-MM-dd">
                    </div>
                </div>
            </div>

        </form>
        <table class="layui-table" id="addOutBillTable"></table>
    </div>
</div>
</body>
<script>
    layui.use('laydate', function() {
        var laydate = layui.laydate;

        //常规用法
        laydate.render({
            elem: '#outBillDate'
        });
    })

    layui.use('table', function(){
        var table = layui.table;

        //展示已知数据
        table.render({
            elem: '#addOutBillTable'
            , cols: [[ //标题栏
                {type:'numbers',title:'序号', sort: true, width: 80}
                ,{field: 'itemsName', title: '品名', width: 100}
                ,{field: 'itemsType', title: '规格', width: 100}
                ,{field: 'ItemNum', title: '数量', width: 100}
                ,{field: 'ItemPrice', title: '单价', width: 100}
                ,{field: 'ItemTotal', title: '合计', width: 100}
                ,{field: 'DepartmentName', title: '领用部门', width: 120}
                ,{field: 'Project', title: '项目', width: 150}
            ]]
            ,data:[{
                'itemsName':" ",
                'itemsType':" ",
                'ItemNum':" ",
                'ItemPrice':" ",
                'ItemTotal':" ",
                'DepartmentName':"1",
                'Project':"1",
            }]
        });
    })
</script>
</html>
