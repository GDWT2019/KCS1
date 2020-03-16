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
<div class="">
    <div class="layui-col-lg12" style="padding: 10px 10px;">
        <div class=" layui-col-lg12" style="padding: 10px 15px; border-radius: 5px;">
            <div class="layui-col-lg12" style="text-align: center; font-size: 30px;"><span>入库单</span></div>
            <div class="layui-col-lg12 " style="margin:30px 0;padding:10px;border-radius: 5px;">
                <form class="layui-form" id="InBillForm" type="post">
                    <input type="hidden" name="operator" value="${user.userID}"/>
                    <div class="layui-row">
                        <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                            <span style="text-align: left;font-size: 25px;">修改时间：</span>
                            <div class="grid-demo grid-demo-bg1">
                                <input id="InBillTime" type="text" readonly name="InBillTime"
                                       style="font-size: 25px;border: 0px" value="${loadtime}"/>
                            </div>
                        </div>
                        <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                            <span style="text-align: center;font-size: 25px;">供应商：</span>
                            <div class="grid-demo layui-bg-red" style="width: 300px">
                                <select id="providerID" name="providerID" lay-verify="required" lay-search=""></select>
                            </div>
                        </div>
                        <div class="layui-col-xs4 layui-col-sm12 layui-col-md4">
                            <span style="font-size: 25px;">编号：</span>
                            <div class="grid-demo layui-bg-blue" style="width: 300px">
                                <input id="InBillID" type="text" class="layui-input" name="InBillID" autocomplete="on"
                                       style="font-size: 25px; border: 0px " readonly>
                            </div>
                        </div>
                    </div>
                    <div class="layui-bg-gray" style="margin-top:10px;padding:10px;">
                        <div class="layui-table">
                            <table class="layui-table" id="table">
                                <tr>
                                    <th>序号</th>
                                    <th width="120px">品名</th>
                                    <th>类别</th>
                                    <th>规格</th>
                                    <th>数量</th>
                                    <th>单价</th>
                                    <th>合计</th>
                                    <th>位置</th>
                                    <th>附注</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${itemInList}" var="inBillPresent" varStatus="status">
                                    <tr>
                                        <td>
                                                ${status.count}
                                        </td>
                                        <td>
                                            <select id="itemsName${status.count}" lay-verify="required"
                                                    name="itemInList[${status.count-1}].GoodsID"
                                                    lay-filter="itemsName${status.count}">
                                                <%--<option value="${inBillPresent.itemsName}" > ${inBillPresent.itemsName} </option>--%>
                                            </select>

                                            <script>
                                                layui.use(["jquery", "upload", "form", "layer", "element"], function () {
                                                    var $ = layui.$,
                                                        form = layui.form;

                                                    var itemsName="${inBillPresent.itemsName}";
                                                    //查询物品名称
                                                    $.ajax({
                                                        type: "POST",
                                                        url: '${pageContext.request.contextPath }/goods/getGoodsName',  //从数据库查询返回的是个list
                                                        dataType: "json",
                                                        async: false,
                                                        success: function (data) {
                                                            $.each(data, function (index, item) {
                                                                if(itemsName==item.itemsName){
                                                                $("#itemsName${status.count}").append("<option selected value='" + item.itemsName + "'>"+ item.itemsName +"</option>");//往下拉菜单里添加元素
                                                                }
                                                                else {
                                                                    $("#itemsName${status.count}").append("<option  value='" + item.itemsName + "'>"+ item.itemsName +"</option>");//往下拉菜单里添加元素

                                                                }
                                                            });
                                                            form.render();//菜单渲染 把内容加载进去
                                                        }
                                                    });



                                                    form.on('select(itemsType${status.count})', function (data) {
                                                        var itemsNameVal = $('select[name="' + goodsID + '"]').find("option:selected").text();
                                                        $.ajax({
                                                            type: "post",
                                                            url: "${pageContext.request.contextPath }/goods/findGoodsByItemsNameAndItemsType",
                                                            data: {itemsType: data.value, itemsName: itemsNameVal},
                                                            dataType: "json",
                                                            success: function (result) {
                                                                console.log(result.goodsID);
                                                                $('select[name="' + goodsID + '"]').find("option:selected").val(result.goodsID);
                                                                form.render();
                                                            }
                                                        })
                                                    });

                                                    //选择物品后，根据物品名称查询该物品的规格，并更新对应的规格
                                                    form.on('select(itemsName${status.count})', function (data) {
                                                        var itemsType = "itemInList[${status.count-1}].Type";
                                                        var Category = "itemInList[${status.count-1}].CategoryID";
                                                        var goodsID = "itemInList[${status.count-1}].GoodsID";
                                                        $('select[name="' + itemsType + '"]').empty();
                                                        form.render();
                                                        if (data.value == "null") {
                                                            $('select[name="' + itemsType + '"]').append("<option value=\"null\" selected> </option>");
                                                            form.render();
                                                            return false;
                                                        }
                                                        $('select[name="' + Category + '"]').empty();
                                                        form.render();
                                                        if (data.value == "null") {
                                                            $('select[name="' + Category + '"]').append("<option value=\"null\" selected> </option>");
                                                            form.render();
                                                            return false;
                                                        }

                                                        $.ajax({
                                                            type: "post",
                                                            url: "${pageContext.request.contextPath }/goods/findGoodsByItemsName",
                                                            data: {itemsName: this.innerText},
                                                            dataType: "json",
                                                            async: false,
                                                            cache: false,
                                                            success: function (result) {
                                                                $.each(result, function (index, item) {
                                                                    $('select[name="' + itemsType + '"]').append("<option value='" + item.itemsType + "'>" + item.itemsType + "</option>");
                                                                    $('select[name="' + Category + '"]').append("<option value='" + item.categoryID + "'>" + item.categories[0].categoryName + "</option>")
                                                                });
                                                                var itemsTypeVal = $('select[name="' + itemsType + '"]').find("option:selected").text();
                                                                $.ajax({
                                                                    type: "post",
                                                                    url: "${pageContext.request.contextPath }/goods/findGoodsByItemsNameAndItemsType",
                                                                    data: {itemsType: itemsTypeVal, itemsName: data.value},
                                                                    dataType: "json",
                                                                    success: function (result) {
                                                                        console.log(result.goodsID);
                                                                        $('select[name="' + goodsID + '"]').find("option:selected").val(result.goodsID);
                                                                        form.render();
                                                                    }
                                                                });
                                                                form.render();
                                                            }
                                                        })
                                                    });

                                                    var itemsType = "itemInList[${status.count-1}].Type";
                                                    <%--var Category = "itemInList[${status.count-1}].CategoryID";--%>
                                                    var goodsID = "itemInList[${status.count-1}].GoodsID";

                                                    var itemsTypeVal = $('select[name="' + itemsType + '"]').find("option:selected").val();
                                                    var itemsNameVal = $('select[name="' + goodsID + '"]').find("option:selected").val();
                                                    $.ajax({
                                                        type: "post",
                                                        url: "${pageContext.request.contextPath }/goods/findGoodsByItemsNameAndItemsType",
                                                        data: {itemsType: itemsTypeVal, itemsName: itemsNameVal},
                                                        dataType: "json",
                                                        success: function (result) {
                                                            console.log(result.goodsID);
                                                            $('select[name="' + goodsID + '"]').find("option:selected").val(result.goodsID);
                                                            form.render();
                                                        }
                                                    });
                                                    <%--var goodsID ="${inBillPresent.goodsID}";--%>
                                                    <%--console.log(goodsID);--%>
                                                    <%--$("#itemsName${status.count}").val(goodsID);--%>
                                                    <%--form.render();//菜单渲染 把内容加载进去--%>
                                                });
                                            </script>
                                        </td>
                                        <td>
                                            <select name="itemInList[${status.count-1}].CategoryID"
                                                    lay-verify="required" lay-filter="Category${status.count}">
                                                <option value="${inBillPresent.categoryID}"> ${inBillPresent.categoryName} </option>
                                                    <%--<option value="" selected></option>--%>
                                            </select>
                                        </td>
                                        <td>
                                            <select id="itemsType${status.count}" name="itemInList[${status.count-1}].Type" lay-verify="required"
                                                    lay-filter="itemsType${status.count}">
                                                <%--<option value="${inBillPresent.type}"  > ${inBillPresent.type} </option>--%>
                                                    <%--<option value="" selected></option>--%>
                                            </select>

                                            <script>
                                                var goodsID = "itemInList[${status.count-1}].GoodsID";
                                                var itemsNameVal ='${inBillPresent.itemsName}';

                                                //通过物品名称查询规格
                                                $.ajax({
                                                    type: "POST",
                                                    url: '${pageContext.request.contextPath }/goods/findGoodsByItemsName',  //从数据库查询返回的是个list
                                                    data: {itemsName: itemsNameVal},
                                                    dataType: "json",
                                                    async: false,
                                                    success: function (data) {
                                                        $.each(data, function (index, item) {
                                                            $("#itemsType${status.count}").append("<option value='" + item.itemsType + "'>" + item.itemsType + "</option>");//往下拉菜单里添加元素
                                                        });
                                                        form.render();//菜单渲染 把内容加载进去
                                                    }
                                                });
                                            </script>
                                        </td>
                                        <td>
                                            <input name="itemInList[${status.count-1}].ItemNum" class="layui-input" onblur="NumCount(this)"
                                                   type="number" placeholder="数量" min="1"
                                                   value="${inBillPresent.itemNum}"/>

                                        </td>
                                        <td>
                                            <input name="itemInList[${status.count-1}].ItemPrice" class="layui-input"  onblur="PriceCount(this)"
                                                   type="text" value="${inBillPresent.itemPrice}"/>
                                        </td>
                                        <td>
                                            <input id="itemTotal1" name="itemInList[${status.count-1}].ItemTotal" class="layui-input"
                                                   type="text" readonly value="${inBillPresent.itemTotal}"/>
                                        </td>
                                        <td>

                                            <input name="itemInList[${status.count-1}].StorePosition"
                                                   class="layui-input" type="text" placeholder="仓库位置"
                                                   value="${inBillPresent.storePosition}"/>

                                        </td>
                                        <td>
                                            <input name="itemInList[${status.count-1}].Note" class="layui-input"
                                                   type="text" placeholder="" value="${inBillPresent.note}"/>
                                        </td>
                                        <td>
                                            <div class="layui-form-item">
                                                <button type="button" onclick="delTr(this)"
                                                        class="layui-btn layui-btn-danger">移除
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="text-align: center;width: 100% ">
                                <div class="layui-inline" style="width: 100%">
                                    <i onclick="addTr()"
                                       class="layui-btn layui-btn-sm layui-btn-fluid layui-icon layui-icon-down"
                                       style="width: 100%"></i>
                                </div>
                            </div>
                            <div class="layui-row" style="margin: 10px 5px;">
                                <span style="font-size: 22px;">合计：<input  id="alTotal" name="alTotal" style="font-size: 22px;border:0px; width: 100px" readonly/>元</span>
                                <%--<span style="font-size: 22px;">合计：<input  id="alTotal" name="alTotal" style="font-size: 22px;border:0px; width: 100px" readonly/>元</span>--%>
                            </div>
                        </div>
                        <div class="layui-bg-gray" style="border-radius: 2px;">
                            <div class="layui-row">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">仓管员：</label>
                                    <div class="layui-input-inline">
                                        <select id="warehouse" name="warehouse" lay-verify="required" lay-search="">

                                        </select>
                                    </div>
                                    <label class="layui-form-label">采购人：</label>
                                    <div class="layui-input-inline">
                                        <select id="buyer" name="buyer" lay-verify="required" lay-search="">

                                        </select>
                                    </div>
                                </div>
                                <div class="layui-row">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">审批人：</label>
                                        <div class="layui-input-inline">
                                            <select id="Approvaler" name="Approvaler" lay-verify="required"
                                                    lay-search="">

                                            </select>
                                        </div>
                                        <label class="layui-form-label">制表人：</label>
                                        <div class="layui-input-inline">
                                            <select id="lister" name="lister" lay-verify="required" lay-search="">

                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div  style="float: right; margin-right: 30px;">
            <button onclick="updateBill()" class="layui-btn layui-btn-lg">
                修改入库单
            </button>
        </div>
    </div>
</div>
<script>

    layui.use(['form'], function () {
        var form = layui.form;
        form.render();   //重新渲染新增的行中select的信息
    });

    function updateBill() {
       $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath }/inBill/checkInAmountbiggerOutAmonut",
            data: $("#InBillForm").serialize(),
            success: function (htq) {
                if (htq == 1) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath }/inBill/updateBill",
                        data: $("#InBillForm").serialize(),
                        success: function () {
                            layer.alert("修改成功",function(){
                                window.parent.layer.closeAll();
                            });
                        },
                        error: function () {
                            layer.alert("修改失败！");
                        }
                    });
                }else{
                    layer.alert("入库数小于出库数！");
                }
            },
            error: function () {
                layer.alert("修改失败！");
            }
        });


    }

    function NumCount(ii) {
        var altotal = 0;
        var price = $(ii).parent().next().find("input").val();
        var total = $(ii).val();
        if(total>0){
            $(ii).parent().next().next().find("input").val((price * total).toFixed(2));
        }else {

            layer.tips("数量格式错误！需要大于等于1", ii, {
                tips: [1, "#2B2B2B"]
            });
            $(ii).val(1);
            var val = $(ii).val();
            $(ii).parent().next().next().find("input").val((price * val).toFixed(2));
        }

        var total = $(ii).parent().next().next().find("input").val();
        var i = 0;
        var length = $("#table").find("tr").length; //行数
        for (i = 1; i < length; i++) {
            var text = $("#table").find("tr").eq(i).find("td").eq(6).find("input").val();
            altotal = Number(altotal) + Number(text);
        }
        $("#alTotal").val(altotal.toFixed(2));

    }

    function PriceCount(ii) {
        var altotal = 0;
        var price = $(ii).val();
        var total = $(ii).parent().prev().find("input").val();
        if(price>0){
            $(ii).parent().next().find("input").val((price * total).toFixed(2));
        }else{
            layer.tips("价格格式错误！需要大于0", ii, {
                tips: [1, "#2B2B2B"]
            });
            $(ii).val(1);
            var val = $(ii).val();
            $(ii).parent().next().find("input").val((val * total).toFixed(2));
        }
        var total = $(ii).parent().next().find("input").val();

        var i = 0;
        var length = $("#table").find("tr").length; //行数
        for (i = 1; i < length; i++) {
            var text = $("#table").find("tr").eq(i).find("td").eq(6).find("input").val();
            altotal = Number(altotal) + Number(text);

        }
        $("#alTotal").val(altotal.toFixed(2));
    }

    function getParent(el, parentTag) {
        do {
            el = el.parentNode;
        } while (el && el.tagName !== parentTag);
        return el;
    }

    function delTr(el) {
        var length1 = $("#table").find("tr").length;
        for (i = 1; i < length1; i++) {
            var itemsName = $("#table").find("tr").eq(i).find("td").eq(1).find("input").val();
            var itemsType = $("#table").find("tr").eq(i).find("td").eq(3).find("input").val();
            var itemsNum = $("#table").find("tr").eq(i).find("td").eq(4).find("input").val();
        }

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath }/inBill/checkInAmountbiggerOutAmonutForRemove",
            data: {"itemsType": itemsType, "itemsName": itemsName,"itemsNum":itemsNum},
            success: function (htq) {
                if (htq == 1) {
                    el = getParent(el, 'TR');
                    var rowIndex = el.rowIndex;
                    if (rowIndex > 1) {
                        el = getParent(el, 'TABLE');
                        el.deleteRow(rowIndex);
                        var altotal = 0;
                        var length = $("#table").find("tr").length; //行数
                        console.log("length" + length);
                        for (i = 1; i < length; i++) {
                            var text = $("#table").find("tr").eq(i).find("td").eq(6).find("input").val();
                            altotal = Number(altotal) + Number(text);

                        }
                        $("#alTotal").val(altotal.toFixed(2));
                        layui.use(['form'], function () {
                            var form = layui.form;
                            $("#alTotal").val(altotal.toFixed(2));
                            form.render();
                        })
                    }
                    else {
                        layer.alert("删除失败！");
                    }
                }else{
                    layer.alert("入库数小于出库数！");
                }
            },
            error: function () {
                layer.alert("修改失败！");
            }
        });



    }

    $("#InBillID").val(parent.InBillID);

    //将用户的ID和供应商ID和时间回显

    function addTr() {
        var itemsName = $("#itemsName").html();          //拿到已加载好的物品名称信息

        //改变序号
        var trl = document.getElementsByTagName("tr").length;
        var num=null;
        if(trl>1){

        var lastTr = document.getElementsByTagName("tr")[trl - 1];
        num = lastTr.cells[0].innerHTML;
        num = Number(num) + Number(1);
        }
        else {
            num = Number(1);
        }

        //拼接字符串及以参数的形式给select的lay-filter，name重新赋值
        var tr = "<tr id=" + num + " >" +
            "<td>" + num + "</td>" +
            "<td>" +
            "<div class=\"layui-form-item\">" +
            "<select value=\"null\" lay-verify=\"required\" id=\"itemsName" + num + "\" name=\"itemInList[" + (num - 1) + "].GoodsID\" lay-filter=\"itemsName" + num + "\">" +
            "<option value=\"\" selected> </option>" +
            "</select>" +
            "</div>" +
            "</td>" +
            "<td>" +
            "<div class=\"layui-form-item\">" +
            "<select  id=\"Category" + num + "\" name=\"itemInList[" + (num - 1) + "].CategoryID\" lay-verify=\"required\" lay-filter=\"Category" + num + "\">" +
            "<option value=\"\" selected> </option>" +
            "</select>" +
            "</div>" +
            "</td>" +
            "<td>" +
            "<div class=\"layui-form-item\">" +
            "<select  id=\"itemsType" + num + "\" name=\"itemInList[" + (num - 1) + "].Type\" lay-verify=\"required\" lay-filter=\"itemsType" + num + "\">" +
            "<option value=\"\" selected> </option>" +
            "</select>" +
            "</div>" +
            "</td>" +
            "<td>" +
            "<input id=\"itemNum" + num + "\" name=\"itemInList[" + (num - 1) + "].ItemNum\"  min=\"1\" onblur=\"NumCount(this)\" class=\"layui-input\" type=\"number\" placeholder=\"数量\"/>" +
            "</td>" +
            "<td>" +
            "<input id=\"itemPrice" + num + "\" name=\"itemInList[" + (num - 1) + "].ItemPrice\" onblur=\"PriceCount(this)\" class=\"layui-input\" type=\"text\" />" +
            "</td>" +
            "<td>" +
            "<input id=\"itemTotal" + num + "\" name=\"itemInList[" + (num - 1) + "].ItemTotal\" class=\"layui-input\"  type=\"text\" readonly=\"readonly\" />" +
            "</td>" +
            "<td>" +
            "<input id=\"StorePosition" + num + "\" name=\"itemInList[" + (num - 1) + "].StorePosition\" class=\"layui-input\"  type=\"text\" />" +
            "</td>" +
            "<td>" +
            "<div class=\"layui-form-item\">" +
            "<input id=\"note" + num + "\" name=\"itemInList[" + (num - 1) + "].Note\" class=\"layui-input\" type=\"text\"  >" +
            "</div>" +
            "</td>" +
            "<td>" +
            "<div class=\"layui-form-item\">" +
            "<button type=\"button\" class=\"layui-btn layui-btn-danger\" onclick=\"delTr(this)\">移除</button>" +
            "</div>" +
            "</td>" +
            "</tr>";
        $("#table").append(tr);

        layui.use(['form'], function () {
            var form = layui.form;
            form.render();   //重新渲染新增的行中select的信息

            //查询物品名称
            $.ajax({
                type: "POST",
                url: '${pageContext.request.contextPath }/goods/getGoodsName',  //从数据库查询返回的是个list
                dataType: "json",
                async: false,
                cache: false,
                success: function (data) {
                    $.each(data, function (index, item) {
                        $("#itemsName"+num).append("<option value='" + item.itemsName + "'>" + item.itemsName + "</option>");//往下拉菜单里添加元素
                    });
                    form.render();//菜单渲染 把内容加载进去
                }
            });

            //选择物品后，根据物品名称查询该物品的规格，并更新对应的规格
            form.on('select(itemsName' + num + ')', function (data) {
                var itemsType = "itemInList[" + (num - 1) + "].Type";
                var Category = "itemInList[" + (num - 1) + "].CategoryID";
                var goodsID = "itemInList[" + (num - 1) + "].GoodsID";
                $('select[name="' + itemsType + '"]').empty();
                form.render();
                if (data.value == "null") {
                    $('select[name="' + itemsType + '"]').append("<option value=\"null\" selected> </option>");
                    form.render();
                    return false;
                }
                $('select[name="' + Category + '"]').empty();
                form.render();
                if (data.value == "null") {
                    $('select[name="' + Category + '"]').append("<option value=\"null\" selected> </option>");
                    form.render();
                    return false;
                }

                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/goods/findGoodsByItemsName",
                    data: {itemsName: this.innerText},
                    dataType: "json",
                    async: false,
                    cache: false,
                    success: function (result) {
                        $.each(result, function (index, item) {
                            $('select[name="' + itemsType + '"]').append("<option value='" + item.itemsType + "'>" + item.itemsType + "</option>");
                            $('select[name="' + Category + '"]').append("<option value='" + item.categoryID + "'>" + item.categories[0].categoryName + "</option>")
                        });
                        var itemsTypeVal = $('select[name="' + itemsType + '"]').find("option:selected").text();
                        $.ajax({
                            type: "post",
                            url: "${pageContext.request.contextPath }/goods/findGoodsByItemsNameAndItemsType",
                            data: {itemsType: itemsTypeVal, itemsName: data.value},
                            dataType: "json",
                            success: function (result) {
                                console.log(result.goodsID);
                                $('select[name="' + goodsID + '"]').find("option:selected").val(result.goodsID);
                                form.render();
                            }
                        });
                        form.render();
                    }
                })
            })
        });

        layui.use('form', function ($, form) {
            var $ = layui.$;//重点在layui中引用JQ必须写这一句
            var form = layui.form;
            var goodsID = "itemInList[" + (num - 1) + "].GoodsID";
            form.on('select(itemsType' + num + ')', function (data) {

                var itemsNameVal = $('select[name="' + goodsID + '"]').find("option:selected").text();
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/goods/findGoodsByItemsNameAndItemsType",
                    data: {itemsType: data.value, itemsName: itemsNameVal},
                    dataType: "json",
                    success: function (result) {
                        console.log(result.goodsID);
                        $('select[name="' + goodsID + '"]').find("option:selected").val(result.goodsID);
                        form.render();
                    }
                })

            })
        });
    }

    layui.use('form', function ($, form) {
        var $ = layui.$;//重点在layui中引用JQ必须写这一句
        var form = layui.form;

        form.on('select(itemsName1)', function (data) {
            $('select[name="itemInList[0].Type"]').empty();
            form.render()
            if (data.value == "null") {
                $('select[name="itemInList[0].Type"]').append("<option value=\"null\" selected> </option>");
                form.render()
                return false;
            }
            $('select[name="itemInList[0].CategoryID"]').empty();
            form.render()
            if (data.value == "null") {
                $('select[name="itemInList[0].CategoryID"]').append("<option value=\"null\" selected> </option>");
                form.render()
                return false;
            }
            console.log("test:"+ this.innerText);
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath }/goods/findGoodsByItemsName",
                data: {itemsName: this.innerText},
                dataType: "json",
                async: false,
                cache: false,
                success: function (result) {
                    $.each(result, function (index, item) {
                        $('select[name="itemInList[0].Type"]').append("<option value='" + item.itemsType + "'>" + item.itemsType + "</option>")
                        $('select[name="itemInList[0].CategoryID"]').append("<option value='" + item.categoryID + "'>" + item.categories[0].categoryName + "</option>")
                    });
                    var itemsTypeVal = $('select[name="itemInList[0].Type"]').find("option:selected").text();
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath }/goods/findGoodsByItemsNameAndItemsType",
                        data: {itemsType: itemsTypeVal,itemsName:data.value},
                        dataType: "json",
                        success: function (result) {
                            console.log(result.goodsID);
                            $('select[name="itemInList[0].GoodsID"]').find("option:selected").val(result.goodsID);
                            form.render();
                        }
                    });
                    form.render();
                }
            })

        });

    });

    layui.use(["jquery", "upload", "form", "layer", "element"], function () {
        var $ = layui.$,
            form = layui.form;

        $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/provider/getProvider',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#providerID").append("<option value='" + item.providerID + "'>" + item.providerName + "</option>");//往下拉菜单里添加元素
            })
            form.render();//菜单渲染 把内容加载进去
        }
    });

    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/user/getWarehouse',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#warehouse").append("<option value='" + item.userID + "'>" + item.userName + "</option>");//往下拉菜单里添加元素
            })
            form.render();//菜单渲染 把内容加载进去
        }
    });

    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/user/getAlluser',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#buyer").append("<option value='" + item.userID + "'>" + item.userName + "</option>");//往下拉菜单里添加元素
            })
            form.render();//菜单渲染 把内容加载进去
        }
    });

    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/user/getAlluser',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#Approvaler").append("<option value='" + item.userID + "'>" + item.userName + "</option>");//往下拉菜单里添加元素
            })
            form.render();//菜单渲染 把内容加载进去
        }
    });

    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath }/user/getAlllister',  //从数据库查询返回的是个list
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#lister").append("<option value='" + item.userID + "'>" + item.userName + "</option>");//往下拉菜单里添加元素
            });
            form.render();//菜单渲染 把内容加载进去
        }
    });

    $.ajax({
        method: 'post',
        url: "${pageContext.request.contextPath }/itemIn/valueIDandTime",
        data: {"InBillID": parent.InBillID},
        success: function (htq) {
            $("#buyer").val(htq[0].buyer);
            $("#warehouse").val(htq[0].storeManager);
            $("#Approvaler").val(htq[0].checker);
            $("#lister").val(htq[0].tableMaker);
            $("#providerID").val(htq[0].providerID);
            $("#alTotal").val(htq[0].allTotal.toFixed(2));
            form.render();   //重新渲染新增的行中select的信息
        }
    });
    });
</script>
</body>

</html>
