package edu.zjut.webfucksql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
    ResultSet isVaild(Connection connection, String usertype, String num, String encryptPassword) {
        String tablename;
        String columnname;
        if("教工".equals(usertype)){
            tablename = "dbt_teacher";
            columnname = "工号";
        }
        else if("学生".equals(usertype)){
            tablename = "dbt_student";
            columnname = "学号";
        }
        else
            return null;
        String sql = "SELECT * FROM " + tablename + " WHERE " + columnname + "=? AND 密码=?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, num);
            pstmt.setString(2, encryptPassword);
            ResultSet resultset = pstmt.executeQuery();
            if(resultset.next())
                return resultset;
            else
                return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 手动连接数据库
        String dbURL = "jdbc:mysql://mysql.cangcz.fun:7501/web作业?serverTimezone=Asia/Shanghai";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载驱动
//            Context context = new InitialContext();
//            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
//            connection = dataSource.getConnection();
            connection =  DriverManager.getConnection(dbURL, "root", ""); // 建立连接
//        } catch (ClassNotFoundException | NamingException | SQLException e) {
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // 加密密码
        String rawPassword = request.getParameter("password");
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(rawPassword.getBytes(StandardCharsets.UTF_8));
        byte[] encryptByte = md.digest();
        // 转换成字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : encryptByte) {
            sb.append(String.format("%02x", b));
        }
        String encryptPassword = sb.toString();

        // 登陆判断
        String usertype = request.getParameter("usertype");
        String num = request.getParameter("num");
        ResultSet resultSet = isVaild(connection, usertype, num, encryptPassword);
        // 跳转
        if(resultSet != null){
            try {
                request.setAttribute("姓名", resultSet.getString("姓名"));
                request.setAttribute("身份证号", resultSet.getString("身份证号"));
                request.setAttribute("密码", resultSet.getString("密码"));
                request.setAttribute("学院", resultSet.getString("学院"));
                request.setAttribute("num", num);
                if(usertype.equals("教工")){
                    request.setAttribute("角色", resultSet.getString("角色"));
                    request.getRequestDispatcher("teacher.jsp").forward(request, response);
                    return;
                }
                else if(usertype.equals("学生")) {
                    request.setAttribute("专业", resultSet.getString("专业"));
                    request.setAttribute("班级", resultSet.getString("班级"));
                    request.getRequestDispatcher("student.jsp").forward(request, response);
                    return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            response.sendRedirect("error.jsp");
        }

    }
}
