package upeu.mse_notification.service;

import upeu.mse_notification.dto.NotificationResponseDTO;
import upeu.mse_notification.entity.Notification;

import java.util.List;

public interface NotificationService {

    List<NotificationResponseDTO> findAll();


    Notification sendNotification(Notification notification);

    Notification sendUsingTemplate(
            String templateCode,
            Long participantId,
            Long registrationId,
            Long attendanceId,
            Long eventId,
            String emailTo,
            Object data
    );
}

