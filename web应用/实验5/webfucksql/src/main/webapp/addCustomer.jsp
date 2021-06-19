<%--
  Created by IntelliJ IDEA.
  User: C__V
  Date: 2021/6/19
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加顾客</title>
</head>
<body>
<form action="/AddCustomerServlet.do" method="post">
    <label for="custID">客户号：<input type="text" name="custID" id="custID" ></label>
    <label for="custName">客户名：<input type="text" name="custName" id="custName"></label>
    <label for="custBalance">客户余额：<input type="text" name="custBalance" id="custBalance"></label>
    <input type="submit" value="确认添加">
</form>
</body>
</html>
