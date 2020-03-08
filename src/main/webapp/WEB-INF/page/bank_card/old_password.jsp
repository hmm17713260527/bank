<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <style type="text/css">
        .error{
            color:red;
        }
    </style>
</head>
<body>
<form id = "frm">
    <input type="hidden" name="id" value="${bankCard.id}"><br/>
    <input type="hidden" name = "_method" value="get"><br>
    银行卡卡号：
    <input type="text" name="bankCardNumber" value="${bankCard.bankCardNumber}"><br/>
    输入旧密码:
    <input type="password" name="password" placeholder="输入旧密码"><br/>
    <input type="button" value="确认修改" onclick="getPassword()"><br />
</form>
</body>
<script type="text/javascript">
    function getPassword() {
        var index = layer.load(0, {shade:0.5});
        $.get("<%=request.getContextPath()%>/bankCard/getPassword",
            $("#frm").serialize(),
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
                    parent.window.location.href = "<%=request.getContextPath()%>/bankCard/toNewPassword/"+${bankCard.id};
                });

            })
    }

</script>
</html>