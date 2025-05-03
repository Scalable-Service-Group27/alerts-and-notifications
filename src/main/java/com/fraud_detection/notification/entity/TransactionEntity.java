package com.fraud_detection.notification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TransactionEntity {

    @Column(name= "id")
    private Long id;

    @Column(name= "type")
    private String type;

    @Column(name= "userId")
    private Long userId;

    @Column(name= "accNoFrom")
    private String accNoFrom;

    @Column(name= "accNoTo")
    private String accNoTo;

    @Column(name= "status")
    private String status;

    @Column(name= "timestamp")
    private LocalDateTime timestamp;
}
