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
                    borrowMoney:{
                        required:true,
                        digits:true
                    },
                },
                messages:{
                    borrowMoney:{
                        required:"请填写你的借款金额",
                        digits:"只能是整数",
                    },
                },
            })
        })

        $.validator.setDefaults({
            submitHandler: function() {
                if ($("#borrowMoney").val() > ${borrowMoney}) {
                    layer.msg("可借款金额不足", {icon: 5});
                }

                $.post("<%=request.getContextPath()%>/user/borrowMoney",
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
                            parent.window.location.href = "<%=request.getContextPath()%>/user/toLogin";
                        });
                    }
                )
            }
        });

    </script>
    <!-- 错误时提示颜色 -->
    <style>
        .error{
            color:red;
        }
    </style>

</head>
<body>
<form id="fm">
    可借金额:
    借款金额<input type="text" name="borrowMoney" id="borrowMoney" placeholder="请输入借款金额"  oninput="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1').replace(/^0{1,}/g,'')" maxlength="8"><br />
    还款年限<input type="text" name="refund " onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" class="Wdate" style="width:200px"><br />
    <input type="submit" value="借款">
</form>
</body>
</html>