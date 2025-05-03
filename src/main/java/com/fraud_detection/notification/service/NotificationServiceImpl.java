package com.fraud_detection.notification.service;

import com.fraud_detection.notification.entity.TransactionEntity;
import com.fraud_detection.notification.entity.UserDetailsEntity;
import com.fraud_detection.notification.repository.NotificationRepository;
import com.fraud_detection.notification.repository.TransactionRepository;
import com.fraud_detection.notification.response.Fraud1KafkaMessage;
import com.fraud_detection.notification.response.FraudKafkaMessage;
import com.fraud_detection.notification.response.TransactionKafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    private JavaMailSender mailSender;


    public void sendNotification(Long userId, String message) {

        // Logic to send notification
//        "fetch email_id from db, using user_id and account_number";
        log.info("Sending notification: {}", userId);
        // Here you would implement the actual notification sending logic
        // For example, sending an email or a push notification
        UserDetailsEntity userDetails = notificationRepository.findByUserId(userId);
        String emailId = userDetails.getEmail();
        // Send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Notification");
        mailMessage.setText(message);

        try {
            mailSender.send(mailMessage);
            log.info("Email sent successfully to {}", emailId);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", emailId, e.getMessage());
        }
    }

    @Override
    public void sendTransactionNotification(TransactionKafkaMessage transactionKafkaMessage) {

        UserDetailsEntity userDetailsEntity = notificationRepository.findByUserId(transactionKafkaMessage.getUser_id());
        String emailId = userDetailsEntity.getEmail();
        // Send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Notification");
        mailMessage.setText(transactionKafkaMessage.getStatus());

        try {
            mailSender.send(mailMessage);
            log.info("Email sent successfully to {}", emailId);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", emailId, e.getMessage());
        }
    }

    @Override
    public void processFraudNotification(FraudKafkaMessage fraudKafkaMessage) {

        TransactionEntity transactionEntity = transactionRepository.findByTransactionId(Long.valueOf(fraudKafkaMessage.getTransactionId()));
        UserDetailsEntity userDetailsEntity = notificationRepository.findByUserId(transactionEntity.getUserId());
        String emailId = userDetailsEntity.getEmail();
        // Send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Notification");
        mailMessage.setText(fraudKafkaMessage.getReason());

        try {
            mailSender.send(mailMessage);
            log.info("Email sent successfully to {}", emailId);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", emailId, e.getMessage());
        }
    }

    @Override
    public void processFraud1Notification(Fraud1KafkaMessage fraud1KafkaMessage) {
        UserDetailsEntity userDetailsEntity = notificationRepository.findByUserId(Long.valueOf(fraud1KafkaMessage.getUserId()));
        String emailId = userDetailsEntity.getEmail();
        // Send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Notification");
        mailMessage.setText(fraud1KafkaMessage.getTransactionStatus());

        try {
            mailSender.send(mailMessage);
            log.info("Email sent successfully to {}", emailId);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", emailId, e.getMessage());
        }
    }
}


