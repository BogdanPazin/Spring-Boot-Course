package com.luv2code.demo.rest;

public class StudentErrorResponse {
    private int status;
    private String message;
    private int timeStamp;

    public StudentErrorResponse() {

    }

    public StudentErrorResponse(int status, String message, int timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
}
