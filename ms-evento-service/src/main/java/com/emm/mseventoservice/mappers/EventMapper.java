package com.emm.mseventoservice.mappers;


import com.emm.mseventoservice.dtos.CreateEventDTO;
import com.emm.mseventoservice.dtos.EventDTO;
import com.emm.mseventoservice.models.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    default EventDTO toDto(Event event) {
        if (event == null) {
            return null;
        }

        return EventDTO.builder()
                .eventId(event.getEventId())
                .name(event.getName())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .status(event.getStatus())
                .modality(event.getModality())
                .eventType(event.getEventType())
                .maxCapacity(event.getMaxCapacity())
                .organizerId(event.getOrganizerId())
                .address(event.getAddress())
                .build();
    }

    default Event toEntityFromCreateDto(CreateEventDTO createDto) {
        if (createDto == null) {
            return null;
        }

        Event event = Event.builder()
                .name(createDto.getName())
                .description(createDto.getDescription())
                .startDate(createDto.getStartDate())
                .endDate(createDto.getEndDate())
                .modality(createDto.getModality())
                .eventType(createDto.getEventType() != null ? createDto.getEventType() : Event.EventType.FREE)
                .maxCapacity(createDto.getMaxCapacity() != null ? createDto.getMaxCapacity() : 0)
                .organizerId(createDto.getOrganizerId())
                .address(createDto.getAddress())
                .build();

        if (createDto.getStatus() != null) {
            event.setStatus(createDto.getStatus());
        }

        return event;
    }
}