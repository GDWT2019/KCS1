<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" type="text/css"/>
</head>

<div class="layui-row">

        <div class="layui-inline">
            <label class="layui-form-label">时间：</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="time" name="time" placeholder="yyyy-MM">
            </div>
        </div>

</div>

<table class="layui-table" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm export" id = "daochu">导出所有数据报表</button>
        <button class="layui-btn layui-btn-sm export" id = "print">打印</button>
    </div>

    <!--导出表 不展示-->
    <div style="display: none;">
        <table id="data_export" lay-filter="data_export">
        </table>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="update">入库单</a>
    <a class="layui-btn layui-btn-xs" lay-event="check">出库单</a>
</script>

<script>




    layui.use(["jquery", "upload", "form", "layer", "element",'table','laydate'], function () {
        var $ = layui.$,
            form = layui.form,
            table = layui.table;
        var laydate = layui.laydate;



        layui.config({
            base: "${pageContext.request.contextPath}/js/layui_exts/"
        }).extend({
            excel: 'excel'
        });


        //年月选择器
        laydate.render({
            elem: '#time'
            ,type: 'month'
            ,done:function (time) {
                tableIns=table.reload('testSummary',{
                    where: {time: time}
                })
            }
        });

        //回显时间
        $.ajax({
            type: "POST",
            url: '${pageContext.request.contextPath }/summary/findAllTime',  //从数据库查询返回的是个list
            dataType: "json",
            async: false,
            success: function (data) {
                console.log("time"+data)
                $.each(data, function (index, item) {

                    if(index==0){
                    $("#time").val(item.time);//往下拉菜单里添加元素
                    }
                });
                form.render();//菜单渲染 把内容加载进去
            }
        });

        var time = $("#time").val();
        console.log("time"+time)



        table.render({
            elem: '#test'
            ,url:"${pageContext.request.contextPath }/summary/summartyBillData"
            ,toolbar: '#toolbarDemo'
            ,title: '汇总'
            ,totalRow: true//开启合计行
            , cols:  [[ //标题栏
                {type: 'checkbox', fixed: 'left', rowspan:2}
                ,{type:'numbers', title: '序号', rowspan:2, width: 80 ,fixed: 'left', unresize: true, sort: true}
                ,{align: 'center', title: '物品', colspan: 3}
                ,{align: 'center', title: '上月结存', colspan: 3}
                ,{align: 'center', title: '本月入库', colspan: 3}
                ,{align: 'center', title: '本月出库', colspan: 3}
                ,{align: 'center', title: '本月结存', colspan: 3}
            ], [
                {field: 'categoryName', title: '类别', width: 150}
                ,{field: 'itemsName', title: '品名', width: 150}
                ,{field: 'itemsType', title: '型号规格', width: 150}
                ,{field: 'preAmount', title: '数量', width: 150}
                ,{field: 'prePrice', title: '单价', width: 150}
                ,{field: 'preTotal', title: '金额', width: 150}
                ,{field: 'inAmount', title: '数量', width: 150}
                ,{field: 'inPrice', title: '单价', width: 150}
                ,{field: 'inTotal', title: '金额', width: 150}
                ,{field: 'outAmount', title: '数量', width: 150}
                ,{field: 'outPrice', title: '单价', width: 150}
                ,{field: 'outTotal', title: '金额', width: 150}
                ,{field: 'thisAmount', title: '数量', width: 150}
                ,{field: 'thisPrice', title: '单价', width: 150}
                ,{field: 'thisTotal', title: '金额', width: 150}
                ,{field: 'time', title: '时间', width: 150}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:180}
            ]]

            ,page: true
            ,where: {time: time}
            ,limit:10
            ,limits:[5,10,20,30,50]
            ,id:'testSummary'
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