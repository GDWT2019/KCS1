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
        <button class="layui-btn layui-btn-sm" lay-event="addUserRole">添加数据</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${pageContext.request.contextPath}/static/layui/layui.all.js" charset="utf-8"></script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:"${pageContext.request.contextPath }/role/rolePermission?roleID=${role.roleID}"
            ,toolbar: '#toolbarDemo'
            ,title: '角色权限'
            ,totalRow: true//开启合计行
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{type:'numbers',title:'序号'}
                ,{field:'roleName', title:'角色', width:150}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
            ]]
            ,page: true
            ,limit:10
            ,limits:[10,20,30]
            ,id:'testUser'
        });

        //工具栏事件
        table.on('toolbar(test)', function(obj){
            var data = obj.data;
            if(obj.event = 'addUserRole'){
                layer.open({
                    type:2,
                    title:"添加权限",
                    content:'${pageContext.request.contextPath }/role/showAddRolePermission?roleID='+${role.roleID},
                    area:['500px','300px'],
                    moveOut:true,
                    end:function () {
                        location.reload();
                    }
                });
            }
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //删除
            if(obj.event === 'del'){
                layer.confirm('真的删除？', function(index){
                    $.ajax({
                        url:"${pageContext.request.contextPath}/role/delRolePermission",
                        title:"删除角色权限",
                        type:"post",
                        data:{"roleID":data.roleID,"PermissionID":data.permissionID},
                        dataType:"text",
                        success:function (result) {
                            var ajaxResult = JSON.parse(result);
                            if (ajaxResult){
                                layer.confirm(ajaxResult.mesg);
                                obj.del();
                            }else{
                                layer.confirm(ajaxResult.mesg);
                            }
                            layer.close(index);
                        },
                        error:function () {
                            layer.confirm("删除请求错误！");
                            layer.close(index);
                        }
                    })
                });
            }
        });
    });
</script>
</body>
</html>
