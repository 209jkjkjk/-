<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <style>
        .inputClass{
            display: block;
        }
    </style>
    <script type="text/javascript">
        function jiaogong(){
            rolelabel.style.display = '';
            role.type = 'text';
            majorlabel.style.display = 'none';
            major.type = 'hidden';
            userclasslabel.style.display = 'none';
            userclass.type = 'hidden';
            inputmode.setAttribute('onclick', 'xuesheng()');
            inputmode.textContent = '教工模式';
        }
        function xuesheng() {
            rolelabel.style.display = 'none';
            role.type = 'hidden';
            majorlabel.style.display = '';
            major.type = 'text';
            userclasslabel.style.display = '';
            userclass.type = 'text';
            inputmode.setAttribute('onclick', 'jiaogong()');
            inputmode.textContent = '学生模式';
        }
    </script>
</head>
<body>
    <button id="inputmode" onclick="jiaogong()">教工模式</button>
    <label>点击切换模式</label>
    <form id="form1" method="post">
        <label class="inputClass" for="username">姓名：<input id="username" type="text" name="username"></label>
        <label class="inputClass" for="password">密码：<input type="password" name="password" id="password"></label>
        <label class="inputClass" for="IDnumber">身份证号：<input type="text" name="IDnumber" id="IDnumber"></label>
        <label class="inputClass" for="college">学院：<input type="text" name="college" id="college"></label>
        <label class="inputClass" id="rolelabel" for="role">角色：<input type="text" name="role" id="role"></label>
        <label class="inputClass" id="majorlabel" for="major">专业：<input type="text" name="major" id="major"></label>
        <label class="inputClass" id="userclasslabel" for="userclass">班级：<input type="text" name="userclass" id="userclass"></label>
        <input type="submit" value="注册">
    </form>
<%--    默认教工模式--%>
    <script type="text/javascript">
        jiaogong();
    </script>
</body>
</html>
