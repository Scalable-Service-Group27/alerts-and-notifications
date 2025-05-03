package com.fraud_detection.notification.repository;

import com.fraud_detection.notification.entity.TransactionEntity;
import com.fraud_detection.notification.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    TransactionEntity findByTransactionId(Long transactionId);
}
