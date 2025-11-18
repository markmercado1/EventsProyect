package upeu.mse_attendance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.mse_attendance.dto.AttendanceDTO;
import upeu.mse_attendance.dto.AttendanceGroupDTO;
import upeu.mse_attendance.service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // ------------------------------------------------------------
    // REGISTRAR ASISTENCIA INDIVIDUAL
    // ------------------------------------------------------------
    @PostMapping
    public ResponseEntity<AttendanceDTO> registrarAsistencia(@RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO savedAttendance = attendanceService.registrarAsistencia(attendanceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }

    // ------------------------------------------------------------
    // LISTAR TODAS LAS ASISTENCIAS
    // ------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> listarAsistencias() {
        return ResponseEntity.ok(attendanceService.listarAsistencias());
    }

    // ------------------------------------------------------------
    // OBTENER ASISTENCIA POR ID
    // ------------------------------------------------------------
    @GetMapping("/{idAttendance}")
    public ResponseEntity<AttendanceDTO> obtenerAsistenciaPorId(@PathVariable Long idAttendance) {
        return attendanceService.obtenerAsistenciaPorId(idAttendance)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ------------------------------------------------------------
    // ACTUALIZAR ASISTENCIA
    // ------------------------------------------------------------
    @PutMapping("/{idAttendance}")
    public ResponseEntity<AttendanceDTO> actualizarAsistencia(
            @PathVariable Long idAttendance,
            @RequestBody AttendanceDTO attendanceDTO) {

        AttendanceDTO updated = attendanceService.actualizarAsistencia(idAttendance, attendanceDTO);
        return ResponseEntity.ok(updated);
    }

    // ------------------------------------------------------------
    // ELIMINAR ASISTENCIA
    // ------------------------------------------------------------
    @DeleteMapping("/{idAttendance}")
    public ResponseEntity<Void> eliminarAsistencia(@PathVariable Long idAttendance) {
        attendanceService.eliminarAsistencia(idAttendance);
        return ResponseEntity.noContent().build();
    }

    // ------------------------------------------------------------
    // REGISTRO DE ASISTENCIA EN GRUPO (por registros)
    // ------------------------------------------------------------
    @PostMapping("/grupo")
    public ResponseEntity<List<AttendanceDTO>> registrarAsistenciaGrupo(
            @RequestBody AttendanceGroupDTO dto) {

        List<AttendanceDTO> result = attendanceService.registrarAsistenciaGrupo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
