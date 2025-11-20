package upeu.mse_attendance.dto;

import lombok.Data;
import upeu.mse_attendance.enums.AttendanceStatus;
import upeu.mse_attendance.enums.CheckInMethod;

import java.time.LocalDateTime;

@Data
public class AttendanceResponseDTO {
    private Long idAttendance;

    private Long registrationId;

    private LocalDateTime timestamp;

    private AttendanceStatus status;

    private CheckInMethod checkInMethod;

    private String observations;
}
