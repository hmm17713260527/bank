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
            "<%=request.getContextPath()%>/bankLoans/updateStatusById",
            $("#fm").serialize(),
            function(data){

                if(data.code != 200){
                    layer.close(index);
                    layer.msg(data.msg, {icon: 5});
                    return;
                }
                layer.msg(data.msg, {
                    icon: 6,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    parent.window.location.href = "<%=request.getContextPath()%>/bankLoans/toUpdateStatusShow";
                });

            }
        )
    }


</script>
<body>

<form id = "fm">
    <input type="hidden" name = "_method" value="PUT" >
    <input type="hidden" name = "id" value="${bankLoans.id}"/>
    <input type="hidden" name = "userId" value="${bankLoans.userId}"/>
    <input type="hidden" name = "userCard" value="${bankLoans.userCard}"/>
    <input type="hidden" name = "dealMoney" value="${bankLoans.dealMoney}"/>
    <input type="hidden" name = "balanceMoney" value="${bankLoans.balanceMoney}"/>
    <input type="hidden" name = "phone" value="${bankLoans.phone}"/>
    <input type="hidden" name = "carId" value="${bankLoans.carId}"/>
    用户名：${bankLoans.userName}<br/>
    银行卡号:${bankLoans.userCard}<br/>
    银行卡状态：
    <c:forEach items="${baseDataList}" var="base">
        <c:if test="${bankLoans.status == base.id}">
            ${base.name}
        </c:if>
    </c:forEach><br>
    借款金额：${bankLoans.dealMoney}<br/>
    卡上剩余余额：${bankLoans.balanceMoney}<br/>

    <select name="status">
        <c:forEach items="${baseDataList}" var="base">
            <option value="${base.id}">${base.name}</option>
        </c:forEach>
    </select><br />

    <input type="button" value="确认审批" onclick="updateStatusById()">

</form>

</body>
</html>
