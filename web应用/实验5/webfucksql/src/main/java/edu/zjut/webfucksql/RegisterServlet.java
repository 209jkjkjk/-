package edu.zjut.webfucksql;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

@WebServlet("/RegisterServlet.do")
public class RegisterServlet extends HttpServlet {
    boolean isVaild(Connection connection, String usertype, String IDnumber, String num) {
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
            return false;
        String sql = "SELECT * FROM " + tablename + " WHERE " + columnname + "=? OR 身份证号=?;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, num);
            pstmt.setString(2, IDnumber);
            ResultSet resultset = pstmt.executeQuery();
            return !resultset.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        // 手动连接数据库
        String dbURL = "jdbc:mysql://mysql.cangcz.fun:7501/web作业?serverTimezone=Asia/Shanghai";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载驱动
            connection =  DriverManager.getConnection(dbURL, "root", ""); // 建立连接
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // 检测合法性
        String usertype = request.getParameter("usertype");
        String num = request.getParameter("num");
        String IDnumber = request.getParameter("IDnumber");
        if(! isVaild(connection, usertype, num, IDnumber)){
            response.sendRedirect("/failed.jsp");
            return;
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
        for (byte b : encryptByte){
            sb.append(String.format("%02x", b));
        }
        String encryptPassword = sb.toString();

        // 写入数据库
        if(usertype.equals("教工")){
            String sql = "INSERT dbt_teacher (工号, 姓名, 身份证号, 密码, 学院, 角色) VALUES (?, ?, ? ,? ,? ,?);";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, request.getParameter("num"));
                pstmt.setString(2, request.getParameter("username"));
                pstmt.setString(3, request.getParameter("IDnumber"));
                pstmt.setString(4, encryptPassword);
                pstmt.setString(5, request.getParameter("college"));
                pstmt.setString(6, request.getParameter("role"));
                int result = pstmt.executeUpdate();
                if(result == 0)
                    System.out.println("插入失败");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if(usertype.equals("学生")){
            String sql = "INSERT dbt_student (学号, 姓名, 身份证号, 密码, 学院, 专业, 班级) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, request.getParameter("num"));
                pstmt.setString(2, request.getParameter("username"));
                pstmt.setString(3, request.getParameter("IDnumber"));
                pstmt.setString(4, encryptPassword);
                pstmt.setString(5, request.getParameter("college"));
                pstmt.setString(6, request.getParameter("major"));
                pstmt.setString(7, request.getParameter("userclass"));
                int result = pstmt.executeUpdate();
                if(result == 0)
                    System.out.println("插入失败");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
