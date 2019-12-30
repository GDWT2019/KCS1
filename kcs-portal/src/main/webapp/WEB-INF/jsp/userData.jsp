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
        <button class="layui-btn layui-btn-sm" lay-event="addUserData">添加数据</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="lock">冻结</a>
</script>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
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
                ,{type:'numbers',title:'序号'}
                ,{field:'positionID', title:'职位', width:80}
                ,{field:'departmentID', title:'部门', width:80}
                ,{field:'loginName', title:'登录名', width:80}
                ,{field:'userName', title:'用户名', width:80}
                ,{field:'tel', title:'电话', width:120}
                ,{field:'email', title:'邮箱', width:150, templet: function(res){
                        return '<em>'+ res.email +'</em>'
                    }}
                ,{field:'warehouseMark', title:'仓管员', width:80}
                ,{field:'listerMark', title:'制表人', width:80}
                ,{fixed: '', title:'角色', toolbar: '#bar', width:80}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
            ]]
            ,page: true
            ,limit:10
            ,limits:[10,20,30]
            ,id:'testUser'
        });

        //工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            if(obj.event = 'addUserData'){
                layer.open({
                    type:2,
                    title:"添加用户",
                    content:'${pageContext.request.contextPath }/user/showAddUser',
                    area:['1000px','668px'],
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
            //编辑
            if(obj.event === 'edit'){
                layer.open({
                    type:2,
                    title:"修改出库",
                    content:'${pageContext.request.contextPath}/user/showUpdateUser?userID='+data.userID,
                    area:['1200px','668px'],
                    moveOut:true,
                    end:function () {
                        location.reload();
                    }
                });

                //删除数据！！
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url:"${pageContext.request.contextPath}/user/delUserByUserID",
                        title:"删除用户",
                        type:"post",
                        data:{"userID":data.userID},
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

                //冻结！
            }else if (obj.event === 'lock'){
                $.ajax({
                    url:"${pageContext.request.contextPath}/outBill/outBillPresentByOutBillID",
                    type:"post",
                    data:{"outBillID":data.outBillID},
                    dataType:"text",
                    success:function (result) {
                        layer.open({
                            type:1,
                            content: result,
                            title:false,
                            area:['1200px','668px'],
                            end:function () {
                                location.reload();
                            }
                        })
                    },
                    error:function () {
                        layer.confirm("审批请求错误");
                        layer.close(index);
                    }
                })
            }
        });
    });
</script>
</body>
</html>
