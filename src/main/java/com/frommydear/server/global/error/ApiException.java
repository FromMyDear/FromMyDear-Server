package com.frommydear.server.global.error;

/** 도메인 코드에서 의도적으로 던지는 예외. GlobalExceptionHandler가 표준 오류 응답으로 바꾼다. */
public class ApiException extends RuntimeException {

    private final ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        this(errorCode, errorCode.defaultMessage());
    }

    public ApiException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode errorCode() {
        return errorCode;
    }
}
