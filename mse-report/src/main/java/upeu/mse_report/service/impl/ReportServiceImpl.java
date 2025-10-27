package upeu.mse_report.service.impl;

import org.springframework.stereotype.Service;
import upeu.mse_report.entity.Report;
import upeu.mse_report.repository.ReportRepository;
import upeu.mse_report.service.ReportService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report crearReporte(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public List<Report> listarReportes() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<Report> obtenerReportePorId(Long idReport) {
        return reportRepository.findById(idReport);
    }

    @Override
    public Report actualizarReporte(Long idReport, Report report) {
        return reportRepository.findById(idReport)
                .map(r -> {
                    r.setGeneratedBy(report.getGeneratedBy());
                    r.setType(report.getType());
                    r.setFormat(report.getFormat());
                    r.setStatus(report.getStatus());
                    r.setCreatedAt(report.getCreatedAt());
                    r.setGeneratedAt(report.getGeneratedAt());
                    r.setFileUrl(report.getFileUrl());
                    return reportRepository.save(r);
                })
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + idReport));
    }

    @Override
    public void eliminarReporte(Long idReport) {
        if (!reportRepository.existsById(idReport)) {
            throw new RuntimeException("Reporte no encontrado con ID: " + idReport);
        }
        reportRepository.deleteById(idReport);
    }
}
