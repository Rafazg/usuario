package com.zgdev.usuario.infrastructure.exceptions;

public class ResourceNotFoundEexception extends RuntimeException {
    public ResourceNotFoundEexception(String message) {
        super(message);
    }

    public ResourceNotFoundEexception(String message, Throwable throwable){
        super(message, throwable);
    }
}
