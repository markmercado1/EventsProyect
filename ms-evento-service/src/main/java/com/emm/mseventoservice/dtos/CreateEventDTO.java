package com.emm.mseventoservice.dtos;

import com.emm.mseventoservice.models.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEventDTO {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Event.Modality modality;
    private Event.EventType eventType;
    private Integer maxCapacity;
    private Long organizerId;
    private String address;
}