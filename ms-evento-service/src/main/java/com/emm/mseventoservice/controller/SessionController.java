package com.emm.mseventoservice.controller;

import com.emm.mseventoservice.dtos.CreateSessionDTO;
import com.emm.mseventoservice.dtos.SessionDTO;
import com.emm.mseventoservice.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionDTO> createSession(@RequestBody CreateSessionDTO dto) {
        return ResponseEntity.ok(sessionService.createSession(dto));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<SessionDTO>> getSessionsByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(sessionService.getSessionsByEvent(eventId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SessionDTO> getSessionById(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getSessionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}