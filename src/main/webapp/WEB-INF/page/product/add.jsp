<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2020/3/7
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form action="<%=request.getContextPath()%>/product/add" method="post" enctype="multipart/form-data">
        <input type="hidden" name="isDel" value="1">
        <table class="layui-table">
            <tr align="center">
                <td>商品名:</td>
                <td><input type="text" name="proName" /></td>
            </tr>
            <tr align="center">
                <td>库存:</td>
                <td><input type="text" name="count" /></td>
            </tr>
            <tr align="center">
                <td>积分:</td>
                <td><input type="text" name="proIntegral"></td>
            </tr>
            <tr align="center">
                <td>图片</td>
                <td><input type="file" name="file" accept="image/*" id="file"></td>
            </tr>
            <tr align="center">
                <td colspan="2"><input type="submit" style="color: red" value="添加"></td>
            </tr>
        </table>
    </form>
</body>
<script type="text/javascript">
    if("" != "${msg}"){
        layer.msg("${msg}");
    }
</script>
</html>
