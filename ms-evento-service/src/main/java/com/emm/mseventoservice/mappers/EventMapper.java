package com.emm.mseventoservice.mappers;

import com.emm.mseventoservice.dtos.CreateEventDTO;
import com.emm.mseventoservice.dtos.EventDTO;
import com.emm.mseventoservice.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO toDto(Event event);


    @Mapping(target = "status", expression = "java(com.emm.mseventoservice.models.Event.EventStatus.ACTIVE)")
    Event toEntityFromCreateDto(CreateEventDTO createDto);
}