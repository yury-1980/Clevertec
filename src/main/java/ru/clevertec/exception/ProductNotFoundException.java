package ru.clevertec.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(int productId) {
        super(HttpStatus.NOT_FOUND, new ApiError("product.not.found", "Product" +
                " not found id = " + productId));
    }

    public ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, new ApiError("product.not.found", "Product" +
                " not found."));
    }
}
