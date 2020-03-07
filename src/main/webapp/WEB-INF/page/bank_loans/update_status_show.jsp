<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/7
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Title</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">
    $(function(){
        search();
    })

    function search() {
        $.get(
            "<%=request.getContextPath()%>/bankLoans/bankCardList",
            {"status" : 16, "_method" : "GET"},
            function(data){
                var html = "";
                for (var i = 0; i < data.data.length; i++) {
                    var u = data.data[i];
                    html += "<tr>"
                    html += "<td><input type='checkbox' name = 'ids' value = '"+u.id+"'/></td>";
                    html += "<td>"+u.userName+"</td>"
                    html += "<td>"+u.bankCardNumber+"</td>"
                    html += "<td>"+u.payMoneyAll+"</td>"
                    html += "<td>"+u.payMonthNumber+"</td>"
                    html += "<td>"+u.payMoneyMonth+"</td>"
                    if (u.status == 17) {
                        html += "<td>正常</td>"
                    } else if (u.status == 16) {
                        html += "<td>待审核</td>"
                    } else {
                        html += "<td>审核未通过</td>"
                    }
                    html += "</tr>"
                }
                $("#tbd").html(html);
            }
        )
    }



    function toUpdateStatus() {
        var chkValue = $('input[name="ids"]:checked');
        if (chkValue.length == 0) {
            layer.msg('未选中');
        } else if (chkValue.length > 1) {
            layer.msg('多选');
        } else {
            var id = chkValue.val();
            layer.open({
                type: 2,
                title: '审核页面',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '80%'],
                content: '<%=request.getContextPath()%>/bankCard/toUpdateStatus/'+id
            });
        }
    }



</script>
<body>

<form id = "frm">
    <input type="button" value="审核" onclick="toUpdateStatus()"/>
    <table border="1px" cellpadding="10px" cellspacing="0px" align="center">
        <tr>
            <td>id</td>
            <th>借款人</th>
            <th>银行卡号</th>
            <th>借款金额</th>
            <th>还款分期(月)</th>
            <th>每月应还</th>
            <th>状态</th>
        </tr>
        <tbody id="tbd">

        </tbody>
    </table>
</form>

</body>
</html>
