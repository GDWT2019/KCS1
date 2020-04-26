<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
</head>
<body>


    <div class="layui-form" lay-filter="layuiFromCategory">
        <div class="layui-form-item">
            <label class="layui-form-label">类别名称</label>
            <div class="layui-input-inline">
                <input id="categoryName"  type="text" name="categoryName" lay-verify="required" lay-reqtext="不能为空" placeholder="类别名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn"  lay-submit lay-filter="update">修改</button>
            </div>
        </div>
    </div>


    <script>
        layui.use(['jquery','upload','form','layer','element', 'layedit', 'laydate'], function () {
            var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;

            var $ = layui.$,
                element = layui.element,
                upload = layui.upload;


            //日期
            laydate.render({
                elem: '#date'
            });
            laydate.render({
                elem: '#date1'
            });


            //自定义验证规则
            form.verify({

                role: function(value, item){ //value：表单的值、item：表单的DOM对象
                    if(value==""||value==null){
                        return '不能为空！'
                    }
                }
            });

            var categoryID = parent.categoryID;
            //回显数据
            $.ajax({
                url: "${pageContext.request.contextPath }/category/showUpdateCategoryByID"
                ,data: {categoryID: categoryID}
                ,type:"post"
                , success: function (data) {
                    //success函数传递的data其实是字符串，要指定返回的数据类型dataType
                    data = JSON.parse(data);
                    $.each(data, function (i, item) {
                        //循环获取数据
                        form.val("layuiFromCategory", {
                            "categoryName": item.categoryName
                        });
                        form.render();
                    });
                }
            });

            form.on('submit(update)', function(){
                var categoryID = parent.categoryID;
                var categoryName = $("#categoryName").val();
                $.ajax({
                    url:"${pageContext.request.contextPath}/category/updateCategory",
                    type:"post",
                    data:{
                        "categoryID":categoryID,
                        "categoryName":categoryName,
                    },
                    dataType:"text",
                    success:function (result) {
                        if (result!=null){
                            var data = JSON.parse(result);
                            layer.alert(data.mesg,function(){
                                window.parent.layer.closeAll();
                            });
                        }
                        else
                            alert("失败！")
                    },
                    error:function () {
                        alert("请求错误！")
                    }
                })
            });

            //表单取值
            layui.$('#LAY-component-form-getval').on('click', function () {
                var data = form.val('example');
                alert(JSON.stringify(data));
            });
        });

    </script>


</body>
</html>