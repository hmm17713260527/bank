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
</head>
<body>

<form id = "fm">
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

</form>

</body>
</html>
