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
    <legend>基本资料</legend>
</fieldset>

<%--<span>${user.loginName}</span>--%>

<form class="layui-form"  lay-filter="layuiFromUser" id="baseForm">
    <div class="layui-form-item">
        <label class="layui-form-label">登录名</label>
        <div class="layui-input-inline">
            <input readonly="readonly" type="text" name="loginName" lay-verify="required" placeholder="请输入"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="userName" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">职位</label>
        <div class="layui-input-inline">
            <select id="positionID" name="positionID" lay-verify="required" lay-search="" lay-filter="position">
                <option value="">直接选择或搜索选择</option>
            </select>
        </div>
        <a id="addPosition"><i class="layui-icon layui-icon-add-circle" style="font-size: 30px"></i></a>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">部门</label>
        <div class="layui-input-inline">
            <select id="departmentID" name="departmentID" lay-verify="required" lay-search=""  lay-filter="department">
                <option value="">直接选择或搜索选择</option>

            </select>
        </div>
        <a id="addDepartment"><i class="layui-icon layui-icon-add-circle" style="font-size: 30px"></i></a>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input id="man" type="radio" name="sex" value="true" title="男">
            <input id="women" type="radio" name="sex" value="false" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">验证手机</label>
            <div class="layui-input-inline">
                <input type="tel" name="tel" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">验证邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" autocomplete="on" class="layui-input">
            </div>
        </div>
    </div>

    <%--<input type="hidden" name="photo" class="image">
    <div class="layui-form-item">
        <label class="layui-form-label ">照片:</label>
        <div class="layui-upload">
            <button type="button" class="layui-btn" id="test1">上传图片</button>
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="demo1">
                <p id="demoText"></p>
            </div>
        </div>
    </div>--%>

    <div class="layui-form-item">
        <label class="layui-form-label">仓管员标记</label>
        <div class="layui-input-block">
            <input type="checkbox" name="warehouseMark" lay-skin="switch" lay-text="是|否">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">制表人标记</label>
        <div class="layui-input-block">
            <input type="checkbox" name="listerMark" lay-skin="switch" lay-text="是|否">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="note" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="bt_update" type="submit" class="layui-btn"  lay-submit="" lay-filter="demo1">立即修改</button>
        </div>
    </div>

</form>

<script src="${pageContext.request.contextPath }/static/layui/layui.all.js" charset="utf-8"></script>

<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });


        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            }
            , pass: [
                /^[\S]{6,12}$/
                , '密码必须6到12位，且不能出现空格'
            ]
            , content: function (value) {
                layedit.sync(editIndex);
            }
        });

        <%--$("#bt_update").click(function () {--%>
         <%--$.post("${pageContext.request.contextPath }/user/updateBase" , $("#baseForm").serialize());--%>
         <%--layer.alert("修改成功！");--%>
         <%--return false;--%>
        <%--});--%>

        form.on('submit(demo1)', function(data){
            $.post("${pageContext.request.contextPath }/user/updateBase" , $("#baseForm").serialize());
            layer.alert("修改成功！",function () {
                window.parent.layer.closeAll();
            });
            return false;
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
        });


</script>
<script type="text/javascript">
    layui.use(["jquery", "upload", "form", "layer", "element"], function () {
        var $ = layui.$,
            element = layui.element,
            layer = layui.layer,
            upload = layui.upload,
            form = layui.form;

        $.ajax({
            type:"POST",
            url:'${pageContext.request.contextPath }/position/getPosition',  //从数据库查询返回的是个list
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

        $.ajax({
            type:"POST",
            url:'${pageContext.request.contextPath }/department/getDepartment',  //从数据库查询返回的是个list
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

        var loginName = "${user.loginName}";
        console.log("loginName"+loginName);
        //回显数据
        $.ajax({
            url: "${pageContext.request.contextPath }/user/getUser"
            , data: {loginName: loginName}
            ,type:"post"
            , success: function (data) {
                //success函数传递的data其实是字符串，要指定返回的数据类型dataType
                data = JSON.parse(data);
                $.each(data, function (i, item) {
                    //循环获取数据
                    if (item.sex) {
                        $("#man").prop("checked", true);
                        $("#women").prop("checked", false);
                    } else {
                        $("#man").prop("checked", false);
                        $("#women").prop("checked", true);
                    }
                    form.val("layuiFromUser", {
                        "loginName": item.loginName
                        , "userName": item.userName
                        , "positionID": item.positionID
                        , "departmentID": item.departmentID
                        , "sex": item.sex
                        , "tel": item.tel
                        , "email": item.email
                        , "photo": "123"
                        , "note": item.note
                        , "warehouseMark": item.warehouseMark
                        , "listerMark": item.listerMark
                    });
                    form.render('radio');
                    form.render();
                });
            }
        });

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            , url: '${pageContext.request.contextPath }/user/upload'
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
</script>


</body>
</html>