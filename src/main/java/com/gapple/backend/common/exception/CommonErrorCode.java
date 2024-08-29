package com.gapple.backend.common.exception;

import org.springframework.http.HttpStatus;

public interface CommonErrorCode {

    HttpStatus getStatus();

    String getMessage();
}
