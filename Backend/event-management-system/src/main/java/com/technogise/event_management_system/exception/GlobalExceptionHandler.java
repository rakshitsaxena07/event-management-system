package com.technogise.event_management_system.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //it allows to handle exception gobally=listen for exceptions thrown in ANY controller and handle them here
public class GlobalExceptionHandler {

   /*  @ExceptionHandler(UserNotFoundException.class) //this method will handle UserNotFoundException, and when this exception is thrown, it will create an ErrorResponse object with the exception message, HTTP status code, and timestamp, and return it as a ResponseEntity with a NOT_FOUND status
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse( //it creates an ErrorResponse object with the exception message, HTTP status code, and timestamp which will call the constructor of ErrorResponse class
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); //it returns a ResponseEntity containing the ErrorResponse object and sets the HTTP status to NOT_FOUND, which indicates that the requested resource (user) was not found. This allows the client to receive a structured error response when a UserNotFoundException is thrown in any controller.
    }*/
    
}
//response entity example
/*
HTTP/1.1 404 NOT FOUND                                  //HttpStatus.NOT_FOUND
Content-Type: application/json

{                                                       //error
    "message": "User not found",
    "statusCode": 404,
    "timestamp": "2024-03-07T10:30:45.123"
}
 */