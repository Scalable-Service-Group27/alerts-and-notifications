package com.fraud_detection.notification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_details")
@AllArgsConstructor
@NoArgsConstructor

@Builder
@Getter


public class UserDetailsEntity {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "account_number")
    private double accountNumber;


}
