package upeu.mse_notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private Long idNotification;

    private String title;

    private String message;

    private String type; // ATTENDANCE_ALERT, EVENT_REMINDER, GENERAL

    private String status; // SENT, PENDING, FAILED

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;


    private AuthUserDTO authUserDTO;
    private EventDTO eventDTO;
    private ParticipantDTO participantDTO;
}
