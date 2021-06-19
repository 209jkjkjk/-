package com.controller;

import com.dao.CustomerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteCustomerServlet.do")
public class DeleteCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String custID = request.getParameter("custID");

        // 生成DAO对象 删除数据
        CustomerDAO dao = new CustomerDAO();
        boolean success = dao.deleteCustomerByCustID(custID);

        // 打印结果
        response.setContentType("text/html;charset=utf-8");
        if (success){
            response.getWriter().write("你成功了");
        } else {
            response.getWriter().write("你失败了");
        }
    }
}
