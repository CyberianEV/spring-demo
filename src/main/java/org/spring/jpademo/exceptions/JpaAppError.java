package org.spring.jpademo.exceptions;

public class JpaAppError {
    private int code;
    private String message;

    public JpaAppError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JpaAppError() {
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
