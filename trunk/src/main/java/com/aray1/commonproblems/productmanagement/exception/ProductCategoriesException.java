package com.aray1.commonproblems.productmanagement.exception;

public class ProductCategoriesException extends RuntimeException {

    public ProductCategoriesException(String message) {
        super(message);
    }

    public ProductCategoriesException(String message, Throwable cause) {
        super(message, cause);
    }
}
