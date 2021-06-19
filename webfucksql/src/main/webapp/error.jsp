<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误</title>
    <script type="text/javascript">
        function setTimeout_href(){
            self.location.href="login.jsp";
        }
    </script>
</head>
<body>
    <p style="font-size: 2em">您的用户名或密码错误</p>
    <script type="text/javascript">
        window.setTimeout("setTimeout_href()",3000);
    </script>
</body>
</html>
