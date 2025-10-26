package upeu.mse_attendance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.mse_attendance.dto.AttendanceDTO;
import upeu.mse_attendance.service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<AttendanceDTO> registrarAsistencia(@RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO nuevaAsistencia = attendanceService.registrarAsistencia(
                // Convertimos DTO a entidad dentro del service, no aquí
                toEntity(attendanceDTO)
        );
        return ResponseEntity.ok(nuevaAsistencia);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> listarAsistencias() {
        List<AttendanceDTO> asistencias = attendanceService.listarAsistencias();
        return ResponseEntity.ok(asistencias);
    }


    @GetMapping("/{idAttendance}")
    public ResponseEntity<AttendanceDTO> obtenerAsistenciaPorId(@PathVariable Long idAttendance) {
        return attendanceService.obtenerAsistenciaPorId(idAttendance)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{idAttendance}")
    public ResponseEntity<AttendanceDTO> actualizarAsistencia(
            @PathVariable Long idAttendance,
            @RequestBody AttendanceDTO attendanceDTO) {
        try {
            AttendanceDTO asistenciaActualizada = attendanceService.actualizarAsistencia(idAttendance, attendanceDTO);
            return ResponseEntity.ok(asistenciaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idAttendance}")
    public ResponseEntity<Void> eliminarAsistencia(@PathVariable Long idAttendance) {
        try {
            attendanceService.eliminarAsistencia(idAttendance);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private upeu.mse_attendance.entity.Attendance toEntity(AttendanceDTO dto) {
        upeu.mse_attendance.entity.Attendance attendance = new upeu.mse_attendance.entity.Attendance();
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
