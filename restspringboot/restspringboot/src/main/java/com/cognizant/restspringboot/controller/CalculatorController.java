package com.cognizant.restspringboot.controller;

import com.cognizant.restspringboot.exception.DivideByZeroException;
import com.cognizant.restspringboot.exception.UnsupportedMathException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private static AtomicLong counter= new AtomicLong();
    @GetMapping (value="/sum/{numberone}/{numbertwo}")
    public Double sum(@PathVariable("numberone")String numberone, @PathVariable("numbertwo") String numbertwo) throws Exception{
        if(!isNumeric(numberone)|| !isNumeric(numbertwo)){
            throw new UnsupportedMathException("please enter a number");
        }
        return Double.valueOf(numberone)+ Double.valueOf(numbertwo);
    }

    @GetMapping (value="/difference/{numberone}/{numbertwo}")
    public Double difference(@PathVariable("numberone")String numberone, @PathVariable("numbertwo") String numbertwo) throws Exception{
        if(!isNumeric(numberone)|| !isNumeric(numbertwo)){
            throw new UnsupportedMathException("please enter a number");
        }
        return Double.valueOf(numberone)-Double.valueOf(numbertwo);
    }

    @GetMapping (value="/multiply/{numberone}/{numbertwo}")
    public Double multiply(@PathVariable("numberone")String numberone, @PathVariable("numbertwo") String numbertwo) throws Exception{
        if(!isNumeric(numberone)|| !isNumeric(numbertwo)){
            throw new UnsupportedMathException("please enter a number");
        }
        return Double.valueOf(numberone)* Double.valueOf(numbertwo);
    }

    @GetMapping (value="/divide/{numberone}/{numbertwo}")
    public Double divide(@PathVariable("numberone")String numberone, @PathVariable("numbertwo") String numbertwo) throws Exception{
        if(!isNumeric(numberone)|| !isNumeric(numbertwo)){
            throw new UnsupportedMathException("please enter a number");
        }
        if(Double.valueOf(numbertwo)==0){
            throw new DivideByZeroException("the second number should not be zero");
        }
        return Double.valueOf(numberone)/Double.valueOf(numbertwo);
    }
    public boolean isNumeric(String value){
        return value.chars().anyMatch(Character::isDigit);
    }

}
