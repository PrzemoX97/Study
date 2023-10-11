package com.demo.springboot.domain.dto;

import org.springframework.http.HttpStatus;

public enum ErrorMessages {

    ERROR_PATH("Niepoprawana sciezka dla pliku. Prosze zmienic sciezke dla stalej PATH!", HttpStatus.NOT_FOUND),
    ;

    private String errorMessage;
    private HttpStatus errorCode;

    ErrorMessages(String errorMessage, HttpStatus errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
}
