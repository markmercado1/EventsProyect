package upeu.mse_notification.service.impl;

import upeu.mse_notification.entity.NotificationTemplate;
import upeu.mse_notification.repository.NotificationTemplateRepository;
import upeu.mse_notification.service.NotificationTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final NotificationTemplateRepository templateRepository;

    public NotificationTemplateServiceImpl(NotificationTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public NotificationTemplate createTemplate(NotificationTemplate template) {
        return templateRepository.save(template);
    }

    @Override
    public List<NotificationTemplate> getAllTemplates() {
        return templateRepository.findAll();
    }

    @Override
    public Optional<NotificationTemplate> getTemplateById(Long idTemplate) {
        return templateRepository.findById(idTemplate);
    }

    @Override
    public Optional<NotificationTemplate> getTemplateByName(String name) {
        return templateRepository.findByName(name);
    }

    @Override
    public NotificationTemplate updateTemplate(Long idTemplate, NotificationTemplate updatedTemplate) {
        return templateRepository.findById(idTemplate)
                .map(existingTemplate -> {
                    existingTemplate.setName(updatedTemplate.getName());
                    existingTemplate.setContent(updatedTemplate.getContent());
                    existingTemplate.setType(updatedTemplate.getType());
                    existingTemplate.setEnabled(updatedTemplate.getEnabled());
                    return templateRepository.save(existingTemplate);
                })
                .orElseThrow(() -> new RuntimeException("Notification Template not found with ID: " + idTemplate));
    }

    @Override
    public NotificationTemplate toggleTemplateStatus(Long idTemplate, Boolean enabled) {
        return templateRepository.findById(idTemplate)
                .map(template -> {
                    template.setEnabled(enabled);
                    return templateRepository.save(template);
                })
                .orElseThrow(() -> new RuntimeException("Notification Template not found with ID: " + idTemplate));
    }

    @Override
    public void deleteTemplate(Long idTemplate) {
        if (!templateRepository.existsById(idTemplate)) {
            throw new RuntimeException("Notification Template not found with ID: " + idTemplate);
        }
        templateRepository.deleteById(idTemplate);
    }
}
