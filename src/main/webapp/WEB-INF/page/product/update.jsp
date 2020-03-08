<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2020/3/7
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
<body>
    <form action="<%=request.getContextPath()%>/product/updateById" method="post" enctype="multipart/form-data">
        <input type="hidden" value="${product.id}" name="id">
        <input type="hidden" value="${product.proImg}" name="proImg">
        <table class="layui-table">
            <tr align="center">
                <td>商品名：</td>
                <td style="color: red">
                    ${product.proName}
                </td>
            </tr>
            <tr align="center">
                <td>积分：</td>
                <td>
                    <input type="text" value="${product.proIntegral}" name="proIntegral">
                </td>
            </tr>
            <tr align="center">
                <td>商品库存：</td>
                <td>
                    <input type="text" value="${product.count}" name="count">
                </td>
            </tr>
            <tr align="center">
                <td>商品图片：</td>
                <td>
                    <img src="http://q6gw2qec5.bkt.clouddn.com/${product.proImg}" width="100px" height="100px"><input style="color: red" type="file" name="file" accept="image/*" >
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <input type="submit" style="color: #0000FF" value="确定修改">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
