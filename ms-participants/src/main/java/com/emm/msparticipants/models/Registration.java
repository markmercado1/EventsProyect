package com.emm.msparticipants.models;


import com.emm.msparticipants.enums.RegistrationStatus;
import jakarta.persistence.*;

import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    @Column(nullable = false)
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RegistrationStatus status = RegistrationStatus.PENDING;

    @Column(length = 255)
    private String qrCode;

    @Column(nullable = false)
    private Boolean requiresPayment = false;

    private Long paymentOrderId;

}
