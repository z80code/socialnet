package com.z80.exceptions;

public class AuthException extends Exception {

    public AuthException() {
        super("Authentication error!");
    }

    public AuthException(String message) {
        super(message);
    }
}
