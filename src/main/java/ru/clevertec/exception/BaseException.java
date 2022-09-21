package ru.clevertec.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends Exception{

    HttpStatus httpStatus;
    ApiError apiError;

    public BaseException(HttpStatus httpStatus, ApiError apiError) {
        super(apiError.description);
        this.httpStatus = httpStatus;
        this.apiError = apiError;
    }
}
