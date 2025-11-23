package upeu.mse_notification.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import upeu.mse_notification.dto.NotificationResponseDTO;
import upeu.mse_notification.entity.Notification;
import upeu.mse_notification.entity.NotificationTemplate;
import upeu.mse_notification.repository.NotificationRepository;
import upeu.mse_notification.service.NotificationService;
import upeu.mse_notification.service.NotificationTemplateService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationTemplateService templateService;
    private final JavaMailSender mailSender;


    @Override
    public List<NotificationResponseDTO> findAll() {
        return notificationRepository.findAll()
                .stream()
                .map(n -> NotificationResponseDTO.builder()
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
                        .build()
                ).toList();
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

        } catch (Exception e) {
            notification.setStatus("ERROR");
            notification.setErrorMessage(e.getMessage());
            e.printStackTrace();
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
            Object dataObj
    ) {
        Map<String, Object> data = (Map<String, Object>) dataObj;

        NotificationTemplate template = templateService.getByCode(templateCode);

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

        notificationRepository.save(notification);

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
