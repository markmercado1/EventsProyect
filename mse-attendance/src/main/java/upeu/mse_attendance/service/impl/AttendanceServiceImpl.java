package upeu.mse_attendance.service.impl;

import org.springframework.stereotype.Service;
import upeu.mse_attendance.dto.*;
import upeu.mse_attendance.entity.Attendance;
import upeu.mse_attendance.feign.AuthUserFeign;
import upeu.mse_attendance.feign.EventFeign;
import upeu.mse_attendance.repository.AttendanceRepository;
import upeu.mse_attendance.service.AttendanceService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AuthUserFeign authUserFeign;
    private final EventFeign eventoFeign;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 AuthUserFeign authUserFeign,
                                 EventFeign eventoFeign) {
        this.attendanceRepository = attendanceRepository;
        this.authUserFeign = authUserFeign;
        this.eventoFeign = eventoFeign;
    }

    @Override
    public List<AttendanceDTO> listarAsistencias() {
        return attendanceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AttendanceDTO> obtenerAsistenciaPorId(Long idAttendance) {
        return attendanceRepository.findById(idAttendance)
                .map(this::convertToDTO);
    }

    @Override
    public AttendanceDTO registrarAsistencia(Attendance attendance) {
        Attendance saved = attendanceRepository.save(attendance);
        return convertToDTO(saved);
    }

    @Override
    public AttendanceDTO actualizarAsistencia(Long idAttendance, AttendanceDTO attendanceDTO) {
        if (!attendanceRepository.existsById(idAttendance)) {
            throw new RuntimeException("❌ Asistencia no encontrada con ID: " + idAttendance);
        }

        Attendance attendance = convertToEntity(attendanceDTO);
        attendance.setIdAttendance(idAttendance);
        Attendance updated = attendanceRepository.save(attendance);
        return convertToDTO(updated);
    }

    @Override
    public void eliminarAsistencia(Long idAttendance) {
        if (!attendanceRepository.existsById(idAttendance)) {
            throw new RuntimeException("❌ Asistencia no encontrada con ID: " + idAttendance);
        }
        attendanceRepository.deleteById(idAttendance);
    }


    private AttendanceDTO convertToDTO(Attendance attendance) {
        // 🔹 Comunicación vía Eureka con los otros microservicios
        AuthUserDTO user = authUserFeign.buscarPorId(attendance.getAuthUserId());
        EventDTO event = eventoFeign.buscarPorId(attendance.getEventId());

        return AttendanceDTO.builder()
                .idAttendance(attendance.getIdAttendance())
                .authUserDTO(user)
                .eventDTO(event)
                .timestamp(attendance.getTimestamp())
                .status(attendance.getStatus())
                .checkInMethod(attendance.getCheckInMethod())
                .observations(attendance.getObservations())
                .build();
    }

    private Attendance convertToEntity(AttendanceDTO dto) {
        Attendance attendance = new Attendance();
        attendance.setIdAttendance(dto.getIdAttendance());
        attendance.setAuthUserId(dto.getAuthUserDTO().getId());
        attendance.setEventId(dto.getEventDTO().getIdEvento());
        attendance.setTimestamp(dto.getTimestamp());
        attendance.setStatus(dto.getStatus());
        attendance.setCheckInMethod(dto.getCheckInMethod());
        attendance.setObservations(dto.getObservations());
        return attendance;
    }
}
