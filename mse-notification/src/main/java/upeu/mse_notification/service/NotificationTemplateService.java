package upeu.mse_notification.service;


import upeu.mse_notification.entity.NotificationTemplate;
import java.util.List;
import java.util.Optional;

public interface NotificationTemplateService {

    NotificationTemplate createTemplate(NotificationTemplate template);

    List<NotificationTemplate> getAllTemplates();

    Optional<NotificationTemplate> getTemplateById(Long idTemplate);

    Optional<NotificationTemplate> getTemplateByName(String name);

    NotificationTemplate updateTemplate(Long idTemplate, NotificationTemplate template);

    NotificationTemplate toggleTemplateStatus(Long idTemplate, Boolean enabled);

    void deleteTemplate(Long idTemplate);
}

