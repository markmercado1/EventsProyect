package com.emm.msparticipants.services.impl;

import com.emm.msparticipants.dtos.CreateParticipantDTO;
import com.emm.msparticipants.dtos.CreateRegistrationDTO;
import com.emm.msparticipants.dtos.ParticipantDTO;
import com.emm.msparticipants.dtos.RegistrationDTO;
import com.emm.msparticipants.mappers.RegistrationMapper;
import com.emm.msparticipants.models.Participant;
import com.emm.msparticipants.models.Registration;
import com.emm.msparticipants.repositorys.ParticipantRepository;
import com.emm.msparticipants.repositorys.RegistrationRepository;
import com.emm.msparticipants.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final ParticipantRepository participantRepository;
    private final RegistrationMapper registrationMapper;
    @Override
    public RegistrationDTO createRegistration(CreateRegistrationDTO dto) {
        Participant participant = participantRepository.findById(dto.getParticipantId())
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        Registration registration = registrationMapper.toEntityFromCreateDto(dto);
        registration.setParticipant(participant);

        Registration saved = registrationRepository.save(registration);
        return registrationMapper.toDto(saved);
    }
    @Override
    public List<RegistrationDTO> getAllRegistrations() {
        return registrationRepository.findAll()
                .stream()
                .map(registrationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrationDTO> getRegistrationsByEvent(Long eventId) {
        return registrationRepository.findByEventId(eventId)
                .stream()
                .map(registrationMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<RegistrationDTO> getRegistrationsByParticipant(Long participantId) {
        return registrationRepository.findByParticipant_ParticipantId(participantId)
                .stream()
                .map(registrationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

}
