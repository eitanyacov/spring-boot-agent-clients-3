package com.eitanyacov.agentclientsmangment.exceptions;

public class ClientNotFoundException extends Exception{

    public ClientNotFoundException() {
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
