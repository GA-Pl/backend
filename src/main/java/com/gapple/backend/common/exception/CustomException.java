package com.gapple.backend.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final CommonErrorCode commonErrorCode;

    public CustomException(CommonErrorCode commonErrorCode) {
        this.commonErrorCode = commonErrorCode;
    }
}
