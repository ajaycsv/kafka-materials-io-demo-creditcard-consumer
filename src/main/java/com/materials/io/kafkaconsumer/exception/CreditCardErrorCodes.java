package com.materials.io.kafkaconsumer.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CreditCardErrorCodes {

    CREDITCARD_LIMIT_ERROR(90001, "Credit card limit exceeded"),
    CREDIT_CARD_AUTHORIZATION_ERROR(90002, "Card authroixation failed");

    private final int errorCode;
    private String errorMessage;

    private CreditCardErrorCodes(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
