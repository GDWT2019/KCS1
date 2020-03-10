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
<table class="layui-hide" id="test" lay-filter="test" lay-data="{id: 'idTest'}"></table>

<script type="text/html" id="toolbarDemo">
	<div class="layui-inline">
		时间范围：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="test10" placeholder="请选择时间段" style="width: 180%">
		</div>
	</div>

	<div class="layui-inline" style="margin-left: 200px;">
		人员：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="name"  placeholder="请输入登录名">
		</div>
	</div>

	<div class="layui-inline" style="margin-left: 50px;">
		<div class="layui-input-inline">
			<input type="button" class="layui-btn"  id="do_search" value="搜索">
		</div>
	</div>

</script>


<script src="${pageContext.request.contextPath}/static/layui/layui.all.js" charset="utf-8"></script>

<script>
	layui.use('table', function(){
		var table = layui.table;
		table.render({
			elem: '#test'
			,url: '${pageContext.request.contextPath }/log/findAllLog'
			,toolbar: '#toolbarDemo'
			,title: '日志'
			,totalRow: true//开启合计行
			,cols: [[
				{type: 'checkbox', fixed: 'left'}
				,{type:'numbers',title:'序号'}
				,{field:'logID', title:'日志ID', width:120,sort:true}
				,{field:'userID', title:'用户ID', width:120,sort:true}
				,{field:'userName', title:'用户名', width:120,sort:true}
				,{field:'loginName', title:'登录名', width:120,sort:true}
				,{field:'time', title:'时间', width:200,sort:true}
				,{field:'operation', title:'操作', width:200}
				,{field:'result', title:'结果', width:200}
			]]
			,where: {"time1":null,"time2":null,"name":null}
			,page: true
			,limit:10
			,limits:[5,10,20,30,50]
			,id:'logTable'
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
			}
		});

		$("#addOutBillBtn").on("click",function () {
			layer.open({
				type:2,
				title:"添加出库",
				content:'${pageContext.request.contextPath}/outBill/showAddOutBill',
				area:['1200px','668px'],
				moveOut:true,
				end:function () {
					location.reload();
				}
			});
		});

		$("#do_search").on("click",function () {
			var time1 = null;
			var time2 = null;
			var name = null;
			var timeRange=$("#test10").val();
			if (timeRange!=null || timeRange !=""){
				time1 = timeRange.substring(0,19);
				time2 = timeRange.substring(21,41);
			}
			if(($("#name").val()) != null || ($("#name").val()) != "")
				name =$("#name").val();
			console.log(time1+" "+time2+" "+name)
			table.reload('logTable', {
				 where: {"time1":time1,"time2":time2,"name":name} //设定异步数据接口的额外参数
				,height: 300
			});
		})
	});

	layui.use('laydate', function() {
		var laydate = layui.laydate;
		//日期范围
		//日期时间范围
		laydate.render({
			elem: '#test10'
			,type: 'datetime'
			,range: true
		});
	});
</script>
</body>
</html>
