package com.eitanyacov.agentclientsmangment.exceptions;

public class AgentNotFoundException extends Exception{
    public AgentNotFoundException() {
    }

    public AgentNotFoundException(String message) {
        super(message);
    }

    public AgentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
