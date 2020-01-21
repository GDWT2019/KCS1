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
            <label class="layui-form-label">用户：</label>
            <div class="layui-input-inline" style="margin-top: 5px">
                <span>${user.userName}</span>
                <input id="userID" type="hidden" value="${user.userID}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">角色：</label>
            <div class="layui-input-inline">
                <select id="roleID" name="roleName">
                    <c:forEach items="${roleList}" var="role">
                        <option value="${role.roleID}">${role.roleName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>
<div style="margin: 40px 200px;font-size: 18px">
    <div class="layui-form">
        <div class="layui-inline">
            <button class="layui-btn-lg layui-btn" id="addRole">添加</button>
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

        $("#addRole").on('click',function () {
            var userID = $("#userID").val();
            var roleID = $('select[name="roleName"]').val();
            $.ajax({
                url:"${pageContext.request.contextPath}/role/addUserRole",
                type:"post",
                data:{"userID":userID,"roleID":roleID},
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