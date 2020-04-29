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
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/layui_exts/excel.js"></script>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath }/static/tablePlug/tablePlug.css" type="text/css"/>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>
</head>

<div class="demoTable">
    时间：
    <%--<button class="layui-btn layui-btn-sm layui-icon-prev" id="preMonth">上一月</button>--%>
    <a id="preMonth"><i class="layui-icon layui-icon-prev" style="font-size: 25px"></i></a>
    <div class="layui-inline">
        <input type="text" class="layui-input" id="time1" name="time" placeholder="yyyy-MM">
    </div>
    <a id="nextMonth"><i class="layui-icon layui-icon-next" style="font-size: 25px"></i></a>
    <%--<button class="layui-btn" data-type="reload">搜索</button>--%>

<a href="${pageContext.request.contextPath }/summary/poiSummary" class="layui-btn">导出</a>
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

    layui.config({
        base: '${pageContext.request.contextPath }/static/tablePlug/' //假设这是test.js所在的目录   可以把你需要扩展的js插件都放在一个文件夹内
    }).extend({ //设定组件别名
        tablePlug: 'tablePlug'
    });

    <%--layui.config({base:'${pageContext.request.contextPath }/static/tablePlug/'}).use(["jquery", "upload", "form", "layer", "element","table","laydate","tablePlug"], function () {--%>
    layui.use(["jquery", "upload", "layer","table","laydate","tablePlug"], function () {
        var $ = layui.$,
            table = layui.table;
        var laydate = layui.laydate;
        var tablePlug = layui.tablePlug;
        tablePlug.smartReload.enable(true);//处理不闪动的关键代码


        $("#preMonth").on("click",function () {
            var month = $("#time1").val()+"-1";
            var preMonth = getPreMonth(month);
            console.log("preMonth :"+preMonth);

            var s = preMonth.substring(0,7);
            $("#time1").val(s);
            table.reload('testSummary',{
                where: {time: s}
                , page: {
                    curr: 1
                }
            });
        });
        $("#nextMonth").on("click",function () {
            var month = $("#time1").val()+"-1";
            var nextMonth = getNextMonth(month);
            console.log("nextMonth :"+nextMonth);

            var s = nextMonth.substring(0,7);
            $("#time1").val(s);
            table.reload('testSummary',{
                where: {time: s}
                , page: {
                    curr: 1
                }
            });
            });

        function getNextMonth(date) {
            var arr = date.split('-');
            var year = arr[0]; //获取当前日期的年份
            var month = arr[1]; //获取当前日期的月份
            var day = arr[2]; //获取当前日期的日
            var days = new Date(year, month, 0);
            days = days.getDate(); //获取当前日期中的月的天数
            var year2 = year;
            var month2 = parseInt(month) + 1;
            if (month2 == 13) {
                year2 = parseInt(year2) + 1;
                month2 = 1;
            }
            var day2 = day;
            var days2 = new Date(year2, month2, 0);
            days2 = days2.getDate();
            if (day2 > days2) {
                day2 = days2;
            }
            if (month2 < 10) {
                month2 = '0' + month2;
            }

            var t2 = year2 + '-' + month2 + '-' + day2;
            return t2;
        }

        function getPreMonth(date) {
            var arr = date.split('-');
            var year = arr[0]; //获取当前日期的年份
            var month = arr[1]; //获取当前日期的月份
            var day = arr[2]; //获取当前日期的日
            var days = new Date(year, month, 0);
            days = days.getDate(); //获取当前日期中月的天数
            var year2 = year;
            var month2 = parseInt(month) - 1;
            if (month2 == 0) {
                year2 = parseInt(year2) - 1;
                month2 = 12;
            }
            var day2 = day;
            var days2 = new Date(year2, month2, 0);
            days2 = days2.getDate();
            if (day2 > days2) {
                day2 = days2;
            }
            if (month2 < 10) {
                month2 = '0' + month2;
            }
            var t2 = year2 + '-' + month2 + '-' + day2;
            return t2;
        }

        layui.config({
            base: "${pageContext.request.contextPath}/js/layui_exts/"
        }).extend({
            excel: 'excel'
        });


        var latestDate= new Date();
        var time =null;
        time = latestDate.getFullYear()+"-"+("0" + (latestDate.getMonth() + 1)).slice(-2);
        <%--$.ajax({--%>
            <%--type: "POST",--%>
            <%--url: '${pageContext.request.contextPath }/summary/findAllTime',  //从数据库查询返回的是个list--%>
            <%--dataType: "json",--%>
            <%--async: false,--%>
            <%--success: function (data) {--%>
                <%--console.log("time"+data)--%>
                <%--$.each(data, function (index, item) {--%>
                    <%--if(index==0){--%>
                        <%--time=item.time;//往下拉菜单里添加元素--%>
                    <%--}--%>
                <%--});--%>
                <%--form.render();//菜单渲染 把内容加载进去--%>
            <%--}--%>
        <%--});--%>




        table.render({
            elem: '#test'
            ,url:"${pageContext.request.contextPath }/summary/summartyBillData"
            ,toolbar: '#toolbarDemo'
            ,title: '汇总'
            ,totalRow: true//开启合计行
            ,smartReloadModel:true
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
                    , {field: 'outTotal', title: '金额', width: 150,totalRowText: '当月合计：'}
                    , {field: 'thisAmount', title: '数量', width: 150,totalRow: true}
                    , {field: 'thisPrice', title: '单价', width: 150,totalRowText: '合计：'}
                    , {field: 'thisTotal', title: '金额', width: 150,totalRow: true}
                ]
            ],done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log("total"+$('.layui-table-total .layui-table tr [data-field="thisTotal"]').text());
                var text = $('.layui-table-total .layui-table tr [data-field="thisTotal"]').text();
                var number = Number(text);
                $('.layui-table-total .layui-table tr [data-field="thisTotal"]').text(number.toFixed(2));
                var month = $("#time1").val();
                console.log("month"+month);
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/summary/summaryTotalByMonth",
                    data: {time: month},
                    dataType: "json",
                    success: function (result) {
                        console.log("测试返回的double"+result)
                        $('.layui-table-total .layui-table tr [data-field="thisAmount"]').text(result.toFixed(2));
                        form.render();
                    }
                });
            }
            ,page: true
            ,where: {time: time}
            ,limit:10
            ,limits:[5,10,20,30,50]
            ,id:'testSummary'
        });
        function reloadLaydate(){
            layui.use('laydate', function() {
                var laydate = layui.laydate;
                //日期范围
                //日期时间范围
                //年月选择器
                laydate.render({
                    elem: '#time1'
                    ,type: 'month'
                    ,done:function (time) {
                        tableIns=table.reload('testSummary',{
                            where: {time: time}
                            , page: {
                                curr: 1
                            }
                        });
                        reloadLaydate();
                    }
                });
            });

            $('#daochu').click(function(){
                $.ajax({
                    url: "${pageContext.request.contextPath }/summary/summaryAllData",
                    dataType: 'json',
                    success: function(res) {
                        // 假如返回的 res.data 是需要导出的列表数据
                        console.log(res.data);// [{name: 'wang', age: 18}, {name: 'layui', age: 3}]

                        res.data.unshift({categoryName: '物品', itemsName: '品名',itemsType: '规格'
                            ,preAmount:'上月结存',prePrice:'单价',preTotal:'金额'
                            ,inAmount:'本月入库',inPrice:'单价',inTotal:'金额'
                            ,outAmount:'本月出库',outPrice:'单价',outTotal:'金额'
                            ,thisAmount:'本月结存',thisPrice:'单价',thisTotal:'金额'
                            ,time:'时间'
                        },{categoryName: '类别', itemsName: '品名',itemsType: '规格'
                            ,preAmount:'数量',prePrice:'单价',preTotal:'金额'
                            ,inAmount:'数量',inPrice:'单价',inTotal:'金额'
                            ,outAmount:'数量',outPrice:'单价',outTotal:'金额'
                            ,thisAmount:'数量',thisPrice:'单价',thisTotal:'金额'
                            ,time:'时间'});


                        var mergeConf = LAY_EXCEL.makeMergeConfig([
                            ['A1', 'C1'],
                            ['D1', 'F1'],
                            ['G1', 'I1'],
                            ['J1', 'L1'],
                            ['M1', 'O1'],
                            ['P1', 'P2']
                        ]);

                        var length = "${length}"+2;
                        var last = "P"+length;
                        console.log("length"+length);
                        console.log("last"+last);

                        LAY_EXCEL.setExportCellStyle(res.data, 'A1:P10000', {
                            s: {
                                alignment: {
                                    horizontal: 'center',
                                    vertical: 'center'
                                }
                            }
                        });

                        res.data=LAY_EXCEL.filterExportData(res.data, [
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

                        LAY_EXCEL.exportExcel(res.data, '汇总.xlsx', 'xlsx',{
                            extend: {
                                '!merges': mergeConf
                            }
                        });
                    }
                });

            })
        }

        layui.use('laydate', function() {
            var laydate = layui.laydate;
            //日期范围
            //日期时间范围
            //年月选择器
            laydate.render({
                elem: '#time1'
                ,type: 'month'
                ,done:function (time) {
                    tableIns=table.reload('testSummary',{
                        where: {time: time}
                        , page: {
                            curr: 1
                        }
                    });
                    reloadLaydate();
                }
            });
        });
    $("#time1").val(time);//

        table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            var value = obj.value;
            if(layEvent === 'inBill'){ //该物品的入库记录
                window.goodsID=data.goodsID;
                var index=layer.open({
                    type:2,
                    title:['入库记录', 'font-size:18px;text-align: center;'],
                    content:'${pageContext.request.contextPath }/inBill/rItemInRecord',
                    area:['1200px','668px'],
                    end:function () {
                    }
                });
                // layer.full(index);
            } else if(layEvent === 'outBill'){ //该物品的出库记录
                // layer.alert(data.inBillID);
                window.goodsID=data.goodsID;
                layer.open({
                    type:2,
                    title:['出库记录', 'font-size:18px;text-align: center;'],
                    content:'${pageContext.request.contextPath }/outBill/rItemOutRecord',
                    area:['1200px','668px'],
                    end:function () {

                    }
                });
            }
            else if(layEvent === 'del'){ //删除
                layer.confirm('确定删除吗？', {
                    btn: ['确定', '取消'] //可以无限个按钮
                    ,btn1: function(index, layero){
                        $.ajax({
                            url:'${pageContext.request.contextPath }/itemIn/delItem'
                            ,data:{"ItemsInID":data.itemsInID},
                            success:function(){
                                layer.msg("删除成功！");
                            },
                            error:function () {
                                layer.msg("删除失败！");
                            }
                        });
                    }
                    ,btn2: function(index, layero){
                        layer.close();
                    }
                    ,end:function () {
                        location.reload();
                    }
                });
            }
        });
        $('body').on('click', "#print", function () {
            $.ajax({
                <%--url: '${pageContext.request.contextPath }/summary/poiSummary'--%>
                url: 'http://localhost:8081/kcs_rest_war/summary/poiSummary'
            });
        });
        $('#daochu').click(function(){
            $.ajax({
                url: "${pageContext.request.contextPath }/summary/summaryAllData",
                dataType: 'json',
                success: function(res) {
                    // 假如返回的 res.data 是需要导出的列表数据
                    console.log(res.data);// [{name: 'wang', age: 18}, {name: 'layui', age: 3}]

                    res.data.unshift({categoryName: '物品', itemsName: '品名',itemsType: '规格'
                        ,preAmount:'上月结存',prePrice:'单价',preTotal:'金额'
                        ,inAmount:'本月入库',inPrice:'单价',inTotal:'金额'
                        ,outAmount:'本月出库',outPrice:'单价',outTotal:'金额'
                        ,thisAmount:'本月结存',thisPrice:'单价',thisTotal:'金额'
                        ,time:'时间'
                    },{categoryName: '类别', itemsName: '品名',itemsType: '规格'
                        ,preAmount:'数量',prePrice:'单价',preTotal:'金额'
                        ,inAmount:'数量',inPrice:'单价',inTotal:'金额'
                        ,outAmount:'数量',outPrice:'单价',outTotal:'金额'
                        ,thisAmount:'数量',thisPrice:'单价',thisTotal:'金额'
                        ,time:'时间'});


                      var mergeConf = LAY_EXCEL.makeMergeConfig([
                          ['A1', 'C1'],
                          ['D1', 'F1'],
                          ['G1', 'I1'],
                          ['J1', 'L1'],
                          ['M1', 'O1'],
                          ['P1', 'P2']
                      ]);

                    var length = "${length}"+2;
                    var last = "P"+length;
                    console.log("length"+length);
                    console.log("last"+last);

                    LAY_EXCEL.setExportCellStyle(res.data, 'A1:P10000', {
                        s: {
                            alignment: {
                                horizontal: 'center',
                                vertical: 'center'
                            }
                        }
                    });

                    res.data=LAY_EXCEL.filterExportData(res.data, [
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

                    LAY_EXCEL.exportExcel(res.data, '汇总.xlsx', 'xlsx',{
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