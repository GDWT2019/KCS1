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
<div style="margin: 10px auto">
    <div class="layui-form">
        <div class="layui-inline">
            <label class="layui-form-label">用户名：</label>
            <div class="layui-input-inline">
                <span>${user.userName}</span>
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
        <div class="layui-inline">
            <button class="layui-btn"  lay-submit lay-filter="add">添加</button>
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

        form.render();
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
                    return '角色不能为空！'
                }
            }

            //我们既支持上述函数式的方式，也支持下述数组的形式
            //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
            ,permission: function(value, item) {
                if(value==""||value==null){
                    return '权限不能为空！'
                }
            }
        });


        form.on('submit(add)', function(){
            var loginName = $("#loginName").val();
            var userName = $("#userName").val();
            var password = $("#password1").val();
            var departmentID = $("#departmentID").val();
            var positionID = $("#positionID").val();
            var sex = $('input[name="sex"]:checked').val();
            var tel = $("#tel").val();
            var email = $("#email").val();
            var photo = $(".image").attr("value");
            var warehouseMark = $("#warehouseMark").val();
            var listerMark = $("#listerMark").val();
            var note = $("#note").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/user/addUser",
                type:"post",
                data:{
                    "loginName":loginName,"userName":userName,"password":password,
                    "departmentID":departmentID,"positionID":positionID,"sex":sex,
                    "tel":tel,"email":email,"photo":photo,"warehouseMark":warehouseMark,
                    "listerMark":listerMark,"note":note},
                dataType:"text",
                success:function (result) {
                    if (result!=null){
                        var data = JSON.parse(result);
                        alert(data.mesg);
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

        $("#addRole").on('click',function () {
            layer.prompt({
                formType: 2,
                value: '',
                title: '新增部门',
                area: ['200px', '20px'] //自定义文本域宽高
            }, function(value, index, elem){
                $.ajax({
                    url:"${pageContext.request.contextPath}/department/addDepartment",
                    type:"post",
                    data:{"departmentName":value},
                    dataType:"text",
                    success:function (result) {
                        var data = JSON.parse(result);
                        reflashDepartment();
                    },
                    error(){
                        alert("新增部门请求错误！")
                    }
                })
                layer.close(index);
            });
        });

        $("#addPermission").on('click',function () {
            layer.prompt({
                formType: 2,
                value: '',
                title: '新增职位',
                area: ['200px', '20px'] //自定义文本域宽高
            }, function(value, index, elem){
                $.ajax({
                    url:"${pageContext.request.contextPath}/position/addPosition",
                    type:"post",
                    data:{"positionName":value},
                    dataType:"text",
                    success:function (result) {
                        var data = JSON.parse(result);
                        reflashPosition();
                    },
                    error(){
                        alert("新增职位请求错误！")
                    }
                });
                layer.close(index);
            });
        });

        $.ajax({
            type:"POST",
            url:'${pageContext.request.contextPath}/role/getAllRole',  //从数据库查询返回的是个list
            dataType: "json",
            async: false,
            cache: false,
            success: function (data) {
                $.each(data,function(index,item){
                    $("#roleID").append("<option value='"+item.roleID+"'>"+item.roleName + "</option>");//往下拉菜单里添加元素
                })
                form.render();//菜单渲染 把内容加载进去
            }
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
                    console.log(data.flag+data.mesg)
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