package upeu.mse_report.service;

import upeu.mse_report.entity.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    // Crear un nuevo reporte
    Report crearReporte(Report report);

    // Listar todos los reportes
    List<Report> listarReportes();

    // Obtener un reporte por su ID
    Optional<Report> obtenerReportePorId(Long idReport);

    // Actualizar un reporte existente
    Report actualizarReporte(Long idReport, Report report);

    // Eliminar un reporte por su ID
    void eliminarReporte(Long idReport);
}
