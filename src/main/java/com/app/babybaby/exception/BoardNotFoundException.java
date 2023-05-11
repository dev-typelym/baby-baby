package com.app.babybaby.exception;

import com.app.babybaby.type.ErrorCode;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUND.getMessage());
    }

    public BoardNotFoundException(String message) {
        super(message);
    }
}
