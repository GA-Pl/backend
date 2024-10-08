package com.gapple.backend.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements CommonErrorCode {

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 만료"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터 값 확인 필요"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 리소스 없음"),
    CONFLICT_RESOURCE(HttpStatus.CONFLICT, "중복된 리소스"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
