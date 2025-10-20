package com.emm.mseventoservice.service;

import com.emm.mseventoservice.dtos.CreateEventDTO;
import com.emm.mseventoservice.dtos.EventDTO;

import java.util.List;

public interface EventService {
    EventDTO createEvent(CreateEventDTO dto);
    List<EventDTO> getAllEvents();
    EventDTO getEventById(Long id);
    void deleteEvent(Long id);
}
