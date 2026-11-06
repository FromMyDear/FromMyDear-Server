package com.frommydear.server.global.error;

import java.util.List;

/**
 * 표준 오류 응답 본문.
 * 예: {"code":"INVALID_REQUEST","message":"요청 값이 올바르지 않습니다.","fieldErrors":[{"field":"title","reason":"must not be blank"}]}
 */
public record ErrorResponse(String code, String message, List<FieldError> fieldErrors) {

    public record FieldError(String field, String reason) {
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.name(), errorCode.defaultMessage(), List.of());
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode.name(), message, List.of());
    }

    public static ErrorResponse of(ErrorCode errorCode, List<FieldError> fieldErrors) {
        return new ErrorResponse(errorCode.name(), errorCode.defaultMessage(), fieldErrors);
    }

    /** Security 필터처럼 MessageConverter를 쓸 수 없는 곳에서 사용하는 JSON 문자열. */
    public static String json(ErrorCode errorCode) {
        return "{\"code\":\"" + errorCode.name() + "\",\"message\":\"" + errorCode.defaultMessage()
                + "\",\"fieldErrors\":[]}";
    }
}
