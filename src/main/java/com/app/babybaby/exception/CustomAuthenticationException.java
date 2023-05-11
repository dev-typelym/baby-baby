package com.app.babybaby.exception;

import com.app.babybaby.type.ErrorCode;

public class CustomAuthenticationException extends RuntimeException{
    public CustomAuthenticationException() {
        super(ErrorCode.AUTHENTICATION_FAILED.getMessage());
    }

    public CustomAuthenticationException(String message) {
        super(message);
    }
}
