package com.materials.io.kafkaconsumer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class CreditCardTransaction {

    @Id
    @Column(name="transactionId")
    private String transactionId;
    @Column(name="merchantId")
    private String merchantId;
    @Column(name="cardNumber")
    private long cardNumber;
    @Column(name="amount")
    private Double amount;
    @Column(name="timeStampInMillis")
    private Timestamp timeStampInMillis;
    @Column(name="status")
    private String status;


}
