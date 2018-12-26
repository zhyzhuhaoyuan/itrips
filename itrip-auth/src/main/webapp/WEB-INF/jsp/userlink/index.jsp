<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/retoken.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#search").click(function () {
                var str = {
                    "linkUserName": $("#name").val()
                };
                var arrs = JSON.stringify(str);
                $.ajax({
                    "url": "http://localhost:8001/itrip-biz/api/userinfo/queryuserlinkuser",
                    "type": "POST",
                    "contentType": 'application/json',
                    "dataType": "json",
                    "data": arrs,
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            var list = data.data;
                            $("#tabDiv").html("");
                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td></tr>").appendTo($("#tabDiv"));
                            $(list).each(function () {
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td></tr>");
                            });
                        } else {
                            alert("token失效，请重新登录");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                });
            });

            $("#add").click(function () {
                $("#tabDivAdd").toggle();
            });

            $("#addTijiao").click(function () {
                var str = {
                    linkUserName: $("#linkUserName").val(),
                    linkIdCard: $("#linkIdCard").val(),
                    linkPhone: $("#linkPhone").val(),
                    linkIdCardType:0
                };
                var arrs = JSON.stringify(str);
                console.log(arrs);
                $.ajax({
                    "url": "http://localhost:8001/itrip-biz/api/userinfo/adduserlinkuser",
                    "type": "POST",
                    "contentType": 'application/json',
                    "dataType": "json",
                    "data": arrs,
                    "success": function (data) {
                        console.log(data);
                        if (data.success == "true") {
                            var list = data.data;
                            $("#tabDiv").html("");
                            var tab = $("<table border='1'></table>").append("<tr><td>id</td><td>userId</td><td>用户名</td><td>电话</td></tr>").appendTo($("#tabDiv"));
                            $(list).each(function () {
                                tab.append("<tr><td>" + this.id + "</td><td>" + this.userId + "</td><td>" + this.linkUserName + "</td><td>" + this.linkPhone + "</td></tr>");
                            });
                        } else {
                            alert("token失效，请重新登录");
                        }
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("token", $.cookie("token"));
                    }
                });
            });
        });
    </script>
</head>
<body>
<div>
    旅客姓名：<input type="text" id="name"/>
    <input type="button" id="search" value="获取常用联系人列表"/>
    <input type="button" id="add" value="新增1"/>
    <input type="button" id="del" value="删除"/>
    <input type="button" id="update" value="更新"/>
</div>
<div id="tabDiv">

</div>
<div id="tabDivAdd" style="display: none;">
    <form>
        <table>
            <tr>
                <td>
                    常用联系人姓名:
                </td>
                <td>
                    <input type="text" id="linkUserName" name="linkUserName">
                </td>
            </tr>
            <tr>
                <td>
                    证件号码:
                </td>
                <td>
                    <input type="text" id="linkIdCard" name="linkIdCard">
                </td>
            </tr>
            <tr>
                <td>
                    常用联系人电话:
                </td>
                <td>
                    <input type="text" id="linkPhone" name="linkPhone">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" id="addTijiao" value="提交"/>
                </td>
                <td>
                    <input type="reset" value="重置"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
