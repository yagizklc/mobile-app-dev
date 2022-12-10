package com.example.demo.payload;


public class TransactionPayload {
    String fromAccountId;
    String toAccountId;
    double amount;

    TransactionPayload(){}

    TransactionPayload(String fromAccountId, String toAccountId, double amount){
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    // SETTERS AND GETTERS
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }
    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
    public String getFromAccountId() {
        return fromAccountId;
    }
    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }
    public String getToAccountId() {
        return toAccountId;
    }
}
