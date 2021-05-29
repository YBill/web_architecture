<%--
  Created by IntelliJ IDEA.
  User: taike
  Date: 2021/5/28
  Time: 5:58 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>

<form action="/web_architecture/UserServlet" method="post">
    用户名：<input type="text" name="name">
    <p>
        密 码：<input type="password" name="password">
    <p>
        <input type="submit" name="type" value="注册">
        <input type="submit" name="type" value="登陆">

</form>
</body>
</html>
