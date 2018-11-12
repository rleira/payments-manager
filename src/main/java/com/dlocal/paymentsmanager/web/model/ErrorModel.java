package com.dlocal.paymentsmanager.web.model;

public class ErrorModel {

    public String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorModel setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
