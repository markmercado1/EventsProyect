package upeu.mse_attendance.service.impl;

import org.springframework.stereotype.Service;
import upeu.mse_attendance.dto.*;
import upeu.mse_attendance.entity.Attendance;
import upeu.mse_attendance.feign.AuthUserFeign;
import upeu.mse_attendance.feign.EventFeign;
import upeu.mse_attendance.feign.RegistrationFeign;
import upeu.mse_attendance.repository.AttendanceRepository;
import upeu.mse_attendance.service.AttendanceService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AuthUserFeign authUserFeign;
    private final EventFeign eventFeign;
    private final RegistrationFeign registrationFeign;

    public AttendanceServiceImpl(
            AttendanceRepository attendanceRepository,
            AuthUserFeign authUserFeign,
            EventFeign eventFeign,
            RegistrationFeign registrationFeign
    ) {
        this.attendanceRepository = attendanceRepository;
        this.authUserFeign = authUserFeign;
        this.eventFeign = eventFeign;
        this.registrationFeign = registrationFeign;
    }

    // ---------------------------------------------------------
    // LISTAR TODO
    // ---------------------------------------------------------
    @Override
    public List<AttendanceDTO> listarAsistencias() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // BUSCAR POR ID
    // ---------------------------------------------------------
    @Override
    public Optional<AttendanceDTO> obtenerAsistenciaPorId(Long idAttendance) {
        return attendanceRepository.findById(idAttendance)
                .map(this::convertToDTO);
    }

    // ---------------------------------------------------------
    // REGISTRAR SIMPLE
    // ---------------------------------------------------------
    @Override
    public AttendanceDTO registrarAsistencia(AttendanceDTO attendanceDTO) {
        Attendance attendance = convertToEntity(attendanceDTO);
        Attendance saved = attendanceRepository.save(attendance);
        return convertToDTO(saved);
    }

    // ---------------------------------------------------------
    // ACTUALIZAR
    // ---------------------------------------------------------
    @Override
    public AttendanceDTO actualizarAsistencia(Long idAttendance, AttendanceDTO attendanceDTO) {
        if (!attendanceRepository.existsById(idAttendance)) {
            throw new RuntimeException("Asistencia no encontrada con ID: " + idAttendance);
        }
        Attendance attendance = convertToEntity(attendanceDTO);
        attendance.setIdAttendance(idAttendance);
        Attendance updated = attendanceRepository.save(attendance);
        return convertToDTO(updated);
    }

    // ---------------------------------------------------------
    // ELIMINAR
    // ---------------------------------------------------------
    @Override
    public void eliminarAsistencia(Long idAttendance) {
        attendanceRepository.deleteById(idAttendance);
    }

    // ---------------------------------------------------------
    // REGISTRO POR GRUPO
    // ---------------------------------------------------------
    @Override
    public List<AttendanceDTO> registrarAsistenciaGrupo(AttendanceGroupDTO groupDTO) {

        return groupDTO.getRegistrationResponseDTOList()
                .stream()
                .map(registration -> {

                    Attendance attendance = Attendance.builder()
                            .authUserId(groupDTO.getAuthUserDTO().getId())
                            .eventId(groupDTO.getEventDTO().getIdEvento())
                            .registrationId(registration.getRegistrationId())
                            .status(groupDTO.getStatus())
                            .checkInMethod(groupDTO.getCheckInMethod())
                            .observations(groupDTO.getObservations())
                            .build();

                    Attendance saved = attendanceRepository.save(attendance);
                    return convertToDTO(saved);
                })
                .collect(Collectors.toList());
    }


    // ---------------------------------------------------------
    // CONVERTIR ENTIDAD → DTO
    // ---------------------------------------------------------
    private AttendanceDTO convertToDTO(Attendance attendance) {

        AuthUserDTO user = authUserFeign.buscarPorId(attendance.getAuthUserId());
        EventDTO event = eventFeign.buscarPorId(attendance.getEventId());
        RegistrationResponseDTO registration =
                registrationFeign.buscarPorId(attendance.getRegistrationId());

        return AttendanceDTO.builder()
                .idAttendance(attendance.getIdAttendance())
                .timestamp(attendance.getTimestamp())
                .status(attendance.getStatus())
                .checkInMethod(attendance.getCheckInMethod())
                .observations(attendance.getObservations())
                .authUserDTO(user)
                .eventDTO(event)
                .registrationResponseDTO(registration)
                .build();
    }

    // ---------------------------------------------------------
    // CONVERTIR DTO → ENTIDAD
    // ---------------------------------------------------------
    private Attendance convertToEntity(AttendanceDTO dto) {
        return Attendance.builder()
                .idAttendance(dto.getIdAttendance())
                .authUserId(dto.getAuthUserDTO().getId())
                .eventId(dto.getEventDTO().getIdEvento())
                .registrationId(dto.getRegistrationResponseDTO().getRegistrationId())
                .timestamp(dto.getTimestamp())
                .status(dto.getStatus())
                .checkInMethod(dto.getCheckInMethod())
                .observations(dto.getObservations())
                .build();
    }
}
