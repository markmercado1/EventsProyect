package upeu.mse_report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upeu.mse_report.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
