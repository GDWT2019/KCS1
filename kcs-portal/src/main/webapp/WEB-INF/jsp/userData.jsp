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

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">添加数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">修改数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<script src="${pageContext.request.contextPath}/static/layui/layui.all.js" charset="utf-8"></script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:"${pageContext.request.contextPath }/user/userData"
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,totalRow: true//开启合计行
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'userID', title:'ID', width:80, fixed: 'left', unresize: true, sort: true, totalRowText: '合计'}
                ,{field:'positionID', title:'职位ID', width:120, edit: 'text'}
                ,{field:'departmentID', title:'部门ID', width:120, edit: 'text'}
                ,{field:'loginName', title:'登录名', width:120, edit: 'text'}
                ,{field:'sex', title:'性别', width:120, edit: 'text',templet:function (d) {
                        if(d.sex==true) return '男'
                        else if(d.sex ==false) return '女'
                    }}
                ,{field:'userName', title:'用户名', width:120, edit: 'text'}
                ,{field:'password', title:'密码', width:120, edit: 'text'}
                ,{field:'tel', title:'电话', width:120, edit: 'text'}
                ,{field:'email', title:'邮箱', width:150, edit: 'text', templet: function(res){
                        return '<em>'+ res.email +'</em>'
                    }}
                ,{field:'photo', title:'头像', width:120, edit: 'text'}
                ,{field:'note', title:'备注', width:120, edit: 'text'}
                ,{field:'warehouseMark', title:'仓管员标记', width:120, edit: 'text'}
                ,{field:'listerMark', title:'制表人标记', width:120, edit: 'text'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: false
            /*,limit:10
            ,limits:[10,20,30]*/
            ,id:'testUser'
        });

        //工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选')
                    break;
            };
        });
    });
</script>
</body>
</html>
