<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
</head>
<body>
<form class="layui-form" action="" id="checkStatusForm">
    <input id="InBillID" name="InBillID" type="hidden">
    <div class="layui-form-item" style="">
        <label class="layui-form-label">审核状态</label>
        <div class="layui-input-block">
            <select name="checkStatus" lay-filter="checkStatus">
                <option value=""></option>
                <option value="0">等待审核</option>
                <option value="1">审核通过</option>
                <option value="2">审核未通过</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
        </div>
    </div>
</form>

<script>
    $("#InBillID").val(parent.InBillID);
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //监听提交
        form.on('submit(demo1)', function(data){
           $.ajax({
               method:'post',
               url:'${pageContext.request.contextPath }/itemIn/UpdateCheckStatus',
               data:$("#checkStatusForm").serialize(),
               success:function () {
                   layer.alert("修改成功！");
               },
               error:function () {
                 layer.alert("修改失败!");
               }
           });
            return false;
        });

    });
</script>
</body>
</html>
