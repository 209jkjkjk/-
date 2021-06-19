<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <style>
        .inputClass{
            display: block;
        }
    </style>
    <script type="text/javascript">
        function jiaogong(){
            inputmode.setAttribute('onclick', 'xuesheng()');
            inputmode.textContent = '教工';
            usertype.value = '教工'
            numlabel.innerHTML = '工号：<input type="text" id="num" name="num">';
        }
        function xuesheng() {
            inputmode.setAttribute('onclick', 'jiaogong()');
            inputmode.textContent = '学生';
            usertype.value = '学生'
            numlabel.innerHTML = '学号：<input type="text" id="num" name="num">';
        }
    </script>
</head>
<body>
    <button id="inputmode" onclick="jiaogong()">教工</button>
    <label>点击切换角色</label>
    <form id="form1" method="post" action="/LoginServlet.do">
        <label for="usertype"><input id="usertype" type="hidden" name="usertype" value=""></label>
        <label class="inputClass" id="numlabel" for="num"><input type="text" id="num" name="num"></label>
        <label class="inputClass" for="password">密码：<input type="password" name="password" id="password"></label>
        <input type="submit" value="登陆">
    </form>
    <script type="text/javascript">
        jiaogong();
    </script>
</body>
</html>
