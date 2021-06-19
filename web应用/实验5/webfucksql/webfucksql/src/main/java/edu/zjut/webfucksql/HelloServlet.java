package edu.zjut.webfucksql;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String dburl = request.getParameter("dburl");
        System.out.println(dburl);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =  DriverManager.getConnection(dburl, "root", "");
            // 往数据库里输入数据
            request.setCharacterEncoding("UTF-8");
            String sql = "INSERT INTO test (name) VALUES ( ? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, request.getParameter("name"));
            int rst = pstmt.executeUpdate();
            System.out.println(rst);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(rst);
        } catch (SQLException | ClassNotFoundException throwables) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("GG了");
            throwables.printStackTrace();
        }

    }
}