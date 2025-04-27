package com.fraud_detection.notification.service;

import com.fraud_detection.notification.entity.UserDetailsEntity;
import com.fraud_detection.notification.repository.NotificationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2

public class NotificationService {

    private final NotificationRepository notificationRepository;
    @Autowired
    private JavaMailSender mailSender;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(Long userId, String message) {

        // Logic to send notification
//        "fetch email_id from db, using user_id and account_number";
        log.info("Sending notification: {}", userId);
        // Here you would implement the actual notification sending logic
        // For example, sending an email or a push notification
        UserDetailsEntity userDetails = notificationRepository.findByUserId(userId);
        String emailId = userDetails.getEmailId();
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
}


