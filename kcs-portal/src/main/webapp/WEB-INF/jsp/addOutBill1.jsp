<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
	<script src="${pageContext.request.contextPath}/js/addOutBill.js"></script>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">库存管理系统</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav  layui-layout-left">
			<li class="layui-nav-item"><a href="">控制台</a></li>
			<li class="layui-nav-item"><a href="">商品管理</a></li>
			<li class="layui-nav-item"><a href="">用户</a></li>
			<li class="layui-nav-item">
				<a href="javascript:;">其它系统</a>
				<dl class="layui-nav-child">
					<dd><a href="">出库管理</a></dd>
					<dd><a href="">入库管理</a></dd>
					<dd><a href="">管理</a></dd>
				</dl>
			</li>
		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img id="photo" src="http://localhost:8080/static/${user.photo}" class="layui-nav-img">
					<%--<img id="photo" src="C:\Users\gdwt00001\Desktop\photoes${user.photo}" class="layui-nav-img">--%>
					<span>${user.userName}</span>
				</a>

				<dl class="layui-nav-child">
					<dd><a type="button" id="baseData">基本资料</a></dd>
					<dd><a type="button" id="safeset">安全设置</a></dd>
				</dl>

			</li>
			<li class="layui-nav-item"><a href="user/loginOut">退出</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree layui-inline"   lay-filter="demo" style="margin-right: 10px;">
				<li class="layui-nav-item layui-nav-itemed">
					<a class="" href="javascript:;">业务管理</a>
					<dl class="layui-nav-child">
						<dd>
							<a data-url="${pageContext.request.contextPath }/user/ruser" data-id="11" data-title="用户数据" href="#" class="site-demo-active" data-type="tabAdd">用户数据</a>
						</dd>
						<dd>
							<a href="#" data-url=${pageContext.request.contextPath}+"/test.jsp" data-title="角色数据"  data-id="22" class="site-demo-active" data-type="tabAdd">角色数据</a>
						</dd>
						<dd><a href="javascript:;">列表三</a></dd>
						<dd><a href="">超链接</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;">入库管理</a>
					<dl class="layui-nav-child">
						<dd><a href="javascript:;">列表一</a></dd>
						<dd><a href="javascript:;">列表二</a></dd>
						<dd><a href="">超链接</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;">出库管理</a>
					<dl class="layui-nav-child">
						<dd><a href="${pageContext.request.contextPath }/outBill/addOutBill">新增出库清单</a></dd>
						<dd><a href="javascript:;">列表二</a></dd>
						<dd><a href="">超链接</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item"><a href="">云市场</a></li>
				<li class="layui-nav-item"><a href="">发布商品</a></li>
			</ul>
		</div>
	</div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-col-lg12" style="padding: 10px 10px;">
			<div class="layui-bg-black layui-col-lg12" style="padding: 10px 15px; border-radius: 5px;">
				<div class="layui-col-lg12" style="text-align: center; font-size: 30px;"><span>出库单</span></div>
				<div class="layui-col-lg12 layui-bg-gray" style="margin:30px 0;padding:10px;border-radius: 5px;">
					<form class="layui-form" action="">
					<div class="layui-layout-left" style="margin-top: 30px;margin-left: -170px;">
						  <span style="font-size: 25px;">时间：</span>
						  <div class="layui-inline">
							<input type="date" class="layui-input" name="OutBillTime"  autocomplete="on">
						  </div>
					</div>
					<div class="layui-layout-right" style="margin-top: 30px;margin-right: 40px;">
						  <span style="font-size: 25px;">编号：</span>
						  <div class="layui-inline">
							<input type="text" class="layui-input" name="OutBillID" autocomplete="on">
						  </div>
					</div>
					<div class="layui-bg-gray" style="margin-top:50px;padding:10px;">
						<div class="layui-table">
							<table class="layui-table" >
								<tr>
									<th>序号</th>
									<th>品名</th>
									<th>规格</th>
									<th>数量</th>
									<th>单价</th>
									<th>合计</th>
									<th>领用部门</th>
									<th>项目</th>
									<th>操作</th>
								</tr>
								<tr id="itemsRow">
									<td>
										1
									</td>
									<td>
										<div class="layui-form-item">
											<select id="itemsName" lay-verify="required" name="itemsName" lay-filter="itemsName1">
												<option value="null" selected>请选择</option>
												<c:forEach items="${goodsList}" var="goods">
													<option value="${goods.itemsName}">${goods.itemsName}</option>
												</c:forEach>
											</select>
										</div>
									</td>
									<td>
										<div class="layui-form-item">
											<select name="itemsType1" lay-verify="required" lay-filter="itemsType1">
												<option value="null" selected> </option>
											</select>
										</div>
									</td>
									<td>
										<div class="layui-form-item">
											<input name="itemNum" class="layui-input" type="number" placeholder="数量"/>
										</div>
									</td>
									<td>
										<div class="layui-form-item">
											<input name="itemPrice" class="layui-input" type="text" disabled="disabled"/>
										</div>
									</td>
									<td>
										<div class="layui-form-item">
											<input name="ItemTotal" class="layui-input" type="text" disabled="disabled"/>
										</div>
									</td>
									<td>
										<div class="layui-form-item">
											<select id="departmentName" name="departmentName" lay-verify="required" lay-search="">
												<c:forEach items="${departmentList}" var="department">
													<option>${department.departmentName}</option>
												</c:forEach>
											</select>
										</div>
									</td>
									<td>
										<div class="layui-form-item">
											<input name="project" class="layui-input" type="text" placeholder="项目名称"/>
										</div>
									</td>
									<td>
										<div class="layui-form-item">
											<button class="layui-btn layui-bg-gray" disabled="disabled" >移除</button>
										</div>
									</td>
								</tr>
								<tr id="finalTr">
									<td colspan="9" align="center">
										<i onclick="addTr()" class="layui-btn layui-btn-sm layui-btn-fluid layui-icon layui-icon-down"></i>
									</td>
								</tr>
							</table>
						</div>
						<div class="layui-row" style="margin: 10px 5px;">
							<span style="font-size: 22px;">合计：</span>
						</div>
					</div>
					<div class="layui-bg-gray" style="padding:10px;border-radius: 5px;">
							<div class="layui-row">
								<div class="layui-form-item">
								    <label class="layui-form-label">仓管员：</label>
									<div class="layui-input-inline">
										<select name="CategoryID" lay-verify="required" lay-search="">
										  <option value="1">文具</option>
										  <option value="2">生活用品</option>
										  <option value="3">电器</option>
										  <option value="4">其他</option>
										</select>
									</div>
									<label class="layui-form-label">领用人：</label>
									<div class="layui-input-inline">
										<select name="ItemsName" lay-verify="required" lay-search="">
										  <option value="1">文具</option>
										  <option value="2">生活用品</option>
										  <option value="3">电器</option>
										  <option value="4">其他</option>
										</select>
									</div>
									<label class="layui-form-label">审批人：</label>
									<div class="layui-input-inline">
										<select name="ItemsType" lay-verify="required" lay-search="">
										  <option value="1">文具</option>
										  <option value="2">生活用品</option>
										  <option value="3">电器</option>
										  <option value="4">其他</option>
										</select>
									</div>
									<label class="layui-form-label">制表人：</label>
									<div class="layui-input-inline">
										<select name="ItemNum" lay-verify="required" lay-search="">
										</select>
									</div>
								</div>
							</div>
					</div>
				</form>
				</div>
			</div>                          
        </div>
    </div>
</div>
<script>
	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;

		element.on('tab(filter)', function(data){
			console.log(this); //当前Tab标题所在的原始DOM元素
			console.log(data.index); //得到当前Tab的所在下标
			console.log(data.elem); //得到当前的Tab大容器
		});


		var $ = layui.jquery;
		var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

		//触发事件
		var active = {
			//在这里给active绑定几项事件，后面可通过active调用这些事件
			tabAdd: function(url,id,name) {
				//新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
				//关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
				element.tabAdd('demo', {
					title: name,
					content: '<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'.html" style="width:100%;height:99%;"></iframe>',
					id: id //规定好的id
				})
				CustomRightClick(id); //给tab绑定右击事件
				FrameWH();  //计算ifram层的大小
			},
			tabChange: function(id) {
				//切换到指定Tab项
				element.tabChange('demo', id); //根据传入的id传入到指定的tab项
			},
			tabDelete: function (id) {
				element.tabDelete("demo", id);//删除
			}
			, tabDeleteAll: function (ids) {//删除所有
				$.each(ids, function (i,item) {
					element.tabDelete("demo", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
				})
			}
		};



		//当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
		$('.site-demo-active').on('click', function() {
			var dataid = $(this);

			//这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
			if ($(".layui-tab-title li[lay-id]").length <= 0) {
				//如果比零小，则直接打开新的tab项
				active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
			} else {
				//否则判断该tab项是否以及存在

				var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
				$.each($(".layui-tab-title li[lay-id]"), function () {
					//如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
					if ($(this).attr("lay-id") == dataid.attr("data-id")) {
						isData = true;
					}
				})
				if (isData == false) {
					//标志为false 新增一个tab项
					active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
				}
			}
			//最后不管是否新增tab，最后都转到要打开的选项页面上
			active.tabChange(dataid.attr("data-id"));
		});

		function CustomRightClick(id) {
			//取消右键  rightmenu属性开始是隐藏的 ，当右击的时候显示，左击的时候隐藏
			$('.layui-tab-title li').on('contextmenu', function () { return false; })
			$('.layui-tab-title,.layui-tab-title li').click(function () {
				$('.rightmenu').hide();
			});
			//桌面点击右击
			$('.layui-tab-title li').on('contextmenu', function (e) {
				var popupmenu = $(".rightmenu");
				popupmenu.find("li").attr("data-id",id); //在右键菜单中的标签绑定id属性

				//判断右侧菜单的位置
				l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
				t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
				popupmenu.css({ left: l, top: t }).show(); //进行绝对定位
				//alert("右键菜单")
				return false;
			});
		}

		$(".rightmenu li").click(function () {

			//右键菜单中的选项被点击之后，判断type的类型，决定关闭所有还是关闭当前。
			if ($(this).attr("data-type") == "closethis") {
				//如果关闭当前，即根据显示右键菜单时所绑定的id，执行tabDelete
				active.tabDelete($(this).attr("data-id"))
			} else if ($(this).attr("data-type") == "closeall") {
				var tabtitle = $(".layui-tab-title li");
				var ids = new Array();
				$.each(tabtitle, function (i) {
					ids[i] = $(this).attr("lay-id");
				})
				//如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
				active.tabDeleteAll(ids);
			}

			$('.rightmenu').hide(); //最后再隐藏右键菜单
		})
		function FrameWH() {
			var h = $(window).height() -41- 10 - 60 -10-44 -10;
			$("iframe").css("height",h+"px");
		}

		$(window).resize(function () {
			FrameWH();
		})

	});

	$("#baseData").on("click",function () {
		layer.open({
			type:2,
			title:false,
			content:'${pageContext.request.contextPath }/user/baseData',
			area:['1000px','668px'],
			end:function () {
				location.reload();
			}
		});
	})

	$("#safeset").on("click",function () {
		layer.open({
			type:2,
			title:false,
			content:'${pageContext.request.contextPath }/user/safeData',
			area:['500px','300px'],
			end:function () {
				location.reload();
			}
		});
	})
</script>
</body>