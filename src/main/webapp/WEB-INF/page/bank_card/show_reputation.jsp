<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2020/3/7
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
<body>
    <table class="layui-table">
        <tr>
            <td>银行名称</td>
            <td>信誉值</td>
        </tr>
        <c:forEach items="${bankCardList}" var="list">
            <tr>
                <td>${list.baseName}</td>
                <td style="color: red">${list.reputationValue}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
