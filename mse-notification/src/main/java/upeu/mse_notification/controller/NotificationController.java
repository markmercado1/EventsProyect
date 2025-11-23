package upeu.mse_notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Notification> send(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.sendNotification(notification));
    }

    @PostMapping("/send-template/{templateCode}")
    public ResponseEntity<Notification> sendUsingTemplate(
            @PathVariable String templateCode,
            @RequestParam(required = false) Long participantId,
            @RequestParam(required = false) Long registrationId,
            @RequestParam(required = false) Long attendanceId,
            @RequestParam(required = false) Long eventId,
            @RequestParam String emailTo,
            @RequestBody Map<String, Object> data
    ) {
        return ResponseEntity.ok(
                notificationService.sendUsingTemplate(
                        templateCode,
                        participantId,
                        registrationId,
                        attendanceId,
                        eventId,
                        emailTo,
                        data
                )
        );
    }
}
