package upeu.mse_attendance.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {
    private Long idAttendance;
    private AuthUserDTO authUserDTO;
    private EventDTO eventDTO;          // Viene del EventDTO
    private LocalDateTime timestamp;
    private String status;
    private String checkInMethod;
    private String observations;
}
