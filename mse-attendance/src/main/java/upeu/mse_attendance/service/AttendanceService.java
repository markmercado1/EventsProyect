package upeu.mse_attendance.service;

import upeu.mse_attendance.dto.AttendanceDTO;
import upeu.mse_attendance.dto.AttendanceGroupDTO;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    List<AttendanceDTO> listarAsistencias();

    Optional<AttendanceDTO> obtenerAsistenciaPorId(Long idAttendance);

    AttendanceDTO registrarAsistencia(AttendanceDTO attendanceDTO);

    AttendanceDTO actualizarAsistencia(Long idAttendance, AttendanceDTO attendanceDTO);

    void eliminarAsistencia(Long idAttendance);

    List<AttendanceDTO> registrarAsistenciaGrupo(AttendanceGroupDTO attendanceGroupDTO);
}
