package upeu.mse_notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.mse_notification.dto.NotificationCreateDTO;
import upeu.mse_notification.dto.NotificationResponseDTO;
import upeu.mse_notification.entity.Notification;
import upeu.mse_notification.service.NotificationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @PostMapping("/send")
    public ResponseEntity<NotificationResponseDTO> send(@RequestBody NotificationCreateDTO dto) {
        Notification notification = Notification.builder()
                .emailTo(dto.getEmailTo())
                .title(dto.getTitle())
                .message(dto.getMessage())
                .channel(dto.getChannel() != null ? dto.getChannel() : "EMAIL")
                .status("PENDING")
                .templateCode(dto.getTemplateCode())
                .participantId(dto.getParticipantId())
                .registrationId(dto.getRegistrationId())
                .attendanceId(dto.getAttendanceId())
                .eventId(dto.getEventId())
                .build();

        Notification saved = notificationService.sendNotification(notification);

        return ResponseEntity.ok(notificationService.mapToDTO(saved));
    }

    @PostMapping("/send-template/{templateCode}")
    public ResponseEntity<NotificationResponseDTO> sendUsingTemplate(
            @PathVariable String templateCode,
            @RequestParam(required = false) Long participantId,
            @RequestParam(required = false) Long registrationId,
            @RequestParam(required = false) Long attendanceId,
            @RequestParam(required = false) Long eventId,
            @RequestParam String emailTo,
            @RequestBody Map<String, Object> data
    ) {
        Notification saved = notificationService.sendUsingTemplate(
                templateCode,
                participantId,
                registrationId,
                attendanceId,
                eventId,
                emailTo,
                data
        );

        return ResponseEntity.ok(notificationService.mapToDTO(saved));
    }
}
