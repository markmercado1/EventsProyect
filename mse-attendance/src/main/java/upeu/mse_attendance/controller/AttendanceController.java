package upeu.mse_attendance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.mse_attendance.entity.Attendance;
import upeu.mse_attendance.service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<Attendance> registrarAsistencia(@RequestBody Attendance attendance) {
        Attendance nuevaAsistencia = attendanceService.registrarAsistencia(attendance);
        return ResponseEntity.ok(nuevaAsistencia);
    }




    @GetMapping
    public ResponseEntity<List<Attendance>> listarAsistencias() {
        List<Attendance> asistencias = attendanceService.listarAsistencias();
        return ResponseEntity.ok(asistencias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Attendance> obtenerAsistenciaPorId(@PathVariable Long id) {
        return attendanceService.obtenerAsistenciaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Attendance> actualizarAsistencia(@PathVariable Long id, @RequestBody Attendance attendance) {
        try {
            Attendance asistenciaActualizada = attendanceService.actualizarAsistencia(id, attendance);
            return ResponseEntity.ok(asistenciaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsistencia(@PathVariable Long id) {
        try {
            attendanceService.eliminarAsistencia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
