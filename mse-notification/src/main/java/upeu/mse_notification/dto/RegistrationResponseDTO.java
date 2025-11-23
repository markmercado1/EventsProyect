package upeu.mse_notification.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegistrationResponseDTO {

    private Long registrationId;
    private Long eventId;
    private Long participantId;
    private LocalDateTime registrationDate;
    private String status;
    private String qrCode;
    private Boolean requiresPayment;
    private Long paymentOrderId;

    private EventResponseDTO event;
    private ParticipantResponseDTO participant;
}
