package upeu.mse_notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.mse_notification.dto.NotificationDTO;
import upeu.mse_notification.entity.Notification;
import upeu.mse_notification.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // ----------------- Listar todas las notificaciones -----------------
    @GetMapping
    public ResponseEntity<List<NotificationDTO>> listarNotificaciones() {
        List<NotificationDTO> notificaciones = notificationService.getAllNotifications();
        return ResponseEntity.ok(notificaciones);
    }

    // ----------------- Obtener notificación por ID -----------------
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> obtenerNotificacion(@PathVariable Long id) {
        return notificationService.getNotificationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ----------------- Listar notificaciones por usuario -----------------
    @GetMapping("/auth-user/{authUserId}")
    public ResponseEntity<List<NotificationDTO>> listarPorAuthUser(@PathVariable int authUserId) {
        List<NotificationDTO> notificaciones = notificationService.getNotificationsByAuthUserId(authUserId);
        return ResponseEntity.ok(notificaciones);
    }

    // ----------------- Crear nueva notificación -----------------
    @PostMapping
    public ResponseEntity<Notification> crearNotificacion(@RequestBody Notification notification) {
        Notification guardada = notificationService.createNotification(notification);
        return ResponseEntity.ok(guardada);
    }

    // ----------------- Actualizar estado de notificación -----------------
    @PutMapping("/{id}/status")
    public ResponseEntity<Notification> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String status) {
        try {
            Notification actualizada = notificationService.updateNotificationStatus(id, status);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ----------------- Eliminar notificación -----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
