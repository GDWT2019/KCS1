<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/updateOutBill.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>
</head>
<body>
<div class="layui-body" style="margin-left: -200px;">
    <!-- 内容主体区域 -->
    <div class="layui-col-lg12" style="padding: 10px 10px;">
        <div class="layui-col-lg12" style="padding: 10px 15px; border-radius: 5px;">
            <div class="layui-col-lg12" style="text-align: center; font-size: 30px;"><span>审批</span></div>
            <div class="layui-col-lg12" style="margin:10px 0;padding:10px;border-radius: 5px;">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-layout-left" style="margin-top: 10px;margin-left: -200px;">
                        <div class="layui-form-item">
                            <label class="layui-form-label" style="font-size: 25px;">日期</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="outBillDate" placeholder="yyyy-MM-dd"
                                       value="${outBillPresentList[0].outTime}">
                            </div>
                        </div>
                    </div>
                    <div class="layui-layout-right" style="margin-top: 10px;margin-right: 50px;">
                        <span style="font-size: 25px;">编号：</span>
                        <div class="layui-inline">
                            <input id="outBillID" type="text" class="layui-input" disabled="disabled" name="OutBillID"
                                   autocomplete="on" value="${outBillPresentList[0].outBillID}">
                        </div>
                    </div>
                    <div class="" style="margin-top:30px;padding:10px;">
                        <div class="layui-table">
                            <table class="layui-table" id="tbData">
                                <tr>
                                    <th>序号</th>
                                    <th>品名</th>
                                    <th>规格</th>
                                    <th>数量</th>
                                    <th>单价</th>
                                    <th>合计</th>
                                    <th>仓库位置</th>
                                    <th>领用部门</th>
                                    <th>领用人</th>
                                    <th>操作人</th>
                                    <th>制表人</th>
                                    <th>备注</th>
                                </tr>
                                <c:forEach items="${outBillPresentList}" var="outBillPresent" varStatus="status">
                                    <tr>
                                        <td>
                                                ${status.count}
                                        </td>
                                        <td>
                                                ${outBillPresent.itemsName}
                                        </td>
                                        <td>
                                                ${outBillPresent.itemsType}
                                        </td>
                                        <td>
                                                ${outBillPresent.itemNum}
                                        </td>
                                        <td>
                                                ${outBillPresent.itemPrice}
                                        </td>
                                        <td>
                                                ${outBillPresent.itemTotal}
                                        </td>
                                        <td>
                                                ${outBillPresent.storePosition}
                                        </td>
                                        <td>
                                                ${outBillPresent.departmentName}
                                        </td>
                                        <td>
                                                ${outBillPresent.takerName}
                                        </td>
                                        <td>
                                                ${outBillPresent.operatorName}
                                        </td>
                                        <td>
                                                ${outBillPresent.tableMakerName}
                                        </td>
                                        <td>
                                                ${outBillPresent.note}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div class="layui-row" style="margin: 10px 5px;">
                            <span style="font-size: 22px;">合计：<span id="allTotal"
                                                                    style="font-size: 22px;">${outBillPresentList[0].allTotal}</span>元</span>
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label" style="font-size: 20px">审批意见：</label>
                        <div class="layui-input-block">
                            <textarea id="checkMessage" placeholder="可不填" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="" style="padding:20px;border-radius: 5px;">
                        <div class="layui-row" style="margin: 10px auto;text-align: center">
                            <div style="margin-right: 30px;margin-top: 10px">
                                <button type="button" class="layui-btn layui-btn-lg" onclick="getCheckStatus(true)">通过
                                </button>
                                <button type="button" class="layui-btn layui-btn-lg" onclick="getCheckStatus(false)">
                                    不通过
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['form', 'laydate'], function () {
        var form = layui.form;
        var laydate = layui.laydate;

        laydate.render({
            elem: "#outBillDate"
        });
        //各种基于事件的操作
    });

    function getCheckStatus(status) {
        var outBillID = $("#outBillID").val();
        var checkMessage = $("#checkMessage").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/outBill/makeOutBillCheckStatus",
            type: "post",
            data: {"outBillID": outBillID, "checkMessage": checkMessage, "status": status},
            dataType: "text",
            success: function (result) {
                var data = JSON.parse(result);
                alert(data.mesg)
            },
            error: function () {
                alert("审批请求错误！")
            }
        })
    }
</script>
</body>