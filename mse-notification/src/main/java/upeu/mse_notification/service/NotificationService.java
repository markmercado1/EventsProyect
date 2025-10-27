package upeu.mse_notification.service;

import upeu.mse_notification.dto.NotificationDTO;
import upeu.mse_notification.entity.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationService {

    // Crear nueva notificación
    Notification createNotification(Notification notification);

    // Listar todas las notificaciones
    List<NotificationDTO> getAllNotifications();

    // Buscar notificación por ID
    Optional<NotificationDTO> getNotificationById(Long idNotification);

    // Listar notificaciones por usuario
    List<NotificationDTO> getNotificationsByAuthUserId(int authUserId);

    // Actualizar estado de una notificación
    Notification updateNotificationStatus(Long idNotification, String status);

    // Eliminar notificación
    void deleteNotification(Long idNotification);
}
