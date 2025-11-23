package upeu.mse_notification.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationUpdateDTO {

    private String status; // SENT, FAILED
    private String errorMessage;
}
