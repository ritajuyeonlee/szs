package com.szs.exception;

import com.szs.domain.enumerable.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException {

    protected ApplicationException(HttpStatus httpStatus, ErrorCode errorCode, String errorMessage) {
    }
}