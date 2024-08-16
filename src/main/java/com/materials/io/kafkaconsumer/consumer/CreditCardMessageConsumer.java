package com.materials.io.kafkaconsumer.consumer;

import com.materials.io.kafkaconsumer.repository.CreditCardTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import com.materials.io.kafkaconsumer.exception.CreditCardErrorCodes;
import com.materials.io.kafkaconsumer.exception.CreditCardLimitException;
import com.materials.io.kafkaproducerconsumer.model.CreditCardTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class CreditCardMessageConsumer {

    //TODO: this should be added into properties file
    private String CREDIT_CARD_ERROR_DLT = "creditcard-payment-topic-event-DLT";

    @Autowired
    private CreditCardTransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, CreditCardTransaction> kafkaTemplate;

    @KafkaListener(topics = "creditcard-payment-topic-event", containerFactory = "userKafkaListenerFactory")
    public void listen(@Payload CreditCardTransaction creditCardTransaction) {
        if (Objects.nonNull(creditCardTransaction) && Objects.nonNull(creditCardTransaction.getAmount()) &&
                creditCardTransaction.getAmount() > 4000) {
            try {
                log.error("Credit card limit exceeded {} and error code {} ", creditCardTransaction.getAmount(), CreditCardErrorCodes.CREDITCARD_LIMIT_ERROR);
                throw new CreditCardLimitException(9001, "Credit card Limit exceeded");
            } catch (CreditCardLimitException e) {
                //publish into DLQ topic
                creditCardTransaction.setStatus("UNAUTHORIZED");
                transactionRepository.save(creditCardTransaction);
                kafkaTemplate.send(CREDIT_CARD_ERROR_DLT, creditCardTransaction);

            }
        }
        creditCardTransaction.setStatus("SUCCESS");
        transactionRepository.save(creditCardTransaction);
        log.info("Received and processed Message: {} ", creditCardTransaction);
    }

    @KafkaListener(topics = "creditcard-payment-topic-event-DLT", containerFactory = "userKafkaListenerFactory")
    public void listenDLT(@Payload CreditCardTransaction creditCardTransaction) {
        log.info("Received Message in DLT: {} ", creditCardTransaction);
        log.info("Send notification to stake holders for handling DLT transactions better");
    }

}
