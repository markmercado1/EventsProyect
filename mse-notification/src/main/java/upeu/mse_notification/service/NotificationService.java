package upeu.mse_notification.service;

import upeu.mse_notification.dto.NotificationResponseDTO;
import upeu.mse_notification.entity.Notification;

import java.util.List;
import java.util.Map;

public interface NotificationService {

    List<NotificationResponseDTO> findAll();

    Notification createNotification(Notification notification);

    Notification sendNotification(Notification notification);

    Notification sendUsingTemplate(
            String templateCode,
            Long participantId,
            Long registrationId,
            Long attendanceId,
            Long eventId,
            String emailTo,
            Map<String, Object> data
    );

    NotificationResponseDTO mapToDTO(Notification notification);
}
