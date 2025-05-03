package com.fraud_detection.notification.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudKafkaMessage {


    private String transactionId;

    private String reason;

    private String accountNo;


}
