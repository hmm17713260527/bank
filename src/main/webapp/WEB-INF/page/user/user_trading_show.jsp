<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/3/8
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.all.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
    <script type="text/javascript">
        $(function(){
            search();
        })
        function search() {
            $.get(
                "<%=request.getContextPath()%>/trading/list",
                {},
                function(data){
                    var html = "";
                    for (var i = 0; i < data.data.length; i++) {
                        var t = data.data[i];
                        html += "<tr align = 'center'>"
                        html += "<td>"+t.userName+"</td>"
                        html += "<td>"+t.userCard+"</td>";
                        html += "<td>"+t.dealMoney+"</td>"
                        html += "<td>"+t.dealTime+"</td>"
                        html += "<td>"+t.balanceMoney+"</td>"
                        html += "<td>"+t.payWay+"</td>"
                        html += "</tr>"
                    }
                    $("#tbd").html(html);
                }
            )
        }
    </script>
</head>
<body>
    <table class="layui-table">
        <tr align="center">
            <td>用户名</td>
            <td>银行卡号</td>
            <td>交易金额</td>
            <td>交易时间</td>
            <td>卡内余额</td>
            <td>支付方式</td>
        </tr>
        <tbody id="tbd">
        </tbody>
    </table>
</body>
</html>
