/**
 * 新增一行物品数据
 */
function addTr(){
    var itemsName = $("#itemsName").html()              //拿到已加载好的物品名称信息
    var departmentName = $("#departmentName").html()    //拿到已加载好的部门信息

    //改变序号
    var trl = document.getElementsByTagName("tr").length
	var lastTr = document.getElementsByTagName("tr")[trl-2]
	var num = lastTr.cells[0].innerHTML
	num = Number(num)+Number(1)

    //拼接字符串及以参数的形式给select的lay-filter，name重新赋值
    var tr = "<tr>" +
        "<td>"+num+"</td>"+
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<select value=\"null\" lay-verify=\"required\" name=\"itemsName"+num+"\" lay-filter=\"itemsName"+num+"\">" +
        itemsName+
        "</select>" +
        "</div>" +
        "</td>"+
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<select name=\"itemsType"+num+"\"lay-verify=\"required\" lay-filter=\"itemsType"+num+"\">" +
        "<option value=\"null\" selected> </option>" +
        "</select>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<input name=\"itemsNum\" class=\"layui-input\" type=\"number\" placeholder=\"数量\"/>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<input name=\"itemsPrice\" class=\"layui-input\" type=\"text\" disabled=\"disabled\"/>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<input name=\"ItemsTotal\" class=\"layui-input\" type=\"text\" disabled=\"disabled\"/>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<select name=\"departmentName\" lay-verify=\"required\" lay-search=\"\">" +
        departmentName +
        "</select>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<input name=\"project\" class=\"layui-input\" type=\"text\" placeholder=\"项目名称\"/>" +
        "</div>" +
        "</td>" +
        "<td>" +
        "<div class=\"layui-form-item\">" +
        "<button class=\"layui-btn layui-bg-gray\" disabled=\"disabled\" >移除</button>" +
        "</div>" +
        "</td>" +
        "</tr>";

	$("#finalTr").before(tr);
    layui.use(['form'], function () {
        var form = layui.form;
        form.render()       //重新渲染新增的行中select的信息

        //选择物品后，根据物品名称查询该物品的规格，并更新对应的规格
        form.on('select(itemsName'+num+')',function (data) {
            var itemsType = "itemsType"+num
            $('select[name="'+itemsType+'"]').empty();
            form.render()
            if (data.value=="null")
            {
                $('select[name="'+itemsType+'"]').append("<option value=\"null\" selected> </option>");
                form.render()
                return false;
            }
            $.ajax({
                type:"post",
                url:"findGoodsByItemsName",
                data:{itemsName:data.value},
                dataType:"text",
                success:function (result) {
                    var goodsList = JSON.parse(result)
                    $('select[name="'+itemsType+'"]').append("<option value=\"null\" selected>请选择</option>")
                    $.each(goodsList,function (index,goods) {
                        $('select[name="'+itemsType+'"]').append("<option value='"+goods.itemsType+"'>"+goods.itemsType+"</option>")
                    })
                    form.render()
                }
            })

        })

        //选择好规格后，根据对应的物品名和规格查询物品的数量和单价，更新对应的最大数量和单价
        form.on('select(itemsType'+num+')',function (data) {
            var itemsName = "itemsName"+num         //对应的物品名称

            if (data.value=="null")
            {
                $('select[name="'+itemsType+'"]').append("<option value=\"null\" selected> </option>");
                form.render()
                return false;
            }
            $.ajax({
                type:"post",
                url:"findGoodsByItemsName",
                data:{itemsName:itemsName,itemsType:data.value},
                dataType:"text",
                success:function (result) {
                    var goodsList = JSON.parse(result)
                    $('select[name="'+itemsType+'"]').append("<option value=\"null\" selected>请选择</option>")
                    $.each(goodsList,function (index,goods) {
                        $('select[name="'+itemsType+'"]').append("<option value='"+goods.itemsType+"'>"+goods.itemsType+"</option>")
                    })
                    form.render()
                }
            })

        })

    });
}



layui.use('form', function ($, form) {
	var $ = layui.$;//重点在layui中引用JQ必须写这一句
	var form = layui.form;


    form.on('select(itemsName1)',function (data) {
        $('select[name="itemsType1"]').empty();
        form.render()
        if (data.value=="null")
        {
            $('select[name="itemsType1"]').append("<option value=\"null\" selected> </option>");
            form.render()
            return false;
        }
        $.ajax({
            type:"post",
            url:"findGoodsByItemsName",
            data:{itemsName:data.value},
            dataType:"text",
            success:function (result) {
                var goodsList = JSON.parse(result)
                $('select[name="itemsType1"]').append("<option value=\"null\" selected>请选择</option>");
                $.each(goodsList,function (index,goods) {
                    $('select[name="itemsType1"]').append("<option value='"+goods.itemsType+"'>"+goods.itemsType+"</option>")
                })
                form.render();
            }
        })

    })
});
