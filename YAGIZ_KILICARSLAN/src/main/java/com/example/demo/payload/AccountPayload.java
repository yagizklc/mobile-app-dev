package com.example.demo.payload;


public class AccountPayload {
    private String id;
    private String owner;

    public AccountPayload(){}

    public AccountPayload(String id, String owner){
        this.id = id;
        this.owner = owner;
    }

    // SETTERS AND GETTERS
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getOwner() {
        return owner;
    }
    
}
