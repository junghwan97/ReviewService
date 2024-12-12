package com.example.corporatetesk1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public ReviewApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}