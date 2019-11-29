<html>
<head>
    <title>Title</title>
    <script src="../pages/layui/layui.js"></script><script src="../pages/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="../pages/layui/css/layui.css" type="text/css"/>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header" id="head"></div>
	<div class="layui-side" id="side"></div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <table id="outBill">
                
            </table>
        </div>
    </div>
</div>
<script src="js/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

        element.on('tab(filter)', function(data){
            console.log(this); //当前Tab标题所在的原始DOM元素
            console.log(data.index); //得到当前Tab的所在下标
            console.log(data.elem); //得到当前的Tab大容器
        });
    });
</script>
<script>
	$("#head").load("common/head.html")
	$("#side").load("common/side.html")
</script>
<script>
layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#outBill'
    ,url:'/demo/table/user/'
    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    ,cols: [[
      {field:'id', width:80, title: '序号'}
      ,{field:'OutTime', width:100, title: '日期', sort: true}
      ,{field:'ItemsName', width:100, title: '物品名称'}
      ,{field:'ItemsType', width:100, title: '物品规格'}
      ,{field:'ItemsUnit', width:80, title: '单位'}
      ,{field:'StorePosition', width:100, title: '仓库位置'}
      ,{field:'ItemNum', width:100, title: '出库数量', sort: true}
      ,{field:'Taker', width:100, title: '领用人'}
      ,{field:'Note', width:120, title: '备注'}
    ]]
  });
});
</script>

</body>
</html>
<tr>
                    <td>序号</td>
                    <td>日期</td>
                    <td>物品名称</td>
                    <td>物品规格</td>
                    <td>出库数量</td>
                    <td>单位</td>
                    <td>仓库位置</td>
                    <td>合计金额</td>
                    <td>领用人</td>
                    <td>操作人</td>
                    <td>操作时间</td>
                    <td>制表人</td>
                    <td>审批人</td>
                    <td>审批时间</td>
                    <td>审批状态</td>
                    <td>审批意见</td>
                </tr>
                <tr>
                <c:forEach items="${allOutBill}" var="outBill" varStatus="num">
                    <td>${num.count}</td>
                    <td>${outBill.outTime}</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>${outBill.storePosition}</td>
                    <td>${outBill.allTotal}</td>
                    <td>${outBill.taker}</td>
                    <td>${outBill.operator}</td>
                    <td>${outBill.operateTime}</td>
                    <td>${outBill.tableMaker}</td>
                    <td>${outBill.checker}</td>
                    <td>${outBill.checkTime}</td>
                    <td>${outBill.checkStatus}</td>
                    <td>${outBill.checkMessage}</td>
                </c:forEach>
            </tr>