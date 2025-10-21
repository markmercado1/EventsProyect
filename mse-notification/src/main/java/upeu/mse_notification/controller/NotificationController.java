package upeu.mse_notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<List<Notification>> listarNotificaciones() {
        List<Notification> notificaciones = notificationService.getAllNotifications();
        return ResponseEntity.ok(notificaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> obtenerNotificacion(@PathVariable Long id) {
        return notificationService.getNotificationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> listarPorUsuario(@PathVariable String userId) {
        List<Notification> notificaciones = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notificaciones);
    }

    @PostMapping
    public ResponseEntity<Notification> crearNotificacion(@RequestBody Notification notification) {
        Notification guardada = notificationService.createNotification(notification);
        return ResponseEntity.ok(guardada);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
