package com.example.demo.response;


import java.util.List;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;


public class AccountSummary extends Account{
	List<Transaction> transactionsOuts;
    List<Transaction> transactionsIns;
    double balance;

    public AccountSummary(){}
    public AccountSummary(Account account, List<Transaction> transactionsOuts,  List<Transaction> transactionsIns, double balance){
    	super(account.getId(), account.getOwner());
    	this.transactionsOuts = transactionsOuts;
        this.transactionsIns = transactionsIns;
        this.balance = balance;
    }

    public void setBalance(double balance) {
		this.balance = balance;
	}
    public double getBalance() {
		return balance;
	}
    public void setTransactionsIns(List<Transaction> transactionsIns) {
		this.transactionsIns = transactionsIns;
	}
    public List<Transaction> getTransactionsIns() {
		return transactionsIns;
	}
    public void setTransactionsOuts(List<Transaction> transactionsOuts) {
		this.transactionsOuts = transactionsOuts;
	}
    public List<Transaction> getTransactionsOuts() {
		return transactionsOuts;
	}
    
}
