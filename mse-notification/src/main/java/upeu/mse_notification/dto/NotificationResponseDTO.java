package upeu.mse_notification.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponseDTO {

    private Long notificationId;
    private String templateCode;

    private Long participantId;
    private Long registrationId;
    private Long attendanceId;
    private Long eventId;

    private String channel;
    private String title;
    private String message;

    private String status;
    private String errorMessage;

    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
}
