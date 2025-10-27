package com.emm.msparticipants.mappers;

import com.emm.msparticipants.dtos.CreateRegistrationDTO;
import com.emm.msparticipants.dtos.RegistrationDTO;
import com.emm.msparticipants.enums.RegistrationStatus;
import com.emm.msparticipants.models.Participant;
import com.emm.msparticipants.models.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {RegistrationStatus.class, LocalDateTime.class})
public interface RegistrationMapper {

    @Mapping(target = "participantId", source = "participant.participantId")
    @Mapping(target = "status", source = "status")
    RegistrationDTO toDto(Registration registration);

    @Mapping(target = "registrationId", ignore = true)
    @Mapping(target = "participant", source = "participantId", qualifiedByName = "idToParticipant")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "registrationDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "qrCode", ignore = true)
    @Mapping(target = "paymentOrderId", ignore = true)
    Registration toEntityFromCreateDto(CreateRegistrationDTO createDto);

    @Named("idToParticipant")
    default Participant idToParticipant(Long participantId) {
        if (participantId == null) {
            return null;
        }
        Participant participant = new Participant();
        participant.setParticipantId(participantId);
        return participant;
    }
}