package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Transaction {
    @Id private String Id;
    @DBRef Account from;
    @DBRef Account to;
    double amount;
    String createDate;

    public Transaction(){}

    public Transaction(Account fromAccount, Account toAccount, double amount){
        this.from = fromAccount;
        this.to = toAccount;
        this.amount = amount;
        this.createDate = LocalDateTime.now().toString();
    }

    // GETTERS AND SETTERS
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public Account getFromAccount() {
        return from;
    }
    public void setFromAccount(Account fromAccount) {
        this.to = fromAccount;
    }
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public Account getToAccount() {
        return to;
    }
    public void setToAccount(Account toAccount) {
        this.to = toAccount;
    }
   
}
