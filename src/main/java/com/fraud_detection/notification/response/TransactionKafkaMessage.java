package com.fraud_detection.notification.response;

import lombok.Data;

@Data
public class TransactionKafkaMessage {

    private Long user_id;

    private String status;
}
