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



    layui.use(['table', 'layer', 'tablePlug'], function(){
        var table = layui.table;
        var tablePlug = layui.tablePlug;
        tablePlug.smartReload.enable(true);//处理不闪动的关键代码
        table.render({
            elem: '#test'
            ,url:"${pageContext.request.contextPath }/goods/goodsData"
            ,toolbar: '#toolbarDemo'
            ,title: '物品数据表'
            ,totalRow: false//开启合计行
            ,cols: [[
                {type:'numbers',title:'序号'}
                ,{field:'itemsName', title:'品名'}
                ,{field:'categoryID', title:'类别',templet(d) {
                        var categoryName;
                        var categoryID = Number(d.categoryID);
                        if (!isNaN(categoryID)) {

                            $.ajax({
                                method: 'post',
                                url: '${pageContext.request.contextPath }/category/findCategoryByID',
                                data: {"categoryID": categoryID},
                                async: false,
                                success: function (htq) {
                                    console.log(htq.categoryName);
                                    if (categoryID == htq.categoryID) {
                                        categoryName = htq.categoryName;
                                    }
                                }
                            });
                            return categoryName;
                        }
                    }}
                ,{field:'itemsType', title:'规格'}
                ,{field:'itemsUnit', title:'单位'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 180}
            ]]
            ,page: true
            ,limit:10
            ,limits:[5,10,20,30]
            , smartReloadModel: true
            ,id:'testGoods'
        });



        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //编辑
            if(obj.event === 'edit'){
                window.goodsID=data.goodsID;
                layer.open({
                    type:2,
                    title:"修改物品",
                    content:'${pageContext.request.contextPath}/goods/showUpdateGoods',
                    area:['500px','300px'],
                    moveOut:true,
                    end:function () {
                        table.reload('testGoods');
                    }
                });

                //删除数据！！
            } else if(obj.event === 'del'){
                layer.confirm('是否删除？', function(index){
                    $.ajax({
                        url:"${pageContext.request.contextPath}/goods/delGoods",
                        title:"删除物品",
                        type:"post",
                        data:{"goodsID":data.goodsID},
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
            }else if (obj.event === 'detail'){
                layer.open({
                    type:2,
                    title:data.roleName+"的权限详情",
                    content:'${pageContext.request.contextPath}/role/showRolePermission?roleID='+data.roleID,
                    area:['1200px','668px'],
                    moveOut:true,
                    end:function () {
                        location.reload();
                    }
                });
            }
        });
    });
</script>
</body>
</html>
