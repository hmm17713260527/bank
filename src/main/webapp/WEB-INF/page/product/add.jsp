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
<body>
    <form action="<%=request.getContextPath()%>/product/add" method="post" enctype="multipart/form-data">
        商品名:<input type="text" name="proName" /><br>
        库存:<input type="text" name="count" /><br>
        积分:<input type="text" name="proIntegral"><br>
        图片：<input type="file" name="file" accept="image/*" id="file"><br>
        <input type="hidden" name="isDel" value="1">
        <input type="submit" value="添加">
    </form>
</body>
<script type="text/javascript">
    if("" != "${msg}"){
        layer.msg("${msg}");
    }
</script>
</html>
