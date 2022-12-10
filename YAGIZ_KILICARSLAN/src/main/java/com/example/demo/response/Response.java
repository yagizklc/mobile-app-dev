package com.example.demo.response;


public class Response <T> {
    private String message;
    private T data;

    public Response(){}
    public Response(String message, T data){
        this.message = message;
        this.data = data;
    }

    // SETTERS AND GETTERS
    public void setData(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
