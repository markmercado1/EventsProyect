package upeu.mse_notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import upeu.mse_notification.dto.NotificationRequestDTO;
import upeu.mse_notification.dto.NotificationResponseDTO;
import upeu.mse_notification.service.NotificationService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<NotificationResponseDTO> sendNotification(@Validated @RequestBody NotificationRequestDTO request) throws MessagingException {
        NotificationResponseDTO response = notificationService.sendNotification(request);
        return ResponseEntity.ok(response);
    }
}
