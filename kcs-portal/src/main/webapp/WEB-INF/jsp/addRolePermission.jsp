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
<div style="margin: 10px auto;font-size: 18px">
    <div class="layui-form">
        <div class="layui-inline">
            <label class="layui-form-label">角色：</label>
            <div class="layui-input-inline" style="margin-top: 5px">
                <span>${role.roleName}</span>
                <input id="roleID" type="hidden" value="${role.roleID}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">权限：</label>
            <div class="layui-input-inline">
                <select id="permissionID" name="permissionName">
                    <c:forEach items="${permissionList}" var="permission">
                        <option value="${permission.permissionID}">${permission.permissionName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>
<div style="margin: 40px 200px;font-size: 18px">
    <div class="layui-form">
        <div class="layui-inline">
            <button class="layui-btn-lg layui-btn" id="addPermission">添加</button>
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
            var permissionID = $('select[name="permissionName"]').val();
            $.ajax({
                url:"${pageContext.request.contextPath}/permission/addRolePermission",
                type:"post",
                data:{"roleID":roleID,"permissionID":permissionID},
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