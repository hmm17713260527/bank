<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2020/3/8
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
<body>
<table class="layui-table">
    <tr align="center">
        <td>商品名称</td>
        <td>积分</td>
    </tr>
    <tbody id="tbd">
    </tbody>
</table>
</body>
<script type="text/javascript">
    $(function(){
        search();
    })

    function search() {
        $.get(
            "<%=request.getContextPath()%>/myProduct/show",
            {"_method" : "GET"},
            function(data){
                var html = "";
                for (var i = 0; i < data.data.length; i++) {
                    var u = data.data[i];
                    html += "<tr align = 'center'>"
                    html += "<td>"+u.proName+"</td>"
                    html += "<td>"+u.proIntegral+"</td>"
                    html += "</tr>"
                }
                $("#tbd").html(html);
            }
        )
    }

</script>
</html>
