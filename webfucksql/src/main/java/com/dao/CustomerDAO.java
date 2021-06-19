package com.dao;

import com.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CustomerDAO extends BaseDAO{
    public boolean addCustomer(Customer customer){
        String sql = "INSERT dbt_customer VALUES (?, ?, ?);";
        try(
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            pstmt.setString(1, customer.getCustID());
            pstmt.setString(2, customer.getCustName());
            pstmt.setDouble(3, customer.getCustBalance());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(Customer customer){
        String sql = "UPDATE dbt_customer SET custName=?, custBalance=? WHERE custID=?;";
        try(
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            pstmt.setString(1, customer.getCustName());
            pstmt.setDouble(2, customer.getCustBalance());
            pstmt.setString(3, customer.getCustID());
            return pstmt.executeUpdate() > 0;
//            return pstmt.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomerByCustID(String custID){
        String sql = "Delete FROM dbt_customer WHERE custID=?;";
        try(
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            pstmt.setString(1, custID);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public Vector<Customer> searchCustomerByCustID(String custID){
        String sql = "SELECT * FROM dbt_customer WHERE custID=?;";
        Vector<Customer> result = new Vector<Customer>();
        try(
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            pstmt.setString(1, custID);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setCustID(resultSet.getString("custID"));
                customer.setCustName(resultSet.getString("custName"));
                customer.setCustBalance(resultSet.getDouble("custBalance"));
                result.addElement(customer);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public Vector<Customer> searchAllCustomer(){
        String sql = "SELECT * FROM dbt_customer;";
        Vector<Customer> result = new Vector<Customer>();
        try(
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setCustID(resultSet.getString("custID"));
                customer.setCustName(resultSet.getString("custName"));
                customer.setCustBalance(resultSet.getDouble("custBalance"));
                result.addElement(customer);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public Vector<Customer> fuzzySearchCustomer(String fuzzyString){
        String sql = "SELECT * FROM dbt_customer WHERE custName LIKE ?;";
        Vector<Customer> result = new Vector<Customer>();
        try(
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            pstmt.setString(1, "%" + fuzzyString + "%");
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setCustID(resultSet.getString("custID"));
                customer.setCustName(resultSet.getString("custName"));
                customer.setCustBalance(resultSet.getDouble("custBalance"));
                result.addElement(customer);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
