package com.materials.io.kafkaconsumer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardLimitException extends Exception {
    private int errorCode;
    private String errorMessage;
}
