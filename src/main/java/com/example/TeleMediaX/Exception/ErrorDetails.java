package com.example.TeleMediaX.Exception;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ErrorDetails {
    private LocalDateTime timeStamp;
    private String message;
    private String details;

    public ErrorDetails(){

    }
    public ErrorDetails(LocalDateTime now, String description, String message) {
    }

    public ErrorDetails(LocalDate timeStamp, String message, String details) {
        super();
        this.timeStamp = LocalDateTime.from(timeStamp);
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timeStamp=" + timeStamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
