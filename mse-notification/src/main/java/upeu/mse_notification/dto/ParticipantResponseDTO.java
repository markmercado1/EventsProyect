package upeu.mse_notification.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ParticipantResponseDTO {
    private Long participantId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime registrationDate;
}
