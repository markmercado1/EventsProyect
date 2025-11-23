package upeu.mse_notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.mse_notification.entity.NotificationTemplate;
import upeu.mse_notification.service.NotificationTemplateService;

@RestController
@RequestMapping("/notification-templates")
@RequiredArgsConstructor
public class NotificationTemplateController {

    private final NotificationTemplateService templateService;

    @PostMapping
    public ResponseEntity<NotificationTemplate> create(@RequestBody NotificationTemplate template) {
        return ResponseEntity.ok(templateService.create(template));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationTemplate> update(
            @PathVariable Long id,
            @RequestBody NotificationTemplate template
    ) {
        return ResponseEntity.ok(templateService.update(id, template));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<NotificationTemplate> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(templateService.getByCode(code));
    }
}
