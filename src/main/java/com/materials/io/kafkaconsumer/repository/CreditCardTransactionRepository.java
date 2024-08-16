package com.materials.io.kafkaconsumer.repository;

import com.materials.io.kafkaproducerconsumer.model.CreditCardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransaction, String> {
}
