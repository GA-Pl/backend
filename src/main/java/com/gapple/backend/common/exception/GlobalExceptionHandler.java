package com.gapple.backend.common.exception;

import com.gapple.backend.common.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<Object> handleCustomException(CustomException exception) {

        return ResponseEntity.status(exception.getCommonErrorCode().getStatus())
                .body(ApiResponse.error(exception.getCommonErrorCode().getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {

        return handleInternalException(ErrorCode.INVALID_PARAMETER);
    }

    @ExceptionHandler(MissingRequestValueException.class)
    protected ResponseEntity<Object> handleMissingRequestValueException(MissingRequestValueException exception) {

        return handleInternalException(ErrorCode.INVALID_PARAMETER);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleException(Exception exception) {
        exception.printStackTrace();
        return handleInternalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> handleInternalException(CommonErrorCode commonErrorCode) {

        return ResponseEntity.status(commonErrorCode.getStatus())
                .body(ApiResponse.error(commonErrorCode.getMessage()));
    }
}
