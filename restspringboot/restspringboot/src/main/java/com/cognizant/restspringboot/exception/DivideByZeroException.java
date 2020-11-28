package com.cognizant.restspringboot.exception;

public class DivideByZeroException extends RuntimeException{
    public DivideByZeroException(String ex){
        super(ex);
    }
}
