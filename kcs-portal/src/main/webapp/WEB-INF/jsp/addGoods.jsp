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

            <%--$("#addCategory").on('click',function () {--%>
                <%--layer.prompt({--%>
                    <%--formType: 2,--%>
                    <%--value: '',--%>
                    <%--title: '新增物品类别',--%>
                    <%--area: ['200px', '20px'] //自定义文本域宽高--%>
                <%--}, function(value, index, elem){--%>
                    <%--// alert(value);--%>
                    <%--$.ajax({--%>
                        <%--url:"${pageContext.request.contextPath}/category/addCategory",--%>
                        <%--type:"post",--%>
                        <%--data:{"categoryName":value},--%>
                        <%--dataType:"text",--%>
                        <%--success:function (result) {--%>
                            <%--var data = JSON.parse(result);--%>
                            <%--alert(data.mesg,function(){--%>
                                <%--window.parent.layer.closeAll();--%>
                            <%--});--%>
                            <%--reflashPosition();--%>
                        <%--},--%>
                        <%--error(){--%>
                            <%--alert("新增职位请求错误！")--%>
                        <%--}--%>
                    <%--});--%>
                    <%--location.reload();--%>
                    <%--layer.close(index);--%>
                <%--});--%>
            <%--});--%>


            form.on('submit(add)', function(){
                var goodsName = $("#goodsName").val();
                var categoryID = $("#categoryID").val();
                var goodsType =$("#goodsType").val();
                var goodsUnit =$("#goodsUnit").val();

                $.ajax({
                    url:"${pageContext.request.contextPath}/goods/addGoods",
                    type:"post",
                    data:{
                        "goodsName":goodsName,
                        "categoryID":categoryID,
                        "goodsType":goodsType,
                        "goodsUnit":goodsUnit
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