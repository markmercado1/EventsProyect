package upeu.mse_report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upeu.mse_report.entity.ReportLog;

import java.util.List;

@Repository
public interface ReportLogRepository extends JpaRepository<ReportLog, Long> {
    List<ReportLog> findByReportId(Long reportId);
}
