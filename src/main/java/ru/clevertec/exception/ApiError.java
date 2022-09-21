package ru.clevertec.exception;

import lombok.Data;

@Data
public class ApiError {

    String errorCode;
    String description;

    public ApiError(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }
}
