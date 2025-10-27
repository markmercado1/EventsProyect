package com.emm.msparticipants.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRegistrationDTO {
    private Long eventId;
    private Long participantId;
    private Boolean requiresPayment;
}