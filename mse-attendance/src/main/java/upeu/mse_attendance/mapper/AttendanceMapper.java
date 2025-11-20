package upeu.mse_attendance.mapper;

import upeu.mse_attendance.dto.AttendanceCreateDTO;
import upeu.mse_attendance.dto.AttendanceResponseDTO;
import upeu.mse_attendance.entity.Attendance;

public class AttendanceMapper {

    public static Attendance toEntity(AttendanceCreateDTO dto) {
        return Attendance.builder()
                .idAttendance(dto.getIdAttendance())
                .registrationId(dto.getRegistrationId())
                .timestamp(dto.getTimestamp() != null ? dto.getTimestamp() : null)
                .status(dto.getStatus())
                .checkInMethod(dto.getCheckInMethod())
                .observations(dto.getObservations())
                .build();
    }

    public static AttendanceResponseDTO toResponse(Attendance entity) {
        AttendanceResponseDTO dto = new AttendanceResponseDTO();
        dto.setIdAttendance(entity.getIdAttendance());
        dto.setRegistrationId(entity.getRegistrationId());
        dto.setTimestamp(entity.getTimestamp());
        dto.setStatus(entity.getStatus());
        dto.setCheckInMethod(entity.getCheckInMethod());
        dto.setObservations(entity.getObservations());
        return dto;
    }
}
