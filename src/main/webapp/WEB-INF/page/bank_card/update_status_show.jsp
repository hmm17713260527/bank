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
            "<%=request.getContextPath()%>/bankCard/show",
            {"status" : 16, "_method" : "GET"},
            function(data){
                var html = "";
                for (var i = 0; i < data.data.length; i++) {
                    var u = data.data[i];
                    html += "<tr>"
                    html += "<td><input type='checkbox' name = 'ids' value = '"+u.id+"'/></td>";
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
</html>
