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
    <%--<div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addRoleData">添加角色</button>
    </div>--%>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${pageContext.request.contextPath}/static/layui/layui.all.js" charset="utf-8"></script>

<script>

    layui.config({
        base: '${pageContext.request.contextPath }/static/tablePlug/'
    }).extend({ //设定组件别名
        tablePlug: 'tablePlug'
    });

    layui.use(['table', 'tablePlug'], function(){
        var table = layui.table;
        var tablePlug = layui.tablePlug;
        tablePlug.smartReload.enable(true);//处理不闪动的关键代码
        table.render({
            elem: '#test'
            ,url:"${pageContext.request.contextPath }/provider/providerData"
            ,toolbar: '#toolbarDemo'
            ,title: '供应商数据表'
            ,totalRow: false//开启合计行
            ,cols: [[
                {type:'numbers',title:'序号'}
                ,{field:'providerName', title:'供应商名'}
                ,{field:'providerAddress', title:'供应商地址'}
                ,{field:'tel', title:'供应商电话'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
            ,page: true
            ,limit:10
            ,limits:[5,10,20,30]
            ,smartReloadModel: true
            ,id:'testProvider'
        });


        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //编辑
            if(obj.event === 'edit'){
                window.providerID=data.providerID;
                layer.open({
                    type:2,
                    title:"修改供应商",
                    content:'${pageContext.request.contextPath}/provider/showUpdateProvider',
                    area:['500px','300px'],
                    moveOut:true,
                    end:function () {
                        table.reload('testProvider');
                    }
                });

                //删除数据！！
            } else if(obj.event === 'del'){
                layer.confirm('是否确认删除？', function(index){
                    $.ajax({
                        url:"${pageContext.request.contextPath}/provider/delProvider",
                        title:"删除供应商",
                        type:"post",
                        data:{"providerID":data.providerID},
                        dataType:"text",
                        success:function (result) {
                            var ajaxResult = JSON.parse(result);
                            if (ajaxResult.flag){
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
