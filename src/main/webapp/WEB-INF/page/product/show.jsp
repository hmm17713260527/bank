<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2020/3/7
  Time: 16:01
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
    <c:if test="${USER_SESSION.type == 2}">
        <a href="<%=request.getContextPath()%>/product/toAdd" style="color: red">点击此处添加商品</a>
    </c:if>
    <table class="layui-table">
        <tr align="center">
            <td>商品名称</td>
            <td>商品图片</td>
            <td>积分</td>
            <td>库存</td>
            <td>操作</td>
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
            "<%=request.getContextPath()%>/product/show",
            {"isDel" : 1, "_method" : "GET"},
            function(data){
                var html = "";
                for (var i = 0; i < data.data.length; i++) {
                    var u = data.data[i];
                    html += "<tr align = 'center'>"
                    html += "<td>"+u.proName+"</td>"
                    html += '<td><img src="http://q6gw2qec5.bkt.clouddn.com/'+u.proImg+'" width="100px" height="100px"></td>';
                    html += "<td>"+u.proIntegral+"</td>"
                    html += "<td>"+u.count+"</td>"
                    if (${USER_SESSION.type == 1}) {
                        html += "<td style='color: #FF00FF'>点击此处兑换商品</td>"
                    } else {
                        html += '<td><input type="button" value="修改" style="color: #FF00FF" onclick="updateById('+u.id+')"><input type="button" value="删除" style="color: red" onclick="updateIsDelById('+u.id+')"></td>'
                    }
                    html += "</tr>"
                }
                $("#tbd").html(html);
            }
        )
    }

    function updateById(id) {
        window.location.href = "<%=request.getContextPath()%>/product/toUpdateById/"+id;
    }

    function updateIsDelById(id) {
        var index = layer.load(0, {shade:0.5});
        $.post(
            "<%=request.getContextPath()%>/product/updateIsDelById",
            {"id":id, "_method":"PUT"},
            function (data) {
                if(data.code == -1){
                    layer.close(index);
                    layer.msg(data.msg, {icon: 5});
                    return
                }
                layer.msg(data.msg, {
                    icon: 6,
                    time: 2000
                }, function(){
                    search();
                    layer.close(index);
                });
            }
        )
    }
    
</script>
</html>
