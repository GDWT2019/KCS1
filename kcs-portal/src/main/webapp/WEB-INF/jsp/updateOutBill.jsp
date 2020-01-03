<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="${pageContext.request.contextPath}/js/updateOutBill.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
</head>
<body>
<div class="layui-body" style="margin-left: -200px;">
    <!-- 内容主体区域 -->
    <div class="layui-col-lg12" style="padding: 10px 10px;">
        <div class="layui-col-lg12" style="padding: 10px 15px; border-radius: 5px;">
            <div class="layui-col-lg12" style="text-align: center; font-size: 30px;"><span>修改出库单</span></div>
            <div class="layui-col-lg12" style="margin:10px 0;padding:10px;border-radius: 5px;">
                <form class="layui-form">
                    <div class="layui-layout-left" style="margin-top: 10px;margin-left: -200px;">
                        <div class="layui-form-item">
                            <label class="layui-form-label" style="font-size: 25px;">日期</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="outBillDate" placeholder="yyyy-MM-dd" value="${outBillPresentList[0].outTime}">
                            </div>
                        </div>
                    </div>
                    <div class="layui-layout-right" style="margin-top: 10px;margin-right: 50px;">
                        <span style="font-size: 25px;">编号：</span>
                        <div class="layui-inline">
                            <input type="text" class="layui-input" disabled="disabled" id="outBillID" autocomplete="on" value="${outBillPresentList[0].outBillID}">
                        </div>
                    </div>
                    <div class="" style="margin-top:30px;padding:10px;">
                        <div class="layui-table">
                            <table class="layui-table" id="tbData" >
                                <tr>
                                    <th>序号</th>
                                    <th>类型</th>
                                    <th>品名</th>
                                    <th>规格</th>
                                    <th width="90px">数量</th>
                                    <th>单价</th>
                                    <th>合计</th>
                                    <th>领用部门</th>
                                    <th>项目</th>
                                    <th>仓库位置</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${outBillPresentList}" var="outBillPresent" varStatus="status">
                                <tr name="tr${status.count}" id="itemsRow">
                                    <td>
                                            ${status.count}
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <select lay-verify="required" name="category${status.count}" lay-filter="category${status.count}" >
                                                <option value="${outBillPresent.categoryName}">${outBillPresent.categoryName}</option>
                                                <c:forEach items="${categoryList}" var="category">
                                                    <option value="${category.categoryID}">${category.categoryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <select lay-verify="required" name="itemsName${status.count}"  lay-filter="itemsName${status.count}" >
                                                <option value="${outBillPresent.goodsID}">${outBillPresent.itemsName}</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <select name="itemsType${status.count}" lay-verify="required" lay-filter="itemsType${status.count}">
                                                <option value="${outBillPresent.itemsType}">${outBillPresent.itemsType}</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <input name="itemNum${status.count}"  class="layui-input" type="number" min="1" value="${outBillPresent.itemNum}" onclick="calAllTotal(${status.count})"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <input name="itemPrice${status.count}"  class="layui-input" type="text" disabled="disabled" value="${outBillPresent.itemPrice}"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <input name="itemTotal${status.count}"  class="layui-input" type="text" disabled="disabled" value="${outBillPresent.itemTotal}"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <select name="department${status.count}"  lay-verify="required" lay-search="">
                                                <option value="${outBillPresent.departmentID}">${outBillPresent.departmentName}</option>
                                                <c:forEach items="${departmentList}" var="department">
                                                    <option value="${department.departmentID}">${department.departmentName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <input name="project${status.count}"  class="layui-input" type="text" placeholder="项目名称" value="${outBillPresent.project}"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="layui-form-item">
                                            <select  name="storePosition${status.count}"  lay-verify="required" lay-search="">
                                                <option value="${outBillPresent.storePosition}">${outBillPresent.storePosition}</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <input name="itemsOutID${status.count}" type="hidden" value="${outBillPresent.itemsOutID}">
                                        <div class="layui-form-item">
                                            <button type="button" class="layui-btn layui-btn-danger" onclick="delTr(this)">删除</button>
                                        </div>
                                    </td>
                                </tr>
                                </c:forEach>
                                <tr id="finalTr">
                                    <td colspan="11" align="center">
                                        <i onclick="addTr()" class="layui-btn layui-btn-sm layui-btn-fluid layui-icon layui-icon-down"></i>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="layui-row" style="margin: 10px 5px;">
                            <span style="font-size: 22px;">合计：<span id="allTotal" style="font-size: 22px;">${outBillPresentList[0].allTotal}</span>元</span>
                        </div>
                    </div>
                    <div class="" style="padding:10px;border-radius: 5px;">
                        <div class="layui-row">
                            <div class="layui-form-item">
                                <label class="layui-form-label">仓管员：</label>
                                <div class="layui-input-inline">
                                    <select name="storeManager"  id="storeManager">
                                        <option value="${outBillPresentList[0].storeManager}">${outBillPresentList[0].storeManagerName}</option>

                                        <c:forEach items="${userList}" var="user">
                                            <option value="${user.userID}">${user.userName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <label class="layui-form-label">领用人：</label>
                                <div class="layui-input-inline">
                                    <select name="taker" id="taker">
                                        <option value="${outBillPresentList[0].taker}">${outBillPresentList[0].takerName}</option>
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
                                    <select name="checker" id="checker">
                                        <option value="${outBillPresentList[0].checker}">${outBillPresentList[0].checkerName}</option>
                                        <c:forEach items="${userList}" var="user">
                                            <option value="${user.userID}">${user.userName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <label class="layui-form-label">制表人：</label>
                                <div class="layui-input-inline">
                                    <select name="tableMaker" id="tableMaker">
                                        <option value="${outBillPresentList[0].tableMaker}">${outBillPresentList[0].tableMakerName}</option>

                                        <c:forEach items="${userList}" var="user">
                                            <option value="${user.userID}">${user.userName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-row">
                            <div  style="float: right; margin-right: 30px;margin-top: 10px">
                                <button type="button" class="layui-btn layui-btn-lg" onclick="checkBill()">确认修改</button>
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

        // var trLength = document.getElementsByTagName("tr").length; //确定数据有多少行
        // trLength = Number(trLength)-Number(2);
        // alert(trLength)

        laydate.render({
            elem:"#outBillDate",
        });
        //各种基于事件的操作

    });
</script>
</body>