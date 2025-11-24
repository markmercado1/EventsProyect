package upeu.mse_notification.service;

import upeu.mse_notification.dto.TemplateCreateDTO;
import upeu.mse_notification.dto.TemplateResponseDTO;
import upeu.mse_notification.dto.TemplateUpdateDTO;

public interface NotificationTemplateService {

    TemplateResponseDTO create(TemplateCreateDTO dto);

    TemplateResponseDTO update(Long templateId, TemplateUpdateDTO dto);

    TemplateResponseDTO getByCode(String code);
}
