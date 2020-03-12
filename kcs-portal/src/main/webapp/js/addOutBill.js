
layui.use('form', function ($, form) {
    var $ = layui.$;//重点在layui中引用JQ必须写这一句
    var form = layui.form;

    form.on('select(category1)',function (data) {
        $('select[name="itemsName1"]').empty();
        $('select[name="itemsType1"]').empty();
        $('input[name="itemNum1"]').val(0);
        $('input[name="itemPrice1"]').val("");
        AllTotalSubThisTotal(1);
        $('input[name="itemTotal1"]').val("");

        var categoryVal = $('select[name="category1"]').find("option:selected").text();
        form.render();

        if (categoryVal=="null"||categoryVal==" "||categoryVal=="undefined"||categoryVal==null||categoryVal=="请选择")
        {
            return false;
        }
        $.ajax({
            url:"findTheLastItemsNameByCategoryName",
            type:"post",
            data:{categoryName:categoryVal},
            dataType:"text",
            success:function (result) {
                if (result==null||result==""){
                    layer.alert("该种类没有任何库存商品！！");
                    return false;
                }
                var itemsNameList = JSON.parse(result);
                $('select[name="itemsName1"]').append("<option>请选择</option>");
                $.each(itemsNameList,function (index,Name) {
                    $('select[name="itemsName1"]').append("<option value='"+Name+"'>"+Name+"</option>");

                });
                form.render();
            }
        })

    });
    form.on('select(itemsName1)',function (data) {
        var itemsNameVal = $('select[name="itemsName1"]').find("option:selected").text();
        $('select[name="itemsType1"]').empty();
        $('input[name="itemNum1"]').val(0);
        $('input[name="itemNum1"]').removeAttr("disabled");
        $('input[name="itemPrice1"]').val("");
        AllTotalSubThisTotal(1)
        $('input[name="itemTotal1"]').val("");

        form.render();

        if (itemsNameVal=="null"||itemsNameVal==" "||itemsNameVal=="undefined"||itemsNameVal==null||itemsNameVal=="请选择")
        {
            return false;
        }
        $.ajax({
            type:"post",
            url:"findAddOutBillByItemsName",
            data:{itemsName:itemsNameVal},
            dataType:"text",
            success:function (result) {
                if (result==null){
                    layer.alert("未找到该物品的相关数据！")
                } 
                var addOutBillList = JSON.parse(result);
                $.each(addOutBillList,function (index,addOutBill) {
                    if (addOutBill.thisAmount>0){
                        $('select[name="itemsName1"]').find("option:selected").val(addOutBill.goodsID);
                        $('select[name="itemsType1"]').append("<option value='"+addOutBill.itemsType+"'>"+addOutBill.itemsType+"</option>");
                        $('input[name="itemPrice1"]').val(addOutBill.thisPrice);
                        $('input[name="itemNum1"]').attr("max",addOutBill.thisAmount);
                    }
                    else {
                        layer.alert("该物品存库数量为："+addOutBill.thisAmount);
                        $('input[name="itemNum1"]').val("");
                        $('input[name="itemNum1"]').attr("disabled","disabled");
                    }
                });
                form.render();
            }
        })
    });
    form.on('select(itemsType1)',function (data) {

        var itemsNameVal = $('select[name="itemsName1"]').find("option:selected").text();
        $('input[name="itemNum1"]').val(0);
        $('input[name="itemPrice1"]').val("");
        AllTotalSubThisTotal(1)
        $('input[name="itemTotal1"]').val("");

        form.render();

        if (data.value=="null")
        {
            return false;
        }
        $.ajax({
            type:"post",
            url:"findSummaryByItemsNameAndItemsType",
            data:{itemsName:itemsNameVal,itemsType:data.value},
            dataType:"text",
            success:function (result) {
                var summary = JSON.parse(result)
                $('input[name="itemNum1"]').attr("max",summary.thisAmount)
                $('input[name="itemPrice1"]').val(summary.thisPrice);
                $('select[name="itemsName1"]').find("option:selected").val(summary.goodsID);
                form.render();
            }
        })
    });
    $(function(){
        calAllTotal(1)
    })
});

/**
 * 新增一行物品数据
 */
function addTr(){
    var category = $("#category1").html()              //拿到已加载好的物品名称信息
    var departmentName = $("#departmentName").html()    //拿到已加载好的部门信息

    //改变序号
    var trl = document.getElementsByTagName("tr").length
	var lastTr = document.getElementsByTagName("tr")[trl-2]
	var num = lastTr.cells[0].innerHTML
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
        "<input name=\"itemNum"+num+"\" class=\"layui-input\" type=\"number\"min=\"1\" value=\"0\"/>" +
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
        "<select name=\"departmentID"+num+"\" lay-verify=\"required\" lay-search=\"\">" +
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
        "<button type=\"button\" class=\"layui-btn layui-btn-danger\" onclick=\"removeTr(this)\">移除</button>" +
        "</div>" +
        "</td>" +
        "</tr>";

	$("#finalTr").before(tr);


    layui.use(['form'], function () {
        var form = layui.form;
        form.render();   //重新渲染新增的行中select的信息

        var categoryName = "category"+num;                                 //对应的物品类型
        var itemsName = "itemsName"+num;                                 //对应的物品名称
        var itemsType = "itemsType"+num ;                                    //规格
        var itemPrice = "itemPrice"+num;                               //对应的物品的当月价格
        var itemNum = "itemNum"+num;                                  //数量
        var itemTotal = "itemTotal"+num;                              //单价*数量

        form.on('select('+categoryName+')',function (data) {
            $('select[name="'+itemsName+'"]').empty();
            $('select[name="'+itemsType+'"]').empty();
            $('input[name="'+itemNum+'"]').val(0);
            $('input[name="'+itemPrice+'"]').val("");
            AllTotalSubThisTotal(num);
            $('input[name="'+itemTotal+'"]').val("");

            var categoryVal = $('select[name="'+categoryName+'"]').find("option:selected").text();
            form.render();

            if (categoryVal=="null"||categoryVal==" "||categoryVal=="undefined"||categoryVal==null||categoryVal=="请选择")
            {
                return false;
            }
            $.ajax({
                url:"findTheLastItemsNameByCategoryName",
                type:"post",
                data:{categoryName:categoryVal},
                dataType:"text",
                success:function (result) {
                    if (result==null||result==""){
                        layer.alert("该种类没有任何库存商品！！");
                        return false;
                    }
                    var itemsNameList = JSON.parse(result);
                    $('select[name="'+itemsName+'"]').append("<option>请选择</option>");
                    $.each(itemsNameList,function (index,Name) {
                        $('select[name="'+itemsName+'"]').append("<option value='"+Name+"'>"+Name+"</option>");

                    });
                    form.render();
                }
            })

        });

        //选择物品后，根据物品名称查询该物品的规格，并更新对应的规格
        form.on('select('+itemsName+')',function (data) {

            //选择物品后，先清空规格，数量，单价，合计的值
            $('select[name="'+itemsType+'"]').empty();
            $('input[name="'+itemNum+'"]').val(0);
            $('input[name="'+itemNum+'"]').removeAttr("disabled");
            $('input[name="'+itemPrice+'"]').val("");
            AllTotalSubThisTotal(num)
            $('input[name="'+itemTotal+'"]').val("");

            var itemsNameVal = $('select[name="'+itemsName+'"]').find("option:selected").text();
            form.render();

            if (itemsNameVal=="null"||itemsNameVal==" "||itemsNameVal=="undefined"||itemsNameVal==null||itemsNameVal=="请选择")
            {
                return false;
            }
            $.ajax({
                type:"post",
                url:"findAddOutBillByItemsName",
                data:{itemsName:itemsNameVal},
                dataType:"text",
                success:function (result) {
                    if (result==null){
                        layer.alert("未找到该物品的相关数据！")
                    }
                    var addOutBillList = JSON.parse(result);
                    $.each(addOutBillList,function (index,addOutBill) {
                        if (addOutBill.thisAmount>0){
                            $('select[name="'+itemsName+'"]').find("option:selected").val(addOutBill.goodsID);
                            $('select[name="'+itemsType+'"]').append("<option value='"+addOutBill.itemsType+"'>"+addOutBill.itemsType+"</option>");
                            $('input[name="'+itemPrice+'"]').val(addOutBill.thisPrice);
                            $('input[name="'+itemNum+'"]').attr("max",addOutBill.thisAmount);
                        }
                        else {
                            layer.alert("该物品存库数量为："+addOutBill.thisAmount);
                            $('input[name="'+itemNum+'"]').val("");
                            $('input[name="'+itemNum+'"]').attr("disabled","disabled");
                        }
                    });
                    form.render();
                }
            })

        })

        //选择好规格后，根据对应的物品名和规格查询物品的数量和单价，更新对应的最大数量和单价
        form.on('select('+itemsType+')',function (data) {

            var itemsNameVal = $('select[name="'+itemsName+'"]').find("option:selected").text();

            $('input[name="'+itemNum+'"]').val(0);
            $('input[name="'+itemPrice+'"]').val("");
            AllTotalSubThisTotal(num)
            $('input[name="'+itemTotal+'"]').val("");

            form.render()

            if (data.value=="null")
            {
                return false;
            }
            $.ajax({
                type:"post",
                url:"findSummaryByItemsNameAndItemsType",
                data:{itemsName:itemsNameVal,itemsType:data.value},
                dataType:"text",
                success:function (result) {
                    var summary = JSON.parse(result)
                    $('input[name="'+itemNum+'"').attr("max",summary.thisAmount)
                    $('input[name="'+itemPrice+'"]').val(summary.thisPrice);
                    $('select[name="'+itemsName+'"]').find("option:selected").val(summary.goodsID);
                    form.render();
                }
            })

        })

        $(function(){
            calAllTotal(num)
        })
    });

}




function calAllTotal(count) {
    $('input[name="itemNum'+count+'"]').on('input propertychange',function(){
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
    });
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

function removeTr(obj) {
        if (!confirm("是否确认删除")) {
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

function checkBill() {



    //出库日期
    var time = $("#outBillDate").val();
    //编号
    //var No = $("#OutBillID").val();

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
    var outBill = {"outBillID":null,"outTime":time,"checkStatus":0,"checkTime":null,"checkMessage":null,"operateTime":null,"allTotal":allTotal,"storeManager":storeManager,"taker":taker,"checker":checker,"tableMaker":tableMaker,"operator":null,"remark":null}


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
        var departmentID = Number($('select[name="departmentID'+i+'"]').val());
        var goodsID = $('select[name="itemsName'+i+'"]').val();
        var itemsType = $('select[name="itemsType'+i+'"]').val();
        var itemNum = Number($('input[name="itemNum'+i+'"]').val());
        var itemNumMax = Number($('input[name="itemNum'+i+'"]').attr("max"));
        var itemPrice = $('input[name="itemPrice'+i+'"]').val();
        var itemTotal = $('input[name="itemTotal'+i+'"]').val();
        var project = $('input[name="project'+i+'"]').val();

        //如果其中一个为undefined,跳过此次循环
        if (typeof(goodsID) == "undefined"){
            continue;
        }

        //判断数量是否超出最大值
        if(itemNum>itemNumMax){
            layer.alert("第："+i+"物品的剩余数量为："+itemNumMax+"，无法出库！");
            return false;
        }

        //判断数量是否小于1
        if(itemNum<1){
            layer.alert("第："+i+"行的数量不能少于1！");
            return false;
        }

        //判断是否为空
        if (IsNull(goodsID)){
            layer.alert("第："+i+"的品名未选择！");
            return false;
        }
        if (IsNull(itemsType)){
            layer.alert("第："+i+"的规格未选择！");
            return false;
        }
        if (IsNull(itemNum)){
            layer.alert("第："+i+"的数量未选择！");
            return false;
        }
        if (IsNull(departmentID)){
            layer.alert("第："+i+"的部门未选择！");
            return false;
        }

        //转成对象格式，
        itemsOut = {"itemsOutID":null,"departmentID":departmentID,"outBillID":null,"goodsID":+goodsID
            ,"itemNum":itemNum,"itemPrice":+itemPrice,"itemTotal":+itemTotal,"project":project,"note":null,"storePosition":null};

        //对象数组
        itemsOutList.push(itemsOut);
    }
    var itemsOutJsonList = JSON.stringify(itemsOutList);
    var outBillJson = JSON.stringify(outBill)
    submitBill(itemsOutJsonList,outBillJson)
}

function IsNull(exp) {
    if (exp == "null" || exp =="" || exp == null)
        return true;
    return false;
}

function submitBill(itemsOutJsonList,outBill) {

    $.ajax({
        url:"insertOutBill",
        type:"post",
        data:{"itemsOutJsonListString":itemsOutJsonList,"outBillString":outBill},
        dataType:"text",
        success:function (result) {
            var data = JSON.parse(result);
            if (data.flag){
                layer.alert(data.mesg,function () {
                    window.parent.layer.closeAll();
                });
            }else{
                layer.alert(data.mesg);
                return false;
            }


        },
        error:function () {
            layer.alert("请求错误！")
        }
    })
}