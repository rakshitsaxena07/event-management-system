package com.technogise.event_management_system.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, int status, LocalDateTime timestamp){
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

}
