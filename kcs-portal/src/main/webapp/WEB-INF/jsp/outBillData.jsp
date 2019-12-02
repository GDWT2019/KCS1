<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Title</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>
</head>
<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" lay-event="getCheckData">添加数据</button>
		<button class="layui-btn layui-btn-sm" lay-event="getCheckLength">修改数据</button>
		<button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
	</div>
</script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="checkBtn">
	<button class="layui-btn layui-btn-checked" lay-event="check">审批</button>
</script>

<script src="${pageContext.request.contextPath}/static/layui/layui.all.js" charset="utf-8"></script>

<script>
	layui.use('table', function(){
		var table = layui.table;

		table.render({
			elem: '#test'
			,url:"${pageContext.request.contextPath }/outBill/getAllOutBill"
			,toolbar: '#toolbarDemo'
			,title: '出库清单表'
			,totalRow: true//开启合计行
			,cols: [[
				{type: 'checkbox', fixed: 'left'}
				,{type:'numbers',title:'序号'}
				,{field:'outTime', title:'日期', width:120, edit: 'text'}
				,{field:'itemsName', title:'物品名称', width:120, edit: 'text'}
				,{field:'itemsType', title:'物品规格', width:120, edit: 'text'}
				,{field:'itemsUnit', title:'单位', width:120, edit: 'text'}
				,{field:'storePosition', title:'仓库位置', width:120, edit: 'text'}
				,{field:'itemNum', title:'出库数量', width:120, edit: 'text'}
				,{field:'taker', title:'领用人', width:120, edit: 'text'}
				,{field:'remark', title:'备注', width:120, edit: 'text'}
				,{field:'checkStatus', title:'审批状态', width:120, edit: 'text',templet:function (d) {
						if(d.checkStatus==0) return '未审批'
						else if(d.checkStatus ==1) return '审批通过'
						else if(d.checkStatus == 2) return '审批未通过'
						else return '审批错误'
					}}
				,{title:'审批', toolbar: '#checkBtn', width:120}
				,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
			]]
			,page: true
			/*,limit:10
            ,limits:[10,20,30]*/
			,id:'testUser'
		});

		//工具栏事件
		table.on('toolbar(test)', function(obj){
			var checkStatus = table.checkStatus(obj.config.id);
			switch(obj.event){
				case 'getCheckData':
					var data = checkStatus.data;
					layer.alert(JSON.stringify(data));
					break;
				case 'getCheckLength':
					var data = checkStatus.data;
					layer.msg('选中了：'+ data.length + ' 个');
					break;
				case 'isAll':
					layer.msg(checkStatus.isAll ? '全选': '未全选')
					break;
			};
		});
	});
</script>
</body>
</html>
