package com.cognizant.restspringboot.exception.handler;

import com.cognizant.restspringboot.exception.DivideByZeroException;
import com.cognizant.restspringboot.exception.ExceptionResponse;
import com.cognizant.restspringboot.exception.ResourceNotFoundException;
import com.cognizant.restspringboot.exception.UnsupportedMathException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse>  handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnsupportedMathException.class)
    public final ResponseEntity<ExceptionResponse>  handleBadRequestExceptions(UnsupportedMathException ex, WebRequest request){
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DivideByZeroException .class)
    public final ResponseEntity<ExceptionResponse>  handleDivideByZeroExceptions(DivideByZeroException ex, WebRequest request){
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse>  handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
