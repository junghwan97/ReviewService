package com.example.corporatetesk1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product Not Found"),
    ALREADY_REVIEWED(HttpStatus.CONFLICT, "User already reviewed"),
    ;

    private HttpStatus status;
    private String message;
}