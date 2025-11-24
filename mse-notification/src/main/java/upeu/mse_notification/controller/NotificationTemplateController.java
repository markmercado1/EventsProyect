//package upeu.mse_notification.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import upeu.mse_notification.service.NotificationTemplateService;
//
//@RestController
//@RequestMapping("/notification-templates")
//@RequiredArgsConstructor
//public class NotificationTemplateController {
//
//    private final NotificationTemplateService templateService;
//
//    @PostMapping
//    public ResponseEntity<TemplateResponseDTO> create(@RequestBody TemplateCreateDTO dto) {
//        return ResponseEntity.ok(templateService.create(dto));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TemplateResponseDTO> update(
//            @PathVariable Long id,
//            @RequestBody TemplateUpdateDTO dto
//    ) {
//        return ResponseEntity.ok(templateService.update(id, dto));
//    }
//
//    @GetMapping("/code/{code}")
//    public ResponseEntity<TemplateResponseDTO> getByCode(@PathVariable String code) {
//        return ResponseEntity.ok(templateService.getByCode(code));
//    }
//}
