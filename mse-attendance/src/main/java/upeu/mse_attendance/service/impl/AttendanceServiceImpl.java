package upeu.mse_attendance.service.impl;

import org.springframework.stereotype.Service;
import upeu.mse_attendance.entity.Attendance;
import upeu.mse_attendance.repository.AttendanceRepository;
import upeu.mse_attendance.service.AttendanceService;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance registrarAsistencia(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> listarAsistencias() {
        return attendanceRepository.findAll();
    }

    @Override
    public Optional<Attendance> obtenerAsistenciaPorId(Long idAsistencia) {
        return attendanceRepository.findById(idAsistencia);
    }

    @Override
    public Attendance actualizarAsistencia(Long idAsistencia, Attendance attendance) {
        if (!attendanceRepository.existsById(idAsistencia)) {
            throw new RuntimeException("Asistencia no encontrada con ID: " + idAsistencia);
        }
        attendance.setId(idAsistencia);
        return attendanceRepository.save(attendance);
    }

    @Override
    public void eliminarAsistencia(Long idAsistencia) {
        if (!attendanceRepository.existsById(idAsistencia)) {
            throw new RuntimeException("Asistencia no encontrada con ID: " + idAsistencia);
        }
        attendanceRepository.deleteById(idAsistencia);
    }
}
