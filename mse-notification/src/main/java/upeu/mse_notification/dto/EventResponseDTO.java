package upeu.mse_notification.dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class EventResponseDTO {

    private Long eventId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    private String modality;
    private String eventType;
    private Integer maxCapacity;

    private Long organizerId;
    private String organizer;
    private String address;
    private String status;

    private java.math.BigDecimal price;
}
