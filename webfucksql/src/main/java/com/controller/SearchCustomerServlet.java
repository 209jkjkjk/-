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

@WebServlet("/SearchCustomerServlet.do")
public class SearchCustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 模糊搜索内容
        CustomerDAO dao = new CustomerDAO();
        Vector<Customer> customers = dao.fuzzySearchCustomer(request.getParameter("fuzzyString"));
        System.out.println(request.getParameter("fuzzyString"));

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/displayCustomer.jsp").forward(request, response);
    }
}
