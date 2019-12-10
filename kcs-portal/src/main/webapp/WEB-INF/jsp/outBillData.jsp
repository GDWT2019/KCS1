<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Title</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" id="addOutBillBtn">添加出库</button>
		<button class="layui-btn layui-btn-sm" lay-event="getCheckData">添加数据</button>
		<button class="layui-btn layui-btn-sm" lay-event="getCheckLength">修改数据</button>
		<button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
	</div>
</script>
<script type="text/html" id="toolRight">
	<button class="layui-btn layui-btn-checked layui-btn-xs" lay-event="check">审批</button>
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${pageContext.request.contextPath}/static/layui/layui.all.js" charset="utf-8"></script>

<script >
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
				,{field:'outTime', title:'日期', width:120,sort:true}
				,{field:'itemsName', title:'物品名称', width:120}
				,{field:'itemsType', title:'物品规格', width:120}
				,{field:'itemsUnit', title:'单位', width:120}
				,{field:'storePosition', title:'仓库位置', width:120}
				,{field:'itemNum', title:'出库数量',sort:true, width:120}
				,{field:'takerName', title:'领用人', width:120}
				,{field:'remark', title:'备注', width:120}
				,{field:'checkStatus', title:'审批状态',sort:true, width:120,templet:function (d) {
						if(d.checkStatus==0) return '未审批';
						else if(d.checkStatus ==1) return '审批通过';
						else if(d.checkStatus == 2) return '审批未通过';
						else return '审批错误'
					}}
				,{fixed: 'right', title:'操作', toolbar: '#toolRight', width:200}
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

		//监听行工具事件
		table.on('tool(test)', function(obj){
			var data = obj.data;

			//删除数据！！
			if(obj.event === 'del'){
				layer.confirm('真的删除行么', function(index){
					$.ajax({
						url:"${pageContext.request.contextPath}/itemsOut/delByItemsOutID",
						type:"post",
						data:{"itemsOutID":data.itemsOutID},
						dataType:"text",
						success:function (result) {
							var ajaxResult = JSON.parse(result);
							if (ajaxResult){
								layer.confirm(ajaxResult.mesg);
								obj.del();
							}else{
								layer.confirm(ajaxResult.mesg);
							}
							layer.close(index);
						},
						error:function () {
							layer.confirm("删除请求错误！");
							layer.close(index);
						}
					})
				});

				//编辑
			} else if(obj.event === 'edit'){
				$.ajax({
					url:"${pageContext.request.contextPath}/outBill/findOutBillPresentByOutBillID",
					type:"post",
					data:{"outBillID":data.outBillID},
					dataType:"text",
					success:function (result) {
						layer.prompt({
							value:result
						})
					},
					error:function () {
						layer.confirm("编辑请求错误！");
						layer.close(index);
					}
				})

				//审批
			}else if (obj.event === 'check'){
				layer.prompt({
					formType: 2
					,value: data.checkStatus
				}, function(value, index){
					obj.update({
						email: value
					});
					layer.close(index);
				});
			}
		});

		$("#addOutBillBtn").on("click",function () {
			layer.open({
				type:2,
				title:false,
				content:'${pageContext.request.contextPath }/outBill/showAddOutBill',
				area:['1200px','668px'],
				end:function () {
					location.reload();
				}
			});
		})

	});
</script>
</body>
</html>
