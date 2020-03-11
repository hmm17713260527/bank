<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/8
  Time: 17:18
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

    function update() {
        var index = layer.load(1,{shade:0.5});
        $.post(
            "<%=request.getContextPath()%>/bankLoans/update",
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
                    parent.window.location.href = "<%=request.getContextPath()%>/bankLoans/toRepaymentShow";
                });

            }
        )
    }


</script>
<body>

<form id = "fm">
    <input type="hidden" name = "_method" value="PUT" >
    <input type="hidden" name = "loansId" value="${id}"/>
    选择银行卡：
    <select name="carId">
        <c:forEach items="${cardList}" var="card">
            <option value="${card.id}">${card.typeShow}</option>
        </c:forEach>
    </select><br />
    <input type="button" value="确认还款" onclick="update()">

</form>

</body>
</html>
