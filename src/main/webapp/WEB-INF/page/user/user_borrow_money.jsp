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
            search();
        })

        function search() {
            $.get(
                "<%=request.getContextPath()%>/bankCard/bankCardList",
                {"status" : 17, "_method" : "GET"},
                function(data){
                    var html = "";
                    for (var i = 0; i < data.data.length; i++) {
                        var u = data.data[i];
                        html += "<tr>"
                        html += "<td style=\"color : blue\">${USER_SESSION.userName}</td>"
                        html += "<td style=\"color : rebeccapurple\">"+u.bankCardNumber+"</td>"
                        if (u.type == 11) {
                            html += "<td style=\"color : deeppink\">工商银行</td>"
                        } else if (u.type == 12) {
                            html += "<td style=\"color : deeppink\">建设银行</td>"
                        } else if (u.type == 13) {
                            html += "<td style=\"color : deeppink\">农行银行</td>"
                        } else {
                            html += "<td style=\"color : deeppink\">中国银行</td>"
                        }
                        html += "<td style=\"color : deeppink\">"+u.balance+"</td>"
                        html += "<td style=\"color : darkred\">"+u.integral+"</td>"
                        html += "<td style=\"color : darkred\">"+u.reputationValue+"</td>"
                        html += "<td style=\"color : green\">"+u.createTime+"</td>"
                        html += "<td><input type='button' value='借款' onclick='toBorrow("+u.id+")'></td>"
                        html += "</tr>"
                    }
                    $("#tbd").html(html);
                }
            )
        }

        //去借款
        function toBorrow(id){
                layer.open({
                    type: 2,
                    title: '借款页面',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['380px', '90%'],
                    content: '<%=request.getContextPath()%>/user/toBorrow/'+id
                });
        }

    </script>

</head>
<body>
<form id="fm">
    <table border="1px">
        <tr>
            <th style="color : deeppink">所属人</th>
            <th style="color : deeppink">卡号</th>
            <th style="color : deeppink">类型</th>
            <th style="color : deeppink">银行卡余额</th>
            <th style="color : deeppink">卡上积分</th>
            <th style="color : deeppink">卡信誉值</th>
            <th style="color : deeppink">银行卡申请时间</th>
            <th style="color : deeppink">操作</th>
        </tr>
        <tbody id="tbd">

        </tbody>
    </table>
</form>
</body>
</html>