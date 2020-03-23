
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="timeShow"></div>
<input type="text" id="timeShow1"></input>
</body>
<script type="text/javascript">
    var t = null;
    function time(){
        dt = new Date();
        var y=dt.getFullYear();
        var month = dt.getMonth()+1;
        var date = dt.getDate();
        var h=dt.getHours();
        var m=dt.getMinutes();
        var s=dt.getSeconds();
        document.getElementById("timeShow").innerHTML="当前时间："+y+"年"+month+"月"+date+"日"+h+"时"+m+"分"+s+"秒";
        document.getElementById("timeShow1").value="当前时间："+y+"年"+month+"月"+date+"日"+h+"时"+m+"分"+s+"秒";
        t = setTimeout(time,1000);
    }
    window.onload=function(){time()}
</script>
</html>
