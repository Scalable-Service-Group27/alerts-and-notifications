package com.fraud_detection.notification.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fraud1KafkaMessage {


    private String userId;

    private String accountNo;

    private String transactionStatus;
}
