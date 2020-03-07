<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>\static\layer-v3.1.1\layer\layer.js"></script>
    <script src="<%=request.getContextPath()%>\static\js\jquery.validate.js"></script>
    <script src="<%=request.getContextPath()%>\static\My97DatePicker\WdatePicker.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript">

        $(function(){
            $("#fm").validate({
                //效验规则
                rules: {
                    dealMoney:{
                        required:true,
                    },
                },
                messages:{
                    dealMoney:{
                        required:"请填写充值金额",
                    },
                },
            })
        })

        $.validator.setDefaults({
            submitHandler: function() {

                var index = layer.load(0, {shade:0.5});
                $.post("<%=request.getContextPath()%>/bankCard/updateCardBalance",
                    $("#fm").serialize(),
                    function(data){
                        if(data.code == -1){
                            layer.close(index);
                            layer.msg(data.msg, {icon: 5});
                            return
                        }
                        layer.msg(data.msg, {
                            icon: 6,
                            time: 2000
                        }, function(){
                            parent.window.location.href = "<%=request.getContextPath()%>/user/toUpdateBalance";
                        });
                    }
                )
            }
        });


    </script>

</head>
<body>
<form id="fm">
    当前卡号:${bankCard.bankCardNumber}<br>
    剩余金额:${bankCard.balance}<br>
    充值金额:<input type="text" name="dealMoney" id="dealMoney" oninput="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1').replace(/^0{1,}/g,'')" maxlength="8" placeholder="请输入借款金额"><br>
    <input type="submit" value="充值">
    <input type="hidden" name="userId" value="${bankCard.userId}">
    <input type="hidden" name="userCard" value="${bankCard.bankCardNumber}">
    <input type="hidden" name="balance" id="balance" value="${bankCard.balance}">
    <input type="hidden" name="bankCardId" value="${bankCard.id}">
</form>
</body>
</html>