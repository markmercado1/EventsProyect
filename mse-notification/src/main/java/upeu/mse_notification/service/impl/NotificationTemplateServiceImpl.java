//package upeu.mse_notification.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import upeu.mse_notification.entity.NotificationTemplate;
//import upeu.mse_notification.repository.NotificationTemplateRepository;
//import upeu.mse_notification.service.NotificationTemplateService;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class NotificationTemplateServiceImpl implements NotificationTemplateService {
//
//    private final NotificationTemplateRepository templateRepository;
//
//    @Override
//    public TemplateResponseDTO create(TemplateCreateDTO dto) {
//        NotificationTemplate template = NotificationTemplate.builder()
//                .code(dto.getCode())
//                .name(dto.getName())
//                .channel(dto.getChannel())
//                .subject(dto.getSubject())
//                .body(dto.getBody())
//                .isActive(true)
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        NotificationTemplate saved = templateRepository.save(template);
//        return mapToDTO(saved);
//    }
//
//    @Override
//    public TemplateResponseDTO update(Long templateId, TemplateUpdateDTO dto) {
//        NotificationTemplate existing = templateRepository.findById(templateId)
//                .orElseThrow(() -> new RuntimeException("Template not found"));
//
//        if(dto.getName() != null) existing.setName(dto.getName());
//        if(dto.getBody() != null) existing.setBody(dto.getBody());
//        if(dto.getSubject() != null) existing.setSubject(dto.getSubject());
//        if(dto.getChannel() != null) existing.setChannel(dto.getChannel());
//        if(dto.getIsActive() != null) existing.setIsActive(dto.getIsActive());
//
//        existing.setUpdatedAt(LocalDateTime.now());
//
//        NotificationTemplate saved = templateRepository.save(existing);
//        return mapToDTO(saved);
//    }
//
//    @Override
//    public TemplateResponseDTO getByCode(String code) {
//        NotificationTemplate template = templateRepository.findByCodeAndIsActiveTrue(code)
//                .orElseThrow(() -> new RuntimeException("Template not found or inactive"));
//        return mapToDTO(template);
//    }
//
//    private TemplateResponseDTO mapToDTO(NotificationTemplate t) {
//        return TemplateResponseDTO.builder()
//                .templateId(t.getTemplateId())
//                .code(t.getCode())
//                .name(t.getName())
//                .channel(t.getChannel())
//                .subject(t.getSubject())
//                .body(t.getBody())
//                .isActive(t.getIsActive())
//                .createdAt(t.getCreatedAt())
//                .updatedAt(t.getUpdatedAt())
//                .build();
//    }
//}
