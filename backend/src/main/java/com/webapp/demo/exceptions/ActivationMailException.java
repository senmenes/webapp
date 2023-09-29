package com.webapp.demo.exceptions;

public class ActivationMailException extends RuntimeException{
    public ActivationMailException(){
        super("Activation Mail couldn't send. Try Again");
    }
}
