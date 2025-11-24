package upeu.mse_notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationCreateDTO {

    @NotBlank
    private String templateCode;

    private Long participantId;
    private Long registrationId;
    private Long attendanceId;
    private Long eventId;

    private String channel;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    @Email
    @NotBlank
    private String emailTo;
}

