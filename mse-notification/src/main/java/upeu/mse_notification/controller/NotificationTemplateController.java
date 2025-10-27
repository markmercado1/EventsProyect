package upeu.mse_notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.mse_notification.entity.NotificationTemplate;
import upeu.mse_notification.service.NotificationTemplateService;

import java.util.List;

@RestController
@RequestMapping("/notification-templates")
public class NotificationTemplateController {

    private final NotificationTemplateService templateService;

    public NotificationTemplateController(NotificationTemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationTemplate>> listarTemplates() {
        List<NotificationTemplate> templates = templateService.getAllTemplates();
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationTemplate> obtenerTemplate(@PathVariable Long id) {
        return templateService.getTemplateById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<NotificationTemplate> obtenerPorNombre(@PathVariable String name) {
        return templateService.getTemplateByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NotificationTemplate> crearTemplate(@RequestBody NotificationTemplate template) {
        NotificationTemplate guardado = templateService.createTemplate(template);
        return ResponseEntity.ok(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationTemplate> actualizarTemplate(
            @PathVariable Long id,
            @RequestBody NotificationTemplate template) {
        try {
            NotificationTemplate actualizado = templateService.updateTemplate(id, template);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<NotificationTemplate> cambiarEstado(
            @PathVariable Long id,
            @RequestParam Boolean enabled) {
        try {
            NotificationTemplate actualizado = templateService.toggleTemplateStatus(id, enabled);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTemplate(@PathVariable Long id) {
        templateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}
