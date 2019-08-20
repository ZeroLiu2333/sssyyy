<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>教师端</title>
    <style>
        table {
            width: 50%;
            font-size: .938em;
            border-collapse: collapse;/*边框合并*/
        }
        th {
            text-align: left;
            padding: .5em .5em;
            font-weight: bold;
            background: #66677c;color: #fff;
        }

        td {
            padding: .5em .5em;
            border-bottom: solid 1px #ccc;
        }

        table,table tr th, table tr td { border:1px solid #0094ff; }/*设置边框*/
    </style>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<body #fcf8e3>
<div class="container-fluid" >
    <div class="row-fluid">
        <div class="col-lg-3">
            <hr>
        </div>
        <div class="col-lg-6">
            <h1 align="center">教师管理系统</h1>
            <hr>
            <br>
        </div>
        <div class="col-lg-3">
            <div class="row-fluid">
                <div class="col-lg-4">
                    <hr>
                    <button class="btn btn-info" data-toggle="modal" data-target="#info_teacher" onclick="teacher_info()">${Session["user"].username}个人信息</button>
                </div>
                <div class="col-lg-4">
                    <hr>
                    <button class="btn btn-info" data-toggle="modal" data-target="#addModal">${Session["user"].username}添加课程</button>
                </div>
                <div class="col-lg-4">
                    <hr>
                    <button class="btn btn-info" data-toggle="modal" data-target="#upModal">${Session["user"].username}修改密码</button>

                </div>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-lg-4">
            <div class="row-fluid">

                <div class="col-lg-8">
                    <div class="form-group">
                        <input type="text" class="form-control" id="teacher" name="teacher"
                               placeholder="请输入教师名">
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="form-group">
                        <button class="btn btn-info" name="selectteacher" id="selectteacher" onclick="st()" ">查询</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="row-fluid">
                <div class="col-lg-8">
                    <div class="form-group">
                        <input type="text" name="school" id="school" class="form-control" placeholder="请输入学院">
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="form-group">
                        <button class="btn btn-info" name="schoolselect" id="schoolselect" onclick="ot()">查询</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="row-fluid">
                <div class="col-lg-8">
                    <div class="form-group">
                        <input type="text" name="cid" id="cid" class="form-control" placeholder="请输入课程编号">
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="form-group">
                        <button class="btn btn-info" name="cidselect" id="cidselect" onclick="cids()">查询</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-lg-12">
            <table class="table table-bordered table-hover" style="background-color: #d9edf7">
                <thead>
                <tr class="info">
                    <th>课程编号</th>
                    <th>课程名称</th>
                    <th>教师名称</th>
                    <th>人数限制</th>
                    <th>已选人数</th>
                    <th>学院限制</th>
                    <th>年级限制</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list course_list1 as course1>
                    <tr class="warning">
                        <td>${course1.cid}</td>
                        <td>${course1.cname}</td>
                        <td>${course1.teacher}</td>
                        <td>${course1.number}</td>
                        <td>${course1.selected}</td>
                        <td>${course1.slimit}</td>
                        <td>${course1.glimit}</td>
                        <td>${(course1.start_date?string('yyyy.MM.dd HH:mm:ss'))!}</td>
                        <td>${(course1.end_date?string('yyyy.MM.dd HH:mm:ss'))!}</td>
                        <td><#if flag==0>
                                <button class="btn btn-info" onclick="selectone(${course1.cid},${page.currentPageNum})">选课</button>
                            <#else >
                                <button class="btn btn-info" onclick="unselect(${course1.cid},${page.currentPageNum})">退课</button>
                            </#if>
                        </td>
                    </tr>
                </#list>
                <#list course_add as course1>
                    <tr>
                        <td>${course1.cid}</td>
                        <td>${course1.cname}</td>
                        <td>${course1.teacher}</td>
                        <td>${course1.number}</td>
                        <td>${course1.selected}</td>
                        <td>${course1.slimit}</td>
                        <td>${course1.glimit}</td>
                        <td><button data-toggle="modal" data-target="#updateModal" onclick="editInfo(${course1.cid})">修改</button>
                            <button onclick="deleteone(${course1.cid},${page.currentPageNum})">删除</button></td>
                    </tr>
                </#list>
                <#list course_add1 as course1>
                    <tr>
                        <td>${course1.cid}</td>
                        <td>${course1.cname}</td>
                        <td>${course1.teacher}</td>
                        <td>${course1.number}</td>
                        <td>${course1.selected}</td>
                        <td>${course1.slimit}</td>
                        <td>${course1.glimit}</td>
                        <td><button data-toggle="modal" data-target="#updateModal" onclick="editInfo(${course1.cid})">修改</button>
                            <button onclick="deleteone(${course1.cid},${page.currentPageNum})">删除</button></td>
                    </tr>
                </#list>
                <#list course_add2 as course1>
                    <tr>
                        <td>${course1.cid}</td>
                        <td>${course1.cname}</td>
                        <td>${course1.teacher}</td>
                        <td>${course1.number}</td>
                        <td>${course1.selected}</td>
                        <td>${course1.slimit}</td>
                        <td>${course1.glimit}</td>
                        <td><button data-toggle="modal" data-target="#updateModal" onclick="editInfo(${course1.cid})">修改</button>
                            <button onclick="deleteone(${course1.cid},${page.currentPageNum})">删除</button></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-lg-12">
            <div >
                <nav style="text-align: center">
                    <ul class="pagination pagination-lg">
                        <#if page.currentPageNum==1>
                            <li>
                                <a href="${page.url}?num=1">首页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="${page.url}?num=1">首页</a>
                            </li>
                            <li>
                                <a href="${page.url}?num=${page.prePageNum}">上一页</a>
                            </li>
                        </#if>
                        <#list page.startPage .. page.endPage as i>
                            <li>
                                <a href="${page.url}?num=${i}">${i}</a>
                            </li>
                        </#list>

                        <#if page.currentPageNum == page.totalpagenum>
                            <li>
                                <a href="${page.url}?num=${page.totalpagenum}">末页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="${page.url}?num=${page.nextPageNum}">下一页</a>
                            </li>
                            <li>
                                <a href="${page.url}?num=${page.totalpagenum}">末页</a>
                            </li>
                        </#if>


                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h3 class="modal-title" id="myModalLabel">
                    修改课程信息
                </h3>
            </div>
            <form id="form_data">
                <div class="modal-body">
                    原始编号: <input type="text" id="up_old_cid" name="cid" readonly="readonly"/><br><hr>
                    课程编号: <input type="text" id="up_cid" name="cid"/><br><hr>
                    课程名称: <input type="text" id="up_cname" name="cname"/><br><hr>
                    教师姓名: <input type="text" id="up_teacher" name="teacher"/><br><hr>
                    人数限制: <input type="text" id="up_number" name="number"/><br><hr>
                    学院限制: <input type="text" id="up_slimit" name="slimit"/><br><hr>
                    年级限制: <input type="text" id="up_glimit" name="glimit"/><br><hr>
                    <input type="hidden" id="act" value="add" name="act"/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" onclick="update_info()" class="btn btn-primary">
                        提交更改
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h3 class="modal-title" id="myModalLabel">
                    添加课程信息
                </h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">课程编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_cid"
                                   placeholder="请输入课程编号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cname" class="col-sm-2 control-label">课程名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_cname"
                                   placeholder="请输入课程名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cname" class="col-sm-2 control-label">教师姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_teacher"
                                   placeholder="请输入教师姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cname" class="col-sm-2 control-label">人数限制</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_number"
                                   placeholder="请输入教师姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cname" class="col-sm-2 control-label">学院限制</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_slimit"
                                   placeholder="请输入学院限制">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cname" class="col-sm-2 control-label">年级限制</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_glimit"
                                   placeholder="请输入年级限制">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" onclick="add_info()" class="btn btn-primary">
                            提交
                        </button>
                    </div>
                </form>
            </div>



        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="upModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h3 class="modal-title" id="myModalLabel">
                    修改密码
                </h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">请输入新密码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_password"
                                   placeholder="请输入新密码">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" onclick="up_password()" class="btn btn-primary">
                            提交
                        </button>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="info_teacher" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h3 class="modal-title" id="myModalLabel">
                    教师个人信息表
                </h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" style="background-color: #d9edf7">
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="info_name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">工号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="info_id"
                            >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">所属院系</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="info_class"
                            >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">教学年级</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="info_grade"
                                   placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">手机号码</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="info_phone"
                                   placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">电子邮件</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="info_email"
                                   placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">家庭地址</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="info_address"
                                   placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>

                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->

<script type="text/javascript">
    function st() {
        var teacher = $("#teacher").val();

        window.location.href = "/checkcourse/checkteacher?teacher=" + teacher+"&num=1";

    }
    function ot() {
        var slimit = $("#school").val();
        alert(slimit)
        $.ajax({
            url: "/checkcourse/check1",
            type: "post",
            data: {
                slimit: $("#school").val()
            },
            success: function (data) {
                if (data.code == 0) {
                    window.location.href = "/checkcourse/checkschool?slimit=" + slimit+"&num=1";
                } else {
                    alert("用户名或密码不正确！！")

                }

            },
            error: function () {

            }
        })
    }
    function cids() {
        var cid = $("#cid").val();
        $.ajax({
            url: "/checkcourse/check2",
            type: "post",
            data: {
                cid: $("#cid").val()
            },
            success: function (data) {
                if (data.code == 0) {
                    window.location.href = "/checkcourse/checkcid?cid=" + cid+"&num=1";
                } else {
                    alert("用户名或密码不正确！！")

                }

            },
            error: function () {

            }
        })
    }
    function deleteone(cid,page) {
        window.location.href= "/checkcourse/DeCourse?cid="+cid;

    }
    function update_info() {
        var gcid=document.getElementById("up_old_cid").value;
        var cid=document.getElementById("up_cid").value;
        var cname= document.getElementById("up_cname").value;
        var teacher=document.getElementById("up_teacher").value;
        var slimit= document.getElementById("up_slimit").value;
        var glimit=document.getElementById("up_glimit").value;
        var number=document.getElementById("up_number").value;
        $.ajax({
            url: "/checkcourse/UpdateCouse",
            type: "post",
            data: {
                gcid:gcid,
                cid:cid,
                cname: cname,
                teacher:teacher,
                slimit: slimit,
                glimit:glimit,
                number:number
            },
            success: function (data) {
                if (data.code == 0) {
                    window.location.href = "/checkcourse/usertype?num=1";
                } else {
                    alert("服务器忙！！")
                }
            },
            error: function () {
            }
        })
    }
    function add_info() {
        var cid=document.getElementById("add_cid").value;
        var cname= document.getElementById("add_cname").value;
        var teacher=document.getElementById("add_teacher").value;
        var slimit= document.getElementById("add_slimit").value;
        var glimit=document.getElementById("add_glimit").value;
        var number=document.getElementById("add_number").value;

        $.ajax({
            url: "/checkcourse/add",
            type: "post",
            data: {
                cid:cid,
                cname: cname,
                teacher:teacher,
                slimit: slimit,
                glimit:glimit,
                number:number
            },
            success: function (data) {
                if (data.code == 0) {
                    window.location.href = "/checkcourse/usertype?num=1" ;
                } else {
                    alert("服务器忙！！")
                }
            },
            error: function () {
            }
        })
    }
    function editInfo(cid) {
        //向模态框中传值
        $('#up_old_cid').val(cid);
        $('#updateModal').modal('show');
    }
    function up_password() {
        var password=document.getElementById("new_password").value;
        $.ajax({
            url: "/check/up_password",
            type: "post",
            data: {
                password:password
            },
            success: function (data) {
                if (data.code == 0) {
                    alert("修改密码成功");
                } else {
                    alert("服务器忙！！")
                }

            },
            error: function () {
            }
        })
    }
    function teacher_info() {


        $.ajax({
            url: "/check/information",
            type: "post",

            success: function (data) {
                $('#info_name').val(data.data.username);

                $('#info_id').val(data.data.uid);
                $('#info_class').val(data.data.class1);
                $('#info_grade').val(data.data.grade);
                $('#info_phone').val(data.data.phone);
                $('#info_address').val(data.data.address);
                $('#info_email').val(data.data.email);
                $('#info_user').modal('show');
            },
            error: function () {
            }
        })
    }
</script>
</body>
</html>