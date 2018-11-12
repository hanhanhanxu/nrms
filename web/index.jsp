<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>南阳市移网资源管理系统</title>
</head>
<body>
<div style="text-align: center">
    <br>
    <h1>登录以了解更多内容</h1>
    <br><br>
    <span>输入您的账户</span>
    <span id="forget"><a href="${pageContext.request.contextPath}/UILogin/FindPass.action">我忘记了我的密码</a></span>
    <br><br>
    ${message} <!--显示注册成功消息-->
    <br><br>
    <span>
        <form action="${pageContext.request.contextPath}/LoginAndRegist/login.action" method="post">
            *您的登录账号：<input type="text" name="username" onkeyup="this.value=this.value.replace(/\s+/g,'')" >
            ${form.errors.username}
            <br>
            *您的登录密码：<input type="password" name="password" onkeyup="this.value=this.value.replace(/\s+/g,'')" >
            ${form.errors.password}
            <br>
            身份：
            <select name="identity">
              <option value="2">用户</option>
              <option value="1">管理员</option>
            </select>
            <br><br><br><br>
            <input type="submit" value="继   续">
        </form>
    </span>

    <br>
    <br>
    没有账号？<a href="${pageContext.request.contextPath}/UI/regist.action">立即加入</a>

</div>
</body>
</html>
