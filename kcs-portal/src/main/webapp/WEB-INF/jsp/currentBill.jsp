<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/layui_exts/excel.js"></script>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath }/static/tablePlug/tablePlug.css" type="text/css"/>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>
</head>

<div class="demoTable">
    物品名称
    <div class="layui-inline">
        <input type="text" class="layui-input" id="itemName" placeholder="请输入物品名">
    </div>
    时间范围：
    <div class="layui-inline">
        <input type="text" class="layui-input" id="timeRange" placeholder="请选择时间段">
    </div>
    <input  type="button" class="layui-btn" id="search" value="搜索">
    <a href="${pageContext.request.contextPath }/summary/poiSummary" class="layui-btn">导出</a>
    <%--<button class="layui-btn" data-type="reload">搜索</button>--%>
</div>

<table class="layui-table" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-form">
        <div class="layui-form-item">
        </div>
    </div>

    <!--导出表 不展示-->
    <div style="display: none;">
        <table id="data_export" lay-filter="data_export">
        </table>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="inBill">入库记录</a>
    <a class="layui-btn layui-btn-xs" lay-event="outBill">出库记录</a>
</script>
<%--<script src="${pageContext.request.contextPath }/static/tablePlug/tablePlug.js"></script>--%>

<script>

    function load(){
        $.get("${pageContext.request.contextPath }/summary/poiSummary");
    }

    layui.config({
        base: '${pageContext.request.contextPath }/static/tablePlug/'
    }).extend({ //设定组件别名
        tablePlug: 'tablePlug'
    });

    <%--layui.config({base:'${pageContext.request.contextPath }/static/tablePlug/'}).use(["jquery", "upload", "form","layer","element","table","laydate","tablePlug"], function () {--%>
    layui.use(["jquery", "upload","layer","table","laydate","tablePlug"], function () {
        var $ = layui.$,
            table = layui.table;
        var laydate = layui.laydate;
        var tablePlug = layui.tablePlug;
        tablePlug.smartReload.enable(true);//处理不闪动的关键代码
            //日期范围
            //日期时间范围
            laydate.render({
                elem: '#timeRange'
                ,type: 'month'
                ,range: true
            });


        layui.config({
            base: "${pageContext.request.contextPath}/js/layui_exts/"
        }).extend({
            excel: 'excel'
        });

        table.render({
            elem: '#test'
            , url: "${pageContext.request.contextPath }/summary/summaryAllCurrentdata"
            , toolbar: '#toolbarDemo'
            , title: '流水单'
            , totalRow: false//开启合计行
            , smartReloadModel:true
            , cols: [
                [
                    {type: 'numbers',title: '序号', fixed: 'left', rowspan: 2},
                    {field: 'time', title: '时间', width: 150,fixed: 'left',rowspan: 2},
                    {title: '物品', colspan: 3, align: 'center'},
                    {title: '上月结存', colspan: 3, align: 'center'},
                    {title: '本月入库', colspan: 3, align: 'center'},
                    {title: '本月出库', colspan: 3, align: 'center'},
                    {title: '本月结存', colspan: 3, align: 'center'},
                    {fixed: 'right',type: 'toolbar',  title: '操作', width: 180, align: 'center', toolbar: '#barDemo', rowspan: 2}
                ]
                , [ //表头
                    {field: 'categoryName', title: '类别', width: 150}
                    , {field: 'itemsName', title: '品名', width: 150}
                    , {field: 'itemsType', title: '型号规格', width: 150}
                    , {field: 'preAmount', title: '数量', width: 150}
                    , {field: 'prePrice', title: '单价', width: 150}
                    , {field: 'preTotal', title: '金额', width: 150}
                    , {field: 'inAmount', title: '数量', width: 150}
                    , {field: 'inPrice', title: '单价', width: 150}
                    , {field: 'inTotal', title: '金额', width: 150}
                    , {field: 'outAmount', title: '数量', width: 150}
                    , {field: 'outPrice', title: '单价', width: 150}
                    , {field: 'outTotal', title: '金额', width: 150}
                    , {field: 'thisAmount', title: '数量', width: 150}
                    , {field: 'thisPrice', title: '单价', width: 150}
                    , {field: 'thisTotal', title: '金额', width: 150}
                ]
            ]
            , where: {"time1":null,"time2":null,"itemName": null}
            , page: true
            , limit: 10
            , limits: [1, 5, 10, 20, 30, 50]
            , id: 'testSummaryCurrent'
        });

        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            var value = obj.value;
            if (layEvent === 'inBill') { //该物品的入库记录
                window.goodsID = data.goodsID;
                var index = layer.open({
                    type: 2,
                    title: ['入库记录', 'font-size:18px;text-align: center;'],
                    content: '${pageContext.request.contextPath }/inBill/rItemInRecord',
                    area: ['1200px', '668px'],
                    end: function () {

                    }
                });
                // layer.full(index);
            } else if (layEvent === 'outBill') { //该物品的出库记录
                // layer.alert(data.inBillID);
                window.goodsID = data.goodsID;
                layer.open({
                    type: 2,
                    title: ['出库记录', 'font-size:18px;text-align: center;'],
                    content: '${pageContext.request.contextPath }/outBill/rItemOutRecord',
                    area: ['1200px', '668px'],
                    end: function () {

                    }
                });
            } else if (layEvent === 'del') { //删除
                layer.confirm('确定删除吗？', {
                    btn: ['确定', '取消'] //可以无限个按钮
                    , btn1: function (index, layero) {
                        $.ajax({
                            url: '${pageContext.request.contextPath }/itemIn/delItem'
                            , data: {"ItemsInID": data.itemsInID},
                            success: function () {
                                layer.msg("删除成功！");
                            },
                            error: function () {
                                layer.msg("删除失败！");
                            }
                        });
                    }
                    , btn2: function (index, layero) {
                        layer.close();
                    }
                    , end: function () {
                        location.reload();
                    }
                });
            }
        });

        $('body').on('click', "#search", function () {
            // 搜索条件
            var time1 =null;
            var time2 =null;
            var itemName = null;
            var timeRange = $('#timeRange').val();
            if (($("#itemName").val()) != null && ($("#itemName").val()) != "") {
                itemName = $("#itemName").val();
            }
            if( timeRange!=null && timeRange!="") {
                time1 = timeRange.substring(0,7);
                time2 = timeRange.substring(10,17);
            }
            table.reload('testSummaryCurrent', {
                method: 'post'
                , where: {
                    "time1": time1,
                    "time2": time2,
                    "itemName": itemName,
                }
                , page: {
                    curr: 1
                }
            });
        });


        $('body').on('click', "#daochu", function () {
            // $('#daochu').click(function(){
            $.ajax({
                url: "${pageContext.request.contextPath }/summary/summaryAllData",
                dataType: 'json',
                success: function (res) {
                    // 假如返回的 res.data 是需要导出的列表数据

                    res.data.unshift({
                        categoryName: '物品', itemsName: '品名', itemsType: '规格'
                        , preAmount: '上月结存', prePrice: '单价', preTotal: '金额'
                        , inAmount: '本月入库', inPrice: '单价', inTotal: '金额'
                        , outAmount: '本月出库', outPrice: '单价', outTotal: '金额'
                        , thisAmount: '本月结存', thisPrice: '单价', thisTotal: '金额'
                        , time: '时间'
                    }, {
                        categoryName: '类别', itemsName: '品名', itemsType: '规格'
                        , preAmount: '数量', prePrice: '单价', preTotal: '金额'
                        , inAmount: '数量', inPrice: '单价', inTotal: '金额'
                        , outAmount: '数量', outPrice: '单价', outTotal: '金额'
                        , thisAmount: '数量', thisPrice: '单价', thisTotal: '金额'
                        , time: '时间'
                    });


                    var mergeConf = LAY_EXCEL.makeMergeConfig([
                        ['A1', 'C1'],
                        ['D1', 'F1'],
                        ['G1', 'I1'],
                        ['J1', 'L1'],
                        ['M1', 'O1'],
                        ['P1', 'P2']
                    ]);

                    var length = "${length}" + 2;
                    var last = "P" + length;

                    LAY_EXCEL.setExportCellStyle(res.data, 'A1:P10000', {
                        s: {
                            alignment: {
                                horizontal: 'center',
                                vertical: 'center'
                            }
                        }
                    });

                    res.data = LAY_EXCEL.filterExportData(res.data, [
                        'categoryName',
                        'itemsName',
                        'itemsType',
                        'preAmount',
                        'prePrice',
                        'preTotal',
                        'inAmount',
                        'inPrice',
                        'inTotal',
                        'outAmount',
                        'outPrice',
                        'outTotal',
                        'thisAmount',
                        'thisPrice',
                        'thisTotal',
                        'time',
                    ]);

                    LAY_EXCEL.exportExcel(res.data, '汇总.xlsx', 'xlsx', {
                        extend: {
                            '!merges': mergeConf
                        }
                    });
                }
            });

        })

    });

</script>


</body>
</html>