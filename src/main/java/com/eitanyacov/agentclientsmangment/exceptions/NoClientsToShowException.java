package com.eitanyacov.agentclientsmangment.exceptions;

public class NoClientsToShowException extends Exception{

    public NoClientsToShowException() {
    }

    public NoClientsToShowException(String message) {
        super(message);
    }

    public NoClientsToShowException(String message, Throwable cause) {
        super(message, cause);
    }
}
