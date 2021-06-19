package com.controller;

import com.dao.CustomerDAO;
import com.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

@WebServlet("/DisplayCustomerServlet.do")
public class DisplayCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 生成DAO对象 得到数据
        CustomerDAO dao = new CustomerDAO();
        Vector<Customer> customers = dao.searchAllCustomer();

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/displayCustomer.jsp").forward(request, response);
    }
}
