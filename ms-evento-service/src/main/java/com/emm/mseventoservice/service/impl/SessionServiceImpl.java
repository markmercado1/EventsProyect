package com.emm.mseventoservice.service.impl;

import com.emm.mseventoservice.dtos.CreateSessionDTO;
import com.emm.mseventoservice.dtos.SessionDTO;
import com.emm.mseventoservice.mappers.SessionMapper;
import com.emm.mseventoservice.models.Session;
import com.emm.mseventoservice.repository.SessionRepository;
import com.emm.mseventoservice.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    @Override
    public SessionDTO createSession(CreateSessionDTO dto) {
        Session session = sessionMapper.toEntityFromCreateDto(dto);
        Session saved = sessionRepository.save(session);
        return sessionMapper.toDto(saved);
    }

    @Override
    public List<SessionDTO> getSessionsByEvent(Long eventId) {
        return sessionRepository.findByEvent_EventId(eventId)
                .stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SessionDTO getSessionById(Long id) {
        return sessionRepository.findById(id)
                .map(sessionMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
