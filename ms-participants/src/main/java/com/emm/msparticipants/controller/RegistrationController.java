package com.emm.msparticipants.controller;


import com.emm.msparticipants.dtos.CreateRegistrationDTO;
import com.emm.msparticipants.dtos.RegistrationDTO;
import com.emm.msparticipants.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationDTO> createRegistration(@RequestBody CreateRegistrationDTO dto) {
        RegistrationDTO created = registrationService.createRegistration(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDTO>> getAllRegistrations() {
        List<RegistrationDTO> registrations = registrationService.getAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<RegistrationDTO>> getRegistrationsByEvent(@PathVariable Long eventId) {
        List<RegistrationDTO> registrations = registrationService.getRegistrationsByEvent(eventId);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<RegistrationDTO>> getRegistrationsByParticipant(@PathVariable Long participantId) {
        List<RegistrationDTO> registrations = registrationService.getRegistrationsByParticipant(participantId);
        return ResponseEntity.ok(registrations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
