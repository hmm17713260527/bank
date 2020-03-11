<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>\static\js\jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>\static\js\md5-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>\static\layer-v3.1.1\layer\layer.js"></script>
    <script src="<%=request.getContextPath()%>\static\js\jquery.validate.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript">

        jQuery.validator.addMethod("phone",
            function(value, element) {
                var tel = /^1[3456789]\d{9}$/;
                return tel.test(value)
            }, "请正确填写您的手机号");


        $(function(){
            $("#fm").validate({
                //效验规则
                rules: {
                    userName:{
                        required:true,
                        minlength:2,
                        remote: {
                            type: 'GET',
                            url: "<%=request.getContextPath()%>/user/distinct",
                            data:{
                                userName:function() {
                                    return $("#userName").val();
                                },
                                dataType:"json",
                                dataFilter:function(data,type){
                                    if (data == 'true'){
                                        return true;
                                    }else {
                                        return false	;
                                    }
                                }
                            }
                        }
                    },
                    password:{
                        required:true,
                        minlength:1,
                        digits:true
                    },
                    password2:{
                        required:true,
                        minlength:1,
                        digits:true,
                        equalTo:"#pwd"
                    },
                    age:{
                        required:true,
                        minlength:2,
                        digits:true,
                    },
                    phone:{
                        required:true,
                        phone:true,
                        digits:true,
                        remote: {
                            type: 'GET',
                            url: "<%=request.getContextPath()%>/user/distinct",
                            data:{
                                phone:function() {
                                    return $("#phone").val();
                                },
                                dataType:"json",
                                dataFilter:function(data,type){
                                    if (data == 'true'){
                                        return true;
                                    }else {
                                        return false	;
                                    }
                                }
                            }
                        }
                    },
                    email:{
                        required:true,
                        email:5,
                        remote: {
                            type: 'GET',
                            url: "<%=request.getContextPath()%>/user/distinct",
                            data:{
                                email:function() {
                                    return $("#email").val();
                                },
                                dataType:"json",
                                dataFilter:function(data,type){
                                    if (data == 'true'){
                                        return true;
                                    }else {
                                        return false	;
                                    }
                                }
                            }
                        }
                    },
                },
                messages:{
                    userName:{
                        required:"请填写名字",
                        minlength:"最少两个字"
                    },
                    password:{
                        required:"请填写密码",
                        minlength:"最少1个字",
                        digits:"只能是数字"
                    },
                    password2:{
                        required:"请确认密码",
                        minlength:"最少1个字",
                        digits:"只能是数字",
                        equalTo:"两次输入密码不同"
                    },
                    age:{
                        required:"请确认年龄",
                        minlength:"最少双位数",
                        digits:"只能是数字",
                    },
                    phone:{
                        required:"请填写手机号",
                        rangelength:"11位呀",
                        digits:"只能是数字"
                    },
                    email:{
                        required:"请填写你的邮箱",
                        email:"邮箱格式不对"
                    },
                },
            })
        })

        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(1,{shade:0.5});
                var pwd = md5($("#pwd").val());
                var salt = $("#salt").val();
                var md5pwd = md5(pwd + salt);
                $("#pwd").val(md5pwd);
                $.post("<%=request.getContextPath()%>/user/add",
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
        //div隐藏展示
        function isShow(isShow) {
            if (isShow == 5) {
                $("#div").show();
            } else {
                $("#div").hide();
            }
        }

        $(function(){
            $("#div").hide();
        })

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
    用户名:<input type="text" name="userName" id="userName"><br />
    密码:<input type="password" name="password" id="pwd"><br />
    确认密码:<input type="password" name="password2"><br />
    手机号:<input type="text" name="phone" id="phone"><br />
    年龄:<input type="text" name="age" id="age"><br />
    邮箱:<input type="text" name="email" id="email"><br />
    性别:
        男<input type="radio" name="sex" value="1" checked>
        女<input type="radio" name="sex" value="2"><br />
    角色:
        普通用户<input type="radio" name="type" value="1" checked>
        管理员<input type="radio" name="type" value="2"><br />
    <input type="hidden" name="isDel" value="1">
    <input type="hidden" name="salt" value="${salt}" id="salt">
    <a href="<%=request.getContextPath()%>/user/toLogin">已有账号?前去登录</a><br/>
    <input type="submit" value="注册">
</form>
</body>
</html>