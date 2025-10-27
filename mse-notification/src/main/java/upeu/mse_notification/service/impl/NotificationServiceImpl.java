package upeu.mse_notification.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upeu.mse_notification.dto.AuthUserDTO;
import upeu.mse_notification.dto.EventDTO;
import upeu.mse_notification.dto.NotificationDTO;
import upeu.mse_notification.dto.ParticipantDTO;
import upeu.mse_notification.entity.Notification;
import upeu.mse_notification.feign.AuthUserFeign;
import upeu.mse_notification.feign.EventFeign;
import upeu.mse_notification.feign.ParticipantFeign;
import upeu.mse_notification.repository.NotificationRepository;
import upeu.mse_notification.service.NotificationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final AuthUserFeign authUserFeign;
    private final EventFeign eventFeign;
    private final ParticipantFeign participantFeign;

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotificationDTO> getNotificationById(Long idNotification) {
        return notificationRepository.findById(idNotification)
                .map(this::convertToDTO);
    }

    @Override
    public List<NotificationDTO> getNotificationsByAuthUserId(int authUserId) {
        return notificationRepository.findAll()
                .stream()
                .filter(n -> n.getAuthUserId() == authUserId)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Notification updateNotificationStatus(Long idNotification, String status) {
        Notification notification = notificationRepository.findById(idNotification)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setStatus(status);
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long idNotification) {
        notificationRepository.deleteById(idNotification);
    }

    // ----------------- Conversión a DTO usando Feign -----------------
    private NotificationDTO convertToDTO(Notification notification) {
        AuthUserDTO authUserDTO = authUserFeign.buscarPorId(notification.getAuthUserId());
        EventDTO eventDTO = eventFeign.buscarPorId(notification.getEventId());
        ParticipantDTO participantDTO = participantFeign.buscarPorId(notification.getParticipantId());

        return NotificationDTO.builder()
                .idNotification(notification.getIdNotification())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .type(notification.getType())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .authUserDTO(authUserDTO)
                .eventDTO(eventDTO)
                .participantDTO(participantDTO)
                .build();
    }
}
