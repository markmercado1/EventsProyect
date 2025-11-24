package upeu.mse_notification.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import upeu.mse_notification.dto.NotificationResponseDTO;
import upeu.mse_notification.dto.TemplateResponseDTO;
import upeu.mse_notification.entity.Notification;
import upeu.mse_notification.entity.NotificationTemplate;
import upeu.mse_notification.repository.NotificationRepository;
import upeu.mse_notification.service.NotificationService;
import upeu.mse_notification.service.NotificationTemplateService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationTemplateService templateService;
    private final JavaMailSender mailSender;

    private final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public List<NotificationResponseDTO> findAll() {
        return notificationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public NotificationResponseDTO mapToDTO(Notification n) {
        return NotificationResponseDTO.builder()
                .notificationId(n.getNotificationId())
                .templateCode(n.getTemplateCode())
                .participantId(n.getParticipantId())
                .registrationId(n.getRegistrationId())
                .attendanceId(n.getAttendanceId())
                .eventId(n.getEventId())
                .channel(n.getChannel())
                .title(n.getTitle())
                .message(n.getMessage())
                .status(n.getStatus())
                .errorMessage(n.getErrorMessage())
                .sentAt(n.getSentAt())
                .createdAt(n.getCreatedAt())
                .build();
    }

    @Override
    public Notification createNotification(Notification notification) {
        if (notification.getChannel() == null) notification.setChannel("EMAIL");
        if (notification.getStatus() == null) notification.setStatus("PENDING");
        if (notification.getCreatedAt() == null) notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification sendNotification(Notification notification) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notification.getEmailTo());
            message.setSubject(notification.getTitle());
            message.setText(notification.getMessage());

            mailSender.send(message);

            notification.setStatus("SENT");
            notification.setSentAt(LocalDateTime.now());

            log.info("Correo enviado a {}", notification.getEmailTo());

        } catch (Exception e) {
            notification.setStatus("ERROR");
            notification.setErrorMessage(e.getMessage());
            log.error("Error enviando correo a {}: {}", notification.getEmailTo(), e.getMessage());
        }

        return notificationRepository.save(notification);
    }

    @Override
    public Notification sendUsingTemplate(
            String templateCode,
            Long participantId,
            Long registrationId,
            Long attendanceId,
            Long eventId,
            String emailTo,
            Map<String, Object> data
    ) {

        TemplateResponseDTO template = templateService.getByCode(templateCode);

        String message = fillTemplate(template.getBody(), data);
        String subject = fillTemplate(template.getSubject(), data);

        Notification notification = Notification.builder()
                .templateCode(templateCode)
                .participantId(participantId)
                .registrationId(registrationId)
                .attendanceId(attendanceId)
                .eventId(eventId)
                .emailTo(emailTo)
                .channel("EMAIL")
                .title(subject)
                .message(message)
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        createNotification(notification); // guardamos antes de enviar

        return sendNotification(notification);
    }

    private String fillTemplate(String text, Map<String, Object> data) {
        String output = text;
        for (String key : data.keySet()) {
            output = output.replace("{{" + key + "}}", data.get(key).toString());
        }
        return output;
    }
}
