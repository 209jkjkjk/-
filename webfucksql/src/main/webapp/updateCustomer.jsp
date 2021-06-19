<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新客户信息</title>
</head>
<body>
    <form action="/UpdateCustomerServlet.do" method="post">
        <label for="custID">客户号：<input type="text" name="custID" id="custID" readonly="readonly" value="${param.custID}"></label>
        <label for="custName">客户名：<input type="text" name="custName" id="custName" value="${param.custName}"></label>
        <label for="custBalance">客户余额：<input type="text" name="custBalance" id="custBalance" value="${param.custBalance}"></label>
        <input type="submit" value="确认修改">
    </form>
</body>
</html>
