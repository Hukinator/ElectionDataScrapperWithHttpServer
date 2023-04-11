package com.hukinator.httpServer.config;

public class HTTPConfigurationException extends RuntimeException {
    public HTTPConfigurationException() {
    }

    public HTTPConfigurationException(String message) {
        super(message);
    }

    public HTTPConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HTTPConfigurationException(Throwable cause) {
        super(cause);
    }
}
