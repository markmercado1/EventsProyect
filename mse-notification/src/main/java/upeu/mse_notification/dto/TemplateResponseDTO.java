package upeu.mse_notification.dto;


import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateResponseDTO {

    private Long templateId;
    private String code;
    private String name;
    private String channel;
    private String subject;
    private String body;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
