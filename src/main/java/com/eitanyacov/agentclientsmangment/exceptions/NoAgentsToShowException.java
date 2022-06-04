package com.eitanyacov.agentclientsmangment.exceptions;

public class NoAgentsToShowException extends Exception{
    public NoAgentsToShowException() {
    }

    public NoAgentsToShowException(String message) {
        super(message);
    }

    public NoAgentsToShowException(String message, Throwable cause) {
        super(message, cause);
    }
}
