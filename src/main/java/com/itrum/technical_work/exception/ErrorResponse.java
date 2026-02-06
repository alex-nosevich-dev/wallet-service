package com.itrum.technical_work.exception;

import org.springframework.boot.web.error.Error;

public class ErrorResponse {
    private String errorCode;
    private String message;

    public ErrorResponse() {

    }

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }



    public String getMessage() {
        return message;
    }


}
