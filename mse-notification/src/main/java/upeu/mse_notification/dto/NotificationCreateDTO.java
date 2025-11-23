package upeu.mse_notification.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationCreateDTO {

    private String templateCode;

    private Long participantId;
    private Long registrationId;
    private Long attendanceId;
    private Long eventId;

    private String channel;

    private String title;
    private String message;
}
