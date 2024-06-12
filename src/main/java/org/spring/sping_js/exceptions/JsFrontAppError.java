package org.spring.sping_js.exceptions;

public class JsFrontAppError {
    private int code;
    private String message;

    public JsFrontAppError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsFrontAppError() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
