package upeu.mse_report.service.impl;

import org.springframework.stereotype.Service;
import upeu.mse_report.entity.ReportLog;
import upeu.mse_report.repository.ReportLogRepository;
import upeu.mse_report.service.ReportLogService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportLogServiceImpl implements ReportLogService {

    private final ReportLogRepository reportLogRepository;

    public ReportLogServiceImpl(ReportLogRepository reportLogRepository) {
        this.reportLogRepository = reportLogRepository;
    }

    @Override
    public ReportLog crearLog(ReportLog reportLog) {
        return reportLogRepository.save(reportLog);
    }

    @Override
    public List<ReportLog> listarLogs() {
        return reportLogRepository.findAll();
    }

    @Override
    public List<ReportLog> listarLogsPorReporte(Long idReport) {
        return reportLogRepository.findByReport_IdReport(idReport);
    }

    @Override
    public Optional<ReportLog> obtenerLogPorId(Long idReportLog) {
        return reportLogRepository.findById(idReportLog);
    }

    @Override
    public void eliminarLog(Long idReportLog) {
        if (!reportLogRepository.existsById(idReportLog)) {
            throw new RuntimeException("Log no encontrado con ID: " + idReportLog);
        }
        reportLogRepository.deleteById(idReportLog);
    }
}
