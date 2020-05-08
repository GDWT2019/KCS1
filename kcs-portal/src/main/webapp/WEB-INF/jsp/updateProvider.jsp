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


    <div class="layui-form"  lay-filter="layuiFromProvider"  >
        <div class="layui-form-item">
            <label class="layui-form-label">供应商名称</label>
            <div class="layui-input-inline">
                <input id="providerName"  type="text" name="providerName" lay-verify="required" lay-reqtext="不能为空" placeholder="供应商名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">供应商地址</label>
            <div class="layui-input-inline">
                <input id="providerAddress"  type="text" name="providerAddress" lay-verify="required" lay-reqtext="不能为空" placeholder="供应商地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">手机/固话</label>
            <div class="layui-input-inline">
                <input type="tel" id="tel" name="tel"  autocomplete="off" class="layui-input">
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

        var providerID = parent.providerID;
        //回显数据
        $.ajax({
            url: "${pageContext.request.contextPath }/provider/showUpdateProviderByID"
            , data: {providerID: providerID}
            ,type:"post"
            , success: function (data) {
                //success函数传递的data其实是字符串，要指定返回的数据类型dataType
                data = JSON.parse(data);
                $.each(data, function (i, item) {
                    //循环获取数据
                    form.val("layuiFromProvider", {
                        "providerName": item.providerName
                        , "providerAddress": item.providerAddress
                        , "tel": item.tel
                    });
                    form.render();
                });
            }
        });


        form.on('submit(update)', function(){
            var providerID = parent.providerID;
             var providerName = $("#providerName").val();
             var providerAddress =$("#providerAddress").val();
             var tel =$("#tel").val();

            $.ajax({
                url:"${pageContext.request.contextPath}/provider/updateProvider",
                type:"post",
                data:{
                    "providerID":providerID,
                    "providerName":providerName,
                    "providerAddress":providerAddress,
                    "tel":tel
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

    function reflashDepartment() {
        layui.use("form", function () {
            var $ = layui.$,
                form = layui.form;
            $("#departmentID").empty();
            $.ajax({
                type:"POST",
                url:'${pageContext.request.contextPath}/department/getDepartment',  //从数据库查询返回的是个list
                dataType: "json",
                async: false,
                cache: false,
                success: function (data) {
                    $.each(data,function(index,item){
                        $("#departmentID").append("<option value='"+item.departmentID+"'>"+item.departmentName + "</option>");//往下拉菜单里添加元素
                    })
                    form.render();//菜单渲染 把内容加载进去
                }
            });
        });
    }

    function reflashPosition() {
        layui.use("form", function () {
            var $ = layui.$,
                form = layui.form;
            $("#positionID").empty();
            $.ajax({
                type:"POST",
                url:'${pageContext.request.contextPath}/position/getPosition',  //从数据库查询返回的是个list
                dataType: "json",
                async: false,
                cache: false,
                success: function (data) {
                    $.each(data,function(index,item){
                        $("#positionID").append("<option value='"+item.positionID+"'>"+item.positionName + "</option>");//往下拉菜单里添加元素
                    })
                    form.render();//菜单渲染 把内容加载进去
                }
            });
        });
    }

    $("#loginName").change(function () {
        var loginName= $("#loginName").val();
        $.ajax({
            type:"POST",
            url:'${pageContext.request.contextPath}/user/loginNameExist',
            data:{"loginName":loginName},
            dataType: "text",
            success: function (result) {
                var data = JSON.parse(result);
                if(!data.flag){
                    layui.use('form', function () {
                        var form = layui.form
                        //自定义验证规则
                        form.verify({
                            loginName: function (value, item) {
                                if (value==data.mesg){
                                    return '该登录名已存在！'
                                }
                            }
                        });
                    })
                }
            },
            error:function () {

            }
        });
    })
</script>


</body>
</html>