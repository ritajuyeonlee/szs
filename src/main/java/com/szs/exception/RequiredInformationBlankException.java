package com.szs.exception;

import com.szs.exception.enumerable.ErrorCode;
import org.springframework.http.HttpStatus;

public class RequiredInformationBlankException extends ApplicationException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;
    private static final ErrorCode ERROR_CODE = ErrorCode.BLANK_VALUE;
    private static final String ERROR_MESSAGE = "필수값이 비어있습니다.";

    public RequiredInformationBlankException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
