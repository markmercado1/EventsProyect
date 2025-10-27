package upeu.mse_notification.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_template")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_template")
    private Long idTemplate;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // Contenido con placeholders dinámicos

    @Column(nullable = false, length = 30)
    private String type; // ATTENDANCE_ALERT, EVENT_REMINDER, GENERAL

    @Column(nullable = false)
    private Boolean enabled;
}
