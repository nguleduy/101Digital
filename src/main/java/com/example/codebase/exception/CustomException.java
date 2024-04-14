package com.example.codebase.exception;

import lombok.Getter;

/**
 * UserNotFoundException.
 */
@Getter
public class CustomException extends RuntimeException {

    private final int errorCode;

    public CustomException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
