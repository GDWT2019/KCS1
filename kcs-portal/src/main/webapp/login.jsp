<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>库存管理系统</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" />

    <link href="${pageContext.request.contextPath }/css/signin.css" rel="stylesheet" />
</head>

<body style="background: #DDEEFE ">
<%--<% request.getSession().invalidate();%>--%>
<form id="form1" action="${pageContext.request.contextPath}/user/loginCheck" class="form-signin" method="post" >
    <security:csrfInput/>
    <h1 class="h3 mb-3 font-weight-normal" >库存管理系统</h1><br />
    <!--判断-->

    <input class="sr-only" >登录名</input>
    <input type="text" id="loginName"  name="loginName" class="form-control" placeholder="登录名"  required="" autofocus=""/><br />
    <input class="sr-only" >密码</input>
    <input type="password" id="password" name="password" class="form-control" placeholder="密码"  required="" /><br />
    <%--<button class="btn btn-lg btn-primary btn-block" type="button" onclick="login()">Sign in</button>--%>
    <button class="btn btn-lg btn-primary btn-block" type="button" id="loginBtn" >登录</button>


</form>
</body>
<script>


    var _topWin = window;
    while (_topWin != _topWin.parent.window) {
        _topWin = _topWin.parent.window;
    }
    if (window != _topWin)_topWin.document.location.href = '${pageContext.request.contextPath }/login.jsp';


    $('body').on('click','#loginBtn',function () {
        var loginName = $('#loginName').val();
        var password = $('#password').val();

        $.ajax({
            url:"${pageContext.request.contextPath}/user/loginCheck",
            type:"post",
            data:{loginName:loginName,password:password},
            dataType:"text",
            success:function (data) {
                var message = JSON.parse(data);
                if (!message.flag){
                    toastr.error(message.mesg)
                }else {
                    $.ajax({
                        url:"${pageContext.request.contextPath}/login",
                        type:"post",
                        data:{loginName:loginName,password:password},
                        dataTpye:"text",
                        success:function (data) {
                            $(window).attr('location',data)
                        }
                    })
                }

            },
            error:function () {
                toastr.error("请求错误！")
            }
        }


        )

    });

    $(function () {

        toastr.options.positionClass = 'toast-top-center';
    });
    function login() {
        var name = $("#name").val();
        var password = $("#password").val();

        $.post("user/loginUser",{"loginName":name,"password":password},function (htq) {
            if(htq.flag){
                location.href="index.jsp";
            }
            else {
                toastr.error(htq.mesg);
            }
        })

    }
</script>
</html>