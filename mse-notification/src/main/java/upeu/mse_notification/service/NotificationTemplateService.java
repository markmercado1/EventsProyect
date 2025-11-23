package upeu.mse_notification.service;


import upeu.mse_notification.entity.NotificationTemplate;

public interface NotificationTemplateService {

    NotificationTemplate create(NotificationTemplate template);

    NotificationTemplate update(Long templateId, NotificationTemplate template);

    NotificationTemplate getByCode(String code);
}
