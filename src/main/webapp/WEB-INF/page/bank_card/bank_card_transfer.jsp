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
    <input type="hidden" name = "_method" value="put"><br>
   选择转出银行卡：
    <select name = "bankCardNumber">
        <option>请选择银行卡</option>
        <c:forEach items="${bankCardList}" var="bank">
            <option value="${bank.bankCardNumber}">${bank.bankCardNumber}</option>
        </c:forEach>
    </select><br>
    选择转入银行卡：
    <input type="text" name="bankCrdNumberTransfer" id="bankCrdNumberTransfer" onblur="getBankCardNumber(this)"><br/>
    转账金额：
    <input type="text" name="balance"><br/>
    密码：
    <input type="password" name="password" id="password"><br/>
    <input type="submit" value="提交"><br />
</form>
</body>
<script type="text/javascript">
    $(function (){
        $("#frm").validate({
            rules:{
                password:{
                    required:true,
                    minlength:6,
                    digits:true
                },
            },
            messages:{

                password:{
                    required:"请输入密码",
                    minlength:"请输入正确密码",
                    digits:"请输入正确密码"
                },
            }
        })
    })

    function getBankCardNumber(obj) {
        var index = layer.load(1,{shade:0.5});
        $.get("<%=request.getContextPath()%>/bankCard/getBankCardNumber",
            {bankCardNumber : obj.value, "_method" : "GET"},
            function (data) {
                if(data.code == -1){
                    layer.close(index);
                    layer.msg(data.msg, {icon: 5});
                    return
                }
                layer.close(index);
                layer.msg(data.msg, {
                    icon: 6,
                    time: 2000
                });
            })
    }
    // 提交转账
    $.validator.setDefaults({
        submitHandler: function (){
            var index = layer.load(1,{shade:0.5});
            $.post(
                "<%=request.getContextPath()%>/bankCard/transfer",
                $("#frm").serialize(),
                function (data){

                    if(data.code != 200){
                        layer.close(index);
                        layer.msg(data.msg, {icon: 5});
                        return;
                    }
                    layer.msg(data.msg, {
                        icon: 6,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){

                        parent.window.location.href = "<%=request.getContextPath()%>/index/toIndex";
                    });
                })
        }

    })
</script>
</html>