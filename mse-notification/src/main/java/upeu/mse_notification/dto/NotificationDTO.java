package upeu.mse_notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private Long idNotification;
    private String type;
    private String recipient;
    private String subject;
    private String message;
    private Timestamp sendDate;
    private String status;
    private Integer attempts;
    private String provider;
    private String providerResponse;
    private String extraPayload;
    private Timestamp createdAt;
}
