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
    <div class="layui-form"  lay-filter="layuiFromGoods">
        <div class="layui-form-item">
            <label class="layui-form-label">物品名称</label>
            <div class="layui-input-inline">
                <input id="goodsName"  type="text" name="goodsName" lay-verify="required" placeholder="物品名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">物品类别</label>
            <div class="layui-input-inline">
                <select id="categoryID" name="categoryID" lay-verify="required" lay-search="" lay-filter="position">
                </select>
            </div>
            <a id="addCategory"><i class="layui-icon layui-icon-add-circle" style="font-size: 30px"></i></a>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">物品规格</label>
            <div class="layui-input-inline">
                <input id="goodsType"  type="text" name="goodsType" lay-verify="required" placeholder="物品规格" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">物品单位</label>
            <div class="layui-input-inline">
                <input id="goodsUnit"  type="text" name="goodsUnit" lay-verify="required" placeholder="物品单位" autocomplete="off" class="layui-input">
            </div>
        </div>
        <input type="hidden" name="image" id="image" class="image">
        <div class="layui-form-item">
            <label class="layui-form-label ">图片:</label>
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="test1">上传图片</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo">
                    <p id="demoText"></p>
                </div>
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


            //自定义验证规则

            $.ajax({
                type:"POST",
                url:'${pageContext.request.contextPath}/category/getCategory',  //从数据库查询返回的是个list
                dataType: "json",
                async: false,
                cache: false,
                success: function (data) {
                    $.each(data,function(index,item){
                        $("#categoryID").append("<option value='"+item.categoryID+"'>"+item.categoryName + "</option>");//往下拉菜单里添加元素
                    })
                    form.render();//菜单渲染 把内容加载进去
                }
            });

            $("#addCategory").on('click',function () {
                layer.open({
                    type: 2,
                    title: "新增物品类别",
                    content: '${pageContext.request.contextPath }/category/rCategory',
                    area: ['380px', '200px'],
                    end: function () {
                        $("#categoryID").empty();
                        $.ajax({
                            type:"POST",
                            url:'${pageContext.request.contextPath}/category/getCategory',  //从数据库查询返回的是个list
                            dataType: "json",
                            async: false,
                            cache: false,
                            success: function (data) {
                                $.each(data,function(index,item){
                                    $("#categoryID").append("<option value='"+item.categoryID+"'>"+item.categoryName + "</option>");//往下拉菜单里添加元素
                                })
                                form.render();//菜单渲染 把内容加载进去
                            }
                        });
                    }
                });

            })


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

            var goodsID = parent.goodsID;
            //回显数据
            $.ajax({
                url: "${pageContext.request.contextPath }/goods/showUpdateGoodsByID"
                 ,data: {goodsID: goodsID}
                ,type:"post"
                , success: function (data) {
                    //success函数传递的data其实是字符串，要指定返回的数据类型dataType
                    data = JSON.parse(data);
                    $.each(data, function (i, item) {
                        //循环获取数据
                        form.val("layuiFromGoods", {
                            "goodsName": item.itemsName
                            , "categoryID": item.categoryID
                            , "goodsType": item.itemsType
                            , "goodsUnit": item.itemsUnit
                            , "image": item.image
                        });
                        form.render();
                    });
                }
            });

            form.on('submit(update)', function(){
                var goodsID = parent.goodsID;
                var goodsName = $("#goodsName").val();
                var categoryID = $("#categoryID").val();
                var goodsType =$("#goodsType").val();
                var goodsUnit =$("#goodsUnit").val();
                var image = $("#image").val();

                $.ajax({
                    url:"${pageContext.request.contextPath}/goods/updateGoods",
                    type:"post",
                    data:{
                        "goodsID":goodsID,
                        "itemsName":goodsName,
                        "categoryID":categoryID,
                        "itemsType":goodsType,
                        "itemsUnit":goodsUnit,
                        "image":image
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