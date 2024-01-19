package org.example;

public class DuplicateIsbnException extends RuntimeException {

    public DuplicateIsbnException(String message) {
        super(message);
    }
}