package com.hukinator.http;

public abstract class HttpMessage {
    private String method;
    private String requestTarget;
    private String httpVersion;

    HttpMessage() {
    }
}
