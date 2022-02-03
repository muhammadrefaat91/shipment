package com.springcard.exception;

public class RuntimeSystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private SystemException systemException;

    public RuntimeSystemException() {
    }

    public RuntimeSystemException(SystemException systemException) {
        this.systemException = systemException;
    }

    public SystemException getError() {
        return systemException;
    }

    @Override
    public String toString() {
        return "RuntimeSystemException{" +
                "systemException=" + systemException +
                '}';
    }
}
