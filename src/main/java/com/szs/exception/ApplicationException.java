package com.szs.exception;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException {

    protected ApplicationException(HttpStatus httpStatus, ErrorCode errorCode, String errorMessage) {
    }
}