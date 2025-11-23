package upeu.mse_notification.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upeu.mse_notification.entity.NotificationTemplate;
import upeu.mse_notification.repository.NotificationTemplateRepository;
import upeu.mse_notification.service.NotificationTemplateService;

@Service
@RequiredArgsConstructor
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final NotificationTemplateRepository templateRepository;

    @Override
    public NotificationTemplate create(NotificationTemplate template) {
        return templateRepository.save(template);
    }

    @Override
    public NotificationTemplate update(Long templateId, NotificationTemplate template) {
        NotificationTemplate existing = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        existing.setName(template.getName());
        existing.setBody(template.getBody());
        existing.setSubject(template.getSubject());
        existing.setChannel(template.getChannel());
        existing.setIsActive(template.getIsActive());

        return templateRepository.save(existing);
    }

    @Override
    public NotificationTemplate getByCode(String code) {
        return templateRepository.findByCodeAndIsActiveTrue(code)
                .orElseThrow(() -> new RuntimeException("Template not found or inactive"));
    }
}
