<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2020/3/8
  Time: 11:09
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
    <form id="frm">
        <input type="hidden" value="${USER_SESSION.id}" name="userId">
        <input type="hidden" value="${proId}" name="proId">
        <input type="hidden" name="_method" value="POST">
        <table class="layui-table">
            <tr align="center">
                <td>我的手机号：</td>
                <td><input type="text" name="myPhone"></td>
            </tr>
            <tr align="center">
                <td>我的地址：</td>
                <td><input type="text" name="mySite"><span style="color: red">市，区，街道，小区，门牌号</span></td>
            </tr>
            <tr align="center">
                <td>选择银行卡兑换</td>
                <td>
                    <c:forEach var="list" items="${bankCardList}">
                        <c:if test="${list.integral >= integral}">
                            <input type="radio" value="${list.type}" name="type">${list.typeShow}
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr align="center">
                <td colspan="2"><input type="hidden" value="3" name="status"><input type="button" value="提交" onclick="add()"></td>
            </tr>
        </table>
    </form>
</body>
<script>
    function add() {
        var index = layer.load(1,{shade:0.5});
        $.post(
            "<%=request.getContextPath()%>/myProduct/add",
            $("#frm").serialize(),
            function (data){
                if(data.code != 200){
                    layer.msg(data.msg, {icon: 5});
                    layer.close(index);
                    return;
                }
                layer.msg(data.msg, {
                    icon: 6,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    layer.close(index);
                    window.location.href = "<%=request.getContextPath()%>/product/toShow";
                });

            })
    }
</script>
</html>
