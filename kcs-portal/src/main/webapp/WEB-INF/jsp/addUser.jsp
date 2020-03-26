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

<div class="layui-form" >
    <div class="layui-form-item">
        <label class="layui-form-label">登录名</label>
        <div class="layui-input-inline">
            <input id="loginName"  type="text" name="loginName" lay-verify="loginName" placeholder="登录名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" id="userName" name="userName" lay-verify="userName" placeholder="用户名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="password" id="password1" name="password" lay-verify="password" placeholder="密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-inline">
            <input type="password" id="password2"  placeholder="确认密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">部门</label>
        <div class="layui-input-inline">
            <select id="departmentID" name="departmentID" lay-verify="required" lay-search=""  lay-filter="department">

            </select>
        </div>
        <a id="addDepartment"><i class="layui-icon layui-icon-add-circle" style="font-size: 30px"></i></a>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">职位</label>
        <div class="layui-input-inline">
            <select id="positionID" name="positionID" lay-verify="required" lay-search="" lay-filter="position">
            </select>
        </div>
        <a id="addPosition"><i class="layui-icon layui-icon-add-circle" style="font-size: 30px"></i></a>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="1" title="男">
            <input type="radio" name="sex" value="0" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">验证手机</label>
            <div class="layui-input-inline">
                <input type="tel" id="tel" name="tel" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">验证邮箱</label>
            <div class="layui-input-inline">
                <input type="text" id="email" name="email" lay-verify="email" autocomplete="on" class="layui-input">
            </div>
        </div>
    </div>
    <input type="hidden" name="photo" class="image">
    <%--<div class="layui-form-item">
        <label class="layui-form-label">照片:</label>
        <div class="layui-upload">
            <button type="button" class="layui-btn" id="test1">上传图片</button>
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="imgName">
                <p id="demoText"></p>
            </div>
        </div>
    </div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">仓管员标记</label>
        <div class="layui-input-block">
            <input type="checkbox" id="warehouseMark" name="warehouseMark" lay-skin="switch" lay-filter="switchTest1"  lay-text="on|off">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">制表人标记</label>
        <div class="layui-input-block">
            <input type="checkbox" id="listerMark" name="listerMark" lay-skin="switch" lay-filter="switchTest2"  lay-text="on|off">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="note" id="note" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
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


        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });


        //自定义验证规则
        form.verify({
            loginName: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(value==""||value==null){
                    return '登录名不能为空！'
                }
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                    return '登录名不能有特殊字符';
                }
                if(/(^\_)|(\__)|(\_+$)/.test(value)){
                    return '登录名首尾不能出现下划线\'_\'';
                }
                if(/^\d+\d+\d$/.test(value)){
                    return '登录名不能全为数字';
                }
            },
            userName: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(value==""||value==null){
                    return '登录名不能为空！'
                }
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                    return '用户名不能有特殊字符';
                }
                if(/(^\_)|(\__)|(\_+$)/.test(value)){
                    return '用户名首尾不能出现下划线\'_\'';
                }
                if(/^\d+\d+\d$/.test(value)){
                    return '用户名不能全为数字';
                }
                if(value.length<2||value.length>10){
                    return '用户名长度为2-10个字符'
                }
            }

            //我们既支持上述函数式的方式，也支持下述数组的形式
            //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
            ,password: function(value, item) {
                if (value.length<6||value.length>12) {
                    return '密码必须6到12位，且不能出现空格'
                }
                var password2 = $("#password2").val();
                if (value!=password2){
                    return '两次密码不一致！'
                }
            }
        });

        var warehouseMark = false;
        var listerMark = false;

        form.on('switch(switchTest1)', function(){
            warehouseMark= this.checked ? 'true' : 'false'

        });

        form.on('switch(switchTest2)', function(){
            listerMark = this.checked ? 'true' : 'false'
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
            var photo = "123";

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
                        layer.alert(data.mesg,function () {
                            window.parent.layer.closeAll();
                        })
                    }
                    else
                        layer.alert("失败！",function () {
                            window.parent.layer.closeAll();
                        })
                },
                error:function () {
                    layer.alert("请求错误！")
                }
            })
        });

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function () {
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

        $("#addDepartment").on('click',function () {
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
                        layer.alert(data.mesg);
                        reflashDepartment();
                    },
                    error(){
                        layer.alert("新增部门请求错误！")
                    }
                })
                layer.close(index);
            });
        });

        $("#addPosition").on('click',function () {
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
                        layer.alert(data.mesg);
                        reflashPosition();
                    },
                    error(){
                        layer.alert("新增职位请求错误！")
                    }
                });
                layer.close(index);
            });
        });

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


        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            , url: '${pageContext.request.contextPath}/user/upload'
            , accept: 'images'
            , method: 'post'
            , size: 50000
            , before: function (obj) {

                obj.preview(function (index, file, result) {

                    $('#demo1').attr('src', result);
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
                var demoText = $('#demoText');
                demoText.html('<span style="color: #4cae4c;">上传成功</span>');

                var fileupload = $(".image");
                fileupload.attr("value", res.data.src);
                console.log(fileupload.attr("value"));
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
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