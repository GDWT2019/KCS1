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

<div class="demoTable">
	时间范围：
	<div class="layui-inline">
		<input type="text" class="layui-input" name="timeRange" id="timeRange" placeholder="请选择时间段">
	</div>
	物品名：
	<div class="layui-inline">
		<input type="text" class="layui-input" id="itemName"  placeholder="请输入物品名">
	</div>
	审核状态：
	<div class="layui-inline">
		<select id="checkStatu" name="checkStatu">
			<option value="">请选择</option>
			<option value="0">待审批</option>
			<option value="1">通过</option>
			<option value="2">未通过</option>
		</select>
	</div>
	<input type="button" class="layui-btn" id="search"  value="搜索">
	<%--<button class="layui-btn" data-type="reload">搜索</button>--%>
</div>

<table class="layui-hide" id="test" lay-filter="test"></table>
<script type="text/html" id="toolbarDemo">
	<div class="layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<button class="layui-btn layui-btn-sm" id="addOutBillBtn">添加出库</button>
			</div>

			<%--<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px">时间范围：</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="timeRange" id="timeRange" placeholder="请选择时间段">
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px">物品名：</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="itemName"  placeholder="请输入物品名">
				</div>
			</div>

			<div class="layui-inline">
			<label class="layui-form-label" style="width: 100px">审批状态：</label>
			<div class="layui-input-inline">
				<select id="checkStatu" name="checkStatu">
						<option value="">请选择</option>
						<option value="0">待审批</option>
						<option value="1">通过</option>
						<option value="2">未通过</option>
				</select>
			</div>
		</div>

			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="button" class="layui-btn" id="search" value="搜索">
				</div>
			</div>--%>
		</div>
	</div>
</script>
<script type="text/html" id="toolRight">
	<button class="layui-btn layui-btn-xs" lay-event="check">审核</button>
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
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
			,totalRow: false//开启合计行
			,cols: [[
				{type: 'checkbox', fixed: 'left'}
				,{type:'numbers',title:'序号'}
				,{field:'outBillID', title:'单号', width:80,sort:true}
				,{field:'outTime', title:'日期', width:110,sort:true}
				,{field:'itemsName', title:'物品名称', width:110}
				,{field:'itemsType', title:'物品规格', width:100}
				,{field:'itemsUnit', title:'单位', width:60}
				,{field:'storePosition', title:'位置', width:60}
				,{field:'itemNum', title:'出库数量',sort:true, width:110}
				,{field:'takerName', title:'领用人', width:100}
				,{field:'remark', title:'备注', width:120}
				,{field:'checkStatus', title:'审批状态',sort:true, width:120,templet:function (d) {
						if(d.checkStatus==0) return '待审批';
						else if(d.checkStatus ==1) return '<span style="color: #009688;">通过</span>';
						else if(d.checkStatus == 2) return '<span style="color: #FF5722;">未通过</span>';
						else return '审批错误'
					}}
				,{field:'checkerName', title:'审批人', width:120}
				,{fixed: 'right', title:'操作', toolbar: '#toolRight', width:180}
			]]
			,where: {"time1":"","time2":"","itemName":"","checkStatu":""}
			,page: true
			,limit:10
			,limits:[5,10,20,30,50]
			,id:'outBillData'
		});

		$('body').on('click',"#search",function () {
			// 搜索条件
			var time1 = null;
			var time2 = null;
			var itemName = null;
			var checkStatu = null;
			var timeRange = $('#timeRange').val();
			if (($("#itemName").val()) != null && ($("#itemName").val()) != "") {
				itemName = $("#itemName").val();
			}
			//TODO
			if (($('select[name="checkStatu"]').find("option:selected").val()) != null && ($('select[name="checkStatu"]').find("option:selected").val()) != "") {
				checkStatu = $('select[name="checkStatu"]').find("option:selected").val();
			}
			if (timeRange != null && timeRange != "") {
				time1 = timeRange.substring(0, 10);
				time2 = timeRange.substring(13, 23);
			}

			table.reload('outBillData', {
				method: 'post'
				, where: {
					"time1": time1,
					"time2": time2,
					"itemName": itemName,
					"checkStatu":checkStatu,
				}
				, page: {
					curr: 1
				}
			});
			layui.use('laydate', function() {
				var laydate = layui.laydate;
				//日期范围
				laydate.render({
					elem: '#timeRange'
					, range: true
				});
			});
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
				layer.confirm('真的删除行么',{title:'删除出库单'}, function(index){
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
							layer.close(layer.index);
                            location.reload();
						},
						error:function () {
							layer.confirm("删除请求错误！");
							layer.close();
                            location.reload();
						}
					})
				});

				//编辑
			} else if(obj.event === 'edit'){
				layer.open({
					type:2,
					title:"修改出库",
					content:'${pageContext.request.contextPath}/outBill/showUpdateOutBill?outBillID='+data.outBillID,
					area:['1200px','668px'],
					moveOut:true,
					end:function () {
						location.reload();
					}
				});

				//审批
			}else if (obj.event === 'check'){
				$.ajax({
					url:"${pageContext.request.contextPath}/outBill/outBillPresentByOutBillID",
					type:"post",
					data:{"outBillID":data.outBillID},
					dataType:"text",
					success:function (result) {
						layer.open({
							type:1,
							content: result,
							title:"审批清单",
							area:['1200px','668px'],
							end:function () {
								location.reload();
							}
						})
					},
					error:function () {
						layer.confirm("审批请求错误");
						layer.close(layer.index);
					}
				})
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
		})
	});

	layui.use('laydate', function() {
		var laydate = layui.laydate;
		//日期范围
		laydate.render({
			elem: '#timeRange'
			, range: true
		});
	});
</script>
</body>
</html>
