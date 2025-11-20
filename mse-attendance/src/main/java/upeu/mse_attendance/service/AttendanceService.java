package upeu.mse_attendance.service;

import upeu.mse_attendance.dto.*;

import java.util.List;

public interface AttendanceService {

    AttendanceResponseDTO register(AttendanceCreateDTO dto);

    List<AttendanceResponseDTO> registerGroup(AttendanceGroupCreateDTO dto);

    AttendanceResponseDTO update(Long idAttendance, AttendanceUpdateDTO dto);

    AttendanceResponseDTO getById(Long idAttendance);

    List<AttendanceResponseDTO> getAll();
}
