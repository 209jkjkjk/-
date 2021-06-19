package com.controller;

import com.dao.CustomerDAO;
import com.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddCustomerServlet.do")
public class AddCustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 生成要插入的对象
        Customer customer = new Customer();
        customer.setCustID(request.getParameter("custID"));
        customer.setCustName(request.getParameter("custName"));
        customer.setCustBalance(Double.parseDouble(request.getParameter("custBalance")));

        // 生成DAO对象 保存数据
        CustomerDAO dao = new CustomerDAO();
        boolean success = dao.addCustomer(customer);

        // 打印结果
        response.setContentType("text/html;charset=utf-8");
        if (success){
            response.getWriter().write("你成功了");
        } else {
            response.getWriter().write("你失败了");
        }

    }
}
