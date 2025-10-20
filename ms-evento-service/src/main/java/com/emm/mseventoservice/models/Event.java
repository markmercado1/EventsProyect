package com.emm.mseventoservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Event {
    public Event(Long eventId) {
        this.eventId = eventId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modality modality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType = EventType.FREE;

    private Integer maxCapacity = 0;
    @Column(nullable = false)
    private Long organizerId;
    @Column(length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status = EventStatus.ACTIVE;
    public enum Modality {
        PRESENTIAL,
        VIRTUAL,
        HYBRID
    }

    public enum EventType {
        FREE,
        PAID,
    }

    public enum EventStatus {
        ACTIVE,
        INACTIVE
    }

}
