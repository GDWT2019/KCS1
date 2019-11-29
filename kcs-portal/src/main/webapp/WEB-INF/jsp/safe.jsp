<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" media="all">
</head>
<body>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>安全设置</legend>
    </fieldset>

    <form class="layui-form"  lay-filter="layuiFromUser" id="baseForm">
        <div class="layui-form-item">
            <label class="layui-form-label">登录名</label>
            <div class="layui-input-inline">
                <input readonly="readonly" type="text" name="loginName" lay-verify="required" placeholder="请输入"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-inline">
                <input  id="password" type="password" name="password" lay-verify="required" lay-reqtext="密码是必填项，岂能为空？"  lay-verify="required" placeholder="请输入新密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-inline">
                <input id="password2"  type="password" lay-verify="required" lay-reqtext="密码是必填项，岂能为空？"  lay-verify="required" placeholder="请再次输入新密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button id="bt_update" type="button" class="layui-btn" lay-submit="" lay-filter="demo1"  >立即修改</button>
            </div>
        </div>

    </form>

    <script src="${pageContext.request.contextPath }/static/layui/layui.all.js" charset="utf-8"></script>
<script>
    layui.use(["jquery", "upload", "form", "layer", "element"], function () {
        var $ = layui.$,
            element = layui.element,
            layer = layui.layer,
            upload = layui.upload,
            form = layui.form;

        //修改密码
        form.on('submit(demo1)', function(data){
            $.post("${pageContext.request.contextPath }/user/updatePass" , $("#baseForm").serialize());
            layer.alert("修改成功！");
            return false;
        });

        //提示两次密码不一致
        $("#password2").blur(function () {
            var password = $("#password").val();
            var password2 = $("#password2").val();
            if(password2!=password){
                var tips = layer.tips('密码不一致<br />请重新输入', '#password2',{
                    tips: [2, '#C81522']
                    // 上右下左四个方向，通过1-4进行方向设定
                });
                $("#password").val("");
                $("#password2").val("");
                sleep(5000);
                layer.close(tips);
            }
        });

        var loginName = "${user.loginName}";
        //回显数据
        $.ajax({
            url: "${pageContext.request.contextPath }/user/getUser"
            , data: {loginName: loginName}
            ,type:"post"
            , success: function (data) {
                //success函数传递的data其实是字符串，要指定返回的数据类型dataType
                data = JSON.parse(data);
                $.each(data, function (i, item) {

                    form.val("layuiFromUser", {
                        "loginName": item.loginName
                    });
                    form.render();
                });
            }
        });
    });
</script>
</body>
</html>
