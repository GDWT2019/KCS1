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
        <div class="layui-inline">
            时间范围：
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="test10" placeholder="请选择时间段" style="width: 180%">
            </div>
        </div>

        <div class="layui-inline" style="margin-left: 200px;">
            人员：
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="name"  placeholder="请输入登录名">
            </div>
        </div>

        <div class="layui-inline" style="margin-left: 50px;">
            <div class="layui-input-inline">
                <input type="button" class="layui-btn"  id="search" value="搜索">
            </div>
        </div>
</script>
<%--<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>--%>

<script src="${pageContext.request.contextPath}/static/layui/layui.all.js" charset="utf-8"></script>

<script>
    layui.use(['table','form'], function(){
        var table = layui.table;
        var form =layui.form;

        table.render({
            elem: '#test'
            ,url: '${pageContext.request.contextPath }/log/findAllLog'
            ,toolbar: '#toolbarDemo'
            ,title: '日志'
            ,cols: [[
                {type:'numbers',title:'序号'}
                ,{field:'logID', title:'日志ID',sort:true}
                ,{field:'userID', title:'用户ID',sort:true}
                ,{field:'userName', title:'用户名',sort:true}
                ,{field:'loginName', title:'登录名',sort:true}
                ,{field:'time', title:'时间',sort:true}
                ,{field:'operation', title:'操作'}
                ,{field:'result', title:'结果'}
            ]]
            ,where: {"time1":null,"time2":null,"name":null}
            ,page: true
            ,limit:10
            ,limits:[5,10,20,30,50]
            ,id:'logTable'
        });

        $('body').on('click',"#search",function () {
            // 搜索条件
            var time1 = null;
            var time2 = null;
            var name = null;
            var timeRange=$("#test10").val();
            if (timeRange!=null || timeRange !=""){
                time1 = timeRange.substring(0,19);
                time2 = timeRange.substring(21,41);
            }
            if (($("#name").val()) != null && ($("#name").val()) != "") {
                name = $('#name').val();
            }
            table.reload('logTable', {
                method: 'post'
                ,where: {"time1":time1,"time2":time2,"name":name} //设定异步数据接口的额外参数
                , page: {
                    curr: 1
                }
            });
            layui.use('laydate', function() {
                var laydate = layui.laydate;
                //日期范围
                //日期时间范围
                laydate.render({
                    elem: '#test10'
                    ,type: 'datetime'
                    ,range: true
                });
            });
            $("#test10").val(timeRange);
            $('#name').val(name);
        });


        /*//删除数据！！
           if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url:"/user/delUserByUserID",
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
                            table.reload('testUser', {
                                method: 'post'
                                , where: {
                                    "name": null,
                                }
                                , page: {
                                    curr: 1
                                }
                            });
                            layer.close(index);
                        },
                        error:function () {
                            layer.confirm("删除请求错误！");
                            layer.close(index);
                        }
                    })
                });
           }*/
    });
    layui.use('laydate', function() {
        var laydate = layui.laydate;
        //日期范围
        //日期时间范围
        laydate.render({
            elem: '#test10'
            ,type: 'datetime'
            ,range: true
        });
    });
</script>
</body>
</html>
