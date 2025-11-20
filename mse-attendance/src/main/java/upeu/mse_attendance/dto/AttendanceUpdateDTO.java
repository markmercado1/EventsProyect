package upeu.mse_attendance.dto;

import lombok.Data;
import upeu.mse_attendance.enums.AttendanceStatus;
import upeu.mse_attendance.enums.CheckInMethod;

@Data
public class AttendanceUpdateDTO {
    private AttendanceStatus status;

    private CheckInMethod checkInMethod;

    private String observations;
}
