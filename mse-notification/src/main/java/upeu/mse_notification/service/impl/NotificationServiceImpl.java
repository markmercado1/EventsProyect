package upeu.mse_notification.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upeu.mse_notification.dto.NotificationRequestDTO;
import upeu.mse_notification.dto.NotificationResponseDTO;
import upeu.mse_notification.entity.Notification;
import upeu.mse_notification.entity.NotificationTemplate;
import upeu.mse_notification.feign.EventFeign;
import upeu.mse_notification.feign.ParticipantFeign;
import upeu.mse_notification.repository.NotificationRepository;
import upeu.mse_notification.repository.NotificationTemplateRepository;
import upeu.mse_notification.service.NotificationService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationTemplateRepository templateRepository;
    private final ParticipantFeign participantFeign;
    private final EventFeign eventFeign;
    private final JavaMailSender mailSender;

    @Override
    @Transactional
    public NotificationResponseDTO sendNotification(NotificationRequestDTO request) throws MessagingException {
        // Obtener participante y evento vía Feign
        var participant = participantFeign.getParticipantById(request.getParticipantId());
        var event = eventFeign.getEventById(request.getEventId());

        // Obtener template
        NotificationTemplate template = templateRepository.findByCodeAndIsActiveTrue(request.getTemplateCode())
                .orElseThrow(() -> new RuntimeException("Template no encontrado o inactivo"));

        // Construir mensaje reemplazando variables
        String message = template.getBody()
                .replace("{participantName}", participant.getFirstName() + " " + participant.getLastName())
                .replace("{eventName}", event.getName())
                .replace("{eventAddress}", event.getAddress());

        String subject = template.getSubject()
                .replace("{participantName}", participant.getFirstName())
                .replace("{eventName}", event.getName());

        // Crear entidad Notification (AQUÍ ESTABA EL ERROR)
        Notification notification = Notification.builder()
                .templateCode(template.getCode())          // ✅ NECESARIO
                .participantId(participant.getParticipantId())
                .eventId(event.getEventId())
                .channel(template.getChannel())
                .title(subject)
                .message(message)
                .emailTo(participant.getEmail())
                .status("PENDING")
                .build();

        notification = notificationRepository.save(notification);

        // Enviar email
        sendEmail(notification.getEmailTo(), subject, message);

        // Actualizar estado
        notification.setStatus("SENT");
        notification.setSentAt(LocalDateTime.now());
        notificationRepository.save(notification);

        // Retornar DTO
        NotificationResponseDTO response = new NotificationResponseDTO();
        response.setNotificationId(notification.getNotificationId());
        response.setTitle(subject);
        response.setMessage(message);
        response.setStatus(notification.getStatus());
        response.setEmailTo(notification.getEmailTo());
        response.setSentAt(notification.getSentAt());

        return response;
    }


    private void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true); // true = HTML
        mailSender.send(mimeMessage);
    }
}
