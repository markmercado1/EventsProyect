package upeu.mse_notification.service;

import jakarta.mail.MessagingException;
import upeu.mse_notification.dto.NotificationRequestDTO;
import upeu.mse_notification.dto.NotificationResponseDTO;
import upeu.mse_notification.entity.Notification;

import java.util.List;
import java.util.Map;

public interface NotificationService {

    NotificationResponseDTO sendNotification(NotificationRequestDTO request) throws MessagingException;
}
