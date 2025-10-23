package upeu.mse_attendance.service;

import upeu.mse_attendance.entity.Attendance;
import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    // Listar todas las asistencias
    List<Attendance> listarAsistencias();

    // Obtener una asistencia por su ID
    Optional<Attendance> obtenerAsistenciaPorId(Long id);

    // Registrar una nueva asistencia
    Attendance registrarAsistencia(Attendance attendance);

    // Actualizar una asistencia existente
    Attendance actualizarAsistencia(Long id, Attendance attendance);

    // Eliminar una asistencia por ID
    void eliminarAsistencia(Long id);
}
