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
                    <input type="hidden" name="operator" value="${user.userID}">
                    <div class="layui-row">
                        <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                            <span style="text-align: left;font-size: 25px;">时间：</span>
                            <div class="grid-demo grid-demo-bg1">
                                <input id="InBillTime" type="text" readonly name="InBillTime" style="font-size: 25px; border: 0px" value="${loadtime}"/>
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
                                <input id="InBillID" type="text" class="layui-input" name="InBillID" autocomplete="on" style="font-size: 25px; border: 0px " readonly value="${newInBillID}">
                            </div>
                        </div>
                        </div>

                        <div class="layui-bg-gray" style="margin-top:10px;padding:10px;">
                            <div class="layui-table">
                                <table class="layui-table" id="table">
                                    <tr>
                                        <th>序号</th>
                                        <th>品名</th>
                                        <th>类别</th>
                                        <th>规格</th>
                                        <th>数量</th>
                                        <th>单价</th>
                                        <th>合计</th>
                                        <th>位置</th>
                                        <th>附注</th>
                                        <th>操作</th>
                                    </tr>
                                    <tr id="1">
                                        <td>
                                            1
                                        </td>
                                        <td>
                                            <select id="itemsName" lay-verify="required" name="itemInList[0].GoodsID"
                                                    lay-filter="itemsName1">
                                                <option value="null" selected>请选择</option>
                                            </select>
                                        </td>
                                        <td>
                                            <select id="Category1" lay-verify="required" name="itemInList[0].CategoryID"
                                                    lay-filter="Category1">
                                                <option value="" selected>请选择</option>
                                            </select>
                                        </td>
                                        <td>
                                            <select id="itemsType1" name="itemInList[0].Type" lay-verify="required"
                                                    lay-filter="itemsType1">
                                                <option value="" selected></option>
                                            </select>
                                        </td>
                                        <td>
                                            <input id="itemNum1" name="itemInList[0].ItemNum" onblur="NumCount(this)"
                                                   class="layui-input" type="number" placeholder="数量"/>
                                        </td>
                                        <td>
                                            <input id="itemPrice1" name="itemInList[0].ItemPrice"
                                                   onblur="PriceCount(this)"
                                                   class="layui-input" type="text"/>
                                        </td>
                                        <td>
                                            <input id="itemTotal1" name="itemInList[0].ItemTotal" class="layui-input"
                                                   type="text" readonly="readonly"/>
                                        </td>
                                        <td>
                                            <input id="StorePosition" name="itemInList[0].StorePosition"
                                                   class="layui-input"
                                                   type="text"/>
                                        </td>
                                        <td>
                                            <input id="note1" name="itemInList[0].Note" class="layui-input"
                                                   type="text"/>
                                        </td>
                                        <td>
                                            <div class="layui-form-item">
                                                <button type="button" class="layui-btn layui-btn-disabled">移除</button>
                                            </div>
                                        </td>
                                    </tr>

                                </table>
                                <div style="text-align: center;width: 100% ">
                                    <div class="layui-inline" style="width: 100%">
                                        <i onclick="addTr()" class="layui-btn layui-btn-sm layui-btn-fluid layui-icon layui-icon-down" style="width: 100%"></i>
                                    </div>
                                </div>
                                <div class="layui-row" style="margin: 10px 5px;">
                                <span style="font-size: 22px;">合计：<input id="alTotal" name="alTotal" style="font-size: 22px;border: 0px;width: 100px" readonly />元</span>
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
    <div class="layui-layout-right" style="margin-top: 30px;margin-right: 40px;">
        <button onclick="addBill()" class="layui-btn layui-btn-radius layui-btn-normal" style="margin-right: 40px;">
            完成添加
        </button>
    </div>
</div>

<script>


    function addBill() {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath }/inBill/insertBill",
            data: $("#InBillForm").serialize(),
            success: function () {
                layer.alert("添加成功")
            },
            error: function () {
                layer.alert("请选择物品名称！");
            }
        });
    }

    function NumCount(ii) {
        var altotal = 0;
        var price = $(ii).parent().next().find("input").val();
        var total = $(ii).val();
        $(ii).parent().next().next().find("input").val((price * total).toFixed(2));
        var total = $(ii).parent().next().next().find("input").val();
        var i = 0;
        var length = $("#table").find("tr").length; //行数
        for (i = 1; i < length; i++) {
            var text = $("#table").find("tr").eq(i).find("td").eq(6).find("input").val();
            altotal = Number(altotal) + Number(text);
        }
        $("#alTotal").val(altotal);
        /*   $("#alTotal").empty();
          $("#alTotal").append(altotal);*/
    }

    function PriceCount(ii) {
        var altotal = 0;
        var price = $(ii).val();
        var total = $(ii).parent().prev().find("input").val();
        $(ii).parent().next().find("input").val((price * total).toFixed(2));
        var total = $(ii).parent().next().find("input").val();

        var i = 0;
        var length = $("#table").find("tr").length; //行数
        for (i = 1; i < length; i++) {
            var text = $("#table").find("tr").eq(i).find("td").eq(6).find("input").val();
            altotal = Number(altotal) + Number(text);
            console.log(text);
            console.log(altotal);
        }
        $("#alTotal").val(altotal);
        /* $("#alTotal").empty();
         $("#alTotal").append(altotal);*/
    }

    function getParent(el, parentTag) {
        do {
            el = el.parentNode;
        } while (el && el.tagName !== parentTag);
        return el;
    }

    function delTr(el) {
        el = getParent(el, 'TR');
        var rowIndex = el.rowIndex;

            el = getParent(el, 'TABLE');
            el.deleteRow(rowIndex);
            var altotal = 0;
            var length = $("#table").find("tr").length; //行数
        console.log("length"+length);
            for (i = 1; i < length; i++) {
                var text = $("#table").find("tr").eq(i).find("td").eq(6).find("input").val();
                altotal = Number(altotal) + Number(text);
                console.log(altotal);
            }
            $("#alTotal").val(altotal);
            layui.use(['form'], function () {
                var form = layui.form;
                $("#alTotal").val(altotal);
                form.render();
            })

    }

    /*function delTr() {
        if ($("#table tr").length > 2) {
            var altotal = 0;
            var i = 0;
            $("#table").find("tr").last().remove();
            var length = $("#table").find("tr").length; //行数
            for (i = 1; i < length; i++) {
                var text = $("#table").find("tr").eq(i).find("td").eq(6).find("input").val();
                altotal = Number(altotal) + Number(text);
            }
            $("#alTotal").val(altotal);

        } else {
            layer.alert("删除失败！");
        }

    }*/


    /**
     * 新增一行物品数据
     */
    function addTr() {
        var itemsName = $("#itemsName").html();          //拿到已加载好的物品名称信息
        //改变序号
        var trl = document.getElementsByTagName("tr").length;
        var lastTr = document.getElementsByTagName("tr")[trl-1];
        var num = lastTr.cells[0].innerHTML
        num = Number(num)+Number(1)

        //拼接字符串及以参数的形式给select的lay-filter，name重新赋值
        var tr = "<tr id=" + num + " >" +
            "<td>" + num + "</td>" +
            "<td>" +
            "<div class=\"layui-form-item\">" +
            "<select value=\"null\" lay-verify=\"required\" id=\"itemsName\" name=\"itemInList[" + (num - 1) + "].GoodsID\" lay-filter=\"itemsName" + num + "\">" +
            itemsName +
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
            "<input id=\"itemNum" + num + "\" name=\"itemInList[" + (num - 1) + "].ItemNum\" onblur=\"NumCount(this)\" class=\"layui-input\" type=\"number\" placeholder=\"数量\"/>" +
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
                    data: {itemsName: data.value},
                    dataType: "json",
                    async: false,
                    cache: false,
                    success: function (result) {
                        $.each(result, function (index, item) {
                            $('select[name="' + itemsType + '"]').append("<option value='" + item.itemsType + "'>" + item.itemsType + "</option>");
                            $('select[name="' + Category + '"]').append("<option value='" + item.categoryID + "'>" + item.categoryID + "</option>")
                        });
                        var itemsTypeVal = $('select[name="' + itemsType + '"]').find("option:selected").text();
                        $.ajax({
                            type: "post",
                            url: "${pageContext.request.contextPath }/goods/findGoodsByItemsNameAndItemsType",
                            data: {itemsType: itemsTypeVal,itemsName:data.value},
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

                var itemsNameVal =  $('select[name="' + goodsID + '"]').find("option:selected").text();
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/goods/findGoodsByItemsNameAndItemsType",
                    data: {itemsType: data.value,itemsName:itemsNameVal},
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
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath }/goods/findGoodsByItemsName",
                data: {itemsName: data.value},
                dataType: "json",
                async: false,
                cache: false,
                success: function (result) {
                    $.each(result, function (index, item) {
                        $('select[name="itemInList[0].Type"]').append("<option value='" + item.itemsType + "'>" + item.itemsType + "</option>")
                        $('select[name="itemInList[0].CategoryID"]').append("<option value='" + item.categoryID + "'>" + item.categoryID + "</option>")
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
                    })
                    form.render();
                }
            })

        });

    });

    layui.use('form', function ($, form) {
        var $ = layui.$;//重点在layui中引用JQ必须写这一句
        var form = layui.form;

        form.on('select(itemsType1)', function (data) {

            var itemsNameVal = $('select[name="itemInList[0].GoodsID"]').find("option:selected").text();
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath }/goods/findGoodsByItemsNameAndItemsType",
                data: {itemsType: data.value,itemsName:itemsNameVal},
                dataType: "json",
                success: function (result) {
                    console.log(result.goodsID);
                    $('select[name="itemInList[0].GoodsID"]').find("option:selected").val(result.goodsID);
                    form.render();
                }
            })

        })
    });

    layui.use(["jquery", "upload", "form", "layer", "element"], function () {
        var $ = layui.$,
            form = layui.form;


        //查询物品名称
        $.ajax({
            type: "POST",
            url: '${pageContext.request.contextPath }/goods/getGoodsName',  //从数据库查询返回的是个list
            dataType: "json",
            async: false,
            cache: false,
            success: function (data) {
                $.each(data, function (index, item) {
                    $("#itemsName").append("<option value='" + item.itemsName + "'>" + item.itemsName + "</option>");//往下拉菜单里添加元素
                })
                form.render();//菜单渲染 把内容加载进去
            }
        });

        //查询供应商
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

        //查询仓管员
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

        //查询采购人
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

        //查询审批人
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

        //查询制表人
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
    });
</script>
</body>

</html>
