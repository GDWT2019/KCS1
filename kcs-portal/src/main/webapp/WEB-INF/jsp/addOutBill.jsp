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
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/addOutBill.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>
</head>
<body>
<div class="layui-body" style="margin-left: -200px;">
    <!-- 内容主体区域 -->
    <div class="layui-col-lg12" style="padding: 10px 10px;">
        <div class="layui-col-lg12" style="padding: 10px 15px; border-radius: 5px;">
            <div class="layui-col-lg12" style="text-align: center; font-size: 30px;"><span>出库单</span></div>
            <div class="layui-col-lg12" style="margin:10px 0;padding:10px;border-radius: 5px;">
                <form class="layui-form" action="">
                    <div class="layui-layout-left" style="margin-top: 10px;margin-left: -200px;">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="font-size: 25px;">日期</label>
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="outBillDate" placeholder="yyyy-MM-dd">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-layout-right" style="margin-top: 10px;margin-right: 50px;">
                        <span style="font-size: 25px;">编号：</span>
                        <div class="layui-inline">
                            <input type="text" class="layui-input" disabled="disabled" name="OutBillID" autocomplete="on" value="${outBillID}">
                        </div>
                    </div>
                    <div class="" style="margin-top:30px;padding:10px;">
                        <div class="layui-table">
                            <table class="layui-table" id="tbData" >
                                <tr>
                                    <th>序号</th>
                                    <th>品名</th>
                                    <th>规格</th>
                                    <th width="100px">数量</th>
                                    <th>单价</th>
                                    <th>合计</th>
                                    <th>领用部门</th>
                                    <th>项目</th>
                                    <th>操作</th>
                                </tr>
                                <tr name="tr1" id="itemsRow">
                                    <td>
                                        1
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <select id="itemsName" lay-verify="required" name="itemsName1" lay-filter="itemsName1">
                                                <option value="null">请选择</option>
                                                <c:forEach items="${goodsList}" var="goods">
                                                    <option value="${goods.itemsName}">${goods.itemsName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <select name="itemsType1" lay-verify="required" lay-filter="itemsType1">
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <input name="itemNum1" class="layui-input" type="number" placeholder="数量" min="1" value="0"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <input name="itemPrice1" class="layui-input" type="text" disabled="disabled"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <input name="itemTotal1" class="layui-input" type="text" disabled="disabled"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <select id="departmentName" name="departmentID1" lay-verify="required" lay-search="">
                                                <c:forEach items="${departmentList}" var="department">
                                                    <option value="${department.departmentID}">${department.departmentName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <input name="project1" class="layui-input" type="text" placeholder="项目名称"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <button type="button" class="layui-btn layui-btn-disabled">移除</button>
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
                            <span style="font-size: 22px;">合计：<span id="allTotal" style="font-size: 22px;"></span>元</span>
                        </div>
                    </div>
                    <div class="" style="padding:10px;border-radius: 5px;">
                        <div class="layui-row">
                            <div class="layui-form-item">
                                <label class="layui-form-label">仓管员：</label>
                                <div class="layui-input-inline">
                                    <select  id="storeManager">
                                        <c:forEach items="${userList}" var="user">
                                            <option value="${user.userID}">${user.userName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <label class="layui-form-label">领用人：</label>
                                <div class="layui-input-inline">
                                    <select  id="taker">
                                        <c:forEach items="${userList}" var="user">
                                            <option value="${user.userID}">${user.userName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-row">
                            <div class="layui-form-item">
                                <label class="layui-form-label">审批人：</label>
                                <div class="layui-input-inline">
                                    <select  id="checker">
                                        <c:forEach items="${userList}" var="user">
                                            <option value="${user.userID}">${user.userName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <label class="layui-form-label">制表人：</label>
                                <div class="layui-input-inline">
                                    <select  id="tableMaker">
                                        <c:forEach items="${userList}" var="user">
                                            <option value="${user.userID}">${user.userName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-row">
                            <div  style="float: right; margin-right: 30px;margin-top: 10px">
                                <button type="button" class="layui-btn layui-btn-lg" onclick="checkBill()">提交出库单</button>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['form','laydate'], function(){
        var form = layui.form;
        var laydate = layui.laydate;

        laydate.render({
            elem:"#outBillDate"
        });
        //各种基于事件的操作
    });
</script>
</body>

</html>
