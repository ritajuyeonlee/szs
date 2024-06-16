package com.szs.exception;

import com.szs.domain.enumerable.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidInformationException extends ApplicationException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;
    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_INFORMATION;
    private static final String ERROR_MESSAGE = "옳지 않은 입력값입니다";

    public InvalidInformationException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
