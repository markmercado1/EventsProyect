package upeu.mse_report.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // Puede ser el ID del usuario o el texto "SYSTEM"
    @Column(name = "generated_by", nullable = false)
    private String generatedBy;

    // Ejemplo de valores posibles: GENERAL, EVENT_SUMMARY, USER_ATTENDANCE
    @Column(nullable = false, length = 50)
    private String type;

    // Ejemplo de valores: PDF, CSV, EXCEL
    @Column(nullable = false, length = 20)
    private String format;

    // Ejemplo de valores: GENERATED, IN_PROGRESS, FAILED
    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    // Puede ser una URL o path al archivo generado
    @Column(name = "file_url")
    private String fileUrl;

    // Relación uno a muchos con los logs
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ReportLog> logs;
}
