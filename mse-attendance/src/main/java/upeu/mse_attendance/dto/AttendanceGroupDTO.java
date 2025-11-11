package upeu.mse_attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceGroupDTO {
    private AuthUserDTO authUserDTO;
    private EventDTO eventDTO;
    private List<ParticipantDTO> participantDTOs;
    private String status;
    private String checkInMethod;
    private String observations;
}
