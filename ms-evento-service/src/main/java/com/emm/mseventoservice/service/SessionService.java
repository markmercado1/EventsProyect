package com.emm.mseventoservice.service;

import com.emm.mseventoservice.dtos.CreateSessionDTO;
import com.emm.mseventoservice.dtos.SessionDTO;

import java.util.List;

public interface SessionService {
    SessionDTO createSession(CreateSessionDTO dto);
    List<SessionDTO> getSessionsByEvent(Long eventId);
    SessionDTO getSessionById(Long id);
    void deleteSession(Long id);
}
