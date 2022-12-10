package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.payload.AccountPayload;
import com.example.demo.payload.TransactionPayload;
import com.example.demo.repo.AccountRepository;
import com.example.demo.repo.TransactionRepository;
import com.example.demo.response.AccountSummary;
import com.example.demo.response.Response;


@RestController
@RequestMapping("/")
public class BankController {
    
    @Autowired private AccountRepository accountRepository;
    @Autowired private TransactionRepository transactionRepository;
    
    // private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    @PostConstruct
    public void init(){
        if (accountRepository.count() == 0){
            Account account1 = new Account("1111", "account1");
            Account account2 = new Account("2222", "account2");

            accountRepository.save(account1);
            accountRepository.save(account2);

            double amount1 = 1500;
            double amount2 = 2500;

            Transaction transaction1 = new Transaction(account1, account2, amount1);
            Transaction transaction2 = new Transaction(account2, account1, amount2);
            
            transactionRepository.save(transaction1);
            transactionRepository.save(transaction2);
        
        }
    }




    @PostMapping("/account/save")
    @ResponseBody
    public Response<Account> createNewAccount(@RequestBody AccountPayload payload){
        
        try{
            String id = payload.getId();
            String owner = payload.getOwner();
            
            if ( (owner == null) || (id == null) ) {
                Response<Account> response = new Response<Account>("ERROR:missing fields", null);
                return response;
            }
            
            
            Account accountToSave = new Account(id, owner);
            Account accountSaved = accountRepository.save(accountToSave);
            Response<Account> response = new Response<Account>("SUCCESS", accountSaved);
            return response;
        }
        catch (Exception e){
            Response<Account> response = new Response<Account>("ERROR:missing fields", null);
            return response;
        } 
    }

    @PostMapping("transaction/save")
    @ResponseBody
    public Response<Transaction> createTransaction(@RequestBody TransactionPayload payload){
        
        try{
            String fromAccountId = payload.getFromAccountId();
            String toAccountId = payload.getToAccountId();
            double amount = payload.getAmount();

            Optional<Account> toAccount = accountRepository.findById(toAccountId);
            Optional<Account> fromAccount = accountRepository.findById(fromAccountId);

            if ((!toAccount.isPresent()) || (!fromAccount.isPresent())){
                return new Response<Transaction>("ERROR: account id", null); 
            }

            Transaction tx = new Transaction(fromAccount.get(), toAccount.get(), amount);
            Transaction txSaved = transactionRepository.save(tx);
            
            Response<Transaction> response = new Response<Transaction>("SUCCESS", txSaved);
            return response;
        }
        catch (Exception e){
            return new Response<Transaction>("ERROR:missing fields", null);
        }
     

        
    }
    
    @GetMapping("/account/{accountId}")
    @ResponseBody
    public Response<AccountSummary> accountInfo(@PathVariable String accountId){
    
        // acc = retrieve account
        Optional<Account> accountInfo = accountRepository.findById(accountId);
        if (!accountInfo.isPresent()){ // non existent id
            Response<AccountSummary> response = new Response<AccountSummary>("ERROR:account doesnt exist!", null);
            return response;
        }
        
        double balance = 0;

        // outtx = query outgoing tx
        List<Transaction> outTx = transactionRepository.findByFromId(accountId);
        for (Transaction tx : outTx){
            balance -= tx.getAmount();
        }
        
        // inctx = query incoming tx
        List<Transaction> inTx = transactionRepository.findByToId(accountId);
        for (Transaction tx : inTx){
            balance += tx.getAmount();
        }
        
        //logger.info(outTx.toString());
        // logger.info(inTx.toString());
        
        
        AccountSummary summary = new AccountSummary(accountInfo.get(), outTx, inTx, balance);
        
        return new Response<AccountSummary>("SUCCESS", summary); 

    }

    @GetMapping("/transaction/to/{accountId}")
    public Response<List<Transaction>> IncomingTx(@PathVariable String accountId){
        Optional<Account> accountInfo = accountRepository.findById(accountId);
        
        if (!accountInfo.isPresent()){
            Response<List<Transaction>> response = new Response<List<Transaction>>("ERROR:account doesn’t exist", null);
            return response;
        }

        List<Transaction> inTx = transactionRepository.findByToId(accountId);
        return new Response<List<Transaction>>("SUCCESS", inTx);

    }


    @GetMapping("/transaction/from/{accountId}")
    public Response<List<Transaction>> OutgoingTx(@PathVariable String accountId){
        Optional<Account> accountInfo = accountRepository.findById(accountId);
        
        if (!accountInfo.isPresent()){
            return new Response<List<Transaction>>("ERROR:account doesn’t exist", null);
        }

        List<Transaction> outTx = transactionRepository.findByFromId(accountId);
        return new Response<List<Transaction>>("SUCCESS", outTx);

    }   
}
