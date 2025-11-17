package com.emm.msparticipants.mappers;

import com.emm.msparticipants.dtos.CreateParticipantDTO;
import com.emm.msparticipants.dtos.ParticipantDTO;
import com.emm.msparticipants.models.Participant;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-16T20:31:13-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class ParticipantMapperImpl implements ParticipantMapper {

    @Override
    public void updateParticipantFromDto(CreateParticipantDTO dto, Participant participant) {
        if ( dto == null ) {
            return;
        }

        participant.setFirstName( dto.getFirstName() );
        participant.setLastName( dto.getLastName() );
        participant.setEmail( dto.getEmail() );
        participant.setPhone( dto.getPhone() );
    }

    @Override
    public ParticipantDTO toDto(Participant participant) {
        if ( participant == null ) {
            return null;
        }

        ParticipantDTO.ParticipantDTOBuilder participantDTO = ParticipantDTO.builder();

        participantDTO.participantId( participant.getParticipantId() );
        participantDTO.firstName( participant.getFirstName() );
        participantDTO.lastName( participant.getLastName() );
        participantDTO.email( participant.getEmail() );
        participantDTO.phone( participant.getPhone() );
        participantDTO.registrationDate( participant.getRegistrationDate() );

        return participantDTO.build();
    }

    @Override
    public Participant toEntityFromCreateDto(CreateParticipantDTO createDto) {
        if ( createDto == null ) {
            return null;
        }

        Participant.ParticipantBuilder participant = Participant.builder();

        participant.firstName( createDto.getFirstName() );
        participant.lastName( createDto.getLastName() );
        participant.email( createDto.getEmail() );
        participant.phone( createDto.getPhone() );

        participant.registrationDate( LocalDateTime.now() );

        return participant.build();
    }
}
