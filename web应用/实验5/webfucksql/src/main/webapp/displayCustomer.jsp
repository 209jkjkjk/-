<%@ page import="com.model.Customer" %>
<%@ page import="java.util.Vector" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户列表</title>
</head>
<body>
    <form action="/SearchCustomerServlet.do" method="post">
        <input type="text" name="fuzzyString" placeholder="搜索客户">
        <input type="submit" value="搜索">
    </form>
    <a href="/DisplayCustomerServlet.do">查看全部顾客</a>
    <a href="/addCustomer.jsp">添加顾客</a>
    <table>
        <tr>
            <th>客户号</th>
            <th>客户名</th>
            <th>余额</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
    <% for(Customer c: (Vector<Customer>)request.getAttribute("customers")){ %>
        <tr>
            <td><%= c.getCustID() %></td>
            <td><%= c.getCustName() %></td>
            <td><%= c.getCustBalance() %></td>
            <td><a href="/updateCustomer.jsp?custID=<%= c.getCustID() %>&custName=<%= c.getCustName() %>&custBalance=<%= c.getCustBalance() %>">修改</a></td>
            <td><a href="/DeleteCustomerServlet.do?custID=<%= c.getCustID() %>">删除</a></td>
        </tr>
    <% } %>
    </table>
</body>
</html>
