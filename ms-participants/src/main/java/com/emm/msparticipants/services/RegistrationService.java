package com.emm.msparticipants.services;

import com.emm.msparticipants.dtos.CreateParticipantDTO;
import com.emm.msparticipants.dtos.CreateRegistrationDTO;
import com.emm.msparticipants.dtos.ParticipantDTO;
import com.emm.msparticipants.dtos.RegistrationDTO;

import java.util.List;

public interface RegistrationService {
    RegistrationDTO createRegistration(CreateRegistrationDTO dto);
    List<RegistrationDTO> getAllRegistrations();
    List<RegistrationDTO> getRegistrationsByEvent(Long eventId);
    List<RegistrationDTO> getRegistrationsByParticipant(Long participantId);
    void deleteRegistration(Long id);


}
