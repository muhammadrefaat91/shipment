package com.springcard.exception;

public class SystemException extends RuntimeException {
    private String message;
    private ErrorCode code;

    public enum ErrorCode {
        NOT_UNIQUE, NULL_PROPERTY, UNEXPECTED_ERROR, NOT_FOUND, NOT_VALID, FUTURE_DATE
    };

    public SystemException() {
    }

    public SystemException(String message) {
        this.message = message;
    }

    public SystemException(String message, ErrorCode errorCode) {
        this.message = message;
        this.code = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return code;
    }

    public void setErrorCode(ErrorCode code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Error [message=" + message + ", code=" + code + "]";
    }

}

