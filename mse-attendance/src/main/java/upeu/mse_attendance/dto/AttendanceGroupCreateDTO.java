package upeu.mse_attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import upeu.mse_attendance.enums.AttendanceStatus;
import upeu.mse_attendance.enums.CheckInMethod;

import java.util.List;

@Data
public class AttendanceGroupCreateDTO {

    private List<AttendanceCreateDTO> attendances;

}

