package com.emm.mseventoservice.service.impl;

import com.emm.mseventoservice.dtos.CreateEventDTO;
import com.emm.mseventoservice.dtos.EventDTO;
import com.emm.mseventoservice.mappers.EventMapper;
import com.emm.mseventoservice.models.Event;
import com.emm.mseventoservice.repository.EventsRepository;
import com.emm.mseventoservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventService {
    private final EventsRepository eventRepository;
    private final EventMapper eventMapper;
    @Override
    public EventDTO createEvent(CreateEventDTO dto) {
        Event event = eventMapper.toEntityFromCreateDto(dto);
        Event saved = eventRepository.save(event);
        return eventMapper.toDto(saved);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO getEventById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
