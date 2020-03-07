<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/3/7
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.all.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
    <script type="text/javascript">
        $(function(){
            search();
        })
        function search() {
            $.get(
                "<%=request.getContextPath()%>/user/list",
                {"isDel" : 1, "_method" : "GET","type":1},
                function(data){
                    var html = "";
                    for (var i = 0; i < data.data.length; i++) {
                        var u = data.data[i];
                        html += "<tr align = 'center'>"
                        html += "<td>"+u.userName+"</td>"
                        html += "<td>"+u.password+"</td>";
                        html += "<td>"+u.phone+"</td>"
                        html += "<td>"+u.email+"</td>"
                        html += u.sex == 1 ? "<td>男</td>" : "<td>女</td>";
                        html += "<td>"+u.age+"</td>"
                        html += "<td>"+u.bankCardNumber+"</td>"
                        html += "<td>"+u.cType+"</td>"
                        html += "<td>"+u.reputationValue+"</td>"
                        if (u.reputationValue <= 30) {
                            html += '<td style="color: #FF00FF"><input type="button" value="可拉黑该用户" style="color: red" onclick="updateIsDelById('+u.id+')"></td>'
                        } else {
                            html += "<td style='color: #FF00FF'>该用户信誉值正常</td>"
                        }
                        html += "</tr>"
                    }
                    $("#tbd").html(html);
                }
            )
        }

        function updateIsDelById(id) {
            var index = layer.load(0, {shade:0.5});
            $.post(
                "<%=request.getContextPath()%>/user/updateIsDelById",
                {"id":id, "_method":"PUT", "isDel":2},
                function (data) {
                    if(data.code == -1){
                        layer.close(index);
                        layer.msg(data.msg, {icon: 5});
                        return
                    }
                    layer.msg(data.msg, {
                        icon: 6,
                        time: 2000
                    }, function(){
                        search();
                        layer.close(index);
                    });
                }
            )
        }
    </script>
</head>
<body>

    <table class="layui-table">
        <tr align="center">
            <td>用户名</td>
            <td>密码</td>
            <td>手机号</td>
            <td>邮箱</td>
            <td>性别</td>
            <td>年龄</td>
            <td>银行卡号</td>
            <td>银行名称</td>
            <td>信誉值</td>
            <td>操作</td>
        </tr>
        <tbody id="tbd">

        </tbody>
    </table>
</body>
</html>
