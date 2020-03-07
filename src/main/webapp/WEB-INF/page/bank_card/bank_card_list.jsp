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
    <table border="1px" cellpadding="10px" cellspacing="0px" align="center">
        <tr>
            <th>银行卡号</th>
            <th>剩余余额</th>
            <th>卡上积分</th>
            <th>卡信誉值</th>
            <th>银行卡状态</th>
            <th>银行卡申请时间</th>
            <th>银行卡所属行</th>
            <th>剩余所借金额</th>
        </tr>
        <tbody id="tbd">

        </tbody>
    </table>
</form>
</body>
<script type="text/javascript">
    $(function(){
        search();
    })

    function search() {
        $.get(
            "<%=request.getContextPath()%>/bankCard/bankCardList",
            {"status" : 1, "_method" : "GET"},
            function(data){
                var html = "";
                for (var i = 0; i < data.data.length; i++) {
                    var u = data.data[i];
                    html += "<tr>"
                    html += "<td>"+u.bankCardNumber+"</td>"
                    html += "<td>"+u.balance+"</td>"
                    html += "<td>"+u.integral+"</td>"
                    html += "<td>"+u.reputationValue+"</td>"
                    if (u.status == 1) {
                        html += "<td>正常</td>"
                    } else if (u.status == 2) {
                        html += "<td>冻结</td>"
                    } else if (u.status == 3) {
                        html += "<td>待审核</td>"
                    } else {
                        html += "<td>审核未通过</td>"
                    }
                    html += "<td>"+u.createTime+"</td>"
                    if (u.type == 11) {
                        html += "<td>工商银行</td>"
                    } else if (u.type == 12) {
                        html += "<td>建设银行</td>"
                    } else if (u.type == 13) {
                        html += "<td>农行银行</td>"
                    } else {
                        html += "<td>中国银行</td>"
                    }
                    html += "<td>"+u.borrowBalance+"</td>"
                    html += "</tr>"
                }
                $("#tbd").html(html);
            }
        )
    }
</script>
</html>