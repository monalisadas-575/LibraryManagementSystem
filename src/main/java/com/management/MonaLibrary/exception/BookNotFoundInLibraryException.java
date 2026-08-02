package com.management.MonaLibrary.exception;

public class BookNotFoundInLibraryException extends RuntimeException{
    public BookNotFoundInLibraryException(String message) {
        super(message);
    }
}
