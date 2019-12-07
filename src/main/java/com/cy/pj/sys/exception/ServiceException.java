package com.cy.pj.sys.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -4168762844270529023L;

    public ServiceException(String message){
        super(message);
    }
}
