<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>库存管理系统</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" style="font-size: 25px ">库存管理系统</div>

        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                   <span>${user.userName}</span>
                </a>

                <dl class="layui-nav-child">
                    <dd><a type="button" id="baseData">基本资料</a></dd>
                    <dd><a type="button" id="safeset">安全设置</a></dd>
                    <dd><a href="${pageContext.request.contextPath }/user/getInfo">获取信息</a></dd>
                </dl>

            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/logout">退出</a></li>
            <%--<li class="layui-nav-item"><form action="${pageContext.request.contextPath}/logout" method="post">
                <security:csrfInput/>
                <input type="submit" value="退出">
            </form></li>--%>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree layui-inline"   lay-filter="demo" style="margin-right: 10px;">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">库存管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a data-url="${pageContext.request.contextPath }/inBill/rInBill" data-id="3" data-title="入库明细单" href="#" class="site-demo-active" data-type="tabAdd">入库明细单</a>
                        </dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd><a data-url="${pageContext.request.contextPath }/outBill/showAllOutBill" data-id="outBill" data-title="出库明细单" href="#" class="site-demo-active" data-type="tabAdd">出库明细单</a></dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd>
                            <a data-url="${pageContext.request.contextPath }/summary/rSummary" data-id="summary" data-title="汇总" href="#" class="site-demo-active" data-type="tabAdd">汇总</a>
                        </dd>
                        <dd>
                            <a data-url="${pageContext.request.contextPath }/summary/rCurrentBill" data-id="currentBill" data-title="流水单" href="#" class="site-demo-active" data-type="tabAdd">流水单</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a data-url="${pageContext.request.contextPath }/user/ruser" data-id="1" data-title="用户" href="#" class="site-demo-active" data-type="tabAdd">用户</a>
                        </dd>
                        <dd>
                            <a href="#" data-url=${pageContext.request.contextPath}/role/showRoleData" data-title="角色"  data-id="2" class="site-demo-active" data-type="tabAdd">角色</a>
                        </dd>
                        <dd>
                            <a href="#" data-url=${pageContext.request.contextPath}/permission/showPermissionData" data-title="权限"  data-id="permission" class="site-demo-active" data-type="tabAdd">权限</a>
                        </dd>
                        <dd>
                            <a data-url="${pageContext.request.contextPath }/log/showAllLog" data-id="log" href="#" data-title="日志" class="site-demo-active" data-type="tabAdd">日志</a>
                        </dd>
                    </dl>
                </li>

            </ul>
        </div>
    </div>

    <div class="layui-tab" lay-filter="demo" lay-allowclose="true" style="margin-left: 200px;">
        <ul class="layui-tab-title">
        </ul>
        <ul class="rightmenu" style="display: none;position: absolute;">
            <li data-type="closethis">关闭当前</li>
            <li data-type="closeall">关闭所有</li>
        </ul>
        <div class="layui-tab-content">
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath }/static/layui/layui.all.js"  charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

        element.on('tab(filter)', function(data){
            console.log(this); //当前Tab标题所在的原始DOM元素
            console.log(data.index); //得到当前Tab的所在下标
            console.log(data.elem); //得到当前的Tab大容器
        });


        var $ = layui.jquery;
        var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function(url,id,name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'.html" style="width:100%;height:99%;"></iframe>',
                    id: id //规定好的id
                })
                CustomRightClick(id); //给tab绑定右击事件
                FrameWH();  //计算ifram层的大小
            },
            tabChange: function(id) {
                //切换到指定Tab项
                element.tabChange('demo', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("demo", id);//删除
            }
            , tabDeleteAll: function (ids) {//删除所有
                $.each(ids, function (i,item) {
                    element.tabDelete("demo", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
                })
            }
        };



        //当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
        $('.site-demo-active').on('click', function() {
            var dataid = $(this);

            //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                //如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
            } else {
                //否则判断该tab项是否以及存在

                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

        function CustomRightClick(id) {
            //取消右键  rightmenu属性开始是隐藏的 ，当右击的时候显示，左击的时候隐藏
            $('.layui-tab-title li').on('contextmenu', function () { return false; })
            $('.layui-tab-title,.layui-tab-title li').click(function () {
                $('.rightmenu').hide();
            });
            //桌面点击右击
            $('.layui-tab-title li').on('contextmenu', function (e) {
                var popupmenu = $(".rightmenu");
                popupmenu.find("li").attr("data-id",id); //在右键菜单中的标签绑定id属性

                //判断右侧菜单的位置
                l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
                t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
                popupmenu.css({ left: l, top: t }).show(); //进行绝对定位
                //alert("右键菜单")
                return false;
            });
        }

        $(".rightmenu li").click(function () {

            //右键菜单中的选项被点击之后，判断type的类型，决定关闭所有还是关闭当前。
            if ($(this).attr("data-type") == "closethis") {
                //如果关闭当前，即根据显示右键菜单时所绑定的id，执行tabDelete
                active.tabDelete($(this).attr("data-id"))
            } else if ($(this).attr("data-type") == "closeall") {
                var tabtitle = $(".layui-tab-title li");
                var ids = new Array();
                $.each(tabtitle, function (i) {
                    ids[i] = $(this).attr("lay-id");
                })
                //如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
                active.tabDeleteAll(ids);
            }

            $('.rightmenu').hide(); //最后再隐藏右键菜单
        })
        function FrameWH() {
            var h = $(window).height() -41- 10 - 60 -10-44 -10;
            $("iframe").css("height",h+"px");
        }

        $(window).resize(function () {
            FrameWH();
        })

    });

    $("#baseData").on("click",function () {
        layer.open({
            type:2,
            title:false,
            content:'${pageContext.request.contextPath }/user/baseData',
            area:['1000px','668px'],
            end:function () {
                location.reload();
            }
        });
    })

    $("#safeset").on("click",function () {
        layer.open({
            type:2,
            title:false,
            content:'${pageContext.request.contextPath }/user/safeData',
            area:['500px','300px'],
            end:function () {
                location.reload();
            }
        });
    })
</script>
</body>
</html>