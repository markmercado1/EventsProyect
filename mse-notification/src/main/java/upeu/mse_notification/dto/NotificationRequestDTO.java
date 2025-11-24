package upeu.mse_notification.dto;


import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class NotificationRequestDTO {

    @NotNull(message = "participantId es obligatorio")
    private Long participantId;

    @NotNull(message = "eventId es obligatorio")
    private Long eventId;

    @NotNull(message = "templateCode es obligatorio")
    private String templateCode; // Código del template que se va a usar
}
