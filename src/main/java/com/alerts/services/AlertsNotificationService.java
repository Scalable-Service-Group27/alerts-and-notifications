package com.example.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AlertsNotificationService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "fraud-alerts", groupId = "alerts-service")
    public void consumeFraudAlert(String message) {
        System.out.println("Received Fraud Alert: " + message);
        emailService.sendEmail("recipient@example.com", "Fraud Alert", message);
    }
}