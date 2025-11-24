package upeu.mse_notification.dto;


import lombok.Data;

@Data
public class NotificationTemplateDTO {

    private Long templateId;
    private String code;       // Código único del template
    private String name;       // Nombre del template
    private String subject;    // Asunto del email
    private String body;       // Cuerpo del mensaje con placeholders
    private String channel;    // EMAIL, SMS, WHATSAPP, etc.
}
