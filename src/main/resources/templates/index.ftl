<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<body style="background-color: #d9edf7">
<h1 align="center" >欢迎使用选课系统</h1>
<hr>
<br>

<div class="container" >
    <div class="row-fluid">
        <form class="form-horizontal" role="form" name="blogin" id="blogin" method="post">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="username" name="username"
                           placeholder="请输入用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="请输入密码">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-10">
                    <button  class="btn-lg btn-info" onclick="login()">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    function login() {

        $("#blogin").validate({
            submitHandler:function (form) {
                dologin();
            }
        });

    }
    function dologin() {
        $.ajax({
            url:"/login/do_login",
            type:"post",
            data:{
                username:$("#username").val(),
                password:$("#password").val(),
            },
            success:function (data) {
                if(data.code==0){
                    window.location.href="/checkcourse/usertype?num=1";
                }else{
                    alert("用户名或密码不正确！！")

                }

            },
            error:function () {

            }
        });

    }
</script>
</html>