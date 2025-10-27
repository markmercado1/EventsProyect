package upeu.mse_report.service;

import upeu.mse_report.entity.ReportLog;

import java.util.List;
import java.util.Optional;

public interface ReportLogService {

    ReportLog crearLog(ReportLog reportLog);

    List<ReportLog> listarLogs();

    List<ReportLog> listarLogsPorReporte(Long idReport);

    Optional<ReportLog> obtenerLogPorId(Long idReportLog);

    void eliminarLog(Long idReportLog);
}
