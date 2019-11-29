<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" type="text/css"/>
	<script src="${pageContext.request.contextPath}/js/addOutBill.js"></script>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header" id="head">
		<jsp:include page="common/head.jsp"/>
	</div>
	<div class="layui-side" id="side">
		<jsp:include page="common/side.jsp"/>
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

</body>