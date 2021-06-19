package com.model;

public class Customer {
    private String custID;
    private String custName;
    private double custBalance;

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public double getCustBalance() {
        return custBalance;
    }

    public void setCustBalance(double custBalance) {
        this.custBalance = custBalance;
    }
}
