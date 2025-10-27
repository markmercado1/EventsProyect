package upeu.mse_report.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.mse_report.entity.ReportLog;
import upeu.mse_report.service.ReportLogService;

import java.util.List;

@RestController
@RequestMapping("/report-logs")
public class ReportLogController {

    private final ReportLogService logService;

    public ReportLogController(ReportLogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<ReportLog>> listarLogs() {
        List<ReportLog> logs = logService.listarLogs();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportLog> obtenerLog(@PathVariable Long id) {
        return logService.obtenerLogPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-report/{reportId}")
    public ResponseEntity<List<ReportLog>> listarPorReporte(@PathVariable Long reportId) {
        List<ReportLog> logs = logService.listarLogsPorReporte(reportId);
        return ResponseEntity.ok(logs);
    }

    @PostMapping
    public ResponseEntity<ReportLog> crearLog(@RequestBody ReportLog log) {
        ReportLog guardado = logService.crearLog(log);
        return ResponseEntity.ok(guardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLog(@PathVariable Long id) {
        try {
            logService.eliminarLog(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
