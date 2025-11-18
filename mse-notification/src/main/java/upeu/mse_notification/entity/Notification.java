package upeu.mse_notification.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Long idNotification;

    @Column(name = "auth_user_id", nullable = false)
    private int authUserId;

    @Column(name = "participant_id", nullable = false)
    private Long participantId;

    // 🔹 Hacer eventId opcional si el evento puede ser temporal
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(nullable = false, length = 150, name = "title")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT", name = "message")
    private String message;

    @Column(nullable = false, length = 30, name = "type")
    private String type; // ATTENDANCE_ALERT, EVENT_REMINDER, GENERAL

    @Column(nullable = false, length = 20, name = "status")
    private String status; // SENT, PENDING, FAILED

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    // 🔹 Se ejecuta antes de persistir la entidad para inicializar createdAt
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}


//package upeu.mse_notification.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "notification")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Notification {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_notification")
//    private Long idNotification;
//
//    @Column(name = "auth_user_id", nullable = false)
//    private int authUserId;
//
//    @Column(name = "participant_id", nullable = false)
//    private Long participantId;
//
//    @Column(name = "event_id", nullable = false)
//    private Long eventId;
//
//    @Column(nullable = false, length = 150, name = "title")
//    private String title;
//
//    @Column(nullable = false, columnDefinition = "TEXT", name = "message")
//    private String message;
//
//    @Column(nullable = false, length = 30, name = "type")
//    private String type; // ATTENDANCE_ALERT, EVENT_REMINDER, GENERAL
//
//
//    @Column(nullable = false, length = 20, name = "status")
//    private String status; // SENT, PENDING, FAILED
//
//    @Column(nullable = false, name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "sent_at")
//    private LocalDateTime sentAt;
//}
