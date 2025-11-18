package upeu.mse_attendance.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegistrationResponseDTO {

    private Long registrationId;
    private Long eventId;
    private Long participantId;
    private LocalDateTime registrationDate;
    private String status; // en lugar de RegistrationStatus
    private String qrCode;
    private Boolean requiresPayment;
    private Long paymentOrderId;
}
