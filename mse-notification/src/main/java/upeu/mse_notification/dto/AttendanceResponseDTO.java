package upeu.mse_notification.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AttendanceResponseDTO {
    private Long idAttendance;
    private Long registrationId;
    private LocalDateTime timestamp;
    private String status;
    private String checkInMethod;
    private String observations;
}
