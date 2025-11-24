package com.emm.msregistrations.dtos;

import lombok.Data;
import java.time.LocalDateTime;

// DTO que recibimos de vuelta del microservicio de notificación
@Data
public class NotificationResponseLocalDTO {
    private Long notificationId;
    private String title;
    private String message;
    private String status; // PENDING, SENT, ERROR
    private String emailTo;
    private LocalDateTime sentAt;
}
