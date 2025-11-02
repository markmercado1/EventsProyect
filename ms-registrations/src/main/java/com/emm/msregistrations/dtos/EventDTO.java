package com.emm.msregistrations.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {
    private Long eventId;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private String location;
}