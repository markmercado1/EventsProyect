package upeu.mse_notification.service.impl;

import upeu.mse_notification.entity.Notification;
import org.springframework.stereotype.Service;
import upeu.mse_notification.repository.NotificationRepository;
import upeu.mse_notification.service.NotificationService;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> getNotificationById(Long idNotification) {
        return notificationRepository.findById(idNotification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public Notification updateNotificationStatus(Long idNotification, String status) {
        return notificationRepository.findById(idNotification)
                .map(n -> {
                    n.setStatus(status);
                    return notificationRepository.save(n);
                })
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    @Override
    public void deleteNotification(Long idNotification) {
        notificationRepository.deleteById(idNotification);
    }
}
