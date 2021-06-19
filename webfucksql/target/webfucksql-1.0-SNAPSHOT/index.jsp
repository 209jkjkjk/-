<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<a href="register.jsp">注册不注册？</a>
<br/>
<form action="hello-servlet" method="post">
    <input type="text" name="name">
    <input type="text" name="dburl" value="jdbc:mysql://mysql.cangcz.fun:7501?/web作业?serverTimezone=Asia/Shanghai">
    <input type="submit" value="冲啊">
</form>
</body>
</html>