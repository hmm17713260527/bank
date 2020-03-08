<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2020/3/7
  Time: 16:15
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
        <tr align="center" >
            <td style="color : red">我的积分</td>
            <td style="color : red">${sumIntegral}</td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <a href="<%=request.getContextPath()%>/product/toShow" style="color : rebeccapurple">点击此处去兑换商品</a>
                <a href="<%=request.getContextPath()%>/myProduct/toShow" style="color: #FF00FF">点击此处查看兑换历史</a>
            </td>
        </tr>
    </table>
</body>
</html>
