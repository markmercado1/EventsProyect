package upeu.mse_attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upeu.mse_attendance.entity.Attendance;
import upeu.mse_attendance.enums.AttendanceStatus;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Buscar asistencia por inscripción (clave principal para tu caso)
    Attendance findByRegistrationId(Long registrationId);

    // Buscar por estado (si quieres todos los presentes / ausentes)
    List<Attendance> findByStatus(AttendanceStatus status);

    // Para asistencia grupal y vista de lista:
    List<Attendance> findByRegistrationIdIn(List<Long> registrationIds);

}
