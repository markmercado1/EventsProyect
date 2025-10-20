package com.emm.mseventoservice.mappers;

import com.emm.mseventoservice.dtos.CreateSessionDTO;
import com.emm.mseventoservice.dtos.SessionDTO;
import com.emm.mseventoservice.models.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(source = "event.eventId", target = "eventId")
    SessionDTO toDto(Session session);

    @Mapping(target = "event", expression = "java(new Event(createDto.getEventId()))")
    Session toEntityFromCreateDto(CreateSessionDTO createDto);
}