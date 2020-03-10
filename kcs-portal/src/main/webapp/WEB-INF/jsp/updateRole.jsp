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
<div style="margin: 20px auto;font-size: 20px">
    <div class="layui-form">
        <div class="layui-inline">
            <label class="layui-form-label">角色名称：</label>
            <div class="layui-input-inline" style="padding-left: 20px">
                <input type="text" name="roleName" id="roleName" placeholder="${role.roleName}">
                <input id="roleID" type="hidden" value="${role.roleID}">
            </div>
        </div>
    </div>
</div>
<div style="margin: 40px 200px;font-size: 18px">
    <div class="layui-form">
        <div class="layui-inline">
            <button class="layui-btn-lg layui-btn" id="addPermission">修改</button>
        </div>
    </div>
</div>
</body>

<script>
    layui.use(['jquery','upload','form','layer','element', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        var $ = layui.$,
            element = layui.element,
            upload = layui.upload;
        form.render();

        $("#addPermission").on('click',function () {
            var roleID = $("#roleID").val();
            var roleName = $("#roleName").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/role/updateRole",
                type:"post",
                data:{"roleID":roleID,"roleName":roleName},
                dataType:"text",
                success:function (result) {
                    if (result!=null) {
                        var data = JSON.parse(result);
                        layer.alert(data.mesg)
                    }else
                        layer.alert("程序错误！")
                },
                error(){
                    layer.alert("请求错误！")
                }
            })
        });

    });

</script>


</body>
</html>