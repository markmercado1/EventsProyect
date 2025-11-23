package upeu.mse_notification.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateUpdateDTO {

    private String name;
    private String channel;
    private String subject;
    private String body;
    private Boolean isActive;
}
