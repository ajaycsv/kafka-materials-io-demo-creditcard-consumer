package org.materials.io.kafkaconsumer.consumer;

import org.materials.io.kafkaproducerconsumer.model.User;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMessageConsumer {

    @KafkaListener(topics = "creditcard-payment-topic-event", containerFactory = "userKafkaListenerFactory")
    public void listen(@Payload User message) {
        System.out.println("Received Message: \n" + message);
    }

}
