
layui.use('form', function ($, form) {
    var $ = layui.$;//重点在layui中引用JQ必须写这一句
    var form = layui.form;
    form.on('select', function (data) {
            var selectedName = data.elem.getAttribute("name");
            var i = selectedName.substring(selectedName.length-1,selectedName.length+1);
        console.log(i+"  :"+selectedName);
            if(selectedName.substring(0,selectedName.length-1) =="category" ) {
                $('select[name="itemsName' + i + '"]').empty();
                $('select[name="itemsType' + i + '"]').empty();
                $('input[name="itemNum' + i + '"]').val(0);
                $('input[name="itemPrice' + i + '"]').val("");
                AllTotalSubThisTotal(i);
                $('input[name="itemTotal' + i + '"]').val("");

                var categoryVal = $('select[name="category' + i + '"]').find("option:selected").text();
                form.render();

                if (categoryVal == "null" || categoryVal == " " || categoryVal == "undefined" || categoryVal == null || categoryVal == "请选择") {
                    return false;
                }
                $.ajax({
                    url: "findTheLastItemsNameByCategoryName",
                    type: "post",
                    data: {categoryName: categoryVal},
                    dataType: "text",
                    success: function (result) {
                        if (result == null || result == "") {
                            layer.alert("该种类没有任何库存商品！！");
                            return false;
                        }
                        var itemsNameList = JSON.parse(result);
                        $('select[name="itemsName' + i + '"]').append("<option>请选择</option>");
                        $.each(itemsNameList, function (index, Name) {
                            $('select[name="itemsName' + i + '"]').append("<option value='" + Name + "'>" + Name + "</option>");

                        });
                        form.render();
                    }
                })

            }
        if(selectedName.substring(0,selectedName.length-1) =="itemsName") {
                var itemsNameVal = $('select[name="itemsName'+i+'"]').find("option:selected").text();
                $('select[name="itemsType'+i+'"]').empty();
                $('input[name="itemNum'+i+'"]').val(0);
                $('input[name="itemNum'+i+'"]').removeAttr("disabled");
                $('input[name="itemPrice'+i+'"]').val("");
                $('select[name="storePosition'+i+'"]').empty()
                AllTotalSubThisTotal(i);
                $('input[name="itemTotal'+i+'"]').val("");

                form.render();

                if (itemsNameVal == "null" || itemsNameVal == " " || itemsNameVal == "undefined" || itemsNameVal == null || itemsNameVal == "请选择") {
                    return false;
                }
                $.ajax({
                    type: "post",
                    url: "findAddOutBillByItemsName",
                    data: {itemsName: itemsNameVal},
                    dataType: "text",
                    success: function (result) {
                        if (result == null) {
                            layer.alert("未找到该物品的相关数据！")
                        }
                        var addOutBillList = JSON.parse(result);
                        $.each(addOutBillList, function (index, addOutBill) {
                            if (addOutBill.thisAmount > 0) {
                                $('select[name="itemsName'+i+'"]').find("option:selected").val(addOutBill.goodsID);
                                $('select[name="itemsType'+i+'"]').append("<option value='" + addOutBill.itemsType + "'>" + addOutBill.itemsType + "</option>");
                                $('input[name="itemPrice'+i+'"]').val(addOutBill.thisPrice);
                                $('input[name="itemNum'+i+'"]').attr("max", addOutBill.thisAmount);
                                $('select[name="storePosition'+i+'"]').append("<option value='"+addOutBill.storePosition+"'>"+addOutBill.storePosition+"</option>");
                            } else {
                                layer.alert("该物品存库数量为：" + addOutBill.thisAmount);
                                $('input[name="itemNum'+i+'"]').val("");
                                $('input[name="itemNum'+i+'"]').attr("disabled", "disabled");
                                $('select[name="itemsType'+i+'"]').empty();
                                $('input[name="itemPrice'+i+'"]').val("");
                            }
                        });
                        form.render();
                    }
                })
            }
        if(selectedName.substring(0,selectedName.length-1) =="itemsType") {

                var itemsNameVal = $('select[name="itemsName'+i+'"]').find("option:selected").text();
                $('input[name="itemNum'+i+'"]').val(0);
                $('input[name="itemPrice'+i+'"]').val("");
                AllTotalSubThisTotal(i)
                $('input[name="itemTotal'+i+'"]').val("");

                form.render();

                if (data.value == "null") {
                    return false;
                }
                $.ajax({
                    type: "post",
                    url: "findSummaryByItemsNameAndItemsType",
                    data: {itemsName: itemsNameVal, itemsType: data.value},
                    dataType: "text",
                    success: function (result) {
                        var summary = JSON.parse(result)
                        $('input[name="itemNum'+i+'"]').attr("max", summary.thisAmount)
                        $('input[name="itemPrice'+i+'"]').val(summary.thisPrice);
                        $('select[name="itemsName'+i+'"]').find("option:selected").val(summary.goodsID);
                        form.render();
                    }
                })
            }
            $(function () {
                calAllTotal(i)
            });
        });
});

//新增一行
function addTr(){
    var category = $('select[name="category1"]').html()              //拿到已加载好的物品名称信息
    var departmentName = $('select[name="department1"]').html()    //拿到已加载好的部门信息

    //改变序号
    var trl = document.getElementsByTagName("tr").length
    var lastTr = document.getElementsByTagName("tr")[trl-2]
    var num = lastTr.cells[0].innerHTML
    if (num==null||num==""){
        num = 1;
    }
    num = Number(num)+Number(1)

    //拼接字符串及以参数的形式给select的lay-filter，name重新赋值
    var tr = "<tr name=\"tr"+num+"\">"+
        "<td>"+num+"</td>"+
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<select lay-verify=\"required\" name=\"category"+num+"\" lay-filter=\"category"+num+"\">" +
        category+
        "</select>" +
        "</div>" +
        "</td>"+
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<select value=\"null\" lay-verify=\"required\" name=\"itemsName"+num+"\" lay-filter=\"itemsName"+num+"\">" +
        "</select>" +
        "</div>" +
        "</td>"+
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<select name=\"itemsType"+num+"\"lay-verify=\"required\" lay-filter=\"itemsType"+num+"\">" +
        "</select>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<input name=\"itemNum"+num+"\" class=\"layui-input\" type=\"number\"min=\"1\" value=\"0\" onclick=\"calAllTotal("+num+")\"/>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<input name=\"itemPrice"+num+"\" class=\"layui-input\" type=\"text\" disabled=\"disabled\"/>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<input name=\"itemTotal"+num+"\" class=\"layui-input\" type=\"text\" disabled=\"disabled\"/>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<select name=\"department"+num+"\" lay-verify=\"required\" lay-search=\"\">" +
        departmentName +
        "</select>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<input name=\"project"+num+"\" class=\"layui-input\" type=\"text\" placeholder=\"项目名称\"/>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<select  name=\"storePosition"+num+"\"  lay-verify=\"required\" lay-search=\"\">" +
        "</select>" +
        "</div>" +
        "</td>"+
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<button type=\"button\" class=\"layui-btn layui-btn-danger\" onclick=\"removeTr(this)\">移除</button>" +
        "</div>" +
        "</td>" +
        "</tr>";

    $("#finalTr").before(tr);


    layui.use(['form'], function () {
        var form = layui.form;
        form.render();   //重新渲染新增的行中select的信息
    });

}

function calAllTotal(count) {
        var n = 0;
        var price = 0;
        var itemTotal = 0;
        var allTotal = 0;
        var trl = document.getElementsByTagName("tr").length
        var lastTr = document.getElementsByTagName("tr")[trl-2]
        var str=lastTr.getAttribute("name");
        var lastNum = str.substring(2)
        n = Number($('input[name="itemNum'+count+'"]').val());
        price = Number($('input[name="itemPrice'+count+'"]').val());
        if (n >0 && price >0){
            itemTotal = Number(n)*Number(price)
            $('input[name="itemTotal'+count+'"]').val(itemTotal);
        }
        for(var i =1;i<=lastNum;i++){
            var thisTotal = $('input[name="itemTotal'+i+'"]').val();
            if (thisTotal>0)
                allTotal = Number(allTotal)+Number(thisTotal);
        }
        $("#allTotal").empty();
        $("#allTotal").append(allTotal);
}

function AllTotalSubThisTotal(count) {
    var thisTotal = $('input[name="itemTotal'+count+'"]').val();
    var allTotal = $("#allTotal").text();
    if (thisTotal>0) {
        var newAllTotal = Number(allTotal)-Number(thisTotal);
        $("#allTotal").html(newAllTotal);
    }
    else
        $("#allTotal").html(allTotal);
}

//移除新增的数据
function removeTr(obj) {
    if (!confirm("是否确认移除？")) {
        return;
    }
    var trName=$(obj).parents("tr").attr("name");
    var count = trName.substring(2);

    AllTotalSubThisTotal(count);
    $(obj).parents("tr").remove();

    var trl = document.getElementsByTagName("tr").length
    if (trl<4)
        return false;

    for(var i = 2;i<trl-1;i++){
        var lastTr = document.getElementsByTagName("tr")[i]
        lastTr.cells[0].innerHTML=i;
    }

}

//删除原有数据
function delTr(obj) {
    console.log(obj);
    var trName=$(obj).parents("tr").attr("name");

    console.log("1:"+trName);
    var count = trName.substring(2);
    console.log("2:"+count);
    var itemsOutID = "itemsOutID"+count
    var itemsOutIDVal = $('input[name="'+itemsOutID+'"]').val();
    console.log("3:"+itemsOutIDVal);
    if (!confirm("删除后将无法恢复，是否确认删除？")) {
        return;
    }
    console.log(getContextPath())
    $.ajax({
        url:getContextPath()+"itemsOut/delByItemsOutID",
        type:"post",
        data:{"itemsOutID":itemsOutIDVal},
        dataType:"text",
        success:function (result) {
            var ajaxResult = JSON.parse(result);
            if (ajaxResult){
                layer.confirm(ajaxResult.mesg);
            }else{
                layer.confirm(ajaxResult.mesg);
            }
        },
        error:function () {
            layer.confirm("删除请求错误！");

        }
    })

    AllTotalSubThisTotal(count);
    $(obj).parents("tr").remove();

    var trl = document.getElementsByTagName("tr").length
    if (trl<4)
        return false;

    for(var i = 2;i<trl-1;i++){
        var lastTr = document.getElementsByTagName("tr")[i]
        lastTr.cells[0].innerHTML=i;
    }

}


function checkBill() {

    //出库日期
    var time = $("#outBillDate").val();
    //编号
    var outBillID = $("#outBillID").val();

    //仓管员id
    var storeManager = Number($("#storeManager").val());
    //领用人id
    var taker = Number($("#taker").val());
    //审批人id
    var checker = Number($("#checker").val());
    //制表人id
    var tableMaker = Number($("#tableMaker").val());

    var allTotal = Number($("#allTotal").text());

    if (IsNull(time)){
        layer.alert("日期未填写哦！");
        return false;
    }
    if(IsNull(storeManager)||IsNull(taker)||IsNull(checker)||IsNull(tableMaker)){
        layer.alert("还有人员未选择哦！");
        return false;
    }

    //将出库单的信息打包
    var outBill = {"outBillID":outBillID,"outTime":time,"checkStatus":null,"checkTime":null,"checkMessage":null,"operateTime":null,"allTotal":allTotal,"storeManager":storeManager,"taker":taker,"checker":checker,"tableMaker":tableMaker,"operator":null,"remark":null}


    //获取最后一行数据的编号,以确定循环次数
    var trl = document.getElementsByTagName("tr").length
    var lastTr = document.getElementsByTagName("tr")[trl-2]
    var str=lastTr.getAttribute("name");
    var lastNum = str.substring(2)

    //定义json数组
    var itemsOutList = [];
    var itemsOut = null;
    //循环获取物品相关数据，并转成json数组
    for(var i =1;i<=lastNum;i++){
        var departmentID = Number($('select[name="department'+i+'"]').val());
        var goodsID = $('select[name="itemsName'+i+'"]').val();
        var itemsType = $('select[name="itemsType'+i+'"]').val();
        var itemNum = Number($('input[name="itemNum'+i+'"]').val());
        var itemNumMax = Number($('input[name="itemNum'+i+'"]').attr("max"));
        var itemPrice = $('input[name="itemPrice'+i+'"]').val();
        var itemTotal = $('input[name="itemTotal'+i+'"]').val();
        var project = $('input[name="project'+i+'"]').val();
        var storePosition = $('input[name="storePosition'+i+'"]').val();
        var itemsOutID = $('input[name="itemsOutID'+i+'"]').val();

        //如果其中一个为undefined,跳过此次循环
        if (typeof(goodsID) == "undefined"){
            continue;
        }

        //如果id未undefined，则该条信息需要插入
        if (typeof(itemsOutID) == "undefined"){
            itemsOutID = null;
        }

        //判断数量是否超出最大值
        if(itemNum>itemNumMax){
            layer.alert("第"+i+"物品的剩余数量为："+itemNumMax+"，你的选择数量已超过最大数量，无法出库！");
            return false;
        }

        //判断数量是否小于1
        if(itemNum<1){
            layer.alert("第"+i+"行的数量不能少于1！");
            return false;
        }

        //判断是否为空
        if (IsNull(goodsID)){
            layer.alert("第"+i+"的品名未选择！");
            return false;
        }
        if (IsNull(itemsType)){
            layer.alert("第"+i+"的规格未选择！");
            return false;
        }
        if (IsNull(itemNum)){
            layer.alert("第"+i+"的数量未选择！");
            return false;
        }
        if (IsNull(departmentID)){
            layer.alert("第"+i+"的 部门 未选择！");
            return false;
        }

        //转成对象格式，
        itemsOut = {"itemsOutID":itemsOutID,"departmentID":departmentID,"outBillID":outBillID,"goodsID":+goodsID
            ,"itemNum":itemNum,"itemPrice":+itemPrice,"itemTotal":+itemTotal,"project":project,"note":null,"storePosition":storePosition};

        //对象数组
        itemsOutList.push(itemsOut);
    }
    var itemsOutJsonList = JSON.stringify(itemsOutList);
    var outBillJson = JSON.stringify(outBill)
    console.log("outBillJson:"+outBillJson)
    console.log("itemsOutJsonList:"+itemsOutJsonList)
    submitBill(itemsOutJsonList,outBillJson)
}

function IsNull(exp) {
    if (exp == "null" || exp =="" || exp == null)
        return true;
    return false;
}

function submitBill(itemsOutJsonList,outBill) {

    $.ajax({
        url:"updateOutBill",
        type:"post",
        data:{"itemsOutJsonListString":itemsOutJsonList,"outBillString":outBill},
        dataType:"text",
        success:function (result) {
            var data = JSON.parse(result);
            if (data.flag){
                layer.alert(data.mesg,function () {
                    window.parent.layer.closeAll();
                })
            }else{
                layer.alert(data.mesg);
                return false;
            }


        },
        error:function () {
            layer.alert("请求错误！")
        }
    })
    window.close();
}


function getContextPath() {
    return window.location.protocol + "//" + window.location.host+"/kcs_portal_war/";
}