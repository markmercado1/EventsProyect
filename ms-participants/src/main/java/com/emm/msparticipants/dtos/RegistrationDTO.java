package com.emm.msparticipants.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDTO {
    private Long registrationId;
    private Long eventId;
    private Long participantId;
    private LocalDateTime registrationDate;
    private String status;
    private String qrCode;
    private Boolean requiresPayment;
    private Long paymentOrderId;
}