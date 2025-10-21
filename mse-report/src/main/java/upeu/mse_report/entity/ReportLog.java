package upeu.mse_report.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación muchos a uno hacia Report
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    // Ejemplo de valores: INFO, WARN, ERROR
    @Column(nullable = false, length = 10)
    private String level;
}
