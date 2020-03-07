<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/7
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Title</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jsencrypt.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">

    function updateStatusById() {
        var index = layer.load(1,{shade:0.5});
        $.post(
            "<%=request.getContextPath()%>/bankCard/updateStatusById",
            $("#fm").serialize(),
            function(data){
                layer.close(index);
                if(data.code != 200){
                    layer.msg(data.msg, {icon: 5});
                    return;
                }
                layer.msg(data.msg, {
                    icon: 6,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    parent.window.location.href = "<%=request.getContextPath()%>/bankCard/toUpdateStatusShow";
                });

            }
        )
    }


</script>
<body>

<form id = "fm">
    <input type="hidden" name = "_method" value="PUT" >
    <input type="hidden" name = "id" value="${bankCard.id}"/>
    银行卡号:${bankCard.bankCardNumber}<br/>
    银行卡状态：
    <c:forEach items="${baseDataList}" var="base">
        <c:if test="${bankCard.status == base.id}">
            ${base.name}
        </c:if>
    </c:forEach><br>
    银行卡申请时间:${bankCard.createTimeShow}<br>
    银行卡所属行:
    <c:forEach items="${baseDataList1}" var="base">
        <c:if test="${bankCard.type == base.id}">
            ${base.name}
        </c:if>
    </c:forEach><br>

    <select name="status">
        <c:forEach items="${baseDataList}" var="base">
            <option value="${base.id}">${base.name}</option>
        </c:forEach>
    </select><br />

    <input type="button" value="确认审批" onclick="updateStatusById()">

</form>

</body>
</html>
