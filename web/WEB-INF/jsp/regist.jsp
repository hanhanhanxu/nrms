<%--
  Created by IntelliJ IDEA.
  User: 11952
  Date: 2018/10/19
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册-南阳市移网资源管理系统</title>

    <script type="text/javascript">
        function changeImage(img){
            img.src = img.src + "?" + new Date().getTime();
        }
    </script>
</head>
<body>

<br><br>
<a href="${pageContext.request.contextPath}/index.jsp">去首页</a>
<br><br>
<div>
    <%--<form action="${pageContext.request.contextPath}/LoginAndRegist/regist.action" method="post">
        &lt;%&ndash;<table>
            <tbody>
            <tr>
                <td> *登录账号：</td>
                <td><input type="text" name="username" value="${form.username}" onkeyup="this.value=this.value.replace(/\s+/g,'')" required="required"></td>
                <td>${form.errors.username}</td>
            </tr>
            <tr>
                <td>*登录密码：</td>
                <td><input type="password" name="password" value="${form.password}" onkeyup="this.value=this.value.replace(/\s+/g,'')" required="required"></td>
                <td>${form.errors.password}</td>
            </tr>
            <tr>
                <td>*确认密码：</td>
                <td><input type="password" name="password2" value="${form.password2}" onkeyup="this.value=this.value.replace(/\s+/g,'')" required="required"></td>
                <td>${form.errors.password2}</td>
            </tr>
            <tr>
                <td>*昵称：</td>
                <td><input type="text" name="nickname" value="${form.nickname}" onkeyup="this.value=this.value.replace(/\s+/g,'')" required="required"></td>
                <td>${form.errors.nickname}</td>
            </tr>
            <tr>
                <td>*图片认证:</td>
                <td>
                    <input type ="text" name="checkcode" required>
                    &lt;%&ndash;<img src="${pageContext.request.contextPath}/IdentifyCode" onclick="changeImage(this)" title="换一张" style="cursor:pointer">&ndash;%&gt;
                    (请输入图中数字)
                </td>
                <td>${form.errors.checkcode}</td>
            </tr>
            </tbody>
        </table>&ndash;%&gt;
        <table>
            <tbody>
            <tr>
                <td> *登录账号：</td>
                <td><input type="text" name="username" value="${form.username}" onkeyup="this.value=this.value.replace(/\s+/g,'')"></td>
                <td>${form.errors.username}</td>
            </tr>
            <tr>
                <td>*登录密码：</td>
                <td><input type="password" name="password" value="${form.password}" onkeyup="this.value=this.value.replace(/\s+/g,'')" ></td>
                <td>${form.errors.password}</td>
            </tr>
            <tr>
                <td>*确认密码：</td>
                <td><input type="password" name="password2" value="${form.password2}" onkeyup="this.value=this.value.replace(/\s+/g,'')" ></td>
                <td>${form.errors.password2}</td>
            </tr>
            <tr>
                <td>*昵称：</td>
                <td><input type="text" name="nickname" value="${form.nickname}" onkeyup="this.value=this.value.replace(/\s+/g,'')" ></td>
                <td>${form.errors.nickname}</td>
            </tr>
            <tr>
                <td>*归属地：</td>
                <td><input type="text" name="pobelong" value="${form.pobelong}"></td>
                <td>${form.errors.pobelong}</td>
            </tr>
            <tr>
                <td>*电话：</td>
                <td><input type="text" name="tel" value="${form.tel}"></td>
                <td>${form.errors.tel}</td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="继 续">
    </form>--%>


        <form name="regist" action="" method="post">
            <table>
                <tbody>
                <tr>
                    <td> *登录账号：</td>
                    <td><input type="text" name="username" value="${form.username}" onkeyup="this.value=this.value.replace(/\s+/g,'')"></td>
                    <td>${form.errors.username}</td>
                </tr>
                <tr>
                    <td>*登录密码：</td>
                    <td><input type="password" name="password" value="${form.password}" onkeyup="this.value=this.value.replace(/\s+/g,'')" ></td>
                    <td>${form.errors.password}</td>
                </tr>
                <tr>
                    <td>*确认密码：</td>
                    <td><input type="password" name="password2" value="${form.password2}" onkeyup="this.value=this.value.replace(/\s+/g,'')" ></td>
                    <td>${form.errors.password2}</td>
                </tr>
                <tr>
                    <td>*昵称：</td>
                    <td><input type="text" name="nickname" value="${form.nickname}" onkeyup="this.value=this.value.replace(/\s+/g,'')" ></td>
                    <td>${form.errors.nickname}</td>
                </tr>
                <tr>
                    <td>*归属地：</td>
                    <td><input type="text" name="pobelong" value="${form.pobelong}"></td>
                    <td>${form.errors.pobelong}</td>
                </tr>
                <tr>
                    <td>*电话：</td>
                    <td><input type="text" name="tel" value="${form.tel}"></td>
                    <td>${form.errors.tel}</td>
                </tr>
                </tbody>
            </table>
            <button onclick="user()"/>注册用户</button>
            <button onclick="admin()"/>注册管理员</button>
        </form>
        <script>
            function user(){
                document.regist.action = "${pageContext.request.contextPath}/LoginAndRegist/registUser.action";
                document.regist.submit();
            }
            function admin() {
                document.regist.action = "${pageContext.request.contextPath}/LoginAndRegist/registAdmin.action";
                document.regist.submit();
            }
        </script>


</div>

</body>
</html>
