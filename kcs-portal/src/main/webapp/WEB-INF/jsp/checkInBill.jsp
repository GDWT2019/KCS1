<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
</head>
<body>
<div class="layui-body" style="margin-left: -200px;">
    <div class="layui-col-lg12" style="padding: 10px 10px;">
        <div class=" layui-col-lg12" style="padding: 10px 15px; border-radius: 5px;">
            <div class="layui-col-lg12" style="text-align: center; font-size: 30px;"><span>入库审批</span></div>
            <div class="layui-col-lg12 " style="margin:30px 0;padding:10px;border-radius: 5px;">
                <form class="layui-form" id="checkStatusForm" type="post">
                    <input type="hidden" name="checker" value="${user.userID}"/>
                    <div class="layui-layout-left" style="margin-top: 10px;margin-left: -200px;">

                        <label class="layui-form-label" style="font-size: 25px;">日期</label>
                        <div class="layui-inline">
                            <input id="InBillTime" type="text" class="layui-input" readonly name="checkTime"
                                   style="font-size: 25px;border: 0px" value="${loadtime}"/>
                        </div>
                    </div>

                    <div class="layui-layout-right" style="margin-top: 10px;margin-right: 50px;">
                        <span style="font-size: 25px;">编号：</span>
                        <div class="layui-inline">
                            <input readonly id="InBillID" type="text" class="layui-input InBillID" name="InBillID"
                                   autocomplete="on" value="" style="font-size: 25px; border: 0px">
                        </div>
                    </div>
                    <div class="" style="margin-top:50px;padding:10px;">
                        <div class="layui-table">

                            <table class="layui-hide" id="test" lay-filter="test"></table>


                            <div class="layui-row" style="margin: 10px 5px;">
                                <span style="font-size: 22px;">合计：<span id="alTotal" style="font-size: 22px;"></span>元</span>
                            </div>
                            <label class="layui-form-label" style="font-size: 20px;width: 100px">审批意见：</label>
                            <div class="layui-row">
                                    <textarea id="checkMessage" placeholder="请输入内容" class="layui-textarea"
                                              name="checkMessage"></textarea>
                            </div>

                            <div style="text-align: center;padding:20px;border-radius: 5px;">
                                <div class="layui-inline">
                                    <button onclick="checkPass()" type="button" class="layui-btn ">
                                        <i class="layui-icon">&#x1005;</i> 通过
                                    </button>
                                    <button onclick="checkFail()" type="button" class="layui-btn  ">
                                        <i class="layui-icon">&#x1007;</i>不通过
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="check">审批</a>
</script>

<script>

    $("#InBillID").val(parent.InBillID);




    $.ajax({
        method: 'post',
        url: "${pageContext.request.contextPath }/inBill/getCheckMessageByID",
        data: {"InBillID": parent.InBillID},
        success: function (htq) {

            $("#checkMessage").val(htq.checkMessage);
        }
    });

    $.ajax({
        method: 'post',
        url: "${pageContext.request.contextPath }/itemIn/valueIDandTime",
        data: {"InBillID": parent.InBillID},
        success: function (htq) {

            // $("#alTotal").val(htq[0].allTotal);
            $("#alTotal").text(htq[0].allTotal.toFixed(2));
        }
    });

    <%--function updateStatus() {--%>
    <%--window.InBillID = parent.InBillID;--%>
    <%--layer.open({--%>
    <%--type: 2,--%>
    <%--title: '审批操作',--%>
    <%--skin: 'layui-layer-rim', //加上边框--%>
    <%--content: '${pageContext.request.contextPath }/itemIn/checkUpdate',--%>
    <%--area: ['380px', '200px'],--%>
    <%--end: function () {--%>
    <%--location.reload();--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    function checkPass() {
        $.ajax({
            method: 'post',
            url: '${pageContext.request.contextPath }/itemIn/UpdateCheckStatus',
            data: $.param({'checkStatus': 1}) + '&' + $("#checkStatusForm").serialize(),
            success: function () {
                var index = parent.layer.getFrameIndex(window.checklayer);
                layer.alert("审核成功！",function(){
                    window.parent.layer.closeAll();
                });

            },
            error: function () {
                layer.alert("审核失败!");
                layer.close(checklayer);
            }
        });
    }

    function checkFail() {
        var checkMessage = $("#checkMessage").val();
        if (checkMessage != null && checkMessage != "" && checkMessage != " ") {
            $.ajax({
                method: 'post',
                url: '${pageContext.request.contextPath }/itemIn/UpdateCheckStatus',
                data: $.param({'checkStatus': 2}) + '&' + $("#checkStatusForm").serialize(),
                success: function () {
                    layer.alert("审核成功！",function(){
                        window.parent.layer.closeAll();
                    });
                },
                error: function () {
                    layer.alert("审核失败!");
                }


        });
        } else {
            layer.tips("请填写审核不通过的意见", '#checkMessage', {
                tips: [1, "#2B2B2B"]
            });
        }
    }

    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#test'
            , url: "${pageContext.request.contextPath }/itemIn/itemsInData"
            // , toolbar: '#toolbarDemo'
            , title: '入库单'
            , totalRow: false//开启合计行
            , cols: [[
                 {field: 'inBillID', title: '入库单号', width: 110, fixed: 'left', unresize: true, sort: true}
                , {field: 'timeIn', title: '日期', width: 160}
                , {
                    field: 'goodsID', title: '物品名称', width: 110, templet(d) {
                        var itemName;
                        var goodsID = Number(d.goodsID);
                        if (!isNaN(goodsID)) {
                            console.log('goodsID:' + goodsID)
                            $.ajax({
                                method: 'post',
                                url: '${pageContext.request.contextPath }/inBill/findGoodsByGoodsID',
                                data: {"goodsID": goodsID},
                                async: false,
                                success: function (htq) {
                                    console.log(htq[0].goodsID);
                                    console.log(htq[0].itemsName);
                                    if (d.goodsID == htq[0].goodsID) {
                                        itemName = htq[0].itemsName;
                                    }
                                }
                            });
                            return itemName;
                        }
                    }
                }
                , {field: 'type', title: '物品规格', width: 110}
                , {field: 'itemNum', title: '入库数量', width: 110}
                , {field: 'itemPrice', title: '入库单价', width: 80}
                , {field: 'itemTotal', title: '合计', width: 110}
                , {
                    field: 'checkStatus', title: '审核状态', width: 150, templet: function (d) {
                        if (d.checkStatus == 0) return '<span>等待审核</span>'
                        else if (d.checkStatus == 1) return '<span style="color: #009688;">审核通过</span>'
                        else if (d.checkStatus == 2) return '<span style="color: #FF5722;">审核未通过</span>'
                    }
                }
                // , {field: 'allTotal', title: '合计金额', width: 110}
                , {field: 'storePosition', title: '仓库位置', width: 110}
                , {
                    field: 'operator', title: '入库人', width: 120, templet(d) {
                        var userName1;
                        var userID = Number(d.operator);
                        if (!isNaN(userID)) {
                            console.log('userID:' + userID)

                            $.ajax({
                                method: 'post',
                                url: '${pageContext.request.contextPath }/user/findUserById',
                                data: {"userID": userID},
                                async: false,
                                success: function (htq) {
                                    console.log(htq.userName);
                                    if (userID == htq.userID) {
                                        userName1 = htq.userName;
                                    }
                                }
                            });
                            return userName1;
                        }
                    }
                }
                , {
                    field: 'checker', title: '审核人', width: 120, templet(d) {
                        var userName2;
                        var userID = Number(d.checker);
                        if (!isNaN(userID)) {
                            console.log('userID:' + userID)

                            $.ajax({
                                method: 'post',
                                url: '${pageContext.request.contextPath }/user/findUserById',
                                data: {"userID": userID},
                                async: false,
                                success: function (htq) {
                                    console.log(htq.userName);
                                    if (userID == htq.userID) {
                                        userName2 = htq.userName;
                                    }
                                }
                            });
                            return userName2;
                        }
                    }
                }
                , {
                    field: 'storeManager', title: '仓管员', width: 120, templet(d) {
                        var userName3;
                        var userID = Number(d.storeManager);
                        if (!isNaN(userID)) {
                            console.log('userID:' + userID)

                            $.ajax({
                                method: 'post',
                                url: '${pageContext.request.contextPath }/user/findUserById',
                                data: {"userID": userID},
                                async: false,
                                success: function (htq) {
                                    console.log(htq.userName);
                                    if (userID == htq.userID) {
                                        userName3 = htq.userName;
                                    }
                                }
                            });
                            return userName3;
                        }
                    }
                }
                , {
                    field: 'buyer', title: '采购人', width: 120, templet(d) {
                        var userName4;
                        var userID = Number(d.buyer);
                        if (!isNaN(userID)) {
                            console.log('userID:' + userID)

                            $.ajax({
                                method: 'post',
                                url: '${pageContext.request.contextPath }/user/findUserById',
                                data: {"userID": userID},
                                async: false,
                                success: function (htq) {
                                    console.log('采购人' + htq.userName);
                                    if (userID == htq.userID) {
                                        userName4 = htq.userName;
                                    }
                                }
                            });
                            return userName4;
                        }
                    }
                }
                , {
                    field: 'tableMaker', title: '制表人', width: 120, templet(d) {
                        var userName;
                        var userID = Number(d.tableMaker);
                        if (!isNaN(userID)) {
                            console.log('userID:' + userID)

                            $.ajax({
                                method: 'post',
                                url: '${pageContext.request.contextPath }/user/findUserById',
                                data: {"userID": userID},
                                async: false,
                                success: function (htq) {
                                    console.log(htq.userName);
                                    if (userID == htq.userID) {
                                        userName = htq.userName;
                                    }
                                }
                            });
                            return userName;
                        }
                    }
                }

                // , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 180}
            ]]
            , page: false
            , id: 'testInBill'
            , where: {"InBillID": parent.InBillID}
        });

        <%--table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"--%>
        <%--var data = obj.data; //获得当前行数据--%>
        <%--var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）--%>
        <%--var tr = obj.tr; //获得当前行 tr 的DOM对象--%>
        <%--var value = obj.value;--%>
        <%--if (layEvent === 'check') { //修改--%>
        <%--layer.open({--%>
        <%--type: 2,--%>
        <%--title: '审批状态',--%>
        <%--skin: 'layui-layer-rim', //加上边框--%>
        <%--content: '${pageContext.request.contextPath }/itemIn/checkUpdate',--%>
        <%--area: ['380px', '200px'],--%>
        <%--end: function () {--%>
        <%--location.reload();--%>
        <%--}--%>
        <%--});--%>
        <%--}--%>
        <%--});--%>
    });

</script>
</body>
</html>
