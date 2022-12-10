package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Account;


public interface AccountRepository extends MongoRepository<Account, String>{
    
    public List<Account> findByOwner(String owner);
    public Optional<Account> findById(String id);
}
